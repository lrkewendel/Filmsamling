import org.junit.jupiter.api.Test;

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
}