package com.dispensersystem.mfds.services.implementations;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.dispensersystem.mfds.data.models.Fuel;
import com.dispensersystem.mfds.data.models.Receipt;
import com.dispensersystem.mfds.data.repositories.FuelRepository;
import com.dispensersystem.mfds.dtos.request.DispenseFuelByLiterRequest;
import com.dispensersystem.mfds.dtos.request.DispenseFuelByAmountRequest;
import com.dispensersystem.mfds.dtos.response.DispenseFuelByAmountResponse;
import com.dispensersystem.mfds.dtos.response.DispenseFuelByLiterResponse;
import com.dispensersystem.mfds.exceptions.AmountLessThanPricePerLiterException;
import com.dispensersystem.mfds.exceptions.FuelDoesNotExistException;
import com.dispensersystem.mfds.exceptions.InsufficientStockException;
import com.dispensersystem.mfds.exceptions.LiterCannotBeLessThanOneException;
import com.dispensersystem.mfds.services.interfaces.FuelAttendantService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FuelAttendantImplementation implements FuelAttendantService {

    @Getter
    private final FuelRepository fuelRepository;

    @Override
    public DispenseFuelByLiterResponse dispenseFuelByLiter(DispenseFuelByLiterRequest dispenseFuelByLiterRequest) {
        Fuel fuel = findFuel(dispenseFuelByLiterRequest.getFuelName());
        validateLiter(dispenseFuelByLiterRequest.getLitersToBeBought());
        validateAvailableLiters(fuel, dispenseFuelByLiterRequest.getLitersToBeBought());
        fuel.setQuantityAvailable(fuel.getQuantityAvailable() - dispenseFuelByLiterRequest.getLitersToBeBought());
        fuelRepository.save(fuel);

        return null;
    }


    @Override
    public DispenseFuelByAmountResponse dispenseFuelByAmount(DispenseFuelByAmountRequest dispenseFuelByAmountRequest){
        Fuel fuel = findFuel(dispenseFuelByAmountRequest.getName());
        validateAmount(fuel, dispenseFuelByAmountRequest.getAmount());
        double liter = convertToLiters(fuel, dispenseFuelByAmountRequest.getAmount());
        validateAvailableLiters(fuel, liter);
        fuel.setQuantityAvailable(fuel.getQuantityAvailable() - liter);
        fuelRepository.save(fuel);

        return new DispenseFuelByAmountResponse(fuel.getName(),fuel.getPricePerLiter(), liter, dispenseFuelByAmountRequest.getAmount());
    }

    private Fuel findFuel(String name){
        Fuel fuel = fuelRepository.findByName(name);
        if (fuel == null) throw new FuelDoesNotExistException();
        return fuel;
    }

    private double convertToLiters(Fuel fuel, double amount){
        return amount / fuel.getPricePerLiter();
    }


    private void validateAmount(Fuel fuel, double amount){
       double pricePerLiter = fuel.getPricePerLiter();
        if(amount < pricePerLiter) throw new AmountLessThanPricePerLiterException();
    }

    private void validateAvailableLiters(Fuel fuel, double liters){
        double litersAvailable = fuel.getQuantityAvailable();
        if(liters > litersAvailable) throw new InsufficientStockException();
    }

    private void validateLiter(double amount){
        if(amount < 1) throw new LiterCannotBeLessThanOneException();
    }


    /*private void issueOutReceipt(double price, double pricePerLiter){
        LocalDate date = LocalDate.now();

        Receipt receipt = Receipt.builder().price(price).
    }*/
}


