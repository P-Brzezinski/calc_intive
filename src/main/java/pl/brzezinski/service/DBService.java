package pl.brzezinski.service;

import org.springframework.stereotype.Component;
import pl.brzezinski.dto.CalculationRequest;
import pl.brzezinski.dto.HistoryResponse;
import pl.brzezinski.exceptions.NoContentException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public interface DBService {

    void save(CalculationRequest request, String result) throws IOException;

    List<HistoryResponse> results(Optional<String> fileName, LocalDateTime after, LocalDateTime before) throws FileNotFoundException, NoContentException;

    List<String> allFiles() throws FileNotFoundException;

    String deleteHistory() throws FileNotFoundException, NoContentException;
}
