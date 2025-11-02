package helpers

import models.Application
import models.Category
import models.Developer

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
