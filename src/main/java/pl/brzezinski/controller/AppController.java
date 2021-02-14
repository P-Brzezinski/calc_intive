package pl.brzezinski.controller;

import pl.brzezinski.dto.CalculationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.brzezinski.dto.HistoryResponse;
import pl.brzezinski.dto.PossibleCalculationsResponse;
import pl.brzezinski.dto.ResultResponse;
import pl.brzezinski.exceptions.CalculationNotPossibleException;
import pl.brzezinski.exceptions.OperatorNotFoundException;
import pl.brzezinski.exceptions.UnrecognizedValueException;
import pl.brzezinski.exceptions.VectorException;
import pl.brzezinski.service.CalculationService;
import pl.brzezinski.service.HistoryService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppController {

    private final CalculationService calculationService;
    private final HistoryService historyService;

    @Autowired
    public AppController(CalculationService calculationService, HistoryService historyService) {
        this.calculationService = calculationService;
        this.historyService = historyService;
    }

    @PostMapping
    public ResponseEntity<ResultResponse> doCalculation(@RequestBody CalculationRequest calculationRequest) {
        ResultResponse resultResponse;
        try {
            resultResponse = calculationService.doCalculation(calculationRequest);
        } catch (UnrecognizedValueException | OperatorNotFoundException | CalculationNotPossibleException | ArithmeticException | VectorException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultResponse("No result", e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(resultResponse);
    }

    @GetMapping("/possibleCalculations")
    public ResponseEntity<List<PossibleCalculationsResponse>> listOfPossibleCalculations() {
        return ResponseEntity.status(HttpStatus.OK).body(calculationService.getListOfPossibleCalculations());
    }

    @GetMapping("/lastResults")
    public ResponseEntity<List<HistoryResponse>> lastResults() {
        return ResponseEntity.status(HttpStatus.OK).body(historyService.lastCalculations());
    }
}
