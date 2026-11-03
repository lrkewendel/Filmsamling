package domainmodel;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void constructorSetsAllFields() {
        // Arrange + Act
        Movie movie = new Movie("Casablanca", "Michael Curtiz", 1942, false, 102, "Drama");

        // Assert
        assertEquals("Casablanca", movie.getTitle());
        assertEquals("Michael Curtiz", movie.getDirector());
        assertEquals(1942, movie.getYearCreated());
        assertFalse(movie.isInColor());
        assertEquals(102, movie.getLengthInMinutes());
        assertEquals("Drama", movie.getGenre());
    }

    @Test
    void setTitleChangesTitle() {
        // Arrange
        Movie movie = new Movie("Jaws", "Steven Spielberg", 1975, true, 124, "Thriller");

        // Act
        movie.setTitle("Dødens gab");

        // Assert
        assertEquals("Dødens gab", movie.getTitle());
    }
    @Test
    void emptyTitleIsRejected() {
        //Arrange
        String title = "";

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new Movie(title, "JFK", 1960, false, 120, "thriller"));
    }
    @Test
    void newMovieHasNotBeenWatched() {
        //Arrange
        Movie movie = new Movie("Jaws", "Steve. S", 1975, true, 120, "Action");

        //Act + Assert
        assertFalse (movie.hasBeenWatched());
        assertNull(movie.getLastWatched());
    }
    @Test
    void markAsWatchedSavesDate() {
        //Arrange
        Movie movie = new Movie ("H.P Prisoner of Azkaban", "J.K.R", 2000, true, 140, "Thriller");
        LocalDate watchedDate = LocalDate.of(2026, 9, 24);

        //Act
        movie.markAsWatched(watchedDate);

        //Assert
        assertTrue(movie.hasBeenWatched());
        assertEquals(watchedDate, movie.getLastWatched());
    }
    @Test
    void daysSinceWatchedCountsAcroosMonths() {
        //Arrange
        Movie movie = new Movie("H.P Prisoner of Azkaban", "J.K.R", 2000, true, 140, "Thriller");

        movie.markAsWatched(LocalDate.of(2026,9,24));
        LocalDate today = LocalDate.of(2026,11,3);

        //Act
        long days = movie.getDaysSinceWatched(today);

        //Assert
        assertEquals(40L, days);
    }

    @Test
    void year1887IsRejected() {
        //Arrange
        int yearCreated = 1887;

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new Movie("TEST", "TEST", yearCreated, true, 20, "TEST"));
    }
    @Test
    void year1888IsAccepted() {
        //Arrange
        int yearCreated = 1888;

        //Act
        Movie movie = new Movie("Test", "Test", yearCreated, true, 10, "test");

        //Assert
        assertEquals(1888, movie.getYearCreated());
    }
    @Test
    void yearTwoYearsInFutureIsRejected() {
        //Arrange
        int futureYear = LocalDate.now().getYear() + 2;

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new Movie("Test", "Test", futureYear, true, 100, "Horror"));
    }
    @Test
    void zeroLengthIsRejected() {
        //Arrange
        int lenghtInMinutes = 0;

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new Movie("Test", "Test", 1980, true, lenghtInMinutes, "test"));
    }
    @Test
    void invalidYearKeepsOldYear() {
        //Arrange
        Movie movie = new Movie("Test", "Test", 1980, true, 124, "Test");

        //Act
        assertThrows(IllegalArgumentException.class, () -> movie.setYearCreated(1887));

        //Assert
        assertEquals(1980, movie.getYearCreated());
    }
    @Test
    void futureWatchedDateIsRejected() {
        //Arrange
        Movie movie = new Movie("Test", "Test", 2000, true, 120, "Test");
        LocalDate futureDate = LocalDate.now().plusYears(2);

        //Act
        assertThrows(IllegalArgumentException.class, () -> movie.markAsWatched(futureDate));

        //Assert
        assertFalse(movie.hasBeenWatched());
        assertNull(movie.getLastWatched());
    }
}