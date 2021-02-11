package pl.brzezinski.service;

import org.springframework.stereotype.Service;
import pl.brzezinski.config.Configuration;

import java.io.*;
import java.util.Scanner;

@Service
public class FileWriterService {

    private static String PATH = "./calculation_sheets/";
    private static String FILE_NAME = "calculations_history.txt";

    public void createNewHistoryFile(String fileName) throws IOException {
        File file = new File(PATH + fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
    }

    public void writeToFile(String text) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(PATH + FILE_NAME, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(text);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void fileReader() {
        try {
            FileReader fileReader = new FileReader(PATH + FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Scanner reader = new Scanner(bufferedReader);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public boolean isFull() throws IOException {
        int noOfLines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH + FILE_NAME))) {
            while (reader.readLine() != null) {
                noOfLines++;
            }
        }
        return noOfLines >= Configuration.MAX_LINES_IN_FILE;
    }
}
