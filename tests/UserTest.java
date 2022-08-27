import database.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest
{
    /**
     * test user username
     */
    @Test
    public void testUsername()
    {
        User user = new User("Chuck", "1234");
        assertEquals("Chuck", user.getUsername());
    }

    /**
     * Test user password
     */
    @Test
    public void testPassword()
    {
        User user = new User("Chuck", "1234");
        assertEquals("1234", user.getPassword());
    }

    /**
     * Test complete user creation
     */
    @Test
    public void testCustomer()
    {
        User user = new User("ChuckN", "1234", "Customer", "Free");
        assertEquals("ChuckN", user.getUsername());
        assertEquals("1234", user.getPassword());
        assertEquals("Customer", user.getRank());
        assertEquals("Free", user.getSubscription());
    }

    /**
     * Test creating Trainer
     */
    @Test
    public void testTrainer()
    {
        User user = new User("ChuckN", "1234", "Customer", "Free");
        user.setRank("Trainer");
        user.setSubscription("Unlimited");
        assertEquals("Trainer", user.getRank());
        assertEquals("Unlimited", user.getSubscription());
    }
}