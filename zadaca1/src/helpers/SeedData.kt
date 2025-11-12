package helpers

import models.Application
import models.Category
import models.Developer

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
