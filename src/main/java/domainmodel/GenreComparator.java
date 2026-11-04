package domainmodel;

import java.util.Comparator;

public class GenreComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie movie1, Movie movie2) {

        boolean isM1Other = movie1.getGenre().equalsIgnoreCase("OTHER") || movie1.getGenre().equalsIgnoreCase(Genre.OTHER.getDanishName());
        boolean isM2Other = movie2.getGenre().equalsIgnoreCase("OTHER") || movie2.getGenre().equalsIgnoreCase(Genre.OTHER.getDanishName());

        if (isM1Other && isM2Other) {
            return 0;
        }
        if (isM1Other) {
            return 1;
        }
        if (isM2Other) {
            return -1;
        }

        return movie1.getGenre().compareToIgnoreCase(movie2.getGenre());
    }
}
