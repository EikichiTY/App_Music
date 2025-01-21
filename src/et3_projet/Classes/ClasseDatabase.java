package et3_projet.Classes;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.exceptions.CsvException;
import com.opencsv.CSVReader;


public class ClasseDatabase {
    public static List<ClasseChanson> data;

    public ClasseDatabase() {
        String csvFile = "src/et3_projet/spotify_millsongdata.csv";
        data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            // on ignorer la première ligne
            reader.readNext();

            List<String[]> lecture = reader.readAll();
            for (String[] record : lecture) {
                if (record.length >= 4) {
                    String artist = record[0];
                    String title = record[1];
                    String link = record[2];
                    String lyrics = record[3];
                    ClasseChanson chanson = new ClasseChanson(artist, title, link, lyrics);
                    data.add(chanson);
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}