package domainmodel;

import java.sql.Array;
import java.time.LocalDate;
import java.util.*;

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
    //Returnerer en sorteret KOPI
    public ArrayList<Movie> getMoviesSortedByTitle() {
        ArrayList<Movie> sorted = new ArrayList<>(movies);
        Collections.sort(sorted);
        return sorted;
    }

    public ArrayList<Movie> getMoviesSorted(Comparator<Movie> comparator) {
        ArrayList<Movie> sorted = new ArrayList<>(movies);
        sorted.sort(comparator);
        return sorted;
    }
}