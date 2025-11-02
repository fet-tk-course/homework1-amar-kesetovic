package models

// Klasa koja abstrahuje aplikaciju, sva polja su var jer mogu biti mijenjana u opstem slucaju
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