package com.dispensersystem.mfds.services.implementations;

import com.dispensersystem.mfds.data.models.Admin;
import com.dispensersystem.mfds.data.models.Fuel;
import com.dispensersystem.mfds.data.models.FuelAttendant;
import com.dispensersystem.mfds.data.repositories.AdminRepository;
import com.dispensersystem.mfds.data.repositories.FuelAttendantRepository;
import com.dispensersystem.mfds.data.repositories.FuelRepository;
import com.dispensersystem.mfds.dtos.request.*;
import com.dispensersystem.mfds.dtos.response.*;
import com.dispensersystem.mfds.exceptions.*;
import com.dispensersystem.mfds.services.interfaces.AdminService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class AdminServiceImplemtation implements AdminService {

    @Getter
    private final AdminRepository adminRepository;
    private final FuelRepository fuelRepository;
    private final FuelAttendantRepository fuelAttendantRepository;

    @Override
    public RegisterAdminResponse registerAdmin(RegisterAdminRequest adminRequest) {
        if(adminRepository.findAdminByUsername(adminRequest.getUsername() ) != null) throw new AdminAlreadyExitException();
        Admin admin = Admin.builder().name(adminRequest.getName()).username(adminRequest.getUsername()).password(adminRequest.getPassword()).build();
        admin =  adminRepository.save(admin);
        return  new RegisterAdminResponse(admin.getName(), admin.getUsername(), admin.getPassword());
    }

    @Override
    public LoginAdminResponse loginAdmin(LoginAdminRequest loginAdminRequest) {
        Admin admin = adminRepository.findAdminByUsername(loginAdminRequest.getUsername());
        System.out.print(admin);
        if (admin == null) throw new AdminDoesNotExistException();
        validateLogin(admin, loginAdminRequest);
        return new LoginAdminResponse(admin.getName());

    }


    private void validateLogin(Admin admin, LoginAdminRequest loginAdminRequest){
        if(!admin.getUsername().equals(loginAdminRequest.getUsername()) ||
                !admin.getPassword().equals(loginAdminRequest.getPassword())) throw  new InvalidLoginDetailsException();


    }
    @Override
    public AddFuelResponse addFuel(AddFuelRequest addFuelRequest) {
        if(fuelRepository.findByName(addFuelRequest.getName()) != null) throw new FuelAlreadyExistException();
        validatePriceAndQuantity(addFuelRequest.getPricePerLiter(), addFuelRequest.getQuantityToBeStocked());
        Fuel fuel = Fuel.builder().name(addFuelRequest.getName()).pricePerLiter(addFuelRequest.getPricePerLiter()).quantityAvailable(addFuelRequest.getQuantityToBeStocked()).build();
        fuelRepository.save(fuel);
        return new AddFuelResponse(fuel.getName(), fuel.getPricePerLiter(), fuel.getQuantityAvailable());
    }

    @Override
    public UpdateFuelPriceResponse updateFuelPrice(UpdateFuelPriceRequest updateFuelPriceRequest) {
        Fuel fuel = findFuel(updateFuelPriceRequest.getFuelName());
        fuel.setPricePerLiter(updateFuelPriceRequest.getNewPrice());
        fuelRepository.save(fuel);
        return new UpdateFuelPriceResponse(fuel.getName(), fuel.getPricePerLiter());
    }

    @Override
    public RestockFuelResponse restockFuel(RestockFuelRequest restockFuelRequest) {
        Fuel fuel = findFuel(restockFuelRequest.getFuelName());
        fuel.setQuantityAvailable(fuel.getQuantityAvailable() + restockFuelRequest.getQuantityToBeAdded());
        fuelRepository.save(fuel);
        return new RestockFuelResponse(fuel.getName(), fuel.getQuantityAvailable());
    }

    private void validatePriceAndQuantity(double pricePerLiter, double quantity){
        if(pricePerLiter < 1 || quantity < 1) throw new LiterOrQuantityCannotBeLessThanOneException();
    }

    @Override
    public List<GetAvailableFuelResponse> getAvailableFuel() {
        List<Fuel> availableFuels = fuelRepository.findAll();
        List<GetAvailableFuelResponse> responses = new ArrayList<>();
        for(int count = 0; count < availableFuels.size(); count++){
            responses.add(new GetAvailableFuelResponse(
                    availableFuels.get(count).getFuelId(),
                    availableFuels.get(count).getName(),
                    availableFuels.get(count).getPricePerLiter(),
                    availableFuels.get(count).getQuantityAvailable()));

        }
        return responses;
    }

    @Override
    public AddFuelAttendantResponse addFuellAttendant(AddFuelAttendantRequest addFuelAttendentRequest) {
        FuelAttendant fuelAttendant = FuelAttendant.builder().name(addFuelAttendentRequest.getName()).build();
        fuelAttendantRepository.save(fuelAttendant);
        return new AddFuelAttendantResponse(fuelAttendant.getFuelAttendantId(),fuelAttendant.getName());
    }

    @Override
    public RemoveFuelAttendantResponse removeFuellAttendant(RemoveFuelAttendantRequest removeFuelAttendentRequest) {
        FuelAttendant attendant = findFuelAttendant(removeFuelAttendentRequest.getName());
        fuelAttendantRepository.delete(attendant);
        return new RemoveFuelAttendantResponse(attendant.getName());
    }

    private Fuel findFuel(String fuelName){
        Fuel fuel = fuelRepository.findByName(fuelName);
        if(fuel == null) throw new FuelDoesNotExistException();
        return fuel;
    }

    private FuelAttendant findFuelAttendant(String name){
        FuelAttendant attendant =  fuelAttendantRepository.findByName(name);
        if(attendant == null) throw new FuelAttendantDoesNotExistException();
        return attendant;
    }





}


