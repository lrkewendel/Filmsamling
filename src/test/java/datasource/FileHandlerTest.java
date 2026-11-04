package datasource;

import domainmodel.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import java.io.PrintStream;

public class FileHandlerTest {

    private static final String TEST_FILE = "test-movies.csv";

    @AfterEach
    void deleteTestFile() {
        new File(TEST_FILE).delete();
    }

    @Test
    void savedMoviesCanBeLoadedAgain()
            throws FileNotFoundException, UnsupportedEncodingException {
        // Arrange
        FileHandler fileHandler = new FileHandler(TEST_FILE);
        ArrayList<Movie> movies = new ArrayList<>();

        Movie firstMovie = new Movie("Blå øjne og æbler", "Anders Thomas Jensen", 2005, true, 94, "Komedie");

        LocalDate watchedDate = LocalDate.of(2025, 10, 1);
        firstMovie.markAsWatched(watchedDate);

        Movie secondMovie = new Movie("Casablanca", "Michael Curtiz", 1942, false, 102, "Drama");

        movies.add(firstMovie);
        movies.add(secondMovie);

        // Act
        fileHandler.saveMovies(movies);
        ArrayList<Movie> loadedMovies = fileHandler.loadMovies();

        // Assert
        assertEquals(2, loadedMovies.size());

        Movie loadedFirst = loadedMovies.get(0);

        assertEquals("Blå øjne og æbler", loadedFirst.getTitle());
        assertEquals("Anders Thomas Jensen", loadedFirst.getDirector());
        assertEquals(2005, loadedFirst.getYearCreated());
        assertTrue(loadedFirst.isInColor());
        assertEquals(94, loadedFirst.getLengthInMinutes());
        assertEquals("Komedie", loadedFirst.getGenre());
        assertTrue(loadedFirst.hasBeenWatched());
        assertEquals(watchedDate, loadedFirst.getLastWatched());

        Movie loadedSecond = loadedMovies.get(1);

        assertEquals("Casablanca", loadedSecond.getTitle());
        assertEquals("Michael Curtiz", loadedSecond.getDirector());
        assertEquals(1942, loadedSecond.getYearCreated());
        assertFalse(loadedSecond.isInColor());
        assertEquals(102, loadedSecond.getLengthInMinutes());
        assertEquals("Drama", loadedSecond.getGenre());
        assertFalse(loadedSecond.hasBeenWatched());
        assertNull(loadedSecond.getLastWatched());
    }

    @Test
    void savedEmptyListLoadsAsEmpty() throws FileNotFoundException,UnsupportedEncodingException {
        //Arrange
        FileHandler fileHandler = new FileHandler(TEST_FILE);
        ArrayList<Movie> movies = new ArrayList<>();

        //Act
        fileHandler.saveMovies(movies);
        ArrayList<Movie> loadedMovies = fileHandler.loadMovies();

        //Assert
        assertNotNull(loadedMovies);
        assertTrue(loadedMovies.isEmpty());
    }

    @Test
    void loadingMissingFileThrowsException() {
        //Arrange
        FileHandler fileHandler = new FileHandler(TEST_FILE);
        new File(TEST_FILE).delete();

        //Act + Assert
        assertThrows(FileNotFoundException.class, () -> fileHandler.loadMovies());
    }

    @Test
    void brokenLinesAreSkippedAndCounted() throws FileNotFoundException, UnsupportedEncodingException {
        //Arrange
        FileHandler fileHandler = new FileHandler(TEST_FILE);
        PrintStream output = new PrintStream(new File(TEST_FILE), "UTF-8");
        output.println("title;director;yearCreated;inColor;lengthInMinutes;genre;lastWatched");

        output.println("Jaws;Steven Spielberg;1975;true;124;Thriller;");

        output.println("Casablanca;Michael Curtiz");

        output.println("Godzilla;Ishiro Honda; sytten;false;96;Action;");
        output.close();

        //Act
        ArrayList<Movie> loadedMovies = fileHandler.loadMovies();

        //Assert
        assertEquals(1, loadedMovies.size());
        assertEquals("Jaws", loadedMovies.get(0).getTitle());
        assertEquals(2, fileHandler.getSkippedLineCount());
    }




}
