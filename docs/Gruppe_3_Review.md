## GitHub

Før I kigger på koden, så kig på, hvad der ellers ligger i repositoriet.

* [x] Er der en `.gitignore`-fil?
* [x] 💣 Er der uvedkommende filer i GitHub (fx `target`-mappen, `.class`-filer, `movies.csv`)?
* [x] Er der en `README.md` med fornavn og GitHub-brugernavn på hvert medlem og en forklaring på,
  hvordan man kører programmet?
* [x] Ligger dokumentation og andet ikke-kode i en `docs`-mappe – både klassediagram og
  `furps.md` (mindst ét krav pr. bogstav)?
* [ ] Passer klassediagrammet med koden?
* [x] Viser historikken commits fra **alle** i gruppen?
* [x] Er commit-beskederne til at forstå – kan man se, hvad hver commit gjorde?

## Klasser

Der bør være mindst disse klasser: `Main`, `UserInterface`, `Controller`, `MovieCollection`,
`Movie` og `FileHandler` – og en række comparator-klasser. Er nogle navngivet anderledes, så
diskutér, om navnet er bedre, værre eller lige så godt.

Gentag dette afsnit for `Movie`, `MovieCollection`, `Controller`, `UserInterface` og `FileHandler`.
Comparator-klasserne har deres eget afsnit længere nede.

### Klassen: `Movie`

* [x] Er klassen godt navngivet?
* [x] Er det let at regne ud, hvilken rolle klassen har?
* [x] Er der brugt tydelig ental/flertal for at vise, om klassen indeholder ét eller flere
  underobjekter?
* [x] 💣 Er der `System.out` eller `Scanner` i klassen, selv om den ikke er `UserInterface`?

### Klassen: `UserInterface`

* [x] Er klassen godt navngivet?
* [x] Er det let at regne ud, hvilken rolle klassen har?
* [x] Er der brugt tydelig ental/flertal for at vise, om klassen indeholder ét eller flere
  underobjekter?
* [x] 💣 Er der `System.out` eller `Scanner` i klassen, selv om den ikke er `UserInterface`?

## Tests

* [ ] Er der en testklasse for hver klasse med logik (`Movie`, `MovieCollection`, `FileHandler`,
  comparatorerne)?
* [x] Er testmetodernes navne sigende – kan man se, hvad der testes, uden at læse koden?
* [x] Følger testene Arrange – Act – Assert?
* [x] Er de "kedelige" tilfælde testet: tom samling, ingen match, en film, der ikke findes?
* [x] Er grænserne testet – fx årstal 1887/1888, en dato præcis på grænsen?
* [x] Er det testet, at ugyldige værdier afvises (`assertThrows`)?
* [x] 💣 Afhænger testene af hinanden eller af den rækkefølge, de køres i?
* [x] 💣 Afhænger testene af dagens dato, så de kan blive røde i morgen?
* [x] 💣 Kan testene komme til at overskrive eller slette den rigtige `movies.csv`?
* [x] 💣 Er der tests, der ikke tester noget (ingen `assert...`)?

## Bemærkninger
### Gode
* Flot menu
* Gode metodenavne
* Enum til genrer er en god ide, men vil stadig vide hvorfor :)
* Gode kommentarer

### Konstruktivt
* ~~I editMovie(), kan man tilføje forkerte værdier, såsom årstal: 0 eller lændge: 0. Hvorfor det?~~
* ~~Hvorfor bruger i ikke domainmodel.Controller metoder?~~
* ~~Hvad gør notSeenRecently() i UI?~~
* ~~Hvorfor har i ikke lavet en metode for sig til at validere selection? DNRY.~~
* ~~Hvorfor har i valgt at lave Enum til genre?~~
* ~~domainmodel.Controller har importeret packages som ikke bliver brugt. Hvorfor det?~~
