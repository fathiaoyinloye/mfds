import com.dispensersystem.mfds.MfdsApplication;
import com.dispensersystem.mfds.dtos.request.DispenseFuelByAmountRequest;
import com.dispensersystem.mfds.exceptions.FuelDoesNotExistException;
import com.dispensersystem.mfds.services.implementations.FuelAttendantImplementation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = MfdsApplication.class)
public class FuelAttendantImplementationTest {
    @Autowired
    private FuelAttendantImplementation fuelAttendantImplementation;

    @Test
    public void testThatFuelAttendantCannotDispenseFuelThatIsNotAvailable(){
       assertThrows(FuelDoesNotExistException.class, ()-> fuelAttendantImplementation.dispenseFuelByAmount(new DispenseFuelByAmountRequest("Dieel", 2000)));

    }
}
