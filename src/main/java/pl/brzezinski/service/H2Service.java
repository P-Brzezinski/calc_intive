package pl.brzezinski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.brzezinski.db.ResultRepository;
import pl.brzezinski.dto.CalculationRequest;
import pl.brzezinski.dto.HistoryResponse;
import pl.brzezinski.model.Result;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
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
        entity.setCompletedAt(LocalDateTime.now());
        return entity;
    }

    @Override
    public List<HistoryResponse> results(String fileName) throws FileNotFoundException {
        return null;
    }

    @Override
    public List<String> allFiles() {
        return null;
    }

    @Override
    public void deleteHistory() {
        resultRepository.deleteAll();
    }
}
