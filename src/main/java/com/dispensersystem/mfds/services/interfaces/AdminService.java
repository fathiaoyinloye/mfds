package com.dispensersystem.mfds.services.interfaces;

import com.dispensersystem.mfds.dtos.request.*;
import com.dispensersystem.mfds.dtos.response.*;

import java.util.List;

public interface AdminService {
    RegisterAdminResponse registerAdmin(RegisterAdminRequest adminRequest);
    LoginAdminResponse  loginAdmin(LoginAdminRequest loginAdminRequest);
    AddFuelResponse addFuel(AddFuelRequest addFuelRequest);
    UpdateFuelPriceResponse updateFuelPrice(UpdateFuelPriceRequest updateFuelPriceReuest);
    RestockFuelResponse restockFuel(RestockFuelRequest restockFuelRequest);
   List<GetAvailableFuelResponse > getAvailableFuel();
   AddFuelAttendantResponse addFuellAttendant(AddFuelAttendantRequest addFuelAttendentReuest);
    RemoveFuelAttendantResponse removeFuellAttendant(RemoveFuelAttendantRequest removeFuelAttendentRequest);

}
