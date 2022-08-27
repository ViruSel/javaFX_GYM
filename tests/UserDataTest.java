import database.UserData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDataTest
{
    /**
     * test userData without email
     */
    @Test
    public void testUserDataNoEmail()
    {
        UserData userData = new UserData("CarlJ", "1234", "Carl", "Johnson",0,"Male","0712345678","Los Santos","");
        assertEquals("", userData.getEmail());
    }

    /**
     * test userData with email
     */
    @Test
    public void testUserDataEmail()
    {
        UserData userData = new UserData("NikoB", "1234", "Nikolai", "Bellic",0,"Male","0712345678","Los Santos","niko_b@hotmail.com");
        assertEquals("niko_b@hotmail.com", userData.getEmail());
    }

    /**
     * test userData assign trainers
     */
    @Test
    public void testUserDataSetTrainers()
    {
        UserData userData = new UserData();
        userData.setGymTrainer("MickyS");
        userData.setAerobicsTrainer("FrankC");
        userData.setYogaTrainer("TrevorPhillips");
        assertEquals("MickyS", userData.getGymTrainer());
        assertEquals("FrankC", userData.getAerobicsTrainer());
        assertEquals("TrevorPhillips", userData.getYogaTrainer());
    }
}