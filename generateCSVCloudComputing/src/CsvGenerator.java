import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CsvGenerator {

    public static void main(String[] args) {
        String fileDirectory = "C:\\Users\\anast\\Documents\\Uni\\S5\\Cloud Computing\\";
        String fileNamePrefix = "InputData1023";
        int numberOfFiles = 1; // Anzahl der zu erstellenden Dateien

        for (int fileNumber = 1; fileNumber <= numberOfFiles; fileNumber++) {
            String filePath = fileDirectory + fileNamePrefix + fileNumber + ".csv";
            generateCsvFile(filePath);
        }
    }

    private static void generateCsvFile(String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Random random = new Random();

            for (int i = 0; i < 1023; i++) {
                String timestamp = dateFormat.format(new Date());
                int randomValue = random.nextInt(1023); // Random Number between 0 and 1023
                fileWriter.append(timestamp).append(",").append(String.valueOf(randomValue)).append("\n");

                Thread.sleep(1);
            }

            System.out.println("CSV file successfully created: " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while writing the CSV file.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("An error occurred while pausing the thread.");
            e.printStackTrace();
        }
    }
}
