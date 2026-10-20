import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;
    private Controller controller;

    public void startProgram() {
        boolean running = true;

        while (running) {
            String choice = scanner.nextLine().trim();
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

    }

    private void showAllMovies() {

    }

    private void searchMovies() {

    }

    private void editMovie() {

    }

    private void deleteMovie() {

    }

}
