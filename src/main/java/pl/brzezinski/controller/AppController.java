package pl.brzezinski.controller;

import pl.brzezinski.dto.CalculationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.brzezinski.dto.PossibleCalculationsResponse;
import pl.brzezinski.exceptions.CalculationNotPossibleException;
import pl.brzezinski.exceptions.OperatorNotFoundException;
import pl.brzezinski.exceptions.UnrecognizedValueException;
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
    public ResponseEntity<String> doCalculation(@RequestBody CalculationRequest calculationRequest)  {
        try {
            String result = calculationService.doCalculation(calculationRequest);
            // for testing purposes
            System.out.println("Result: " + result);
        } catch (UnrecognizedValueException | OperatorNotFoundException | CalculationNotPossibleException | ArithmeticException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Calculation result saved to file", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PossibleCalculationsResponse>> getListOfPossibleCalculations(){
        return ResponseEntity.status(HttpStatus.OK).body(calculationService.getListOfPossibleCalculations());
    }
}
