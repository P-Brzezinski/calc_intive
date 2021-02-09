package pl.brzezinski.controller;

import pl.brzezinski.dto.CalculationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.brzezinski.dto.PossibleCalculationsResponse;
import pl.brzezinski.dto.Result;
import pl.brzezinski.exceptions.CalculationNotPossibleException;
import pl.brzezinski.exceptions.OperatorNotFoundException;
import pl.brzezinski.exceptions.UnrecognizedValueException;
import pl.brzezinski.exceptions.VectorException;
import pl.brzezinski.service.CalculationService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppController {

    private final CalculationService calculationService;

    @Autowired
    public AppController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping
    public ResponseEntity<Result> doCalculation(@RequestBody CalculationRequest calculationRequest) {
        Result result;
        try {
            result = calculationService.doCalculation(calculationRequest);
        } catch (UnrecognizedValueException | OperatorNotFoundException | CalculationNotPossibleException | ArithmeticException | VectorException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result("No result", e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<PossibleCalculationsResponse>> getListOfPossibleCalculations() {
        return ResponseEntity.status(HttpStatus.OK).body(calculationService.getListOfPossibleCalculations());
    }
}
