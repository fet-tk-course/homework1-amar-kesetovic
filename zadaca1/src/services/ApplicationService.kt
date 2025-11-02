package services

import models.Application
import models.Category

// Funkcija koja filtrira prosljedjenu listu aplikacija prema polju appAverageRating, tako da vraca samo one ciji
// je averageRating veci od drugo argumenta pri pozivu funkcije, implementirano koristenjem ugradjenog filtera
fun filterOnHigherThanAverageScore(apps : List<Application>, filterValue : Double) : List<Application> {
    return apps.filter {it.appAverageRating > filterValue}
}

// Filter funkcija implementirana bez koristenja ugradjene funkcije
fun myFilterOnHigherThanAverageScore(apps : List<Application> , filterValue : Double) : List<Application> {
    val destination = mutableListOf<Application>()
    for (app in apps){
        if (app.appAverageRating > filterValue){ destination.add(app) }
    }
    return destination
}

// Funkcija koja na osnovu prosljedjene liste aplikacija vraca mapu
// u kojoj svaki par predstavlja kategoriju aplikacije i broj takvih aplikacija u prosljedjenog listi
fun myGroupByCategory(apps : List<Application>) : Map<Category, Int> {
    val destination = mutableMapOf<Category, Int>()
    for(app in apps) {
        if(destination[app.appCategory] == null){
            destination[app.appCategory] = 1
        } else {
            destination[app.appCategory] = destination[app.appCategory]!! + 1
        }
    }
    return destination
}

// Funkcija koja listu aplikacija prestavlja u mapu kategorija -> broj aplikacija u kategoriji
// koristeci ugradjenu groupingBy funkciju
fun groupByCategory(apps  : List<Application>) : Map<Category, Int> {
    return apps.groupingBy { it.appCategory}.fold(0) {acc, _ -> acc.inc()}
}

// Funkcija koja sortira prosljedjenu listu aplikacija i vraca novi objekat liste koja je sortirana
// u opadajucem redoslijedu na osnovu polja appDownloadd, implementirano bubble sort algoritmom
fun mySortedDescending(apps : List<Application>) : List<Application> {
    val destination = apps.toMutableList()
    for (i in 0..destination.size-2){
        for(j in  (i+1)..destination.size-1){
            if(destination[i].appDownloads < destination[j].appDownloads){
                val temp = destination[i]
                destination[i] = destination[j]
                destination[j] = temp
            }
        }
    }
    return destination
}

// Funkcija za sortiranja u opadajucem redoslijedu na osnovu broja preuzimanja aplikacija u
// listi prosljedjenoj kao prvi argument koristeci ugradjenu funkciju za sortiranje
fun sortedDescending(apps : List<Application>) : List<Application> {
    return apps.sortedByDescending{it.appDownloads}
}

// Funkcija koja za prosljedjenu listu aplikacija vraca mapu parova
// kategorija -> prosjecna velicina aplikacije u toj kategoriji
// Implementirano koristeci fold sa akumulatorom tipa mapa kategorija -> par(ukupnoSize, brojApplikacija u kategoriji)
// u kombinaciji sa map i forEach funkcijom koja rezultat folda mapira u finalnu mapu kategorija -> prosjek velicine
fun getAverageSizePerCategory(apps : List<Application>) : Map<Category, Double> {
    val folded = apps.fold (mutableMapOf<Category, Pair<Double, Int>>()) {
            acc, app ->
        val (sum, count) = acc[app.appCategory] ?: (0.0 to 0)
        acc[app.appCategory] = (sum + app.appSize) to (count + 1)
        acc
    }
    val mapped = folded.map { (category : Category, sumAndCount : Pair<Double,Int>) ->
        category to (sumAndCount.component1() / sumAndCount.component2())
    }
    val returnMap = mutableMapOf<Category, Double>()
    mapped.forEach { elem -> returnMap[elem.first] = elem.second }
    return returnMap
}

// Funkcija za pronalazak aplikacije u prosljedjenog listi palikacija, na osnovu nekog filter stringa
fun findAppByName(apps : List<Application>, filterString : String) : Application? {
    val app : Application? = apps.firstOrNull {x -> x.appName == filterString}
    return app
}