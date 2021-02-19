package pl.brzezinski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.brzezinski.db.ResultRepository;
import pl.brzezinski.dto.CalculationRequest;
import pl.brzezinski.model.Result;

import java.time.LocalDateTime;

@Service
public class H2DBService {

    private ResultRepository resultRepository;

    @Autowired
    public H2DBService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

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
}
