package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TextWriter {

    public static void writeToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            System.out.println("Ürün bilgisi txt dosyasına yazıldı.");
        } catch (IOException e) {
            System.out.println("Txt dosyasına yazma hatası: " + e.getMessage());
        }
    }
}

