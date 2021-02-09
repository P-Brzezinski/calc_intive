package pl.brzezinski.controller;

import pl.brzezinski.dto.CalculationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.brzezinski.exceptions.OperatorNotFoundException;
import pl.brzezinski.exceptions.UnrecognizedValueException;
import pl.brzezinski.service.CalculationService;

@RestController
@RequestMapping("/api")
public class AppController {

    private final CalculationService calculationService;

    @Autowired
    public AppController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> makeCalculation(@RequestBody CalculationRequest calculationRequest) throws OperatorNotFoundException {
        try {
            calculationService.makeCalc(calculationRequest);
        } catch (UnrecognizedValueException | OperatorNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Calculation result saved to file", HttpStatus.CREATED);
    }
}
