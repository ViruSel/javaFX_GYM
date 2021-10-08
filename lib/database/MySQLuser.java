package database;

public class MySQLuser extends MySQLdb
{
    private int id;
    private String username;
    private String password;
    private String rank;
    private String subscription;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public void setRank(String rank) { this.rank = rank; }

    public boolean isCustomer() {return rank.equals("Customer");}

    public boolean isTrainer() {return rank.equals("Trainer");}

    public boolean isManager() {return rank.equals("Manager");}

    public String getSubscription() { return subscription; }

    public void setSubscription(String subscription) { this.subscription = subscription; }

    public MySQLuser() { }

    public MySQLuser(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public MySQLuser(int id, String username, String password, String rank, String subscription) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rank = rank;
        this.subscription = subscription;
    }

    @Override
    public String toString() { return "User id = " + id + ", username = " + username + ", password = " + password + ", rank = " + rank + ", subscription = " + subscription + "\n"; }
}
