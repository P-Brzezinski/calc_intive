package pl.brzezinski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.brzezinski.config.Configuration;
import pl.brzezinski.dto.CalculationRequest;
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
import pl.brzezinski.service.DBService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AppController {

    @Autowired
    private final CalculationService calculationService;

    @Autowired
    @Qualifier(Configuration.DB)
    private DBService dbService;

    public AppController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping
    public ResponseEntity<ResultResponse> doCalculation(@RequestBody CalculationRequest request) throws IOException {
        String result;
        try {
            result = calculationService.doCalculation(request);
        } catch (UnrecognizedValueException | OperatorNotFoundException | CalculationNotPossibleException | ArithmeticException | VectorException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultResponse("No result", e.getMessage()));
        }
        dbService.save(request, result);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResultResponse("Result saved", result));
    }

    @GetMapping("/possibleCalculations")
    public ResponseEntity<List<PossibleCalculationsResponse>> listOfPossibleCalculations() {
        return ResponseEntity.status(HttpStatus.OK).body(calculationService.getListOfPossibleCalculations());
    }

    @GetMapping("/results")
    public ResponseEntity<List<HistoryResponse>> results(@RequestParam(defaultValue = Configuration.FILE_NAME) String fileName) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(dbService.results(fileName));
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<String>> files() {
        return ResponseEntity.status(HttpStatus.OK).body(dbService.allFiles());
    }

    @DeleteMapping
    public ResponseEntity<String> deleteHistory() {
        try {
            dbService.deleteHistory();
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No history files to delete");
        }
        return ResponseEntity.status(HttpStatus.OK).body("All history files deleted");
    }
}
