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
      require(!appName.isEmpty()) {"App must have a name!"}
        require(appDownloads >= 0) {"App can't have downloads < 0"}
        require((appAverageRating >= 0.0) && (appAverageRating <= 5.0)) {"App can't have average rating < 0 or > 5"}
        require(appSize > 0) {"App size can't be less than 0 MB"}
    }
}
