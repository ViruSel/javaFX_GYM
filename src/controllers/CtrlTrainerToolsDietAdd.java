package controllers;

import animatefx.animation.SlideInLeft;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import database.DB;
import database.UserData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CtrlTrainerToolsDietAdd implements Initializable
{
    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane changedPane;
    @FXML
    private JFXButton back;
    @FXML
    private JFXRadioButton mediterranean;
    @FXML
    private JFXRadioButton lowCarb;
    @FXML
    private JFXRadioButton vegan;
    @FXML
    private JFXRadioButton paleo;

    ToggleGroup tg = new ToggleGroup();

    private final DB db = new DB();
    private final UserData selectedUser = CtrlTrainerToolsDiet.userDataToAddDiet;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        back.getStylesheets().add("assets/virus_titlebar.css");
        mediterranean.getStylesheets().add("assets/virus.css");
        lowCarb.getStylesheets().add("assets/virus.css");
        vegan.getStylesheets().add("assets/virus.css");
        paleo.getStylesheets().add("assets/virus.css");

        mediterranean.setToggleGroup(tg);
        lowCarb.setToggleGroup(tg);
        vegan.setToggleGroup(tg);
        paleo.setToggleGroup(tg);
    }

    @FXML
    void setDietLowCarbs()
    {
        try {
            Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
            String sql = "UPDATE `user_data` SET `diet` =" + "'LowCarbs'" + " WHERE (`username`='" + selectedUser.getUsername() + "');";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }
        catch (SQLException throwables) { throwables.printStackTrace(); }
    }

    @FXML
    void setDietMediterranean()
    {
        try {
            Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
            String sql = "UPDATE `user_data` SET `diet` =" + "'Mediterranean'" + " WHERE (`username`='" + selectedUser.getUsername() + "');";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }
        catch (SQLException throwables) { throwables.printStackTrace(); }
    }

    @FXML
    void setDietPaleo()
    {
        {
            try {
                Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
                String sql = "UPDATE `user_data` SET `diet` =" + "'Paleo'" + " WHERE (`username`='" + selectedUser.getUsername() + "');";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.executeUpdate();
            }
            catch (SQLException throwables) { throwables.printStackTrace(); }
        }
    }

    @FXML
    void setDietVegan()
    {
        {
            try {
                Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
                String sql = "UPDATE `user_data` SET `diet` =" + "'Vegan'" + " WHERE (`username`='" + selectedUser.getUsername() + "');";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.executeUpdate();
            }
            catch (SQLException throwables) { throwables.printStackTrace(); }
        }
    }

    @FXML
    public void backButtonAction()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/TrainerToolsDiet.fxml"));
            setNode(changedPane);

        }
        catch (IOException e) { e.printStackTrace(); }
    }

    private void setNode(Node node)
    {
        holderPane.getChildren().clear();
        holderPane.getChildren().add(node);

        SlideInLeft slideInLeft = new SlideInLeft(changedPane);
        slideInLeft.setNode(node);
        slideInLeft.setCycleCount(1);
        slideInLeft.setSpeed(1);
        slideInLeft.play();
    }
}
