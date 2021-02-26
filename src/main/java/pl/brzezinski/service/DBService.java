package pl.brzezinski.service;

import org.springframework.stereotype.Component;
import pl.brzezinski.dto.CalculationRequest;
import pl.brzezinski.dto.HistoryResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
public interface DBService {

    void save(CalculationRequest request, String result) throws IOException;

    List<HistoryResponse> results(String fileName, LocalDateTime after, LocalDateTime before) throws FileNotFoundException;

    List<String> allFiles();

    void deleteHistory() throws FileNotFoundException;
}
