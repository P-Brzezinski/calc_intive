package pl.brzezinski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.brzezinski.exceptions.NoContentException;
import pl.brzezinski.repository.ResultRepository;
import pl.brzezinski.dto.CalculationRequest;
import pl.brzezinski.dto.HistoryResponse;
import pl.brzezinski.model.Result;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Qualifier("H2Service")
public class H2Service implements DBService {

    private final ResultRepository resultRepository;

    @Autowired
    public H2Service(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    @Transactional
    public void save(CalculationRequest request, String result) {
        Result resultEntity = mapToResultEntity(request, result);
        resultRepository.save(resultEntity);
    }

    private Result mapToResultEntity(CalculationRequest request, String result) {
        Result entity = new Result();
        entity.setValueA(request.getValueA());
        entity.setOperator(request.getOperator());
        entity.setValueB(request.getValueB());
        entity.setResult(result);
        entity.setDateTime(LocalDateTime.now());
        return entity;
    }

    @Override
    public List<HistoryResponse> results(String fileName, LocalDateTime after, LocalDateTime before) throws NoContentException {
        List<HistoryResponse> historyResponses = resultRepository.findAllByDateTimeAfterAndDateTimeBefore(after, before)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        if (historyResponses.isEmpty()) {
            throw new NoContentException("No content to delete");
        }
        return historyResponses;
    }

    private HistoryResponse mapToDto(Result result) {
        HistoryResponse response = new HistoryResponse();
        response.setDate(result.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        response.setCalculation(String.format("%s %s %s = %s",
                result.getValueA(),
                result.getOperator(),
                result.getValueB(),
                result.getResult()));
        return response;
    }

    @Override
    public List<String> allFiles() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Operation unsupported for H2 Database");
    }

    @Override
    @Transactional
    public String deleteHistory() throws NoContentException {
        List<Result> all = resultRepository.findAll();
        if (all.isEmpty()) {
            throw new NoContentException("No content to delete");
        }
        resultRepository.deleteAll();
        return "History deleted";
    }
}
