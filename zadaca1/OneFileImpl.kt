import kotlin.collections.contains

enum class Category {
    Social,
    Games,
    Education,
    Productivity,
    Music,
    Entertainment,
    Shopping,
    Other
}

data class Application (
    var appName : String,
    var appCategory : Category,
    var appDownloads : Long,
    var appAverageRating : Double,
    var appSize : Double
){
    init {
        if(appName.isEmpty()) println("App name can't be empty")
        if(appDownloads <= 0) println("App can't have downloads <= 0")
        if((appAverageRating < 0.0) || (appAverageRating > 5.0)) println("App can't have average rating < 0 or > 5")
        if(appSize <= 0) println("App size can't be less than 0 MB")
    }
}

data class Developer(
    var devName : String,
    var devLastName : String,
    var devCountry : String,
    var appList : MutableList<Application>
){
    init {
        if (devName.isEmpty() || devLastName.isEmpty()) println("Developer must have first name")
        if(devCountry.isEmpty()) println("Developer must have a country")

    }
}

fun prettyPrintApp(app : Application){
    println("App : ${app.appName} | Category: ${app.appCategory} | Rating: ${app.appAverageRating} | Downloads: ${parseDownloads(app.appDownloads)} | Size: ${app.appSize}MB")
}



fun prettyPrintDev(dev : Developer) {
    println("First name: ${dev.devName} | Last name: ${dev.devLastName} | Country: ${dev.devCountry}")
    println("-------------------------APPS-------------------------")
    dev.appList.forEach(::prettyPrintApp)
    println("------------------------------------------------------")
}

fun prettyPrintCategoryToDouble(map : Map<Category,Double>){
    map.forEach { (key,value) -> println("$key - $value") }
}

fun prettyPrintCategoryToInt(map : Map<Category,Int>){
    map.forEach { (key,value) -> println("$key - $value") }
}

fun parseDownloads(num : Long) : String {
    return when {
        num >= 1_000_000_000 -> "${num / 1_000_000_000}Mlrd+"
        num >= 1_000_000 -> "${num / 1_000_000}M+"
        num >= 1_000 -> "${num/1_000}K+"
        else -> num.toString()
    }
}

// Funkcija koja kreira testne hardcoded podatke za aplikaciju, vraca listu 10 objekata tipa Application
fun createTestData() : List<Application> {
    return listOf(
        Application("ChatGPT", Category.Productivity, 500_000_000, 4.6, 24.0),
        Application("Temu", Category.Shopping, 550_000_000, 4.5, 46.12),
        Application("WhatsApp Messenger", Category.Social, 10_000_000_000, 4.3, 48.02),
        Application("Astra AI: Math Tutor", Category.Education, 50_000, 4.7, 0.783),
        Application("Moj dm", Category.Shopping, 10_000_000, 4.8, 40.0),
        Application("Duolingo", Category.Education, 510_000_000, 4.6, 58.0),
        Application("Netflix", Category.Entertainment, 1_000_000_000, 3.8, 48.3),
        Application("Deezer : Music & Podcast Player", Category.Music, 100_000_000, 4.6, 24.0),
        Application("Youtube Kids", Category.Entertainment, 540_000_000, 4.3, 29.0),
        Application("Roblox", Category.Games, 1_000_100_000, 4.4, 125.0)
    )
}

// Funkcija koja kreira testnu listu objekata tipa Developer, vraca listu od 3 developera
fun createTestDevs() : List<Developer> {
    return listOf(
        Developer("Amar", "Kesetovic", "BiH", mutableListOf()),
        Developer("Mahir", "Suljic", "BiH", mutableListOf()),
        Developer("Dzenita", "Moranjkic", "BiH", mutableListOf())
    )
}

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


// Funkcija koja pronalazi developera koji ima najvise akumulirano downloads na svojim aplikacijama
fun findDevsWithMostDownloads(devs : List<Developer>) : List<Developer> {
    var destination : Developer = devs.first()
    var destinationSum : Long = 0
    for(dev in devs){
        var sum : Long = 0
        for(app in dev.appList){
            sum += app.appDownloads
        }
        if(sum > destinationSum) {
            destinationSum = sum
            destination = dev
        }
    }
    val returnList = mutableListOf(destination)
    for(dev in devs.filter {x -> x != destination}) {
        var sum : Long = 0
        for(app in dev.appList){
            sum += app.appDownloads
        }
        if(sum == destinationSum) {
            returnList.add(dev)
        }
    }
    return returnList
}

// Funkcija koja vraca prosjek ocjena aplikacija za prosljedjenog developera
fun getDevAverageRating(dev : Developer) : Double {
    var count = 0
    var totalRating = 0.0
    for(app in dev.appList){
        count += 1
        totalRating += app.appAverageRating
    }
    return totalRating / count
}

// Funkcija koja povezuje aplikaciju i developera
fun connectAppToDev(app : Application, dev : Developer){
    dev.appList.add(app)
}

fun main() {
    val apps : List<Application> = createTestData()
    val devs : List<Developer> = createTestDevs()
    apps.take(3).forEach {x -> connectAppToDev(x, devs[0])}
    apps.drop(3).take(3).forEach{x -> connectAppToDev(x,devs[1])}
    apps.drop(6).take(4).forEach{x -> connectAppToDev(x, devs[2])}

    // FILTERING
    val filterReturn = filterOnHigherThanAverageScore(apps,4.5)
    if(myFilterOnHigherThanAverageScore(apps, 4.5) == filterReturn){
        for(item in filterReturn) {
            if (item.appAverageRating < 4.5) return
        }
        println("Filter is working!")
    }
    println("FILTER RESULT")
    filterReturn.forEach(::prettyPrintApp)

    // PRETRAGA PO IMENU
    val appFindReturn = findAppByName(apps, "ChatGPT")
    if(appFindReturn == apps.first()) println("Filter by name is working!")
    println("FIND BY NAME RESULT")
    if(appFindReturn != null){
        prettyPrintApp(appFindReturn)
    }

    // GRUPISANJE
    val groupingByCategoryReturn = groupByCategory(apps)
    val expectedOutput = mapOf(
        Category.Productivity to 1, Category.Shopping to 2, Category.Social to 1, Category.Education to 2,
        Category.Entertainment to 2, Category.Music to 1, Category.Games to 1)
    if(myGroupByCategory(apps) == groupingByCategoryReturn){
        if(groupingByCategoryReturn == expectedOutput) println("Grouping works!")
        else return
    }
    println("GROUPING BY CATEGORY RESULT")
    prettyPrintCategoryToInt(groupingByCategoryReturn)

    // SORTIRANJE
    val sortedApps = sortedDescending(apps)
    if(mySortedDescending(apps) == sortedApps){
        println("Sorting works!")
    }
    println("SORT DESCENDING RESULT")
    sortedApps.forEach(::prettyPrintApp)

    val averageSizePerCategory = getAverageSizePerCategory(apps)
    println("AVERAGE SIZE PER CATEGORY RESULT")
    prettyPrintCategoryToDouble(averageSizePerCategory)

    // DEV FIND
    val devsFindReturn = findDevsWithMostDownloads(devs)
    if(devsFindReturn.contains(devs[0])) {
        println("Devs find works!")
    }
    println("FIND DEV WITH MOST DOWNLOADS RESULT")
    devsFindReturn.forEach(::prettyPrintDev)

    // DEV AVERAGE
    val devAverage = getDevAverageRating(devs[0])
    println("GET DEV AVERAGE RESULT")
    println(devAverage)
}

