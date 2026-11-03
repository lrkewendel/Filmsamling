import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserInterface {

    private Scanner scanner;
    private Controller controller;

    public UserInterface() {
        this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        this.controller = new Controller();
    }

    // TEKSTFORMATER:
    final String BOLD = "\u001B[1m";
    final String GRAY = "\u001B[90m";
    final String ITALIC = "\u001B[3m";
    final String BLACK = "\u001B[30m";
    final String RESET = "\u001B[0m";
    final String BLACK_BG = "\u001B[40m";
    final String UNDERLINE = "\u001B[4m";

    private void welcome() {
        System.out.println("VELKOMMEN TIL \n");
        System.out.print("""
                ██████  ██ ███    ██     ███████ ██ ██      ███    ███ ███████  █████  ███    ███ ██      ██ ███    ██  ██████ \s
                ██   ██ ██ ████   ██     ██      ██ ██      ████  ████ ██      ██   ██ ████  ████ ██      ██ ████   ██ ██      \s
                ██   ██ ██ ██ ██  ██     █████   ██ ██      ██ ████ ██ ███████ ███████ ██ ████ ██ ██      ██ ██ ██  ██ ██   ███ 
                ██   ██ ██ ██  ██ ██     ██      ██ ██      ██  ██  ██      ██ ██   ██ ██  ██  ██ ██      ██ ██  ██ ██ ██    ██ 
                ██████  ██ ██   ████     ██      ██ ███████ ██      ██ ███████ ██   ██ ██      ██ ███████ ██ ██   ████  ██████  
                                                                                                                               \s
                """);

    }

    private void menu() {
        System.out.print("");
        System.out.println(BLACK_BG + " ■".repeat(97) + " " + RESET);

        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + " ".repeat(20));
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + " ".repeat(23));
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + " ".repeat(22));
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + " ".repeat(20));
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + " ".repeat(22));
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + " ".repeat(23));
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + " ".repeat(21));
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + " ".repeat(18));
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + " ".repeat(16));
        System.out.print(BLACK_BG + BLACK + "|" + RESET + "\n");

        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + "   1. Tilføj film   ");
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + "   2. Markér som set   ");
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + "   3. Vis alle film   ");
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + "   4. Sortér film   ");
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + "   5. Søg efter film  ");
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + "   6. Ikke set længe   ");
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + "   7. Redigér film   ");
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + "   8. Slet film   ");
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + "   0. Afslut    ");
        System.out.print(BLACK_BG + BLACK + "|" + RESET + "\n");

        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + " ".repeat(20));
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + " ".repeat(23));
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + " ".repeat(22));
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + " ".repeat(20));
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + " ".repeat(22));
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + " ".repeat(23));
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + " ".repeat(21));
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + " ".repeat(18));
        System.out.print(BLACK_BG + BLACK + "⎟" + RESET + " ".repeat(16));
        System.out.print(BLACK_BG + BLACK + "|" + RESET + "\n");

        System.out.println(BLACK_BG + " ■".repeat(97) + " " + RESET);
    }

    // START PROGRAM:
    public void startProgram() {
        boolean running = true;
        welcome();

        try {
            controller.loadMovies();
        } catch (FileNotFoundException e) {
            System.out.println("Du har endnu ingen film i din samling.\nStart din filmsamling ved at tilføje din første film!\n");
        }

        while (running) {

            menu();
            System.out.print("\nHvad kunne du tænke dig at lave? Vælg et nummer fra menuen: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> createMovie();
                case "2" -> markAsSeen();
                case "3" -> showAllMovies();
                case "4" -> sortMovies();
                case "5" -> searchMovies();
                case "6" -> notSeenRecently();
                case "7" -> editMovie();
                case "8" -> deleteMovie();
                case "0" -> running = false;
                default ->
                        System.out.print("\nHovsa! \"" + ITALIC + choice + RESET + "\" er ikke en mulighed på menuen. Prøv igen!\n\n");
            }
        }

        try {
            controller.saveMovies();
            System.out.println("Alle dine film er blevet gemt. Vi ses næste gang!");
        } catch (FileNotFoundException e) {
            System.out.println("Hovsa! Noget gik galt, og din filmsamling blev ikke gemt :(");
        } catch (java.io.UnsupportedEncodingException e) {
            System.out.println("Hovsa! Din PC understøtter ikke UTF-8 formatering...");
        }

        scanner.close();
    }

    // 1. TILFØJ FILM:
    private void createMovie() {

        System.out.println(BOLD + "\n\n" + " ".repeat(12) + "⭑ TILFØJ EN FILM TIL DIN SAMLING ⭑" + RESET);
        System.out.println(GRAY + "━".repeat(58) + "\n" + RESET);

        // TITELE:
        System.out.print("Titel: ");
        String title = scanner.nextLine();

        // INSTRUKTØR:
        System.out.print("Instruktør: ");
        String director = scanner.nextLine();

        // UDGIVELSESÅR:
        int yearCreated = readInt("Udgivelsesår: ");

        // I FARVER?:
        String colorInput;
        do {
            System.out.print("Er filmen i farver? (ja/nej): ");
            colorInput = scanner.nextLine().trim();

            if (!colorInput.equalsIgnoreCase("ja") && !colorInput.equalsIgnoreCase("nej")) {
                System.out.println("Hovsa! Skriv \"ja\" eller \"nej\" for at vælge, om filmen er i farver.");
            }

        } while (!colorInput.equalsIgnoreCase("ja") && !colorInput.equalsIgnoreCase("nej"));

        boolean inColor = colorInput.equalsIgnoreCase("ja");

        // LÆNGDE I MINUTTER:
        int lengthInMinutes = readInt("Længde i minutter: ");

        // GENRE:
        System.out.print("\nGenreoversigt: " + "\n|");
        for (Genre type : Genre.values()) {
            System.out.print(" " + ITALIC + type.getDanishName() + RESET + " |");
        }
        System.out.println("\n");

        Genre chosenGenre = null;

        while (chosenGenre == null) {
            System.out.print("Skriv en af genrerne fra listen: ");
            String genreInput = scanner.nextLine().trim();

            for (Genre type : Genre.values()) {
                if (type.getDanishName().equalsIgnoreCase(genreInput)) {
                    chosenGenre = type;
                    break;
                } else {
                    chosenGenre = null;
                }
            }
            if (chosenGenre == null) {
                System.out.println("\"" + genreInput + "\" er ikke en gyldig genre.");
            }
        }

        // TILFØJER FILMEN TIL SAMLING:
        String capitalizedTitle = capitalizeFirstLetter(title);
        try {
            controller.addMovie(title, director, yearCreated, inColor, lengthInMinutes, String.valueOf(chosenGenre));
            System.out.print(ITALIC + "\n" + capitalizedTitle + " blev tilføjet til din filmsamling!\n" + RESET);
        } catch (IllegalArgumentException e) {
            System.out.println("Filmen blev ikke oprettet: " + e.getMessage());
        }
        System.out.println(GRAY + "⎯".repeat(35) + RESET + "\n");
    }


    // 2. MARKÉR SOM SET:
    private void markAsSeen() {

        System.out.println(BOLD + "\n\n" + " ".repeat(14) + "⭑ TILFØJ EN FILM DU HAR SET ⭑" + RESET);
        System.out.println(GRAY + "━".repeat(58) + "\n" + RESET);

        System.out.print("Tryk \"Enter\" for at få vist hele din samling, eller søg efter en bestemt film: ");
        String searchText = scanner.nextLine();

        ArrayList<Movie> matches = controller.searchMovies(searchText);

        if (matches.isEmpty()) {
            System.out.println("\nHovsa! Det ligner ikke at \"" + ITALIC + searchText + RESET + "\" findes i din samling :(");
            System.out.println(GRAY + "⎯".repeat(35) + RESET);
            System.out.println("");
            return;
        }


        if (searchText.isEmpty()) {
            System.out.println(BOLD + "\nFilm i din samling:\n" + RESET);
        } else {
            System.out.println(BOLD + "\nFilm der matcher din søgning \"" + ITALIC + searchText + RESET + BOLD + "\":\n" + RESET);
        }
        for (int i = 0; i < matches.size(); i++) {
            Movie movie = matches.get(i);
            System.out.print((i + 1) + ". " + capitalizeFirstLetter(movie.getTitle()) + " (" + ITALIC + movie.getYearCreated() + RESET + ")\n");
        }

        Integer choice;

        do {
            choice = readInt("\nHvilken film har du set? Skriv nummeret fra listen: ");

            if (choice == null || choice < 1 || choice > matches.size()) {
                System.out.println("Hovsa! Det nummer findes ikke på listen. Prøv igen!");
            }

        } while (choice == null || choice < 1 || choice > matches.size());

        Movie selectedMovie = matches.get(choice - 1);

        boolean validDate;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate watchedDate = null;

        do {
            validDate = true;

            System.out.print("\nSkriv datoen, hvor du så filmen (DD-MM-ÅÅÅÅ)"
                    + " eller tryk \"Enter\", hvis du har set filmen i dag: ");

            String dateInput = scanner.nextLine().trim();

            if (dateInput.isEmpty()) {
                watchedDate = LocalDate.now();

            } else {
                String regex = "\\d{2}-\\d{2}-\\d{4}";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(dateInput);

                if (matcher.matches()) {
                    try {
                        watchedDate = LocalDate.parse(dateInput, formatter);

                        if (watchedDate.getYear() < selectedMovie.getYearCreated()) {
                            System.out.println("Hovsa! \"" + capitalizeFirstLetter(selectedMovie.getTitle()) +
                                    "\" var ikke udkommet på det tidspunkt. Prøv en senere dato.");
                            validDate = false;

                        } else if (watchedDate.isAfter(LocalDate.now())) {
                            System.out.println("Hovsa! Du kan ikke have set en film i fremtiden.");
                            validDate = false;
                        }

                    } catch (DateTimeParseException e) {
                        System.out.println("Hovsa! Den dato findes vist ikke. Prøv igen, f.eks. 28-10-2026.");
                        validDate = false;
                    }

                } else {
                    System.out.println("Hovsa! Brug formatet DD-MM-ÅÅÅÅ, f.eks. 28-10-2026.");
                    validDate = false;
                }
            }

        } while (!validDate);

        selectedMovie.markAsWatched(watchedDate);

        System.out.println(ITALIC + "\n" + capitalizeFirstLetter(selectedMovie.getTitle())
                + " er markeret som set d. " + watchedDate.format(formatter) + RESET);
        System.out.println(GRAY + "⎯".repeat(35) + RESET);
        System.out.println("");
    }

    // 3. VIS ALLE FILM:
    private void showAllMovies() {

        System.out.println(BOLD + "\n\n" + " ".repeat(19) + "⭑ DIN FILMSAMLING ⭑" + RESET);
        System.out.println(GRAY + "━".repeat(58) + RESET);

        ArrayList<Movie> movies = controller.getMovies();

        if (movies.isEmpty()) {
            System.out.println(ITALIC + "Du har ingen film i din samling endnu :(" + RESET);
            return;
        }

        for (Movie movie : movies) {
            printMovie(movie);
        }
        System.out.println("");
    }

    // 4. SORTÈR FILM:
    private void sortMovies() {

        System.out.println(BOLD + "\n\n" + " ".repeat(16) + "⭑ SOTÈR DIN FILMSAMLING ⭑" + RESET);
        System.out.println(GRAY + "━".repeat(58) + "\n" + RESET);
        System.out.println("Sorter din filmsamling efter:\n" +
                "| Titel | Instruktør | Udgivelsesår | Farver | Længde | Genre |");

        String sortMovieInput = scanner.nextLine().toLowerCase();
        String sortingChoice = "";

        Pattern pattern = Pattern.compile("(titel|instruktør|årstal|farver|længde|genre)");
        Matcher matcher = pattern.matcher(sortMovieInput);

        if (matcher.find()) {
            sortingChoice = matcher.group(1);
        }

        if (!sortingChoice.isEmpty()) {
            controller.sortMovies(sortingChoice);
            System.out.println("\nFilmsamlingen er sorteret efter: " + ITALIC + capitalizeFirstLetter(sortingChoice) + RESET);
            showAllMovies();
        } else {
            System.out.println("\nKunne ikke sortere din filmsamling.");
        }
    }

    // 5. SØG EFTER FILM:
    private void searchMovies() {
        System.out.println(BOLD + "\n\n" + " ".repeat(12) + "⭑ SØG EFTER FILM I DIN SAMLING ⭑" + RESET);
        System.out.println(GRAY + "━".repeat(58) + "\n" + RESET);

        System.out.print("Søg efter en film: ");
        String searchText = scanner.nextLine();
        ArrayList<Movie> matches = controller.searchMovies(searchText);

        if (matches.isEmpty()) {
            System.out.println("Hovsa! Vi fandt ingen film, der matcher din søgning \"" + ITALIC + searchText + RESET + "\".");
            return;
        }
        System.out.println("Film der matcher din søgning " + searchText);

        for (Movie movie : matches) {
            printMovie(movie);
        }
    }


    // 6. IKKE SET LÆNGE:
    private void notSeenRecently() {

        System.out.println(BOLD + "\n\n" + " ".repeat(14) + "⭑ FILM DU IKKE HAR SET LÆNGE ⭑" + RESET);
        System.out.println(GRAY + "━".repeat(58) + "\n" + RESET);

    }

    // 7. REDIGÈR FILM:
    private void editMovie() {

        System.out.println(BOLD + "\n\n" + " ".repeat(11) + "⭑ REDIGÈR EN FILM FRA DIN SAMLING ⭑" + RESET);
        System.out.println(GRAY + "━".repeat(58) + "\n" + RESET);

        Movie movieToEdit = selectMovie();
        if (movieToEdit == null) {
            return;
        }

        System.out.println("\n" + BOLD + ITALIC + movieToEdit.getTitle() + " redigeres..." + RESET);
        System.out.println("Tryk \"Enter\" for at beholde nuværende værdi.\n");


        // NY TITLE:
        System.out.print(ITALIC + "Titel: " + movieToEdit.getTitle() + RESET + "\nNy titel: ");
        String newTitle = scanner.nextLine();
        if (newTitle.isBlank()) {
            newTitle = movieToEdit.getTitle();
        }
        // NY INSTRUKTØR:
        System.out.print(ITALIC + "Instruktør: " + movieToEdit.getDirector() + RESET + "\nNy instruktør: ");
        String newDirector = scanner.nextLine();
        if (newDirector.isBlank()) {
            newDirector = movieToEdit.getDirector();
        }

        // NYT UDGIVELSESÅR:
        System.out.println(ITALIC + "Udgivelsesår: " + movieToEdit.getYearCreated() + RESET);

        int newYearCreated = movieToEdit.getYearCreated();

        while (true) {
            System.out.print("Nyt udgivelsesår: ");
            String yearInput = scanner.nextLine().trim();

            if (yearInput.isEmpty()) {
                break;
            }

            try {
                newYearCreated = Integer.parseInt(yearInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Hovsa! Du skal angive et gyldigt årstal, f.eks. \"2026\".");
            }
        }

        // NYT VALG, I FARVER?:
        String colorText;
        if (movieToEdit.isInColor()) {
            colorText = "farver";
        } else {
            colorText = "sort-hvid";
        }
        System.out.print(ITALIC + "Filmen er i " + colorText + RESET + "\n");

        boolean newInColor = movieToEdit.isInColor();

        while (true) {
            System.out.print("Nyt valg, er filmen i farver? (ja/nej): ");

            String colorInput = scanner.nextLine().trim();

            if (colorInput.isEmpty()) {
                break;
            }
            if (colorInput.equalsIgnoreCase("ja") || colorInput.equalsIgnoreCase("j")) {
                newInColor = true;
                break;
            }
            if (colorInput.equalsIgnoreCase("nej") || colorInput.equalsIgnoreCase("n")) {
                newInColor = false;
                break;
            }
            System.out.println("Hovsa! Svar ja, j, nej eller n.");
        }

        // NY LÆNGDE I MINUTTER:
        System.out.println(ITALIC + "Længde i minutter: " + movieToEdit.getLengthInMinutes() + RESET);

        int newLenghtInMinutes = movieToEdit.getLengthInMinutes();

        while (true) {
            System.out.print("Ny længde i minutter: ");
            String lenghtInput = scanner.nextLine().trim();

            if (lenghtInput.isEmpty()) {
                break;
            }
            try {
                newLenghtInMinutes = Integer.parseInt(lenghtInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Hovsa! Du skal angive filmens længde i minutter som et tal, f.eks. \"120\".");
            }
        }

        // NY GENRE:
        System.out.print("\nGenreoversigt: " + "\n|");

        for (Genre type : Genre.values()) {
            System.out.print(" " + ITALIC + type.getDanishName() + RESET + " |");

        }

        Genre movieGenre = Genre.valueOf(movieToEdit.getGenre().toUpperCase());
        System.out.print("\n\n" + ITALIC + "Genre: " + movieGenre.getDanishName() + RESET + "\n");


        String newGenre = movieToEdit.getGenre();

        while (true) {
            System.out.println("Ny genre: ");
            String genreInput = scanner.nextLine().trim();

            if (genreInput.isEmpty()) {
                break;
            }
            Genre chosenGenre = null;

            for (Genre type : Genre.values()) {
                if (type.getDanishName().equalsIgnoreCase(genreInput)) {
                    chosenGenre = type;
                    break;
                }
            }
            if (chosenGenre != null) {
                newGenre = chosenGenre.getDanishName();
                break;
            }
            System.out.println("\"" + genreInput + "\" er ikke en gyldig genre. Vælg en genre fra listen.");
        }

        // RETTELSER OPDATERET:
        try {
            boolean edited = controller.editMovie(movieToEdit, newTitle, newDirector, newYearCreated, newInColor, newLenghtInMinutes, newGenre);

            if (edited) {
                System.out.println("Filmen blev opdateret til: ");
                printMovie(movieToEdit);
            } else {
                System.out.println("Hovs! Filmen findes ikke længere i din samling. ");
            }
        }
        catch(IllegalArgumentException e){
            System.out.println("Filmen blev ikke ændret: " + e.getMessage());
        }
        System.out.println("");
    }


    // 8. SLET FILM:
    private void deleteMovie() {

        System.out.println(BOLD + "\n\n" + " ".repeat(13) + "⭑ SLET EN FILM FRA DIN SAMLING ⭑" + RESET);
        System.out.println(GRAY + "━".repeat(58) + "\n" + RESET);

        System.out.println("Søg efter den film, som du vil slette: ");

        String searchText = scanner.nextLine();
        ArrayList<Movie> matches = controller.searchMovies(searchText);

        if (matches.isEmpty()) {
            System.out.println("Din søgning matchede ingen film i din samling.");
            return;
        }

        System.out.println("Følgende film blev fundet: ");
        for (int i = 0; i < matches.size(); i++) {
            System.out.println((i + 1) + "- " + matches.get(i).getTitle() +
                    " (" + matches.get(i).getYearCreated() + ") ");
        }

        System.out.println("Vælg nummeret på den film, som skal slettes: ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice < 1 || choice > matches.size()) {
            System.out.println("Ugyldigt valg. Sletningen blev afbrudt.");
            return;
        }

        Movie movieDelete = matches.get(choice - 1);

        System.out.println("Skal \"" + movieDelete.getTitle() + "\" slettes? (Ja eller Nej)");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (!confirm.equalsIgnoreCase("Ja")) {
            System.out.println("Sletningen blev afbrudt.");
            return;
        }

        boolean isDeleted = controller.deleteMovie(movieDelete);

        if (isDeleted) {
            System.out.println("Filmen \"" + movieDelete.getTitle() + "\" er nu slettet.");
        } else {
            System.out.println("Der opstod en fejl under sletningen. Filmen kunne ikke slettes.");
        }
    }

    //vælg en film
    private Movie selectMovie() {
        System.out.println("Søg efter en film: ");
        String searchText = scanner.nextLine().trim();

        ArrayList<Movie> matches = controller.searchMovies(searchText);

        if (matches.isEmpty()) {
            System.out.println("ingen film matcher din søgning.");
            return null;
        }

        for (int i = 0; i < matches.size(); i++) {
            Movie movie = matches.get(i);

            System.out.println((i + 1) + " " + movie.getTitle() + " " + movie.getYearCreated());
        }
        int number = readInt("Vælg nummer (0 = fortryd): ");

        while (number < 0 || number > matches.size()) {
            System.out.println("Vælg et nummer mellem 1 og " + matches.size() + " - eller 0 for at fortryde.");
            number = readInt("Vælg nummer (0 = fortryd: ");
        }
        if (number == 0) {
            return null;
        }
        return matches.get((number - 1));
    }



    // UDSKRIV FILMINFO:
    private void printMovie(Movie movie) {
        String colorText;

        if (movie.isInColor()) {
            colorText = "farver";
        } else {
            colorText = "sort-hvid";
        }

        String capitalizedTitle = capitalizeFirstLetter(movie.getTitle());
        String capitalizedDirector = capitalizeFirstLetter(movie.getDirector());

        String danskNavn = movie.getGenre(); // Fallback hvis den ikke findes i enummets liste

        for (Genre type : Genre.values()) {
            // Tjekker om den gemte tekst matcher enten Enum-navnet (fx "ACTION") eller det danske navn
            if (type.name().equalsIgnoreCase(movie.getGenre()) || type.getDanishName().equalsIgnoreCase(movie.getGenre())) {
                danskNavn = type.getDanishName();
                break;
            }
        }

        String capitalizeGenre = capitalizeFirstLetter(danskNavn);

        System.out.println("\n" + BOLD + capitalizedTitle + RESET + " (" + ITALIC + movie.getYearCreated() + RESET + ")\n");

        System.out.print("OM");
        System.out.println("\n" + ITALIC + "Instruktør: " + capitalizedDirector + RESET);
        System.out.println(ITALIC + "Genre: " + capitalizeGenre + RESET);
        System.out.println(ITALIC + "Længde i minutter: " + movie.getLengthInMinutes() + RESET);
        System.out.println(ITALIC + "Filmen er i " + colorText + RESET);
        System.out.println();

        if (movie.hasBeenWatched()) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String date = movie.getLastWatched().format(formatter);
            long days = movie.getDaysSinceWatched(LocalDate.now());

            String daysText = days == 1 ? "1 dag siden" : days + " dage siden";

            System.out.println("Du har sidst set " + capitalizedTitle + " for " + daysText + " (" + date + ")");
        } else {
            System.out.println("Du har aldrig set " + capitalizedTitle);

        }
        System.out.println(GRAY + "⎯".repeat(35) + RESET);
    }

    // STORT FORBOGSTAV:
    private String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    // KONVERTERER TIL HELTAL:
    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(input + " er ikke et helt tal. prøv igen.");
            }
        }
    }
}
