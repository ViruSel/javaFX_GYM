package database;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class MySQLuser_data extends RecursiveTreeObject<MySQLuser_data>
{
    private int id;
    private int age;
    private String name;
    private String lastName;
    private String tel;
    private String city;
    private String email = "";
    private String gender;
    private String username;
    private String password;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

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

    public ObservableValue<String> getNameObs() { return new SimpleStringProperty(name); }
    public ObservableValue<String> getLastNameObs() { return new SimpleStringProperty(lastName); }
    public ObservableValue<String> getAgeObs() { return new SimpleStringProperty(String.valueOf(age)); }
    public ObservableValue<String> getGenderObs() { return new SimpleStringProperty(gender); }
    public ObservableValue<String> getTelObs() { return new SimpleStringProperty(tel); }
    public ObservableValue<String> getEmailObs() { return new SimpleStringProperty(email); }

    public MySQLuser_data() { }

    public MySQLuser_data(MySQLuser_data userData) { }

    public MySQLuser_data(String name, String lastName, int age, String gender, String tel, String city, String email) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.tel = tel;
        this.city = city;
        this.email = email;
    }

    public MySQLuser_data(String username, String password, String name, String lastName, int age, String gender, String tel, String city, String email) {
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

    public MySQLuser_data(int id, String username, String password, String name, String lastName, int age, String gender, String tel, String city, String email) {
        this.id = id;
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

    @Override
    public String toString() {
        return "User Data id = " + id + ", name = " + name + ", lastName = " + lastName + ", age = " + age + ", gender = " + gender + ", tel = " + tel + ", city = " + city + ", email = " + email + ", username = " + username + ", password = " + password;
    }
}
