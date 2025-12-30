import com.dispensersystem.mfds.MfdsApplication;
import com.dispensersystem.mfds.data.repositories.FuelRepository;
import com.dispensersystem.mfds.dtos.request.AddFuelRequest;
import com.dispensersystem.mfds.dtos.request.DispenseFuelByAmountRequest;
import com.dispensersystem.mfds.dtos.request.DispenseFuelByLiterRequest;
import com.dispensersystem.mfds.exceptions.AmountLessThanPricePerLiterException;
import com.dispensersystem.mfds.exceptions.FuelDoesNotExistException;
import com.dispensersystem.mfds.exceptions.InsufficientStockException;
import com.dispensersystem.mfds.exceptions.LiterCannotBeLessThanOneException;
import com.dispensersystem.mfds.services.implementations.AdminServiceImplemtation;
import com.dispensersystem.mfds.services.implementations.FuelAttendantImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = MfdsApplication.class)
public class FuelAttendantImplementationTest {
    @Autowired
    private FuelAttendantImplementation fuelAttendantImplementation;
    @Autowired
    private AdminServiceImplemtation adminServiceImplemtation;
    @Autowired
    private FuelRepository fuelRepository;

    @BeforeEach
    void setUp(){
        fuelRepository.deleteAll();
    }


    @Test
    public void testThatFuelAttendantCannotDispenseFuelThatIsNotAvailable(){
       assertThrows(FuelDoesNotExistException.class, ()-> fuelAttendantImplementation.dispenseFuelByAmount(new DispenseFuelByAmountRequest("Dieel", 2000)));

    }

    @Test
    public void testThatAdminAddFuel_FuelAttendantCantDispenseAvailableFuel_FuelQuatityDecreased(){
        adminServiceImplemtation.addFuel(new AddFuelRequest("Kerosene", 1000, 5));
        assertEquals(5.0, fuelRepository.findByName("Kerosene").getQuantityAvailable());
        assertEquals(1, fuelRepository.count());
        fuelAttendantImplementation.dispenseFuelByAmount(new DispenseFuelByAmountRequest("Kerosene", 2000));
        assertEquals(3.0, fuelRepository.findByName("Kerosene").getQuantityAvailable());

    }

    @Test
    public void testThatAdminAddFuel_FuelAttendantCanDispenseAvailableFuel_FuelQuatityDecrease(){
        adminServiceImplemtation.addFuel(new AddFuelRequest("Kerosene", 1000, 5));
        assertEquals(5.0, fuelRepository.findByName("Kerosene").getQuantityAvailable());
        assertEquals(1, fuelRepository.count());
        fuelAttendantImplementation.dispenseFuelByAmount(new DispenseFuelByAmountRequest("Kerosene", 2000));
        assertEquals(3.0, fuelRepository.findByName("Kerosene").getQuantityAvailable());

    }
    @Test
    public void testThatAdminAddFuel_FuelAttendantCanDispenseAvailableFueByLiter_FuelQuatityDecrease(){
        adminServiceImplemtation.addFuel(new AddFuelRequest("Kerosene", 1000, 5));
        assertEquals(5.0, fuelRepository.findByName("Kerosene").getQuantityAvailable());
        assertEquals(1, fuelRepository.count());
        fuelAttendantImplementation.dispenseFuelBYLiter(new DispenseFuelByLiterRequest("Kerosene", 2.0));
        assertEquals(3.0, fuelRepository.findByName("Kerosene").getQuantityAvailable());

    }
    @Test
    public void testThatAdminAddFuel_FuelAttendantCantDispenseAvailableFuelByQuntityLessThanALiterPrice_throwException(){
        adminServiceImplemtation.addFuel(new AddFuelRequest("Kerosene", 1000, 5));
        assertEquals(5.0, fuelRepository.findByName("Kerosene").getQuantityAvailable());
        assertEquals(1, fuelRepository.count());
        assertThrows(AmountLessThanPricePerLiterException.class, ()->
                fuelAttendantImplementation.dispenseFuelByAmount(new DispenseFuelByAmountRequest("Kerosene", 500)));
        assertEquals(5.0, fuelRepository.findByName("Kerosene").getQuantityAvailable());

    }

    @Test
    public void testThatAdminAddFuel_FuelAttendantCantDispenseAvailableFueByLiterLessThanOne(){
        adminServiceImplemtation.addFuel(new AddFuelRequest("Kerosene", 1000, 5));
        assertEquals(5.0, fuelRepository.findByName("Kerosene").getQuantityAvailable());
        assertEquals(1, fuelRepository.count());
        assertThrows(LiterCannotBeLessThanOneException.class, () ->
        fuelAttendantImplementation.dispenseFuelBYLiter(new DispenseFuelByLiterRequest("Kerosene", 0.0)));
        assertEquals(5.0, fuelRepository.findByName("Kerosene").getQuantityAvailable());

    }
    @Test
    public void testThatAdminAddFuel_FuelAttendantCantDispenseAvailableFuelMoreThanQuantityOwndeByStation(){
        adminServiceImplemtation.addFuel(new AddFuelRequest("Kerosene", 1000, 3));
        assertEquals(3.0, fuelRepository.findByName("Kerosene").getQuantityAvailable());
        assertEquals(1, fuelRepository.count());
        assertThrows(InsufficientStockException.class, () ->
                fuelAttendantImplementation.dispenseFuelBYLiter(new DispenseFuelByLiterRequest("Kerosene", 5.0)));
        assertEquals(3.0, fuelRepository.findByName("Kerosene").getQuantityAvailable());

    }


}
