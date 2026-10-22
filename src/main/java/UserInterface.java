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

        System.out.println("Hvilken film fra din samling skal ændres? ");

        String searchText = scanner.nextLine();
        ArrayList<Movie> matches = controller.searchMovies(searchText);

        if(matches.isEmpty()){
            System.out.println("Din søgning matchede ingen film i din samling.");
        }

        Movie movieToEdit = null;

        if(matches.size() == 1){
            movieToEdit = matches.get(0);
        } else {
            System.out.println("Flere film matchede din søgning. Vælg den film, som du vil ændre: ");

            for (int i = 0; i < matches.size(); i++){
                System.out.print("[" + (i + 1) + "] ");
                printMovie(matches.get(i));
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            if(choice > 0 && choice <= matches.size()){
                movieToEdit = matches.get(choice - 1);
            } else {
                System.out.println("Ugyldigt valg. Redigeringen blev afbruft.");
                return;
            }
        }
        System.out.println(movieToEdit.getTitle() + " redigerers...");
        System.out.println("Tryk 'Enter' for at beholde nuværende værdi");

        System.out.println("Titel: " + movieToEdit.getTitle() + " - Ny titel: ");
        String newTitel = scanner.nextLine();
        if(!newTitel.trim().isEmpty()){
            movieToEdit.setTitle(newTitel);
        }

        System.out.println("Instruktør: " + movieToEdit.getDirector() + " - Ny instruktør: ");
        String newDirector = scanner.nextLine();
        if(!newDirector.trim().isEmpty()){
            movieToEdit.setDirector(newDirector);
        }

        System.out.println("Årstal: " + movieToEdit.getYearCreated() + " - Nyt årstal: ");
        String yearInput = scanner.nextLine();
        if(!yearInput.trim().isEmpty()){
            movieToEdit.setYearCreated(Integer.parseInt(yearInput));
        }

        System.out.println("Farver: " + movieToEdit.isInColor() + " - Farve: ");
        String newColor = scanner.nextLine();
        if(!newColor.trim().isEmpty()){
            movieToEdit.setInColor(Boolean.parseBoolean(newColor));
        }

        System.out.println("Længde: " + movieToEdit.getLengthInMinutes() + " - Ny tid: ");
        String newTime = scanner.nextLine();
        if(!newTime.trim().isEmpty()){
            movieToEdit.setLengthInMinutes(Integer.parseInt(newTime));
        }

        System.out.println("Genre: " + movieToEdit.getGenre() + " - Ny genre: ");
        String newGenre = scanner.nextLine();
        if(!newGenre.trim().isEmpty()){
            movieToEdit.setGenre(newGenre);
        }

        System.out.println("Filmen blev opdateret til: ");
        printMovie(movieToEdit);
    }


    private void deleteMovie() {

        System.out.println("Søg efter den film, som du vil slette: ");

        String searchText = scanner.nextLine();
        ArrayList<Movie> matches = controller.searchMovies(searchText);

        if(matches.isEmpty()){
            System.out.println("Din søgning matchede ingen film i din samling.");
            return;
        }

        System.out.println("Følgende film blev fundet: ");
        for(int i = 0; i < matches.size(); i++){
            System.out.println((i + 1) + "- " + matches.get(i).getTitle() + " (" + matches.get(i).getYearCreated() + ") ");
        }

        System.out.println("Vælg nummeret på den film, som skal slettes: ");
        int choice = Integer.parseInt(scanner.nextLine());

        if(choice < 1 || choice > matches.size()){
            System.out.println("Ugyldigt valg. Sletningen blev afbrudt.");
            return;
        }

        Movie movieDelete = matches.get(choice - 1);

        System.out.println("Skal '" + movieDelete.getTitle() + "' slettes? (Ja eller Nej)");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if(!confirm.equalsIgnoreCase("Ja")){
            System.out.println("Sletningen blev afbrudt.");
            return;
        }

        boolean isDeleted = controller.deleteMovie(movieDelete);

        if(isDeleted){
            System.out.println("Filmen '" + movieDelete.getTitle() + "' er nu slettet.");
        } else {
            System.out.println("Der opstod en fejl under sletningen. Filmen kunne ikke slettes.");
        }
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



