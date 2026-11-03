package domainmodel;
import java.time.LocalDate;
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

        Movie movie = newcollection.getMovies().get(0);
        assertEquals("Cars", movie.getTitle());
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
        Movie movie = collection.searchMovies("Casablanca").get(0);

        //Act
        boolean edited = collection.editMovie(movie, "Cars", "John Lasseter", 2006, true, 116, "Animation");

        //Assert
        assertTrue(edited);
        assertEquals("Cars", movie.getTitle());
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
        assertEquals("Casablanca", anothermovie.getTitle());
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
    @Test
    void movieNeverWatchedAreIncluded() {
        //Arrange
        LocalDate limit = LocalDate.of(2026,11,3);

        //Act
        ArrayList<Movie> result = collection.getMoviesNotWatchedSince(limit);

        //Assert
        assertEquals(3, result.size());
        assertTrue(result.containsAll(collection.getMovies()));
    }
    @Test
    void onlyMoviesWatchedBeforeLimitIncluded() {
        //Arrange
        Movie godfather = collection.searchMovies("The Godfather").get(0);
        Movie casablanca = collection.searchMovies("Casablanca").get(0);
        Movie godzilla = collection.searchMovies("Godzilla").get(0);

        LocalDate limit = LocalDate.of(2025,10,1);

        godfather.markAsWatched(limit.minusDays(1));
        casablanca.markAsWatched(limit);
        godzilla.markAsWatched((limit.plusDays(1)));

        //Act
        ArrayList<Movie> result = collection.getMoviesNotWatchedSince(limit);

        //Assert
        assertEquals(1, result.size());
        assertTrue(result.contains(godfather));
        assertFalse(result.contains(casablanca));
        assertFalse(result.contains(godzilla));
    }

    @Test
    void editMovieInvalidInputNoChange() {
        //Arrange
        Movie movie = collection.searchMovies("Casablanca").get(0);

        //Act + Assert
        assertThrows(IllegalArgumentException.class,() -> collection.editMovie(movie, "Test", "test2", 1800, true, 120, "Horror"));
        //tester med årstallet afvises da en del af opgaven er at før 1888 skal afvises

        assertEquals("Casablanca", movie.getTitle());
        assertEquals("Michael Curtiz", movie.getDirector());
        assertEquals(1942, movie.getYearCreated());
        assertFalse(movie.isInColor());
        assertEquals(102, movie.getLengthInMinutes());
        assertEquals("Drama", movie.getGenre());
    }
}