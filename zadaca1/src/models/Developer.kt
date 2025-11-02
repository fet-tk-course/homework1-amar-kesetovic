package models

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
