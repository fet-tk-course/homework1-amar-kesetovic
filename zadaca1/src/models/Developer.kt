package models

data class Developer(
    var devName : String,
    var devLastName : String,
    var devCountry : String,
    var appList : MutableList<Application>
){
    init {
        require(!devName.isEmpty() && !devLastName.isEmpty()) {"Developer must have a name"}
        require(!devCountry.isEmpty()) {"Developer must have a country"}

    }
}
