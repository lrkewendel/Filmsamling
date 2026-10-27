import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserInterface {

    private Scanner scanner;
    private Controller controller;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
        this.controller = new Controller();
    }

    // TEKSTFORMATER:
    final String BOLD = "\u001B[1m";
    final String GRAY = "\u001B[90m";
    final String ITALIC = "\u001B[3m";
    final String BLACK = "\u001B[30m";
    final String RESET = "\u001B[0m";
    final String BLACK_BG = "\u001B[40m";

    private void welcome() {
        System.out.println("VELKOMMEN TIL DIN FILMSAMLING\n");
        System.out.println("");

    }

    private void menu() {

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
        menu();

        // TEST FILM:
        controller.addMovie("Barbie", "Greta Gerwig", 2023, true, 114, "Fantasy");
        controller.addMovie("Cars", "John Lasseter", 2006, true, 116, "Komedie");

        while (running) {
            System.out.print("\nSkriv et tal fra menuen for at vælge, hvad du vil: ");
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
                        System.out.print("\nHovsa! \"" + ITALIC + choice + RESET + "\" findes ikke på menuen :( \n\nPrøv igen... ");
            }
        }
    }

    // 1. TILFØJ FILM:
    private void createMovie() {

        System.out.println(BOLD + "\n\n" + " ".repeat(12) + "⭑ TILFØJ EN FILM TIL DIN SAMLING ⭑" + RESET);
        System.out.println(GRAY + "━".repeat(58) + "\n" + RESET);

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Instruktør: ");
        String director = scanner.nextLine();

        Integer yearCreated;

        do {
            yearCreated = readInt("Årstal: ");

            if (yearCreated == null) {
                System.out.println("Hovsa! Du skal skriv et tal f.eks \"2026\"");
            }

        } while (yearCreated == null);


        String answer;

        do {
            System.out.print("I farver (ja/nej): ");
            answer = scanner.nextLine().trim();

            if (!answer.equalsIgnoreCase("ja") && !answer.equalsIgnoreCase("nej")) {
                System.out.println("Hovsa! Skriv venligst \"ja\" eller \"nej\".\n");
            }

        } while (!answer.equalsIgnoreCase("ja") && !answer.equalsIgnoreCase("nej"));

        boolean inColor = answer.equalsIgnoreCase("ja");


        Integer lengthInMinutes;

        do {
            lengthInMinutes = readInt("Længde i minutter: ");

            if (lengthInMinutes == null) {
                System.out.println("Hovsa! Du skal skriv et tal f.eks \"120\"");
            }

        } while (lengthInMinutes == null);


        System.out.print("Genre: ");
        for (Genre type : Genre.values()){
            System.out.println("- " + type.getDanishName());
        }

        Genre chosenGenre = null;

        while (chosenGenre == null) {
            System.out.println("Skriv en genre fra listen: ");
            String genreInput = scanner.nextLine().trim();

            for (Genre type : Genre.values()) {
                if (type.getDanishName().equalsIgnoreCase(genreInput)) {
                    chosenGenre = type;
                    break;
                }
            }

            if (chosenGenre == null) {
                System.out.println("\"" + genreInput + "\" er ikke en gyldig genre. Vælg venligst en genre fra listen.");
            }
        }

        String capitalizedTitle = capitalizeFirstLetter(title);

        System.out.print(ITALIC + "\n" + capitalizedTitle + " blev tilføjet til din filmsamling!\n" + RESET);
        System.out.println(GRAY + "⎯".repeat(35) + RESET);

        controller.addMovie(title, director, yearCreated, inColor, lengthInMinutes, String.valueOf(chosenGenre));
    }


    // 2. MARKÉR SOM SET:
    private void markAsSeen() {

        System.out.println(BOLD + "\n\n" + " ".repeat(14) + "⭑ TILFØJ EN FILM DU HAR SET ⭑" + RESET);
        System.out.println(GRAY + "━".repeat(58) + "\n" + RESET);

        System.out.println("Hvilken film har du set? ");
        String searchText = scanner.nextLine();

        ArrayList<Movie> matches = controller.searchMovies(searchText);

        if (matches.isEmpty()) {
            System.out.println("\nHovsa! Det ligner ikke at \"" + ITALIC + searchText + RESET + "\" findes i din samling :(");
            System.out.println(GRAY + "⎯".repeat(35) + RESET);
            return;

        }

        System.out.println("\nFilm der matcher din søgning:");

        for (int i = 0; i < matches.size(); i++) {
            Movie movie = matches.get(i);
            System.out.print((i + 1) + ". " + capitalizeFirstLetter(movie.getTitle()) + " (" + ITALIC + movie.getYearCreated() + RESET + ")\n");
        }

        int choice = readInt("\nSkriv nummeret fra listen på den film du har set: ");


        if (choice < 1 || choice > matches.size()) {
            System.out.println("\nHovsa! Det tal var ikke på listen :(");
            System.out.println(GRAY + "⎯".repeat(35) + RESET);
            return;
        }

        Movie selectedMovie = matches.get(choice - 1);

        controller.markAsWatched(selectedMovie);

        System.out.println(ITALIC + "\n" + selectedMovie.getTitle() + " er markeret som set i dag!" + RESET);
        System.out.println(GRAY + "⎯".repeat(35) + RESET);
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
    }

    // 4. SORTÈR FILM:
    private void sortMovies() {

        System.out.println(BOLD + "\n\n" + " ".repeat(16) + "⭑ SOTÈR DIN FILMSAMLING ⭑" + RESET);
        System.out.println(GRAY + "━".repeat(58) + "\n" + RESET);

    }

    // 5. SØG EFTER FILM:
    private void searchMovies() {
        System.out.println(BOLD + "\n\n" + " ".repeat(12) + "⭑ SØG EFTER FILM I DIN SAMLING ⭑" + RESET);
        System.out.println(GRAY + "━".repeat(58) + "\n" + RESET);

        System.out.print("Søg på en film: ");
        String searchText = scanner.nextLine();
        ArrayList<Movie> matches = controller.searchMovies(searchText);

        if (matches.isEmpty()) {
            System.out.println("Intet resultat");
            return;
        }
        if (!matches.contains(searchText)) {
            System.out.println("\nFilm fra din samling der macher din søgning:");
            for (Movie match : matches) {
                printMovie(match);
            }
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


        System.out.println("Hvilken film fra din samling skal ændres? ");

        String searchText = scanner.nextLine();
        ArrayList<Movie> matches = controller.searchMovies(searchText);

        if (matches.isEmpty()) {
            System.out.println("Din søgning matchede ingen film i din samling.");
        }

        Movie movieToEdit = null;

        if (matches.size() == 1) {
            movieToEdit = matches.get(0);
        } else {
            System.out.println("Flere film matchede din søgning. Vælg den film, som du vil ændre: ");

            for (int i = 0; i < matches.size(); i++) {
                System.out.print("[" + (i + 1) + "] ");
                printMovie(matches.get(i));
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice > 0 && choice <= matches.size()) {
                movieToEdit = matches.get(choice - 1);
            } else {
                System.out.println("Ugyldigt valg. Redigeringen blev afbruft.");
                return;
            }
        }
        System.out.println(movieToEdit.getTitle() + " redigerers...");
        System.out.println("Tryk \"Enter\" for at beholde nuværende værdi");

        System.out.println("Titel: " + movieToEdit.getTitle() + " - Ny titel: ");
        String newTitel = scanner.nextLine();
        if (!newTitel.trim().isEmpty()) {
            movieToEdit.setTitle(newTitel);
        }

        System.out.println("Instruktør: " + movieToEdit.getDirector() + " - Ny instruktør: ");
        String newDirector = scanner.nextLine();
        if (!newDirector.trim().isEmpty()) {
            movieToEdit.setDirector(newDirector);
        }

        System.out.println("Årstal: " + movieToEdit.getYearCreated() + " - Nyt årstal: ");
        String yearInput = scanner.nextLine();
        if (!yearInput.trim().isEmpty()) {
            movieToEdit.setYearCreated(Integer.parseInt(yearInput));
        }

        System.out.println("Farver: " + movieToEdit.isInColor() + " - Farve: ");
        String newColor = scanner.nextLine();
        if (!newColor.trim().isEmpty()) {
            movieToEdit.setInColor(Boolean.parseBoolean(newColor));
        }

        System.out.println("Længde: " + movieToEdit.getLengthInMinutes() + " - Ny tid: ");
        String newTime = scanner.nextLine();
        if (!newTime.trim().isEmpty()) {
            movieToEdit.setLengthInMinutes(Integer.parseInt(newTime));
        }

        System.out.println("Genre: " + movieToEdit.getGenre() + " - Ny genre: ");
        String newGenre = scanner.nextLine();
        if (!newGenre.trim().isEmpty()) {
            movieToEdit.setGenre(newGenre);
        }

        System.out.println("Filmen blev opdateret til: ");
        printMovie(movieToEdit);
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
        String capitalizeGenre = capitalizeFirstLetter(movie.getGenre());

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

    // STORT FORBOKSTAV:
    private String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    // KONVERTERER TIL HELTAL:
    private Integer readInt(String prompt) {

        try {
            System.out.print(prompt);
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}