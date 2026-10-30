import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private String fileName;

    public FileHandler (String fileName){
        this.fileName = fileName;
    }

    public void saveMovies (ArrayList<Movie>movies)
        throws FileNotFoundException{
        PrintStream output = new PrintStream(new File(fileName));

        for(Movie movie : movies){
            output.println(movie.toCsvLine());
        }

        output.close();
    }

    public ArrayList<Movie>loadMovies()
        throws FileNotFoundException{

        ArrayList<Movie> movies = new ArrayList<>();

        Scanner fileScanner = new Scanner(new File(fileName));

        while (fileScanner.hasNextLine()){
            String line = fileScanner.nextLine();

            String[] fields = line.split(";", -1);

            String title = fields[0];
            String director = fields[1];
            int yearCreated = Integer.parseInt(fields[2]);
            boolean inColor = Boolean.parseBoolean(fields[3]);
            int lengthInMinutes = Integer.parseInt(fields[4]);
            String genre = fields[5];
            LocalDate lastWatched = null;

            if(!fields[6].isEmpty() && !fields[6].equals("null")){
                lastWatched = LocalDate.parse(fields[6]);
            }

            Movie movie = new Movie(title, director, yearCreated, inColor, lengthInMinutes, genre);

            if(lastWatched != null){
                movie.markAsWatched(lastWatched);
            }

            movies.add(movie);
        }

        fileScanner.close();

        return movies;
    }


}
