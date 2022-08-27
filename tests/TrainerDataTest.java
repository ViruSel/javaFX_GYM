import database.TrainerData;
import org.junit.jupiter.api.Test;

import java.sql.Time;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrainerDataTest
{
    /**
     * test trainerData creation
     */
    @Test
    public void trainerData()
    {
        Time activity = new Time(10);
        TrainerData trainerData = new TrainerData("NikoB","GYM","","","",activity);
        assertEquals("NikoB", trainerData.getUsername());
        assertEquals("GYM", trainerData.getSpecialization());
        assertEquals("", trainerData.getCustomer1());
        assertEquals("", trainerData.getCustomer2());
        assertEquals("", trainerData.getCustomer3());
        assertEquals(activity, trainerData.getActivity());
    }

    /**
     * test trainerData set Specialization
     */
    @Test
    public void trainerDataSpecialization()
    {
        Time activity = new Time(10);
        TrainerData trainerData = new TrainerData("NikoB","GYM","","","",activity);
        trainerData.setSpecialization("Aerobics");
        assertEquals("Aerobics", trainerData.getSpecialization());
    }

    /**
     * test trainerData set Customers
     */
    @Test
    public void trainerDataCustomers()
    {
        Time activity = new Time(10);
        TrainerData trainerData = new TrainerData("NikoB","GYM","","","",activity);
        trainerData.setCustomer1("CarlJ");
        trainerData.setCustomer2("TommyV");
        trainerData.setCustomer3("Claude");
        assertEquals("CarlJ", trainerData.getCustomer1());
        assertEquals("TommyV", trainerData.getCustomer2());
        assertEquals("Claude", trainerData.getCustomer3());
    }
}