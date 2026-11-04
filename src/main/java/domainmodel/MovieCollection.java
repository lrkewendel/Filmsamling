package domainmodel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection {
    private ArrayList<Movie> movies;
    public MovieCollection() {
        movies = new ArrayList<>();
    }


    public void addMovie(String title, String director, int yearCreated, boolean inColor, int lengthInMinutes, String genre) {

        Movie movie = new Movie(title, director, yearCreated, inColor, lengthInMinutes, genre);
        movies.add(movie);
    }

    public void addMovie(Movie movie){movies.add(movie);}


    public ArrayList<Movie> getMovies() {return movies;}

    public int getNumberOfMovies() {
        return movies.size();
    }

    public ArrayList<Movie> searchMovies(String searchText) {
        ArrayList<Movie> matches = new ArrayList<>();
        String text = searchText.toLowerCase();
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(text)) {
                matches.add(movie);
            }
        }
        return matches;
    }

    public void sortMovies(String sortingChoice){

        Comparator<Movie> c = switch (sortingChoice.toLowerCase()){
            case "titel" -> Comparator.comparing(Movie::getTitle);
            case "instruktør" -> Comparator.comparing(Movie::getDirector);
            case "udgivelsesår" -> Comparator.comparing(Movie::getYearCreated);
            case "farver" -> Comparator.comparing(Movie::isInColor);
            case "længde" -> Comparator.comparing(Movie::getLengthInMinutes);
            case "genre" -> (m1, m2) -> {
                boolean isM1Other = m1.getGenre().equalsIgnoreCase("OTHER") || m1.getGenre().equalsIgnoreCase(Genre.OTHER.name());
                boolean isM2Other = m2.getGenre().equalsIgnoreCase("OTHER") || m2.getGenre().equalsIgnoreCase(Genre.OTHER.name());

                if (isM1Other && isM2Other) return 0;
                if (isM1Other) return 1;
                if (isM2Other) return -1;

                return m1.getGenre().compareTo(m2.getGenre());};

            default -> Comparator.comparing(Movie::getTitle);
        };

        this.movies.sort(c);
    }

    public boolean editMovie(Movie movie, String title, String director, int yearCreated,
                             boolean inColor, int lengthInMinutes, String genre) {

        if (!movies.contains(movie)) {
            return false;
        }
        //validerer oplysninger før eksisterende film ændres, så vi undgår delvis ændring
        new Movie(title, director, yearCreated, inColor, lengthInMinutes, genre);
        movie.setTitle(title);
        movie.setDirector(director);
        movie.setYearCreated(yearCreated);
        movie.setInColor(inColor);
        movie.setLengthInMinutes(lengthInMinutes);
        movie.setGenre(genre);

        return true;
    }

    public boolean deleteMovie(Movie movie) {return movies.remove(movie);}

    public ArrayList<Movie> getMoviesNotWatchedSince(LocalDate date) {
        ArrayList<Movie> result = new ArrayList<>();

        for (Movie movie : movies) {
            if (!movie.hasBeenWatched()
                    || movie.getLastWatched().isBefore(date)) {
                result.add(movie);
            }
        }

        return result;
    }
}