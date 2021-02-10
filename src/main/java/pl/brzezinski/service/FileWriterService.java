package pl.brzezinski.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
            FileWriter writer = new FileWriter(PATH + DEFAULT_FILE_NAME);
            writer.write(text);
            writer.close();
            System.out.println("Successfully wrote to the file");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void fileReader(){
        File file = new File(PATH + DEFAULT_FILE_NAME);
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()){
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
