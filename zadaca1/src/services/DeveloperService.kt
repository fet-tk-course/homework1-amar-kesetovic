package services

import models.Application
import models.Developer

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
