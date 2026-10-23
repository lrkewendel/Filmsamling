import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MovieCollectionTest {

    private MovieCollection collection;

    @BeforeEach
    void setUp() {
        // Kører før HVER test – så starter alle tests med den samme, friske samling
        collection = new MovieCollection();
        collection.addMovie("The Godfather", "Francis Ford Coppola", 1972, true, 175, "Crime");
        collection.addMovie("Casablanca", "Michael Curtiz", 1942, false, 102, "Drama");
        collection.addMovie("Godzilla", "Ishirō Honda", 1954, false, 96, "Sci-fi");
    }

    @Test
    void getNumberOfMoviesZeroMovies() {
        //Arrange
        MovieCollection emptycollection = new MovieCollection();

        //Act
        int numberOfMovies = emptycollection.getNumberOfMovies();

        //Assert
        assertEquals(0, numberOfMovies);
    }

    @Test
    void addMovieOneCorrectMovie() {
        //Arrange
        MovieCollection newcollection = new MovieCollection();

        //Act
        newcollection.addMovie("Cars", "John Lasseter", 2006, true, 116, "Animation");

        //Assert
        assertEquals(1, newcollection.getNumberOfMovies());
    }

    @Test
    void addMovieMultiple() {
        //Arrange
        MovieCollection anothercollection = new MovieCollection();

        //Act
        anothercollection.addMovie("H.P and the Philosopher's Stone", "J.K. Rowling", 2001, true, 152, "Fantasy");
        anothercollection.addMovie("H.P and the Chamber of Secrets", "J.K. Rowling", 2002, true, 161, "Fantasy");
        anothercollection.addMovie("H.P and the Prisoner of Azkaban", "J.K. Rowling", 2004, true, 142, "Fantasy");

        //Assert
        assertEquals(3, anothercollection.getNumberOfMovies());
    }

    @Test
    void searchWithNoMatch() {
        //Arrange - oprettet i setup

        //Act
        ArrayList<Movie> result = collection.searchMovies("John Wick");

        //Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void searchWithExactlyOneMatch() {
        //Arrange - oprettet i setup


        //Act
        ArrayList<Movie> result = collection.searchMovies("Casa");

        //Assert
        assertEquals(1, result.size());
    }

    @Test
    void searchWithSeveralMatches() {
        // "god" findes i både "The Godfather" og "Godzilla"
        ArrayList<Movie> result = collection.searchMovies("god");

        assertEquals(2, result.size());
    }

    @Test
    void searchIgnoreCase() {
        //Arrange - oprettet i setup

        //Act
        ArrayList<Movie> result = collection.searchMovies( "CaSaBlAnCa");

        //Assert
        assertEquals(1, result.size());
    }

    @Test
    void editMovieChangesinfo() {
        //Arrange
        Movie casablanca = collection.searchMovies("Casablanca").get(0);

        //Act
        boolean edited = collection.editMovie(casablanca, "Cars", "John Lasseter", 2006, true, 116, "Animation");

        //Assert
        assertTrue(edited);
    }

    @Test
    void editMovieNotInCollection() {
        //Arrange - henter film fra listen og fjerner
        ArrayList<Movie> movies = collection.getMovies();
        Movie anothermovie = movies.remove(1);

        //Act
        boolean edited = collection.editMovie(anothermovie, "IT", "Stephen King", 2017, true, 132, "Horror");

        //Assert
        assertFalse(edited);
    }

    @Test
    void deleteMovieRemovesIt() {
        // Arrange
        Movie casablanca = collection.searchMovies("Casablanca").get(0);

        // Act
        boolean deleted = collection.deleteMovie(casablanca);

        // Assert
        assertTrue(deleted);
        assertEquals(2, collection.getNumberOfMovies());
        assertTrue(collection.searchMovies("Casablanca").isEmpty());
    }

    @Test
    void deleteMovieNotInCollection() {
        //Arrange
        ArrayList<Movie> movies = collection.getMovies();
        Movie deletedMovie = movies.remove(1); //remove gør filmen ikke er i samlingen og derfor ikke kan slettes

        //Act
        boolean deleted = collection.deleteMovie(deletedMovie);

        //Assert
        assertFalse(deleted);
        assertEquals(2, collection.getNumberOfMovies());
    }
}