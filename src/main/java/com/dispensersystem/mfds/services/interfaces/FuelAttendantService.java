package com.dispensersystem.mfds.services.interfaces;

import com.dispensersystem.mfds.dtos.request.DispenseFuelByLiterRequest;
import com.dispensersystem.mfds.dtos.request.DispenseFuelByAmountRequest;
import com.dispensersystem.mfds.dtos.response.DispenseFuelByAmountResponse;
import com.dispensersystem.mfds.dtos.response.DispenseFuelByLiterResponse;

public interface FuelAttendantService {
   DispenseFuelByLiterResponse dispenseFuelByLiter(DispenseFuelByLiterRequest dispenseFuelBYLiterRequest);
   DispenseFuelByAmountResponse dispenseFuelByAmount(DispenseFuelByAmountRequest dispenseFuelByAmountRequest);
}
