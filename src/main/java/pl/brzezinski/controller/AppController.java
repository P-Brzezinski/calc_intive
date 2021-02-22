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
import pl.brzezinski.exceptions.*;
import pl.brzezinski.service.CalculationService;
import pl.brzezinski.service.DBService;
import pl.brzezinski.service.FileService;
import pl.brzezinski.service.H2Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
        } catch (UnrecognizedValueException | OperatorNotFoundException | CalculationNotPossibleException | ArithmeticException | VectorException | MatrixException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultResponse("Calculation not saved", e.getMessage()));
        }
        dbService.save(request, result);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResultResponse("Result saved", result));
    }

    @GetMapping("/possibleCalculations")
    public ResponseEntity<List<PossibleCalculationsResponse>> listOfPossibleCalculations() {
        return ResponseEntity.status(HttpStatus.OK).body(calculationService.getListOfPossibleCalculations());
    }

    @GetMapping("/results")
    public ResponseEntity<?> results(@RequestParam(defaultValue = Configuration.FILE_NAME) String fileName,
                                     @RequestParam(defaultValue = "0") Integer pageNo,
                                     @RequestParam(defaultValue = "5") Integer pageSize) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(dbService.results(fileName, pageNo, pageSize));
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/files")
    public ResponseEntity<?> files() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(dbService.allFiles());
        }catch (UnsupportedOperationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteHistory() {
        try {
            dbService.deleteHistory();
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No history files to delete");
        }
        return ResponseEntity.status(HttpStatus.OK).body("History deleted");
    }
}
