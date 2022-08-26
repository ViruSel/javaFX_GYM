package database;

import java.sql.Time;

public class TrainerData extends DB
{
    private String username;
    private String specialization;
    private String customer1;
    private String customer2;
    private String customer3;
    private Time activity;

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getSpecialization() { return specialization; }

    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getCustomer1() { return customer1; }

    public void setCustomer1(String customer1) { this.customer1 = customer1; }

    public String getCustomer2() { return customer2; }

    public void setCustomer2(String customer2) { this.customer2 = customer2; }

    public String getCustomer3() { return customer3; }

    public void setCustomer3(String customer3) { this.customer3 = customer3; }

    public Time getActivity() { return activity; }

    public void setActivity(Time activity) { this.activity = activity; }

    public TrainerData() { }

    public TrainerData(String username, String specialization, String customer1, String customer2, String customer3, Time activity)
    {
        this.username = username;
        this.specialization = specialization;
        this.activity = activity;
        this.customer1 = customer1;
        this.customer2 = customer2;
        this.customer3 = customer3;
    }

    @Override
    public String toString()
    {
        return "TrainerData{" +
                ", username='" + username + '\'' +
                ", specialization='" + specialization + '\'' +
                ", customer1='" + customer1 + '\'' +
                ", customer2='" + customer2 + '\'' +
                ", customer3='" + customer3 + '\'' +
                ", activity=" + activity +
                '}';
    }
}
