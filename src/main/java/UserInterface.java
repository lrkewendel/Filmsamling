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

        String choice = scanner.nextLine();

        while (running) {
            choice = choice;

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
        }

        System.out.println("\nDIN FILMSAMLING");

        for (Movie movie : movies){
            movie.printMovie(movie);
        }
    }

    private void searchMovies() {
    }

    private void editMovie() {

    }

    private void deleteMovie() {

    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine().trim());
    }
}



