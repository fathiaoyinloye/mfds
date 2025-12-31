import com.dispensersystem.mfds.MfdsApplication;
import com.dispensersystem.mfds.data.repositories.AdminRepository;
import com.dispensersystem.mfds.data.repositories.FuelAttendantRepository;
import com.dispensersystem.mfds.data.repositories.FuelRepository;
import com.dispensersystem.mfds.dtos.request.*;
import com.dispensersystem.mfds.dtos.response.*;
import com.dispensersystem.mfds.exceptions.*;
import com.dispensersystem.mfds.services.implementations.AdminServiceImplemtation;
import com.dispensersystem.mfds.services.implementations.FuelAttendantImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MfdsApplication.class)
public class AdminImplTest {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private FuelRepository fuelRepository;
    @Autowired
    private AdminServiceImplemtation adminServiceImplemtation;
    @Autowired
    private FuelAttendantRepository fuelAttendantRepository;
    @Autowired
    private FuelAttendantImplementation fuelAttendantImplementation;


    @BeforeEach
    void setUp() {

    adminRepository.deleteAll();
    fuelRepository.deleteAll();
    fuelAttendantRepository.deleteAll();
    }

    @Test
    public void testThatAnAdminCanBeRegistered() {
        RegisterAdminRequest request = new RegisterAdminRequest("Fathia", "omotemmy", "123");
        RegisterAdminResponse response = adminServiceImplemtation.registerAdmin(request);
        assertEquals("Fathia", response.getName());
        assertEquals("omotemmy", response.getUsername());
        assertEquals(1, adminRepository.count());
    }

    @Test
    public void testThatAdminResgisteredTwice_throwsAdminAlreadyExistException(){
        RegisterAdminRequest request = new RegisterAdminRequest("Fathia", "omotemmy", "123");
        adminServiceImplemtation.registerAdmin(request);
        RegisterAdminRequest request2 = new RegisterAdminRequest("Fathia", "omotemmy", "123");
        assertThrows(AdminAlreadyExitException.class , ()->  adminServiceImplemtation.registerAdmin(request));



    }
    @Test
    public void testThatAdminResgistered_AdminCanLogin(){
        RegisterAdminRequest request = new RegisterAdminRequest("Fathia", "omotemmy", "123");
        adminServiceImplemtation.registerAdmin(request);
        LoginAdminRequest request1 = new LoginAdminRequest("omotemmy", "123");
        LoginAdminResponse response = adminServiceImplemtation.loginAdmin(request1);
        assertEquals("Fathia", response.getName());



    }

    @Test
    public void testThatAdminResgistered_AdminCLoginWithWrongPassword_throwsInvalidLoginDetails(){
        RegisterAdminRequest request = new RegisterAdminRequest("Fathia", "omotemmy", "123");
        adminServiceImplemtation.registerAdmin(request);
        LoginAdminRequest request1 = new LoginAdminRequest("omotemmy", "13");
        assertThrows(InvalidLoginDetailsException.class, ()-> adminServiceImplemtation.loginAdmin(request1));

    }
    @Test
    public void AdminLoginWithoutRegistering_throwsAdminDoesNotExistException(){
        LoginAdminRequest request1 = new LoginAdminRequest("omotemmy", "13");
        assertThrows(AdminDoesNotExistException.class, ()-> adminServiceImplemtation.loginAdmin(request1));

    }

    @Test
    public void Admin_Registered_adminCanAddFuel(){
        RegisterAdminRequest request = new RegisterAdminRequest("Fathia", "omotemmy", "123");
        adminServiceImplemtation.registerAdmin(request);
        AddFuelRequest fuelRequest = new AddFuelRequest("Diesel", 40, 90);
        AddFuelResponse response = adminServiceImplemtation.addFuel(fuelRequest);
        assertEquals(1, fuelRepository.count());
        assertEquals("Diesel", response.getName());
        assertEquals(40, response.getPricePerLiter());

    }

    @Test
    public void Admin_Registered_AdminCanAddSameFuelTwice_throwFuelAlreadyExistException(){
        RegisterAdminRequest request = new RegisterAdminRequest("Fathia", "omotemmy", "123");
        adminServiceImplemtation.registerAdmin(request);
        AddFuelRequest fuelRequest = new AddFuelRequest("Diesel", 40, 90);
        AddFuelResponse response = adminServiceImplemtation.addFuel(fuelRequest);
        assertEquals(1, fuelRepository.count());
        assertEquals("Diesel", response.getName());
        assertEquals(40, response.getPricePerLiter());
        assertThrows(FuelAlreadyExistException.class, ()-> adminServiceImplemtation.addFuel(fuelRequest));

    }

    @Test
    public void Admin_Registered_AdminCanAddSFuel_AdminCanUpdateFuelPrice(){
        RegisterAdminRequest request = new RegisterAdminRequest("Fathia", "omotemmy", "123");
        adminServiceImplemtation.registerAdmin(request);
        AddFuelRequest fuelRequest = new AddFuelRequest("Diesel", 40, 90);
        AddFuelResponse response = adminServiceImplemtation.addFuel(fuelRequest);
        assertEquals(1, fuelRepository.count());
        assertEquals("Diesel", response.getName());
        assertEquals(40, response.getPricePerLiter());
        UpdateFuelPriceResponse priceResponse = adminServiceImplemtation.updateFuelPrice(new UpdateFuelPriceRequest("Diesel", 30));
        assertEquals(30.0, priceResponse.getNewPrice());
    }

    @Test
    public void Admin_Registered__AdminUpdateFuelPriceThatDoeNotExist_ThrowFuelDoesNotExistException(){
        RegisterAdminRequest request = new RegisterAdminRequest("Fathia", "omotemmy", "123");
        adminServiceImplemtation.registerAdmin(request);
        assertEquals(0, fuelRepository.count());
        assertThrows(FuelDoesNotExistException.class,()-> adminServiceImplemtation.updateFuelPrice(new UpdateFuelPriceRequest("Diesel", 30)));
    }


    @Test
    public void Admin_Registered_AdminCanAddSFuel_AdminCanRestockFuel(){
        RegisterAdminRequest request = new RegisterAdminRequest("Fathia", "omotemmy", "123");
        adminServiceImplemtation.registerAdmin(request);
        AddFuelRequest fuelRequest = new AddFuelRequest("Diesel", 40, 90);
        AddFuelResponse response = adminServiceImplemtation.addFuel(fuelRequest);
        assertEquals(1, fuelRepository.count());
        assertEquals("Diesel", response.getName());
        assertEquals(40, response.getPricePerLiter());
        RestockFuelResponse quantityResponse = adminServiceImplemtation.restockFuel(new RestockFuelRequest("Diesel", 30));
        assertEquals(120.0, quantityResponse.getNewQuantity());
    }
    @Test
    public void Admin_Registered__AdminRestockFuelThatDoeNotExist_ThrowFuelDoesNotExistException(){
        RegisterAdminRequest request = new RegisterAdminRequest("Fathia", "omotemmy", "123");
        adminServiceImplemtation.registerAdmin(request);
        assertEquals(0, fuelRepository.count());
        assertThrows(FuelDoesNotExistException.class,()-> adminServiceImplemtation.restockFuel(new RestockFuelRequest("Diesel", 30)));
    }

    @Test
    public void Admin_Registered_AdminCanAddFuelAttendant(){
        RegisterAdminRequest request = new RegisterAdminRequest("Fathia", "omotemmy", "123");
        adminServiceImplemtation.registerAdmin(request);
        AddFuelAttendantRequest AttendantRequest = new AddFuelAttendantRequest("Tolu");
        AddFuelAttendantResponse response = adminServiceImplemtation.addFuelAttendant(AttendantRequest);
        assertEquals(1, fuelAttendantRepository.count());
        assertEquals("Tolu", response.getName());
        assertNotEquals(0, response.getFuelAttendantId());

    }

    @Test
    public void Admin_Registered_AdminAddedFuelAttendantTolu_AdminRemoveTolu(){
        RegisterAdminRequest request = new RegisterAdminRequest("Fathia", "omotemmy", "123");
        adminServiceImplemtation.registerAdmin(request);
        AddFuelAttendantRequest AttendantRequest = new AddFuelAttendantRequest("Tolu");
        AddFuelAttendantResponse response = adminServiceImplemtation.addFuelAttendant(AttendantRequest);
        assertEquals(1, fuelAttendantRepository.count());
        assertEquals("Tolu", response.getName());
        adminServiceImplemtation.removeFuelAttendant(new RemoveFuelAttendantRequest("Tolu"));
        assertEquals(0, fuelAttendantRepository.count());

    }


}
