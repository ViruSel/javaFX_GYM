package database;

public class UserData extends DB
{
    private int age;
    private String name;
    private String lastName;
    private String tel;
    private String city;
    private String email = "";
    private String gender;
    private String username;
    private String password;
    private String gymTrainer;
    private String aerobicsTrainer;
    private String yogaTrainer;
    private String requestGymTrainer;
    private String requestAerobicsTrainer;
    private String requestYogaTrainer;
    private String exercisePlan;
    private String diet;
    private String hall;

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getTel() { return tel; }

    public void setTel(String tel) { this.tel = tel; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getGymTrainer() { return gymTrainer; }

    public void setGymTrainer(String gymTrainer) { this.gymTrainer = gymTrainer; }

    public String getAerobicsTrainer() { return aerobicsTrainer; }

    public void setAerobicsTrainer(String aerobicsTrainer) { this.aerobicsTrainer = aerobicsTrainer; }

    public String getYogaTrainer() {
        return yogaTrainer;
    }

    public void setYogaTrainer(String yogaTrainer) {
        this.yogaTrainer = yogaTrainer;
    }

    public String getRequestGymTrainer() { return requestGymTrainer; }

    public void setRequestGymTrainer(String requestGymTrainer) { this.requestGymTrainer = requestGymTrainer; }

    public String getRequestAerobicsTrainer() { return requestAerobicsTrainer; }

    public void setRequestAerobicsTrainer(String requestAerobicsTrainer) { this.requestAerobicsTrainer = requestAerobicsTrainer; }

    public String getRequestYogaTrainer() { return requestYogaTrainer; }

    public void setRequestYogaTrainer(String requestYogaTrainer) { this.requestYogaTrainer = requestYogaTrainer; }

    public String getExercisePlan() { return exercisePlan; }

    public void setExercisePlan(String exercisePlan) { this.exercisePlan = exercisePlan; }

    public String getDiet() { return diet; }

    public void setDiet(String diet) { this.diet = diet; }

    public String getHall() { return hall; }

    public void setHall(String hall) { this.hall = hall; }

    public UserData() { }

    public UserData(String username, String password, String name, String lastName, int age, String gender, String tel, String city, String email)
    {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.tel = tel;
        this.city = city;
        this.email = email;

    }

    public UserData(String name, String lastName, int age, String gender, String tel, String city, String email, String gymTrainer, String aerobicsTrainer, String yogaTrainer, String requestGymTrainer, String requestAerobicsTrainer, String requestYogaTrainer, String exercisePlan, String diet, String hall)
    {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.tel = tel;
        this.city = city;
        this.email = email;
        this.gymTrainer = gymTrainer;
        this.aerobicsTrainer = aerobicsTrainer;
        this.yogaTrainer = yogaTrainer;
        this.requestGymTrainer = requestGymTrainer;
        this.requestAerobicsTrainer = requestAerobicsTrainer;
        this.requestYogaTrainer = requestYogaTrainer;
        this.exercisePlan = exercisePlan;
        this.diet = diet;
        this.hall = hall;
    }

    public UserData(String username, String password, String name, String lastName, int age, String gender, String tel, String city, String email, String gymTrainer, String aerobicsTrainer, String yogaTrainer, String requestGymTrainer, String requestAerobicsTrainer, String requestYogaTrainer, String exercisePlan, String diet, String hall)
    {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.tel = tel;
        this.city = city;
        this.email = email;
        this.gymTrainer = gymTrainer;
        this.aerobicsTrainer = aerobicsTrainer;
        this.yogaTrainer = yogaTrainer;
        this.requestGymTrainer = requestGymTrainer;
        this.requestAerobicsTrainer = requestAerobicsTrainer;
        this.requestYogaTrainer = requestYogaTrainer;
        this.exercisePlan = exercisePlan;
        this.diet = diet;
        this.hall = hall;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", tel='" + tel + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gymTrainer='" + gymTrainer + '\'' +
                ", aerobicsTrainer='" + aerobicsTrainer + '\'' +
                ", yogaTrainer='" + yogaTrainer + '\'' +
                ", requestGymTrainer='" + requestGymTrainer + '\'' +
                ", requestAerobicsTrainer='" + requestAerobicsTrainer + '\'' +
                ", requestYogaTrainer='" + requestYogaTrainer + '\'' +
                ", exercisePlan='" + exercisePlan + '\'' +
                ", diet='" + diet + '\'' +
                ", hall='" + hall + '\'' +
                '}';
    }
}
