package pl.brzezinski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.server.ResponseStatusException;
import pl.brzezinski.configuration.Configuration;
import pl.brzezinski.dto.CalculationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.brzezinski.dto.HistoryResponse;
import pl.brzezinski.dto.PossibleCalculationsResponse;
import pl.brzezinski.dto.ResultResponse;
import pl.brzezinski.exceptions.*;
import pl.brzezinski.service.CalculationService;
import pl.brzezinski.service.DBService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        } catch (UnrecognizedValueException | OperatorNotFoundException | CalculationNotPossibleException | ArithmeticException | VectorException | MatrixException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request: " + e.getMessage());
        }
        dbService.save(request, result);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResultResponse("Result saved", result));
    }

    @GetMapping("/possibleCalculations")
    public ResponseEntity<List<PossibleCalculationsResponse>> listOfPossibleCalculations() {
        return ResponseEntity.status(HttpStatus.OK).body(calculationService.getListOfPossibleCalculations());
    }

    @GetMapping("/results")
    public ResponseEntity<List<HistoryResponse>> results(@RequestParam Optional<String> fileName,
                                                         @RequestParam(defaultValue = "#{T(java.time.LocalDateTime).of(2020,1,1,12,0,0)}") @DateTimeFormat(pattern = Configuration.DATE_TIME_PATTERN) LocalDateTime after,
                                                         @RequestParam(defaultValue = "#{T(java.time.LocalDateTime).now}") @DateTimeFormat(pattern = Configuration.DATE_TIME_PATTERN) LocalDateTime before) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(dbService.results(fileName, after, before));
        } catch (FileNotFoundException | NoContentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (UnsupportedOperationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<String>> files() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(dbService.allFiles());
        } catch (FileNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (UnsupportedOperationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteHistory() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(dbService.deleteHistory());
        } catch (FileNotFoundException | NoContentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
