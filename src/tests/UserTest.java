package tests;

import database.MySQLuser;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

//@WebMvcTest(controllers = UserController.class)
//@ActiveProfiles("test")
//@Autowired
//private MockMvc mockMvc;

//@MockBean
//private UserService userService;

public class UserTest
{
    private List<MySQLuser> userList;

    @BeforeEach
    void setUp() {
        this.userList = new ArrayList<>();
        this.userList.add(new MySQLuser(1,"Ricky", "1234","Customer","1 month"));
        this.userList.add(new MySQLuser(2,"Dicky", "1234","Trainer","Unlimited"));
        this.userList.add(new MySQLuser(1,"Micky", "1234","Manager","Unlimited"));
    }
}
