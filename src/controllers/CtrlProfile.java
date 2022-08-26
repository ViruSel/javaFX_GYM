package controllers;

import animatefx.animation.SlideInRight;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.MySQLdb;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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

    private boolean nameFlag = false;
    private boolean lastNameFlag = false;
    private boolean ageFlag = false;
    private boolean telFlag = false;
    private boolean cityFlag = false;
    private boolean emailFlag = false;

    MySQLdb db = new MySQLdb();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        updateCredentials();
        doneButton.setVisible(false);
        hidingPane.setVisible(true);
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
    }

    @FXML
    private void doneButtonAction()
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
        }
        else if (lastName.getText().equals("") || isNumeric(lastName.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid lastname!");
            alert.show();
        }
        else if (city.getText().equals("") || isNumeric(city.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid city!");
            alert.show();
        }
        else if (!isNumeric(age.getText()) || age.getText().length() > 2)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid age!");
            alert.show();
        }
        else if (Integer.parseInt(age.getText()) > 90) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Why do you even bother going to GYM at that age lol!");
            alert.show();
        }
        else if (!isNumeric(tel.getText()) || (tel.getText().length() < 9 && tel.getText().length() >10))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid phone number!");
            alert.show();
        }
        else if (email.getText().equals(""))
        {
            email.setText("No Email");
        }
        else if (!isEmail(email.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid email, or don't enter it at all!");
            alert.show();
        }
        else
        {
            insertNewCredentials();
            updateCredentials();
        }
    }

    private void insertNewCredentials()
    {
        getFlags();

        if(!nameFlag)
        {
            try {
                Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
                String sql = "UPDATE `test`.`user_data` SET `name` =" + name.getText() +" WHERE (`username`='"+CtrlLogin.user_data.getUsername()+"');";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.executeUpdate(sql);

            } catch (SQLException e) { e.printStackTrace(); }
        }
        if(!lastNameFlag)
        {
            try {
                Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
                String sql = "UPDATE `test`.`user_data` SET `lastName` =" + lastName.getText() +" WHERE (`username`='"+CtrlLogin.user_data.getUsername()+"');";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.executeUpdate(sql);

            } catch (SQLException e) { e.printStackTrace(); }
        }
        if(!ageFlag)
        {
            try {
                Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
                String sql = "UPDATE `test`.`user_data` SET `age` =" + Integer.parseInt(age.getText()) +" WHERE (`username`='"+CtrlLogin.user_data.getUsername()+"');";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.executeUpdate(sql);

            } catch (SQLException e) { e.printStackTrace(); }
        }
        if(!telFlag)
        {
            try {
                Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
                String sql = "UPDATE `user_data` SET `tel` = "+ "'"+tel.getText() +"'"+" WHERE (`username`='"+CtrlLogin.user_data.getUsername()+"');";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.executeUpdate(sql);

            } catch (SQLException e) { e.printStackTrace(); }
        }
        if(!cityFlag)
        {
            try {
                Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
                String sql = "UPDATE `test`.`user_data` SET `city` =" + city.getText() +" WHERE (`username`='"+CtrlLogin.user_data.getUsername()+"');";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.executeUpdate(sql);

            } catch (SQLException e) { e.printStackTrace(); }
        }
        if(!emailFlag)
        {
            try {
                Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
                String sql = "UPDATE `test`.`user_data` SET `email` =" + email.getText() +" WHERE (`username`="+ CtrlLogin.user_data.getUsername()+");";
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

    private void getFlags()
    {
        if(name.getText().equals(CtrlLogin.user_data.getName()))
            nameFlag = true;
        if(lastName.getText().equals(CtrlLogin.user_data.getLastName()))
            lastNameFlag = true;
        if(Integer.parseInt(age.getText()) == (CtrlLogin.user_data.getAge()))
            ageFlag = true;
        if(tel.getText().equals(CtrlLogin.user_data.getTel()))
            telFlag = true;
        if(city.getText().equals(CtrlLogin.user_data.getCity()))
            cityFlag = true;
        if(email.getText().equals(CtrlLogin.user_data.getEmail()))
            emailFlag = true;
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
