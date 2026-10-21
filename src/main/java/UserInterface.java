import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;
    private Controller controller;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
        this.controller = new Controller();
    }

    private void welcome() {
        System.out.print("""
                 VELKOMMEN TIL MIN FILMSAMLING
                
                Skriv et tal fra menuen for at vælge, hvad du vil gøre:
                
                | 1. Opret en film | 2. Vis alle film | 3. Søg efter film | 4. Redigér en film | 5. Slet en film | 0. Afslut |
                """);

    }


    public void startProgram() {
        boolean running = true;
        welcome();

        while (running) {
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> createMovie();
                case "2" -> showAllMovies();
                case "3" -> searchMovies();
                case "4" -> editMovie();
                case "5" -> deleteMovie();
                case "0" -> running = false;
                default -> System.out.println("Ukendt valg: " + choice);
            }
        }
    }

    private void createMovie() {
        System.out.print("\nTitle: ");
        String title = scanner.nextLine();

        System.out.print("Instruktør: ");
        String director = scanner.nextLine();

        int yearCreated = readInt("Årstal: ");

        System.out.print("I farver (ja/nej): ");
        boolean inColor = scanner.nextLine().trim().equalsIgnoreCase("ja");

        int lengthInMinutes = readInt("Længde i minutter: ");

        System.out.print("Genre: ");
        String genre = scanner.nextLine();

        System.out.print("\n" + title + " er tilføjet til samlingen.");

        controller.addMovie(title, director, yearCreated, inColor, lengthInMinutes, genre);
    }

    private void showAllMovies() {

        ArrayList<Movie> movies = controller.getMovies();

        if (movies.isEmpty()) {
            System.out.println("Du har ingen film i din samling :(");
            return;
        }

        System.out.println("\nDIN FILMSAMLING");

        for (Movie movie : movies){
            printMovie(movie);
        }
    }

    private void searchMovies() {
        System.out.print("Søg efter film: ");
        String searchText = scanner.nextLine();
        ArrayList<Movie> matches = controller.searchMovies(searchText);

        if (matches.isEmpty()) {
            System.out.println("Intet resultat");
            return;
        }
        if (!matches.contains(searchText)) {
            for (Movie match : matches) {
                printMovie(match);
            }
        }
    }


    private void editMovie() {

    }

    private void deleteMovie() {

    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine().trim());
    }
    private void printMovie (Movie movie) {
        String colorText;

        if (movie.isInColor()) {
            colorText = "farver";
        }
        else {
            colorText = "sort-hvid";
        }
        System.out.println(movie.getTitle() + movie.getYearCreated());
        System.out.println("Instruktør: " + movie.getDirector());
        System.out.println("Genre: " + movie.getGenre());
        System.out.println("Længde " + movie.getLengthInMinutes() + colorText);
    }
}



