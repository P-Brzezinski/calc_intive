package pl.brzezinski.controller;

import pl.brzezinski.dto.CalculationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<String> makeCalculation(@RequestBody CalculationRequest calculationRequest){
        calculationService.doMatch(calculationRequest);
        return new ResponseEntity<>("Calculation result saved to file", HttpStatus.CREATED);
    }
}
