package pl.brzezinski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brzezinski.dto.HistoryResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryService {

    @Autowired
    private final FileService fileService;

    public HistoryService(FileService fileService) {
        this.fileService = fileService;
    }

    public List<HistoryResponse> lastCalculations() {
        List<String> records = fileService.fileReader();
        List<HistoryResponse> response = new ArrayList<>();
        for (String record : records) {
            response.add(new HistoryResponse(
                    record.substring(0, 19),
                    record.substring(20)
            ));
        }
        return response;
    }
}

