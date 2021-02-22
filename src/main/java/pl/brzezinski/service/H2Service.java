package pl.brzezinski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.brzezinski.repository.ResultRepository;
import pl.brzezinski.dto.CalculationRequest;
import pl.brzezinski.dto.HistoryResponse;
import pl.brzezinski.model.Result;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Qualifier("H2Service")
public class H2Service implements DBService {

    private final ResultRepository resultRepository;

    @Autowired
    public H2Service(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

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
    public List<HistoryResponse> results(String fileName, Integer pageNo, Integer pageSize) {
        List<Result> all = resultRepository.findAll();
        List<HistoryResponse> historyResponses = new ArrayList<>();
        for (Result result : all) {
            historyResponses.add(mapToHistoryResponse(result));
        }
        return historyResponses;
    }

    private HistoryResponse mapToHistoryResponse(Result result) {
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
    public List<String> allFiles() throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Operation unsupported for H2 Database");
    }

    @Override
    public void deleteHistory() {
        resultRepository.deleteAll();
    }
}
