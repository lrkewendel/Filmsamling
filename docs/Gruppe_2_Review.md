Review af gruppe 2's Filmsamling
 
---
 
## GitHub
 
Før I kigger på koden, så kig på, hvad der ellers ligger i repositoriet.
 
* [X] Er der en `.gitignore`-fil?
* [] 💣 Er der uvedkommende filer i GitHub (fx `target`-mappen, `.class`-filer, `movies.csv`)? - movies.txt
* [ ] Er der en `README.md` med fornavn og GitHub-brugernavn på hvert medlem og en forklaring på, - mangler Shabnams GitHub brugernavn
      hvordan man kører programmet?
* [ ] Ligger dokumentation og andet ikke-kode i en `docs`-mappe – både klassediagram og
      `furps.md` (mindst ét krav pr. bogstav)? - mangler FURPS og klassediagram
* [ ] Passer klassediagrammet med koden? - kan ikke finde et klassediagram
* [X] Viser historikken commits fra **alle** i gruppen? - 
* [X] Er commit-beskederne til at forstå – kan man se, hvad hver commit gjorde?
- overordnet ja, men mange af dem kan være mere detaljeret.
---
 
## Kør programmet og testene
 
* [X] Kan projektet åbnes og kompileres uden fejl?
* [ ] Starter programmet, også når der ingen `movies.csv` er? - nej, der er en movies.txt og hvis den er væk stopper den med FileNotFoundException
* [X] Er **alle** tests grønne? *Antal tests: 6 
* [ ] 💣 Går programmet ned, hvis man skriver bogstaver, hvor der skal stå et tal? - ja i sorteringsmenuen - vælg 9 og skrev abc.
"numberformatexception".
 
---
 
## Packages
 
Der bør være tre packages under `src/main/java`: `ui`, `domainmodel` og `datasource` (eller
lignende navne).
 
* [X] Er der tre packages med sigende navne?
* [X] Er det let at regne ud, hvilken rolle hver package har?
* [] Ligger hver klasse i den package, der passer til dens ansvar? - alle undtaget test - skulle have package tilsvarende den klasse de tester. 
* [NEJ] 💣 Importerer en klasse i `domainmodel` eller `datasource` noget fra `ui`?
* [NEJ] Ligger testene i packages med samme navne under `src/test/java`?
 
---
 
## Klasser
 
Der bør være mindst disse klasser: `Main`, `UserInterface`, `Controller`, `MovieCollection`,
`Movie` og `FileHandler` – og en række comparator-klasser. Er nogle navngivet anderledes, så
diskutér, om navnet er bedre, værre eller lige så godt.
 
Gentag dette afsnit for `Movie`, `MovieCollection`, `Controller`, `UserInterface` og `FileHandler`.
Comparator-klasserne har deres eget afsnit længere nede.
 
### Klassen: `-navn-`
 
* [X] Er klassen godt navngivet?
* [X] Er det let at regne ud, hvilken rolle klassen har?
* [X] Er der brugt tydelig ental/flertal for at vise, om klassen indeholder ét eller flere
      underobjekter?
* [JA - filehandler] 💣 Er der `System.out` eller `Scanner` i klassen, selv om den ikke er `UserInterface`?
 
#### Attributter / fields
 
* [Nej] 💣 Er der oprettet attributter, der burde være lokale variable i stedet?
* [NEJ] 💣 Er der erklæret ubenyttede attributter?
 
**Er attributter navngivet godt?**
 
* [X] Er navnene selvforklarende?
* [X] Er navnene fra "problemdomænet"?
* [X] Er navnene tydeligt og korrekt ental eller flertal?
* [X] Er navnene alle på samme sprog (engelsk)?
 
**Har attributter korrekt access?**
 
* [X] Er der brugt `private`?
* [X] Er der getter og setter (hvis nødvendigt)?
* [NEJ] 💣 Er attributter gjort `public` uden getter og setter?
* [NEJ] 💣 Er der hverken brugt `private` eller `public`, men bare "ingenting"?
 
**Getters / setters**
 
* [] 💣 Er der ubenyttede get- og set-metoder? - movie og moviecollection har.
* [NEJ] 💣 Er der metoder kaldet `get` eller `set`, som ikke er en getter/setter?
* [NEJ] 💣 Eller omvendt: er der gettere eller settere, der hedder noget andet end `get`, `set` eller
      (for `boolean`) `is`?
 
#### Metoder
 
Kig på hver metode, først "udefra", altså uden at læse koden inde i metoderne. Det hjælper måske at
"folde" alle metoderne sammen (<kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>-</kbd>, på Mac
<kbd>Cmd</kbd>+<kbd>Shift</kbd>+<kbd>-</kbd>).
 
**Er metoder navngivet godt?**
 
* [X] Er navnene selvforklarende?
* [X] Er navnene fra "problemdomænet"?
* [X] Er det tydeligt, hvad hver metode gør, ud fra sit navn?
* [X] Er det tydeligt nok ud fra navnet, om metoder returnerer eller modtager værdier?
* [X] Hvis metoden modtager parametre, er det så umiddelbart til at regne ud, hvad de er til, ud fra
      navngivningen?
* [X] Hvis metoden returnerer en værdi, giver datatypen så umiddelbar mening?
* [X] Er der en fornuftig ensartethed/struktur i navngivningen af metoderne?
* [X] Er metodenavnene navngivet med korrekt brug af `camelCase`?
* [X] Er parameternavne navngivet med korrekt brug af `camelCase`?
* [X] Er navnene alle på samme sprog (engelsk)?
* [X] 💣 Er der metoder, der gør to ting – fx både finder noget og udskriver det? - tjek Filehandler
 
#### Kode
 
Kig derefter **ind** i hver metode og vurdér koden. I skal ikke besvare en sektion for hver eneste
metode, men kigge på metoderne som helhed og notere, hvis der er et sted, der afviger.
 
**Lokale variabler inde i metoder**
 
* [X] Er variabler navngivet korrekt `camelCase`? - dog flere steder der kun står et ord som delete - edit - man kan tilføje mere konkret.
* [X] Er variablernes navne i tilstrækkelig grad fra "problemdomænet"?
* [X] Er variablerne erklæret, hvor de skal bruges?
* [X] Har variablerne den korrekte datatype?
* [X] Er navnene alle på samme sprog (engelsk)?
 
**Generel kodestil**
 
* [X] Er koden pænt (og korrekt) formateret med indrykninger etc.?
* [ ] Er koden "skimbar" og umiddelbart let at overskue, uden at skulle nærlæse hver linje?
* [NEJ] 💣 Er der smarte "ninja-tricks", der gør koden kompakt, men måske sværere for nogle at læse?
* [NEJ?] 💣 Er der kode, der er kopieret flere steder, og som burde være én metode?
 
**Exceptions**
 
* [X] Bliver exceptions fanget dér, hvor man ved, hvad brugeren skal have at vide (typisk
      `UserInterface`)?
* [X] Bliver ugyldige værdier afvist med en `throw` og en forklarende besked?
* [NEJ] 💣 Bliver exceptions blot håndteret med en tom `catch` eller en `catch` med `printStackTrace`?
* [NEJ] 💣 Bliver `NullPointerException` eller `IndexOutOfBoundsException` fanget, hvor en `if` burde have forhindret dem?
* [NEJ] Kan programmet fortsætte efter en exception? - nedbrud nogle steder
* [NEJ] Er der lavet egne exception-typer – og giver de bedre mening end de indbyggede?
 
#### Kommentarer
 
* [NEJ] Er der kommentarer, der fx viser, hvor en fancy løsning er fundet på nettet?
* [] Er der kommentarer, der beskriver, hvordan/hvorfor en metode er opbygget anderledes end
      forventet?
* [ ] Er der kommentarer til udvikleren selv om ting, der skal rettes/ændres i fremtiden?
* [X] 💣 Er der kommentarer, der ikke længere giver mening, og som burde have været slettet?
* [X] 💣 Er der udkommenteret kode, der burde være slettet?
* [X] Er der gode kommentarer i programmet? - i test (Arrange...)
* [ ] 💣 Er der overflødige kommentarer? Kunne fx være `i++; // lægger en til i`
 
---
 
## FileHandler – filen
 
Ud over spørgsmålene under *Klasser*:
 
* [X] Er al læsning og skrivning af filen samlet i `FileHandler`?
* [NEJ] 💣 Ved andre klasser end `FileHandler` noget om filformatet (fx semikolon eller feltrækkefølge)?
* [X] Bliver filen lukket (`close()`) efter læsning og skrivning?
* [NEJ] Overlever alle felter en tur gennem filen – også æ, ø, å og datoen? - lastwatched gemmes ikke
* [NEJ] Går programmet videre, hvis en linje i filen er ødelagt?
* [NEJ] Skrives filen kun, når der er ændringer? - skrives ved hver normal afslutning.
 
---
 
## Comparator-klasserne
 
Reviewes samlet. Hver comparator bør have et navn med den egenskab, den sammenligner, og ordet
`Comparator` – fx `YearComparator`.
 
* [X] Er klasserne godt navngivet?
* [X] Er der brugt `@Override` på `compare` (og på `compareTo` i `Movie`)?
* [X] Er `compare` implementeret med `compareTo`/`compareToIgnoreCase` eller `Integer.compare` –
      i stedet for egne `if`-sætninger, der returnerer `-1`, `0` og `1`?
* [NEJ] Kan koden i `compare` simplificeres?
* [X] Er der kun **ét** sted i koden, der vælger comparator ud fra brugerens valg? - controller.comparatorFor().
* [NEJ] 💣 Ændrer sorteringen rækkefølgen i selve samlingen?
 
---
 
## Tests
 
* [NEJ] Er der en testklasse for hver klasse med logik (`Movie`, `MovieCollection`, `FileHandler`, - mangler tests til filehandler og comparatorerne
      comparatorerne)?
* [X] Er testmetodernes navne sigende – kan man se, hvad der testes, uden at læse koden?
* [X] Følger testene Arrange – Act – Assert?
* [Delvist] Er de "kedelige" tilfælde testet: tom samling, ingen match, en film, der ikke findes? - tom samling testet men ikke de andre.
* [NEJ] Er grænserne testet – fx årstal 1887/1888, en dato præcis på grænsen?
* [NEJ] Er det testet, at ugyldige værdier afvises (`assertThrows`)?
* [NEJ] 💣 Afhænger testene af hinanden eller af den rækkefølge, de køres i?
* [NEJ] 💣 Afhænger testene af dagens dato, så de kan blive røde i morgen?
* [NEJ] 💣 Kan testene komme til at overskrive eller slette den rigtige `movies.csv`?
* [NEJ] 💣 Er der tests, der ikke tester noget (ingen `assert...`)?
 
---
 
## Yderligere kommentarer til koden fra reviewerne
 
*Her kan I skrive yderligere noter – ting, der ikke lige passer ind i skemaet ovenfor. Ros til
særligt elegant kode, spørgsmål til, hvordan programmørerne fandt på en løsning.*
 
**Det er særlig godt:**
* tydelig opdeling i pakker
* de fleste forkerte input bliver håndteret korrekt.
* comparator virker godt.
 
**De tre vigtigste ting at rette:**
 
1. Ubrugte metoder og Gammel //tekst skal fjernes eller ændres.
2. De manglende tests og klassediagram.
4. Kunne man havde brugt `trim`, på den scanner der læser brugerinput, så man ikke får en fejlmeddelelse (programmet crasher ikke!) hvis man laver mellemrum i terminalen.

**Øvrige kommentarer:**

1. Det ligner at alle boolean (f.eks. Opret film, `inColor` og Opdater film "Vil du ændre ...") beder brugeren om at svare med yes/no, men i opgavebeskrivelsen står der at hele brugerfladen/UI skal være på dansk.
2. Hvorfor har I valgt at oprette jeres `switch` under `menu` som I har? Hvorfor har I valgt at bruge `break` frem for `->` f.eks:
```
{
swicth (input) {
  case "1":
    create();
    break;
  case "2":
    read();
    break;
  }

// VS

switch (indput) {
  case "1" -> create();
  case "2" -> read();
  }
}
```

