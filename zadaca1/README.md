## Struktura projekta 
Projekat se sastoji od helpers, models i services package-a te main kotlin file-a koji predstavlja entry point programa i povezuje sve ostale komponente. Rješenje problema podijeljeno je na smislene logičke cjeline i raspoređeno unutar prethodno navedenih package-a.

> Također u root folderu projekta nalazi se i file pod imenom OneFileImpl.kt koji je namijenjen za jednostavno pokretanje rješenja zadaće unutar kotlin playgrounda. Ovaj fajl obuhvata cjeokupan kod projekta zapisan unutar samo jednog tekstualnog file-a.

## Helpers package 
Unutar helpers package-a nalaze se dva kotlin file-a, Printing.kt u kojem su definirane funkcije za ispis određenih objekata na standardnom izlazu kao i SeedData.kt koji implementira funkcije za kreiranje testnih objekata tjst. podataka.

    prettyPrintApp(app : Application) -> formatiran ispis objekta tipa Application
    prettyPrintDev(dev : Developer) -> formatiran ispis objekta tipa Developer
    prettyPrintCategoryToDouble(map : Map<Category,Double>) -> formatiran ispis mape koja je rezultat grupisanja aplikacija po kategoriji i prosječnoj veličini aplikacije
    prettyPrintCategoryToInt(map : Map<Category,Int>) -> formatiran ispit mape koja je rezultat grupisanja aplikacija po kategoriji i ukupan broj aplikacija po svakoj od njih
    parseDownloads(num : Long) : String -> vraća parsiran broj ukupnih preuzimanja tako da prikazuje samo cifre sa najvećom težinskom vrijednošću (npr 1000000 -> 1M+)
    createTestData() : List<Application> -> kreira i vraća listu od 10 objekata tipa Application 
    craeteTestDevs() : List<Developer> -> kreira i vraća listu od 3 objekta tipa Developer

## Models package
Sadrži 3 file-a koji definiraju korištene klase u implementaciji rješenja. Pored osnovnih Application i Developer klasa tu je i pomoćna  enum klasa Category koja postavlja predefinirane kategorije aplikacija kao i u stvarnim scenarijima.
## Services package
Sadrži dva file-a, ApplicationService koji definira sve funkcije za rad sa Application tipom te DeveloperService koja sadrži implementacije funkcija za rad sa Developer objektima kao i funkciju za povezivanje konkretne aplikacije sa određenim dev-om.

	filterOnHigherThanAverageScore(apps : List<Application>, filterValue : Double) : List<Application> -> filtrira prosljeđenu listu aplikacija tako da vraća listu aplikacija čije polje appAverageRating ima veću vrijednost od vrijednosti drugog argumenta funkcije. 
Ova funkcionalnost često se susreće u stvarnim aplikacijama kao što su npr. web-shopovi gdje korisniku treba omogućiti da filtrira rezultate po nekom kriterijumu.
Korištena je ugrađena funkcija filter kako bi se filter predicate aplicirao na svaki element kolekcije, i vrate samo oni elementi za koji se evaluira kao true.


*myFilterOnHigherThanAverageScore predstavlja implementaciju ove funkcionalnosti bez korištenja ugrađene filter funkcije*

	groupByCategory(apps  : List<Application>) : Map<Category, Int> -> vrši grupisanje prosljeđene liste aplikacija na osnovu polja appCategory, pri čemu za svaku grupu broji broj aplikacija 

Ovako funkcionalnost koristi se često kada imamo listu objekata među kojima možemo izvršiti grupisanje na osnovu polja koja mogu imati istu vrijednost. Primjer iz prakse jeste recimo lista narudžbi koje se spremaju u bazi podataka. Često na front endu želimo prikazati grafik ukupnog broja prodaja po danima, to bismo uradili grupisanjem svih objekata Order po polju datum, a vrijednosti bi bila agregacija broja svih ordera u grupi. Korištena je groupingBy ugrađena funkcija kako bi se izvršilo grupisanje na osnovu kategorije, u kombinaciji sa foldom tako da se za sve elemente jedne grupe dobiva jedinstvena vrijednost koja predstavlja broj aplikacija u grupi.

*myGroupByCategory je implementacija ove funkcionalnost bez korištenja ugrađene groupingBy funkcije*

	sortedDescending(apps : List<Application>) : List<Application> -> vrši sortiranje prosljeđene liste aplikacija na osnovu broja preuzimanja

Sortiranje kolekcija je jako česta pojavi pri razvoju bilo kakve aplikacije, a primjer bi bio recimo web shop i mogućnost sortiranja ponude na osnovu cijene. Koristi se ugrađena funkcija sortedByDescending da se definira sortiranje na osnovu broja preuzimanja.

*mySortedDescending je implementacija ove funkcionalnosti primjenom bubble sort algoritma*

	getAverageSizePerCategory(apps : List<Application>) : Map<Category, Double> -> vrši groupisanje prosljeđene liste aplikacija prema kategoriji, dok su vrijednosti rezultante mape prosječna ocjena svih aplikacija iz te grupe 

Kao i prethodno grupisanje često korišteno za agregacije polja određenih objekata koje možemo svrstati u iste grupe na osnovu nekog atributa. Koristi se fold kako bi se na osnovu kolekcije aplikacija dobila mapa sa ključem po kategoriji čime je efektivno urađeno grupisanje kolekcije, dok je vrijednost tipa Pair i sadrži sumu veličina svih aplikacija date kategorije i ukupan broj.
Rezultat folda kombiniramo sa map funkcijom čime Pair value rezultata folda mapiramo u average size.
> AI je korišten za dobivanje ideje za rješenje ovog problema, bez
> kopiranja koda

	findAppByName(apps : List<Application>, filterString : String) : Application?  -> pronalazi aplikaciju unutar prosljeđene liste Application objekata, ako nađe vraća aplikaciju a u suprotnom null 
Često u aplikacijama imamo potrebu pronaći određeni element unutar kolekcije, da li kao odgovor na user input i samu validaciju, ili iz drugih razloga, te na osnovu rezultata pretrage izvršiti određenu akciju.

	findDevsWithMostDownloads(devs : List<Developer>) : List<Developer> -> vrši pronalazak developera sa najvećim ukupnim brojem preuzimanja aplikacija s kojima je povezan, vraća listu jer je moguće da dva developera imaju isti broj

Također česta funkcionalnost, recimo YouTube često prikazuje najgledaniji kanal po ukupnom broju pregleda na svim klipovima kanala, što je ekvivalent gornjem primjeru.

	getDevAverageRating(dev : Developer) : Double -> računa prosječnu ocjenu među aplikacijama tog developera
	connectAppToDev(app : Application, dev : Developer) -> pravi vezu između objekata tipa Developer i Aplikacija, developer je vlasnik aplikacije
