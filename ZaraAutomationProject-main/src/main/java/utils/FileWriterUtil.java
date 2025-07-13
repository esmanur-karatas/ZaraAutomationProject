package utils;

import com.opencsv.CSVReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileWriterUtil {

    // Dosyaya veri yazan metod
    public static void writeToFile(String data, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(data);
            writer.newLine();
            System.out.println("Bilgi başarıyla dosyaya yazıldı: " + filePath);
        } catch (IOException e) {
            System.out.println("Dosyaya yazma işlemi başarısız: " + e.getMessage());
        }
    }

    public static List<String[]> readCsv(String filePath) {
        List<String[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            reader.skip(1); // header'ı atla
            String[] line;
            while ((line = reader.readNext()) != null) {
                data.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
