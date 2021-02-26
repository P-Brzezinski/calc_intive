package pl.brzezinski.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.brzezinski.dto.CalculationRequest;
import pl.brzezinski.dto.HistoryResponse;
import pl.brzezinski.exceptions.NoContentException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pl.brzezinski.configuration.Configuration.*;

@Component
@Qualifier("fileService")
public class FileService implements DBService {

    @Override
    public void save(CalculationRequest request, String result) throws IOException {
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        File file = new File(PATH + FILE_NAME);
        if (!file.exists())
            createNewHistoryFile();

        if (!isFull()) {
            try {
                FileWriter fileWriter = new FileWriter(PATH + FILE_NAME, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(String.format("%s %s %s %s = %s", dateTime, request.getValueA(), request.getOperator(), request.getValueB(), result));
                bufferedWriter.newLine();
                bufferedWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            renameCurrentFile();
            createNewHistoryFile();
            save(request, result);
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
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        file.createNewFile();
    }

    @Override
    public List<HistoryResponse> results(String fileName, LocalDateTime after, LocalDateTime before) throws FileNotFoundException, NoContentException {
        List<String> records;
        try {
            records = fileReader(PATH + fileName);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File name " + fileName + " not found");
        }
        if (records.isEmpty()) throw new NoContentException("No content found in file " + fileName);
        List<HistoryResponse> historyResponses = filterWithDates(records, after, before);
        if (historyResponses.isEmpty()) throw new NoContentException("No content for given dates and times");
        return historyResponses;
    }

    private List<HistoryResponse> filterWithDates(List<String> records, LocalDateTime after, LocalDateTime before) {
        List<HistoryResponse> response = new ArrayList<>();
        for (String record : records) {
            LocalDateTime recordDate = LocalDateTime.parse(record.substring(0, 19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            if (recordDate.isAfter(after) && recordDate.isBefore(before)) {
                response.add(new HistoryResponse(
                        record.substring(0, 19),
                        record.substring(20)
                ));
            }
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

    @Override
    public List<String> allFiles() throws FileNotFoundException {
        List<String> result = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(Paths.get(PATH))) {
            result = walk.filter(Files::isRegularFile)
                    .map(Path::toString).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result.isEmpty()) throw new FileNotFoundException("No history files found");
        return result;
    }

    @Override
    public String deleteHistory() throws FileNotFoundException {
        File directory = new File(PATH);
        File[] files = directory.listFiles();
        if (files.length > 0)
            for (File file : files) {
                file.delete();
            }
        else throw new FileNotFoundException("No files to delete");
        return "History deleted";
    }
}
