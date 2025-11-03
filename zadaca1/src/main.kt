import helpers.*
import models.*
import services.*


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

    val provjera = topNPerCategory(apps, 3);
    prettyPrintProvjera(provjera);
}