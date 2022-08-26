package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLdb
{
    // CONNECTION //
    private final String HOST = "jdbc:mysql://localhost/test";
    private final String USERNAME = "root";
    private final String PASSWORD = "alexalicia1212";

    public String getHOST() { return HOST; }

    public String getUSERNAME() { return USERNAME; }

    public String getPASSWORD() { return PASSWORD; }

    // see if sth appears multiple times
    // i = 0 , while rs.next -> i++, switch i -> cases

    // METHODS //
        // USER //
    public MySQLuser getUserByUsername(String username)
    {
        MySQLuser user = null;

        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementGetUserByUsername(con, username);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
                user = new MySQLuser(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("rank"), rs.getString("subscription"));
        }
        catch (SQLException e) { e.printStackTrace(); }

        return user;
    }

    public MySQLuser getUserByRank(String rank)
    {
        MySQLuser user = null;

        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps =  createStatementGetUserByRank(con, rank);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
                user = new MySQLuser(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("rank"), rs.getString("subscription"));
        }
        catch (SQLException e) { e.printStackTrace(); }

        return user;
    }

    public List<MySQLuser> getAllUser()
    {
        List<MySQLuser> users = new ArrayList<MySQLuser>();

        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementGetAllUsers(con);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
                users.add(new MySQLuser(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("rank"), rs.getString("subscription")));

        }
        catch (SQLException e) { e.printStackTrace(); }

        return users;
    }

    public void insertUser(MySQLuser user)
    {
        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementInsertUser(con, user)) {
            ps.executeUpdate();
        }
        catch (SQLException e) { e.printStackTrace(); }
    }

    public void deleteUser(int userId)
    {
        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementDeleteUser(con, userId))
        {
            ps.executeUpdate();
        }
        catch (SQLException e) { e.printStackTrace(); }
    }

        // USER CREDENTIALS //
    public MySQLuser_data getUserDataByUsername(String username)
    {
        MySQLuser_data user_data = null;

        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementGetUserData(con, username);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
                user_data = new MySQLuser_data(rs.getString("username"), rs.getString("password"), rs.getString("name"), rs.getString("lastName"), rs.getInt("age"), rs.getString("gender"), rs.getString("tel"), rs.getString("city"), rs.getString("email"));
        }
        catch (SQLException e) { e.printStackTrace(); }

        return user_data;
    }

    public List<MySQLuser_data> getAllUserData()
    {
        List<MySQLuser_data> users_data = new ArrayList<MySQLuser_data>();

        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementGetAllUsersData(con);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
                users_data.add(new MySQLuser_data(rs.getString("username"), rs.getString("password"), rs.getString("name"), rs.getString("lastName"), rs.getInt("age"), rs.getString("gender"), rs.getString("tel"), rs.getString("city"), rs.getString("email")));
        }
        catch (SQLException e) { e.printStackTrace(); }

        return users_data;
    }

    public void insertUserData(MySQLuser_data user_data)
    {
        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementInsertUserData(con, user_data))
        {
            ps.executeUpdate();
        }
        catch (SQLException e) { e.printStackTrace(); }
    }

        // HALLS//

    public MySQLhalls getHallById(int id)
    {
        MySQLhalls hall = null;

        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementGetHallById(con, id);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
                hall = new MySQLhalls(rs.getInt("id"), rs.getString("name"), rs.getInt("capacity"));
        }
        catch (SQLException e) { e.printStackTrace(); }

        return hall;
    }

    public List<MySQLhalls> getAllHalls()
    {
        List<MySQLhalls> halls = new ArrayList<MySQLhalls>();

        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementGetAllHalls(con);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
                halls.add(new MySQLhalls(rs.getInt("id"), rs.getString("name"), rs.getInt("capacity")));

        }
        catch (SQLException e) { e.printStackTrace(); }

        return halls;
    }

    // STATEMENTS //
        // USER //
    private PreparedStatement createStatementGetAllUsers(Connection con) throws SQLException
    {
        String sql = "SELECT * FROM user";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps;
    }

    private PreparedStatement createStatementGetUserByUsername(Connection con, String username) throws SQLException
    {
        String sql = "SELECT * FROM user WHERE username = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        return ps;
    }

    private PreparedStatement createStatementGetUserByRank(Connection con, String rank) throws SQLException
    {
        String sql = "SELECT * FROM user WHERE `rank` = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, rank);
        return ps;
    }

    private PreparedStatement createStatementInsertUser(Connection con, MySQLuser user) throws SQLException {
        String sql = "INSERT INTO user(username, password) " + "VALUES(?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        return ps;
    }

    private PreparedStatement createStatementDeleteUser(Connection con,int userId ) throws SQLException
    {
        String sql = " DELETE FROM user WHERE id =? ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);
        return ps;
    }

        // USER DATA //
    private PreparedStatement createStatementGetAllUsersData(Connection con) throws SQLException
    {
        String sql = "SELECT * FROM user_data";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps;
    }

    private PreparedStatement createStatementGetUserData(Connection con, String username) throws SQLException
    {
        String sql = "SELECT * FROM user_data WHERE username = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        return ps;
    }

    private PreparedStatement createStatementInsertUserData(Connection con, MySQLuser_data user_data) throws SQLException
    {
        String sql = "INSERT INTO user_data(name, lastName, age, gender, tel, city, email, username, password) " + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, user_data.getName());
        ps.setString(2, user_data.getLastName());
           ps.setInt(3, user_data.getAge());
        ps.setString(4, user_data.getGender());
        ps.setString(5, user_data.getTel());
        ps.setString(6, user_data.getCity());
        ps.setString(7, user_data.getEmail());
        ps.setString(8, user_data.getUsername());
        ps.setString(9, user_data.getPassword());
        return ps;
    }

        // HALLS //
    private PreparedStatement createStatementGetHallById(Connection con, int id) throws SQLException
    {
        String sql = "SELECT * FROM halls WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    private PreparedStatement createStatementGetAllHalls(Connection con) throws SQLException
    {
        String sql = "SELECT * FROM halls";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps;
    }
}
