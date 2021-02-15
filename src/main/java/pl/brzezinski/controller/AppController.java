package pl.brzezinski.controller;

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
import pl.brzezinski.service.FileService;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AppController {

    private final CalculationService calculationService;
    private final FileService fileService;

    public AppController(CalculationService calculationService, FileService fileService) {
        this.calculationService = calculationService;
        this.fileService = fileService;
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

    @GetMapping("/results")
    public ResponseEntity<List<HistoryResponse>> results(@RequestParam(defaultValue = Configuration.FILE_NAME) String fileName) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(fileService.results(fileName));
        } catch (FileNotFoundException e) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<String>> files() {
        return ResponseEntity.status(HttpStatus.OK).body(fileService.allFiles());
    }

    @DeleteMapping
    public ResponseEntity<String> deleteHistory() {
        try {
            fileService.deleteHistory();
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No history files to delete");
        }
        return ResponseEntity.status(HttpStatus.OK).body("All history files deleted");
    }
}
