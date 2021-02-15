package pl.brzezinski.service;

import org.springframework.stereotype.Service;
import pl.brzezinski.dto.HistoryResponse;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pl.brzezinski.config.Configuration.*;

@Service
public class FileService {

    public void fileWriter(String text) throws IOException {
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
            fileWriter(text);
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

    public List<HistoryResponse> results(String fileName) throws FileNotFoundException {
        List<String> records = fileReader(PATH + fileName);
        List<HistoryResponse> response = new ArrayList<>();
        for (String record : records) {
            response.add(new HistoryResponse(
                    record.substring(0, 19),
                    record.substring(20)
            ));
        }
        return response;
    }

    private List<String> fileReader(String filename) throws FileNotFoundException {
        List<String> results = new ArrayList<>();
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Scanner reader = new Scanner(bufferedReader);
        while (reader.hasNextLine()) {
            results.add(reader.nextLine());
        }
        reader.close();
        return results;
    }

    public List<String> allFiles() {
        List<String> result = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(Paths.get(PATH))) {
            result = walk.filter(Files::isRegularFile)
                    .map(Path::toString).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void deleteHistory() throws FileNotFoundException {
        File directory = new File(PATH);
        File[] files = directory.listFiles();
        if (files.length > 0){
            for (File file : files) {
                file.delete();
            }
        }else {
         throw new FileNotFoundException();
        }
    }
}
