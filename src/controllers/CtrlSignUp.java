package controllers;

import animatefx.animation.SlideInLeft;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import database.DB;
import database.User;
import database.UserData;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CtrlSignUp implements Initializable
{
    // FXML OBJECTS //
    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane changedPane;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField lastName;
    @FXML
    private JFXTextField age;
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField city;
    @FXML
    private ImageView loading;
    @FXML
    private JFXCheckBox eula;
    @FXML
    private JFXRadioButton male;
    @FXML
    private JFXRadioButton female;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    ToggleGroup tg = new ToggleGroup();

    // SQL //
    DB db = new DB();
    User user;
    UserData user_data;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        loading.setVisible(false);
        username.setStyle("-fx-text-inner-color: white;");
        password.setStyle("-fx-text-inner-color: white;");
        name.setStyle("-fx-text-inner-color: white;");
        lastName.setStyle("-fx-text-inner-color: white;");
        age.setStyle("-fx-text-inner-color: white;");
        tel.setStyle("-fx-text-inner-color: white;");
        email.setStyle("-fx-text-inner-color: white;");
        city.setStyle("-fx-text-inner-color: white;");
        male.getStylesheets().add("assets/virus.css");
        male.setToggleGroup(tg);
        female.getStylesheets().add("assets/virus.css");
        female.setToggleGroup(tg);
    }

    @FXML
    public void signUpAction()
    {
        loading.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(1));
        pt.setOnFinished(e -> {
            loading.setVisible(false);
            validateProfile();
        });
        pt.play();
    }

    @FXML
    public void loginAction() { setPageLogin(); }

    private void setNodeLogin(Node node)
    {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        SlideInLeft slideInLeft = new SlideInLeft(changedPane);
        slideInLeft.setNode(node);
        slideInLeft.setCycleCount(1);
        slideInLeft.setSpeed(1);
        slideInLeft.play();
    }

    private void setPageLogin()
    {
        try
        {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/Login.fxml"));
            setNodeLogin(changedPane);
        }
        catch (IOException e) { e.printStackTrace();}
    }

    private void validateProfile()
    {
        if (username.getText().equals(""))
        {
            loading.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a username!");
            alert.show();
        }
        else if (password.getText().equals(""))
        {
            loading.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a password!");
            alert.show();
        }
        else if (name.getText().equals("") || isNumeric(name.getText()))
        {
            loading.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid name!");
            alert.show();
        }
        else if (lastName.getText().equals("") || isNumeric(lastName.getText()))
        {
            loading.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid lastname!");
            alert.show();
        }
        else if (city.getText().equals("") || isNumeric(city.getText()))
        {
            loading.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid city!");
            alert.show();
        }
        else if (!isNumeric(age.getText()) || age.getText().length() > 2)
        {
            loading.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid age!");
            alert.show();
        }
        else if(Integer.parseInt(age.getText()) > 90)
        {
            loading.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Why do you even bother going to GYM at that age lol!");
            alert.show();
        }
        else if (!isNumeric(tel.getText()) || tel.getText().length() != 10)
        {
            loading.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid phone number!");
            alert.show();
        }
        else if (!isEmail(email.getText()))
        {
            loading.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid email, or don't enter it at all!");
            alert.show();
        }
        else if (!male.isSelected() && !female.isSelected())
        {
            loading.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select your gender!");
            alert.show();
        }
        else if (!eula.isSelected())
        {
            loading.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please accept the all mighty EULA agreement!");
            alert.show();
        }
        else
        {
            try {
                Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
                Statement st = con.createStatement();
                String sql = "Select * FROM user WHERE username='" + username.getText() + "'";
                ResultSet rs = st.executeQuery(sql);

                if(rs.next())
                {
                    loading.setVisible(false);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Username already taken");
                    alert.show();
                }
                else
                {
                    loading.setVisible(false);

                    // CREATE USER DATA
                    user_data = new UserData(
                            username.getText(),
                            password.getText(),
                            name.getText(),
                            lastName.getText(),
                            Integer.parseInt(age.getText()),
                            getGender(),
                            tel.getText(),
                            city.getText(),
                            email.getText()
                    );

                    // CREATE USER AFTER USER_DATA
                    user = new User(
                            username.getText(),
                            password.getText()
                    );

                    user.setRank("Customer");
                    user.setSubscription("Free");

                    db.insertUserData(user_data);
                    db.insertUser(user);

                    userDataHasNotNullValues(user_data);

                    setPageLogin();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Signed Up Successfully"+"\n"+"Now please Login");
                    alert.show();
                }

            }
            catch (SQLException e) { e.printStackTrace(); }
        }
    }

    // NOT NULL ENTRIES IN DB SO NO ERRORS IN JAVA
    private void userDataHasNotNullValues(UserData user_data)
    {
        try {
            Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
            String gymTrainer = "UPDATE `user_data` SET `gymTrainer` =" + "''" + " WHERE (`username`='" + user_data.getUsername() + "');";
            String gymAerobics = "UPDATE `user_data` SET `aerobicsTrainer` =" + "''" + " WHERE (`username`='" + user_data.getUsername() + "');";
            String gymYoga = "UPDATE `user_data` SET `yogaTrainer` =" + "''" + " WHERE (`username`='" + user_data.getUsername() + "');";
            String gymRequest = "UPDATE `user_data` SET `requestGymTrainer` =" + "''" + " WHERE (`username`='" + user_data.getUsername() + "');";
            String aerobicsRequest = "UPDATE `user_data` SET `requestAerobicsTrainer` =" + "''" + " WHERE (`username`='" + user_data.getUsername() + "');";
            String yogaRequest = "UPDATE `user_data` SET `requestYogaTrainer` =" + "''" + " WHERE (`username`='" + user_data.getUsername() + "');";
            String exercisePlan = "UPDATE `user_data` SET `exercisePlan` =" + "''" + " WHERE (`username`='" + user_data.getUsername() + "');";
            String diet = "UPDATE `user_data` SET `diet` =" + "''" + " WHERE (`username`='" + user_data.getUsername() + "');";
            String hall = "UPDATE `user_data` SET `hall` =" + "''" + " WHERE (`username`='" + user_data.getUsername() + "');";
            PreparedStatement ps1 = con.prepareStatement(gymTrainer);
            PreparedStatement ps2 = con.prepareStatement(gymAerobics);
            PreparedStatement ps3 = con.prepareStatement(gymYoga);
            PreparedStatement ps4 = con.prepareStatement(gymRequest);
            PreparedStatement ps5 = con.prepareStatement(aerobicsRequest);
            PreparedStatement ps6 = con.prepareStatement(yogaRequest);
            PreparedStatement ps7 = con.prepareStatement(exercisePlan);
            PreparedStatement ps8 = con.prepareStatement(diet);
            PreparedStatement ps9 = con.prepareStatement(hall);
            ps1.executeUpdate();
            ps2.executeUpdate();
            ps3.executeUpdate();
            ps4.executeUpdate();
            ps5.executeUpdate();
            ps6.executeUpdate();
            ps7.executeUpdate();
            ps8.executeUpdate();
            ps9.executeUpdate();
        }
        catch (SQLException throwables) { throwables.printStackTrace(); }
    }

    private String getGender()
    {
        String gender = "";

        if(male.isSelected())
            gender = "Male";
        else if(female.isSelected())
            gender = "Female";

        return gender;
    }

    public static boolean isNumeric(String age)
    {
        if (age == null)
            return false;

        try { Integer.parseInt(age); }
        catch (NumberFormatException nfe) { return false; }

        return true;
    }

    public static boolean isEmail(String email)
    {
        if(!email.equals(""))
        {
            try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
            }
            catch (AddressException ex) { return false; }
        }

        return true;
    }
}
