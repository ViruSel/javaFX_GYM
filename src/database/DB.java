package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB
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
    public User getUserByUsername(String username)
    {
        User user = null;

        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementGetUserByUsername(con, username);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
                user = new User(rs.getString("username"), rs.getString("password"), rs.getString("rank"), rs.getString("subscription"));
        }
        catch (SQLException e) { e.printStackTrace(); }

        return user;
    }

    public User getUserByRank(String rank)
    {
        User user = null;

        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps =  createStatementGetUserByRank(con, rank);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
                user = new User(rs.getString("username"), rs.getString("password"), rs.getString("rank"), rs.getString("subscription"));
        }
        catch (SQLException e) { e.printStackTrace(); }

        return user;
    }

    public List<User> getAllUser()
    {
        List<User> users = new ArrayList<User>();

        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementGetAllUsers(con);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
                users.add(new User(rs.getString("username"), rs.getString("password"), rs.getString("rank"), rs.getString("subscription")));

        }
        catch (SQLException e) { e.printStackTrace(); }

        return users;
    }

    public void insertUser(User user)
    {
        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementInsertUser(con, user)) {
            ps.executeUpdate();
        }
        catch (SQLException e) { e.printStackTrace(); }
    }

    public void deleteUser(String username)
    {
        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementDeleteUser(con, username))
        {
            ps.executeUpdate();
        }
        catch (SQLException e) { e.printStackTrace(); }
    }

        // USER DATA //
    public UserData getUserDataByUsername(String username)
    {
        UserData user_data = null;

        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementGetUserData(con, username);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
                user_data = new UserData(rs.getString("username"), rs.getString("password"), rs.getString("name"), rs.getString("lastName"), rs.getInt("age"), rs.getString("gender"), rs.getString("tel"), rs.getString("city"), rs.getString("email"), rs.getString("gymTrainer"), rs.getString("aerobicsTrainer"), rs.getString("yogaTrainer"), rs.getString("requestGymTrainer"), rs.getString("requestAerobicsTrainer"), rs.getString("requestYogaTrainer"), rs.getString("exercisePlan"), rs.getString("diet"), rs.getString("hall"));
        }
        catch (SQLException e) { e.printStackTrace(); }

        return user_data;
    }

    public List<UserData> getAllUserData()
    {
        List<UserData> users_data = new ArrayList<UserData>();

        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementGetAllUsersData(con);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
                users_data.add(new UserData(rs.getString("username"), rs.getString("password"), rs.getString("name"), rs.getString("lastName"), rs.getInt("age"), rs.getString("gender"), rs.getString("tel"), rs.getString("city"), rs.getString("email"), rs.getString("gymTrainer"), rs.getString("aerobicsTrainer"), rs.getString("yogaTrainer"), rs.getString("requestGymTrainer"), rs.getString("requestAerobicsTrainer"), rs.getString("requestYogaTrainer"), rs.getString("exercisePlan"), rs.getString("diet"), rs.getString("hall")));
        }
        catch (SQLException e) { e.printStackTrace(); }

        return users_data;
    }

    public void insertUserData(UserData user_data)
    {
        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementInsertUserData(con, user_data))
        {
            ps.executeUpdate();
        }
        catch (SQLException e) { e.printStackTrace(); }
    }

        // TRAINER DATA //

    public TrainerData getTrainerDataByUsername(String username)
    {
        TrainerData trainerData = null;

        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementGetTrainerData(con, username);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
                trainerData = new TrainerData(rs.getString("username"), rs.getString("specialization"), rs.getString("customer1"), rs.getString("customer2"), rs.getString("customer3"),rs.getTime("activity"));
        }
        catch (SQLException e) { e.printStackTrace(); }

        return trainerData;
    }

    public List<TrainerData> getAllTrainerData()
    {
        List<TrainerData> trainerData = new ArrayList<TrainerData>();

        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementGetAllTrainersData(con);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
                trainerData.add(new TrainerData(rs.getString("username"), rs.getString("specialization"), rs.getString("customer1"), rs.getString("customer2"), rs.getString("customer3"),rs.getTime("activity")));
        }
        catch (SQLException e) { e.printStackTrace(); }

        return trainerData;
    }

    public void insertTrainerData(TrainerData trainerData)
    {
        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementInsertTrainerData(con, trainerData))
        {
            ps.executeUpdate();
        }
        catch (SQLException e) { e.printStackTrace(); }
    }

        // HALLS//

    public Halls getHallById(int id)
    {
        Halls hall = null;

        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementGetHallById(con, id);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
                hall = new Halls(rs.getInt("id"), rs.getString("name"), rs.getInt("capacity"));
        }
        catch (SQLException e) { e.printStackTrace(); }

        return hall;
    }

    public ObservableList<Halls> getAllHalls()
    {
        ObservableList<Halls> halls = FXCollections.observableArrayList();

        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementGetAllHalls(con);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
                halls.add(new Halls(rs.getInt("id"), rs.getString("name"), rs.getInt("capacity")));

        }
        catch (SQLException e) { e.printStackTrace(); }

        return halls;
    }

        // FEEDBACK //

    public Feedback getFeedbackById(int id)
    {
        Feedback feedback = null;

        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementGetFeedbackById(con, id);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
                feedback = new Feedback(rs.getString("forWho"), rs.getString("text"));
        }
        catch (SQLException e) { e.printStackTrace(); }

        return feedback;
    }

    public List<Feedback> getAllFeedback()
    {
        List<Feedback> feedback = new ArrayList<Feedback>();

        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementGetAllFeedback(con);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
                feedback.add(new Feedback(rs.getString("forWho"), rs.getString("text")));

        }
        catch (SQLException e) { e.printStackTrace(); }

        return feedback;
    }

        // EXERCISES //

    public Exercises getExerciseByObjective(String objective)
    {
        Exercises exercise = null;

        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementGetExerciseByObjective(con, objective);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
                exercise = new Exercises(rs.getString("objective"), rs.getString("exName"), rs.getString("sets"), rs.getString("reps"), rs.getString("rest"));
        }
        catch (SQLException e) { e.printStackTrace(); }

        return exercise;
    }

    public List<Exercises> getAllExercises()
    {
        List<Exercises> exercises = new ArrayList<Exercises>();

        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementGetAllExercises(con);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
                exercises.add(new Exercises(rs.getString("objective"), rs.getString("exName"), rs.getString("sets"), rs.getString("reps"), rs.getString("rest")));

        }
        catch (SQLException e) { e.printStackTrace(); }

        return exercises;
    }

        // DIET //

    public Diet getDietByName(String name)
    {
        Diet diet = null;

        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementGetDietByName(con, name);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
                diet = new Diet(rs.getInt("id"), rs.getString("name"), rs.getString("objective"));
        }
        catch (SQLException e) { e.printStackTrace(); }

        return diet;
    }

    public List<Diet> getAllDiets()
    {
        List<Diet> diet = new ArrayList<Diet>();

        try (Connection con = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
             PreparedStatement ps = createStatementGetAllDiets(con);
             ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
                diet.add(new Diet(rs.getInt("id"), rs.getString("name"), rs.getString("objective")));

        }
        catch (SQLException e) { e.printStackTrace(); }

        return diet;
    }

    // STATEMENTS //
        // USER //
    private PreparedStatement createStatementGetAllUsers(Connection con) throws SQLException
    {
        String sql = "SELECT * FROM user";
        return con.prepareStatement(sql);
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

    private PreparedStatement createStatementInsertUser(Connection con, User user) throws SQLException {
        String sql = "INSERT INTO user(username, password) " + "VALUES(?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        return ps;
    }

    private PreparedStatement createStatementDeleteUser(Connection con,String username ) throws SQLException
    {
        String sql = " DELETE FROM user WHERE username =? ";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        return ps;
    }

        // USER DATA //
    private PreparedStatement createStatementGetAllUsersData(Connection con) throws SQLException
    {
        String sql = "SELECT * FROM user_data";
        return con.prepareStatement(sql);
    }

    private PreparedStatement createStatementGetUserData(Connection con, String username) throws SQLException
    {
        String sql = "SELECT * FROM user_data WHERE username = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        return ps;
    }

    private PreparedStatement createStatementInsertUserData(Connection con, UserData user_data) throws SQLException
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

        // TRAINER DATA
        private PreparedStatement createStatementGetAllTrainersData(Connection con) throws SQLException
        {
            String sql = "SELECT * FROM trainer_data";
            PreparedStatement ps = con.prepareStatement(sql);
            return ps;
        }

    private PreparedStatement createStatementGetTrainerData(Connection con, String username) throws SQLException
    {
        String sql = "SELECT * FROM trainer_data WHERE username = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        return ps;
    }

    private PreparedStatement createStatementInsertTrainerData(Connection con, TrainerData trainerData) throws SQLException
    {
        String sql = "INSERT INTO trainer_data(username, specialization) " + "VALUES(?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, trainerData.getUsername());
        ps.setString(2, trainerData.getSpecialization());
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
        return con.prepareStatement(sql);
    }

        // HALLS //
    private PreparedStatement createStatementGetFeedbackById(Connection con, int id) throws SQLException
    {
        String sql = "SELECT * FROM feedback WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    private PreparedStatement createStatementGetAllFeedback(Connection con) throws SQLException
    {
        String sql = "SELECT * FROM feedback";
        return con.prepareStatement(sql);
    }

        // EXERCISES //
    private PreparedStatement createStatementGetExerciseByObjective(Connection con, String objective) throws SQLException
        {
            String sql = "SELECT * FROM exercises WHERE objective = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, objective);
            return ps;
        }

    private PreparedStatement createStatementGetAllExercises(Connection con) throws SQLException
    {
        String sql = "SELECT * FROM exercises";
        return con.prepareStatement(sql);
    }

        // DIET //
    private PreparedStatement createStatementGetDietByName(Connection con, String name) throws SQLException
    {
        String sql = "SELECT * FROM diet WHERE name = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        return ps;
    }

    private PreparedStatement createStatementGetAllDiets(Connection con) throws SQLException
    {
        String sql = "SELECT * FROM exercises";
        return con.prepareStatement(sql);
    }
}
