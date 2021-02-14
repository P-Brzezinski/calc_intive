package pl.brzezinski.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Scanner;

import static pl.brzezinski.config.Configuration.*;

@Service
public class FileWriterService {

    public void writeToFile(String text) throws IOException {
        File file = new File(PATH + FILE_NAME);
        if (!file.exists()) {
            createNewHistoryFile();
        }
        if (!isFull()) {
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
        } else {
            renameCurrentFile();
            createNewHistoryFile();
            writeToFile(text);
        }
    }

    private boolean isFull() throws IOException {
        int noOfLines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH + FILE_NAME))) {
            while (reader.readLine() != null) {
                noOfLines++;
            }
        }
        return noOfLines >= MAX_LINES_IN_FILE;
    }

    private void renameCurrentFile() {
        File directory = new File(PATH);
        int fileCount = directory.list().length;
        File oldName = new File(PATH + FILE_NAME);
        File name = new File(String.format("%s%s.%d", PATH, FILE_NAME, fileCount));
        oldName.renameTo(name);
    }

    private void createNewHistoryFile() throws IOException {
        File file = new File(PATH + FILE_NAME);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
    }

    //for console app
    private void fileReader() {
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
}
