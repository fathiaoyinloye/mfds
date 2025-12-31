package com.dispensersystem.mfds.controllers;


import com.dispensersystem.mfds.dtos.request.*;
import com.dispensersystem.mfds.exceptions.*;
import com.dispensersystem.mfds.services.interfaces.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/registerAdmin")
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterAdminRequest registerAdminRequest){
        try{
            return ResponseEntity.status(CREATED).body(adminService.registerAdmin(registerAdminRequest));
        }catch(AdminAlreadyExitException e){
            return ResponseEntity.badRequest().body((e.getMessage()));
        }
    }

    @PostMapping("/loginAdmin")
    public ResponseEntity<?> loginAdmin(@RequestBody LoginAdminRequest loginAdminRequest) {
        try {
          return   ResponseEntity.status(OK).body(adminService.loginAdmin(loginAdminRequest));
        } catch (AdminDoesNotExistException e) {
            return ResponseEntity.status(UNAUTHORIZED).body((e.getMessage()));
        }catch (InvalidLoginDetailsException e){
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());

        }
    }

    @PostMapping("/addFuel")
    public ResponseEntity<?> addFuel(@RequestBody AddFuelRequest addFuelRequest){
        try {
            return ResponseEntity.status(CREATED).body(adminService.addFuel(addFuelRequest));
        }catch (FuelAlreadyExistException e){
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }catch(LiterOrQuantityCannotBeLessThanOneException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/updateFuelPrice")
    public ResponseEntity<?> updateFuelPrice(@RequestBody UpdateFuelPriceRequest request){
        try{
            return ResponseEntity.status(OK).body(adminService.updateFuelPrice(request));
        }catch(FuelDoesNotExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch(PriceCannotBeLessThanOneException e){
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());

        }
    }
    @PutMapping("/restockFuel")
    public ResponseEntity<?> restockFuel(@RequestBody RestockFuelRequest request){
        try{
            return ResponseEntity.status(OK).body(adminService.restockFuel(request));
        }catch(FuelDoesNotExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch(QuantityCannotBeLessThanOneException e){
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping("/addFuelAttendant")
    public ResponseEntity<?> addFuelAttendant(@RequestBody AddFuelAttendantRequest request){
        try{
            return ResponseEntity.status(CREATED).body(adminService.addFuelAttendant(request));
        }catch(FuelAttendantAlreadyExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/removeFuelAttendant")
    public ResponseEntity<?> removeFuelAttendant(@RequestBody RemoveFuelAttendantRequest request){
        try{
            return ResponseEntity.status(OK).body(adminService.removeFuelAttendant(request));
        }catch(FuelAttendantDoesNotExistException e){
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/getAvailableFuel")
    public ResponseEntity<?> getAvailableFuel(){return ResponseEntity.status(OK).body(adminService.getAvailableFuel());

    }
}

