package pl.brzezinski.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Scanner;

@Service
public class FileWriterService {

    private static final String PATH = "./calculation_sheets/";
    private static final String DEFAULT_FILE_NAME = "calculations_history.txt";

    public void createNewHistoryFile() throws IOException {
        File file = new File(PATH + DEFAULT_FILE_NAME);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (file.createNewFile()) {
            System.out.println("File created " + file.getName());
        } else {
            System.out.println("File already exists.");
        }
    }

    public void writeToFile(String text) {
        try {
            FileWriter fileWriter = new FileWriter(PATH + DEFAULT_FILE_NAME, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(text);
            bufferedWriter.newLine();
            bufferedWriter.close();
            System.out.println("Successfully wrote to the file");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void fileReader() {
        try {
            FileReader fileReader = new FileReader(PATH + DEFAULT_FILE_NAME);
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

}
