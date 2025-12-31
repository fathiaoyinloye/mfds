package com.dispensersystem.mfds.controllers;

import com.dispensersystem.mfds.dtos.request.DispenseFuelByAmountRequest;
import com.dispensersystem.mfds.dtos.request.DispenseFuelByLiterRequest;
import com.dispensersystem.mfds.exceptions.AmountLessThanPricePerLiterException;
import com.dispensersystem.mfds.exceptions.FuelDoesNotExistException;
import com.dispensersystem.mfds.exceptions.InsufficientStockException;
import com.dispensersystem.mfds.exceptions.LiterCannotBeLessThanOneException;
import com.dispensersystem.mfds.services.interfaces.FuelAttendantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/fuelAttendant")
public class FuelAttendantController {

    private final FuelAttendantService fuelAttendantService;

    @PutMapping("/dispenseFuelByLiter")
    public ResponseEntity<?> dispenseFuelBYLiter(@RequestBody DispenseFuelByLiterRequest request){
        try{
            return ResponseEntity.status(OK).body(fuelAttendantService.dispenseFuelByLiter(request));
        }catch (FuelDoesNotExistException e){
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }catch(LiterCannotBeLessThanOneException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch(InsufficientStockException e){
            return ResponseEntity.status(UNPROCESSABLE_ENTITY).body(e.getMessage());

        }
    }
    @PutMapping("/dispenseFuelByAmount")
    public ResponseEntity<?> dispenseFuelByAmount(@RequestBody DispenseFuelByAmountRequest request){
        try{
            return ResponseEntity.status(OK).body(fuelAttendantService.dispenseFuelByAmount(request));
        }catch (FuelDoesNotExistException e){
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }catch(AmountLessThanPricePerLiterException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch(InsufficientStockException e){
            return ResponseEntity.status(UNPROCESSABLE_ENTITY).body(e.getMessage());

        }
    }

}
