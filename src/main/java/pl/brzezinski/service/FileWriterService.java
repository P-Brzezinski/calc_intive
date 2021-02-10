package pl.brzezinski.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class FileWriterService {

    private static final String PATH = "./calculation_sheets/";
    private static final String DEFAULT_FILE_NAME = "calculations_history.txt";

    public void createHistoryFile() throws IOException {
        File file = new File(PATH + DEFAULT_FILE_NAME);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if (file.createNewFile()){
            System.out.println("File created " + file.getName());
        }else {
            System.out.println("File already exists.");
            throw new IOException("File already exists");
        }
    }

}
