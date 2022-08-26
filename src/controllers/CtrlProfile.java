package controllers;

import animatefx.animation.SlideInRight;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static controllers.CtrlSignUp.isEmail;
import static controllers.CtrlSignUp.isNumeric;

public class CtrlProfile implements Initializable
{
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
    private JFXTextField gender;
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXTextField city;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXButton editButton;
    @FXML
    private JFXButton doneButton;
    @FXML
    private Pane hidingPane;

    DB db = new DB();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        updateCredentials();
        doneButton.setVisible(false);
        hidingPane.setVisible(true);
        gender.setEditable(false);
        name.setText(CtrlLogin.user_data.getName());
        lastName.setText(CtrlLogin.user_data.getLastName());
        age.setText(getAge(CtrlLogin.user_data.getAge()));
        gender.setText(CtrlLogin.user_data.getGender());
        tel.setText(CtrlLogin.user_data.getTel());
        city.setText(CtrlLogin.user_data.getCity());
        setEmail();
        name.setStyle("-fx-text-inner-color: white;");
        lastName.setStyle("-fx-text-inner-color: white;");
        age.setStyle("-fx-text-inner-color: white;");
        gender.setStyle("-fx-text-inner-color: white;");
        tel.setStyle("-fx-text-inner-color: white;");
        city.setStyle("-fx-text-inner-color: white;");
        email.setStyle("-fx-text-inner-color: white;");
        defaultProfileSettings();
    }

    private void defaultProfileSettings()
    {
        hidingPane.setVisible(true);
        editButton.setVisible(true);
        doneButton.setVisible(false);
        name.setEditable(false);
        lastName.setEditable(false);
        age.setEditable(false);
        tel.setEditable(false);
        city.setEditable(false);
        email.setEditable(false);
        name.setUnFocusColor(Color.web("#121212"));
        lastName.setUnFocusColor(Color.web("#121212"));
        age.setUnFocusColor(Color.web("#121212"));
        gender.setUnFocusColor(Color.web("#121212"));
        tel.setUnFocusColor(Color.web("#121212"));
        city.setUnFocusColor(Color.web("#121212"));
        email.setUnFocusColor(Color.web("#121212"));

    }

    @FXML
    private void editButtonAction()
    {
        hidingPane.setVisible(false);
        editButton.setVisible(false);
        doneButton.setVisible(true);
        name.setEditable(true);
        lastName.setEditable(true);
        age.setEditable(true);
        tel.setEditable(true);
        city.setEditable(true);
        email.setEditable(true);
        name.setUnFocusColor(Color.web("#4d4d4d"));
        lastName.setUnFocusColor(Color.web("#4d4d4d"));
        age.setUnFocusColor(Color.web("#4d4d4d"));
        gender.setUnFocusColor(Color.web("#4d4d4d"));
        tel.setUnFocusColor(Color.web("#4d4d4d"));
        city.setUnFocusColor(Color.web("#4d4d4d"));
        email.setUnFocusColor(Color.web("#4d4d4d"));
    }

    @FXML
    private void doneButtonAction()
    {
        defaultProfileSettings();
        validateNewCredentials();
    }

    @FXML
    private void subscriptionButtonAction() { setPageSubscriptions(); }

    @FXML
    private void trainerButtonAction() { setPageTrainers(); }

    @FXML
    private void hallsButtonAction() { setPageHalls(); }

    @FXML
    private void mapButtonAction() { setPageMap(); }

    private static String getAge(int age) { return Integer.toString(age); }

    private void setEmail ()
    {
        if(CtrlLogin.user_data.getEmail().equals(""))
            email.setText("No Email");
        else
            email.setText(CtrlLogin.user_data.getEmail());
    }

    private void validateNewCredentials()
    {
        if (name.getText().equals("") || isNumeric(name.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid name!");
            alert.show();
            name.setText(CtrlLogin.user_data.getName());
        }
        else if (lastName.getText().equals("") || isNumeric(lastName.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid last name!");
            alert.show();
            lastName.setText(CtrlLogin.user_data.getLastName());
        }
        else if (city.getText().equals("") || isNumeric(city.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid city!");
            alert.show();
            city.setText(CtrlLogin.user_data.getCity());
        }
        else if (!isNumeric(age.getText()) || age.getText().length() > 2)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid age!");
            alert.show();
            age.setText(String.valueOf(CtrlLogin.user_data.getAge()));
        }
        else if (Integer.parseInt(age.getText()) > 90)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Why do you even bother going to GYM at that age lol!");
            alert.show();
        }
        else if (!isNumeric(tel.getText()) || tel.getText().length() != 10)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid phone number!");
            alert.show();
            tel.setText(CtrlLogin.user_data.getTel());
        }
        else if (!email.getText().equals("No Email") && !email.getText().equals("") && !isEmail(email.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid email, or don't enter it at all!");
            alert.show();
            setEmail();
        }
        else
        {
            insertNewCredentials();
            updateCredentials();
            setEmail();
        }
    }

    private void insertNewCredentials()
    {
        if(!name.getText().equals(CtrlLogin.user_data.getName()))
        {
            try {
                Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
                String sql = "UPDATE `user_data` SET `name` =" + "'"+name.getText()+"'" +" WHERE (`username`='"+CtrlLogin.user_data.getUsername()+"');";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.executeUpdate(sql);

            } catch (SQLException e) { e.printStackTrace(); }
        }
        if(!lastName.getText().equals(CtrlLogin.user_data.getLastName()))
        {
            try {
                Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
                String sql = "UPDATE `user_data` SET `lastName` =" + "'"+lastName.getText()+"'" +" WHERE (`username`='"+CtrlLogin.user_data.getUsername()+"');";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.executeUpdate(sql);

            } catch (SQLException e) { e.printStackTrace(); }
        }
        if(Integer.parseInt(age.getText()) != (CtrlLogin.user_data.getAge()))
        {
            try {
                Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
                String sql = "UPDATE `user_data` SET `age` =" +Integer.parseInt(age.getText()) +" WHERE (`username`='"+CtrlLogin.user_data.getUsername()+"');";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.executeUpdate(sql);

            } catch (SQLException e) { e.printStackTrace(); }
        }
        if(!tel.getText().equals(CtrlLogin.user_data.getTel()))
        {
            try {
                Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
                String sql = "UPDATE `user_data` SET `tel` = "+ "'"+tel.getText() +"'"+" WHERE (`username`='"+CtrlLogin.user_data.getUsername()+"');";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.executeUpdate(sql);

            } catch (SQLException e) { e.printStackTrace(); }
        }
        if(!city.getText().equals(CtrlLogin.user_data.getCity()))
        {
            try {
                Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
                String sql = "UPDATE `user_data` SET `city` =" + "'"+city.getText()+"'" +" WHERE (`username`='"+CtrlLogin.user_data.getUsername()+"');";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.executeUpdate(sql);

            } catch (SQLException e) { e.printStackTrace(); }
        }
        if(!email.getText().equals(CtrlLogin.user_data.getEmail()) && !email.getText().equals("No Email"))
        {
            try {
                Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
                String sql = "UPDATE `user_data` SET email =" + "'"+email.getText()+"'" +" WHERE (`username`='"+ CtrlLogin.user_data.getUsername()+"');";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.executeUpdate(sql);

            } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    private void updateCredentials()
    {
        try {
            Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
            Statement st = con.createStatement();
            String sql = "Select * FROM user WHERE username='" + CtrlLogin.user.getUsername() + "'";
            ResultSet rs = st.executeQuery(sql);

            if(rs.next())
            {
                CtrlLogin.user = db.getUserByUsername(CtrlLogin.user.getUsername());
                CtrlLogin.user_data = db.getUserDataByUsername(CtrlLogin.user.getUsername());
            }

        }
        catch (SQLException throwables) { throwables.printStackTrace(); }
    }

    private void setNode(Node node)
    {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        SlideInRight slideInRight = new SlideInRight(changedPane);
        slideInRight.setNode(node);
        slideInRight.setCycleCount(1);
        slideInRight.setSpeed(1);
        slideInRight.play();
    }

    private void setPageSubscriptions()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/ProfileSubscriptions.fxml"));
            setNode(changedPane);

        }
        catch (IOException e) { e.printStackTrace(); }
    }

    private void setPageTrainers()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/ProfileTrainers.fxml"));
            setNode(changedPane);

        }
        catch (IOException e) { e.printStackTrace(); }
    }

    private void setPageHalls()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/ProfileHalls.fxml"));
            setNode(changedPane);

        }
        catch (IOException e) { e.printStackTrace(); }
    }

    private void setPageMap()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/ProfileMap.fxml"));
            setNode(changedPane);

        }
        catch (IOException e) { e.printStackTrace(); }
    }
}
