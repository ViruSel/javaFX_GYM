package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class UserEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "rank", nullable = false)
    private String rank = "Customer";

    @Column(name = "subscription", nullable = false)
    private String subscription = "Free";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public UserEntity(Integer id, String username, String password, String rank, String subscription) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rank = rank;
        this.subscription = subscription;
    }

    public UserEntity() { }

    public String toString() {
      return "UserEntity{id=" + id + 
        ", username=" + username + 
        ", password=" + password + 
        ", rank=" + rank + 
        ", subscription=" + subscription + 
        "}";
    }
}