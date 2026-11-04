package domainmodel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ComparatorTest {

    @Test
    void comparatorsReturnCorrectSigns() {
        // Arrange
        Movie first = new Movie(
                "All is lost", "Alfred Hitchcock",
                1950, true, 100, "Drama");

        Movie second = new Movie(
                "Cars", "Michael Curtiz",
                2000, true, 150, "Thriller");

        TitleComparator titleComparator = new TitleComparator();
        DirectorComparator directorComparator = new DirectorComparator();
        YearComparator yearComparator = new YearComparator();
        LengthComparator lengthComparator = new LengthComparator();
        GenreComparator genreComparator = new GenreComparator();

        // Act
        int titleBefore = titleComparator.compare(first, second);
        int titleAfter = titleComparator.compare(second, first);
        int titleEqual = titleComparator.compare(first, first);

        int directorBefore = directorComparator.compare(first, second);
        int directorAfter = directorComparator.compare(second, first);

        int yearBefore = yearComparator.compare(first, second);
        int yearAfter = yearComparator.compare(second, first);

        int lengthBefore = lengthComparator.compare(first, second);
        int lengthAfter = lengthComparator.compare(second, first);

        int genreBefore = genreComparator.compare(first, second);
        int genreAfter = genreComparator.compare(second, first);

        // Assert
        assertTrue(titleBefore < 0);
        assertTrue(titleAfter > 0);
        assertEquals(0, titleEqual);

        assertTrue(directorBefore < 0);
        assertTrue(directorAfter > 0);

        assertTrue(yearBefore < 0);
        assertTrue(yearAfter > 0);

        assertTrue(lengthBefore < 0);
        assertTrue(lengthAfter > 0);

        assertTrue(genreBefore < 0);
        assertTrue(genreAfter > 0);
    }

    @Test
    void titleComparatorIgnoresCase() {
        //Arrange

        TitleComparator comparator = new TitleComparator();

        Movie first = new Movie("all is Lost", "Test", 2000, true, 120, "Drama");
        Movie second = new Movie("ALL IS LOST", "Test", 2000, true, 150, "thriller");

        //Act
        int result = comparator.compare(first, second);

        //Assert
        assertEquals(0, result);
    }

    @Test
    void compareToUsesTitleOrder() {
        //Arrange
        Movie first = new Movie("all is Lost", "Test", 2000, true, 120, "Drama");
        Movie second = new Movie("Cars", "Test", 2000, true, 150, "thriller");

        //Act
        int before = first.compareTo(second);
        int after = second.compareTo(first);

        //Assert
        assertTrue(before < 0);
        assertTrue(after > 0);
    }

    @Test
    void getMoviesSortedByTitleReturnsAplhabetically() {
        //Arrange
        MovieCollection collection = new MovieCollection();

        collection.addMovie("all is Lost", "Test", 2000, true, 120, "Drama");
        collection.addMovie("cars", "Test", 2000, true, 150, "thriller");
        collection.addMovie("Barbie", "Test", 2000, true, 120, "Drama");

        //Act
        ArrayList<Movie> sorted = collection.getMoviesSortedByTitle();
        //Assert
        assertEquals(3, sorted.size());
        assertEquals("all is Lost", sorted.get(0).getTitle());
        assertEquals("Barbie", sorted.get(1).getTitle());
        assertEquals("cars", sorted.get(2).getTitle());
    }

    @Test
    void sortingDoesNotChangeOrgOrder() {
        //Arrange
        MovieCollection collection = new MovieCollection();

        collection.addMovie("all is Lost", "Test", 2000, true, 120, "Drama");
        collection.addMovie("cars", "Test", 2000, true, 150, "thriller");
        collection.addMovie("Barbie", "Test", 2000, true, 120, "Drama");

        //Act
        collection.getMoviesSortedByTitle();
        ArrayList<Movie> org = collection.getMovies();
        //Assert

        assertEquals(3, org.size());
        assertEquals("all is Lost", org.get(0).getTitle());
        assertEquals("cars", org.get(1).getTitle());
        assertEquals("Barbie", org.get(2).getTitle());
    }

    @Test
    void moviesSortedByYear() {
        //Arrange
        Controller controller = new Controller();

        controller.addMovie("all is Lost", "Test", 2010, true, 120, "Drama");
        controller.addMovie("cars", "Test", 2005, true, 150, "thriller");
        controller.addMovie("Barbie", "Test", 1999, true, 120, "Drama");

        //Act
        ArrayList<Movie> sorted = controller.getMoviesSortedBy(SortField.YEAR);

        //Assert
        assertEquals(3, sorted.size());
        assertEquals(1999, sorted.get(0).getYearCreated());
        assertEquals(2005, sorted.get(1).getYearCreated());
        assertEquals(2010, sorted.get(2).getYearCreated());
    }
}
