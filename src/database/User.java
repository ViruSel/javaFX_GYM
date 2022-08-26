package database;

public class User extends DB
{
    private String username;
    private String password;
    private String rank;
    private String subscription;

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getRank() { return rank; }

    public void setRank(String rank) { this.rank = rank; }

    public boolean isCustomer() {return rank.equals("Customer");}

    public boolean isTrainer() {return rank.equals("Trainer");}

    public boolean isManager() {return rank.equals("Manager");}

    public String getSubscription() { return subscription; }

    public void setSubscription(String subscription) { this.subscription = subscription; }

    public User() { }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String rank, String subscription) {
        this.username = username;
        this.password = password;
        this.rank = rank;
        this.subscription = subscription;
    }

    @Override
    public String toString() { return "username = " + username + ", password = " + password + ", rank = " + rank + ", subscription = " + subscription + "\n"; }
}
