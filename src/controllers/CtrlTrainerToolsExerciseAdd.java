package controllers;

import animatefx.animation.SlideInLeft;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import database.DB;
import database.TrainerData;
import database.UserData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CtrlTrainerToolsExerciseAdd implements Initializable
{

    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane changedPane;
    @FXML
    private JFXButton back;
    @FXML
    private JFXRadioButton athlete;
    @FXML
    private JFXRadioButton vShape;
    @FXML
    private JFXRadioButton yoga;
    @FXML
    private JFXRadioButton aerobics;

    ToggleGroup tg = new ToggleGroup();

    private final DB db = new DB();
    private final UserData selectedUser = CtrlTrainerToolsExercise.userDataToAddExercise;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        back.getStylesheets().add("assets/virus_titlebar.css");
        athlete.getStylesheets().add("assets/virus.css");
        vShape.getStylesheets().add("assets/virus.css");
        yoga.getStylesheets().add("assets/virus.css");
        aerobics.getStylesheets().add("assets/virus.css");

        athlete.setToggleGroup(tg);
        vShape.setToggleGroup(tg);
        yoga.setToggleGroup(tg);
        aerobics.setToggleGroup(tg);

        checkTrainer();
    }

    @FXML
    void setPlanAerobics()
    {
        try {
            Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
            String sql = "UPDATE `user_data` SET `exercisePlan` =" + "'Aerobics'" + " WHERE (`username`='" + selectedUser.getUsername() + "');";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Selected exercise plan: Aerobics!\nEnjoy yourself!");
            alert.show();
        }
        catch (SQLException throwables) { throwables.printStackTrace(); }
    }

    @FXML
    void setPlanAthlete()
    {
        try {
            Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
            String sql = "UPDATE `user_data` SET `exercisePlan` =" + "'Athlete'" + " WHERE (`username`='" + selectedUser.getUsername() + "');";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Selected exercise plan: Athlete!\nEnjoy yourself!");
            alert.show();
        }
        catch (SQLException throwables) { throwables.printStackTrace(); }
    }

    @FXML
    void setPlanVShape()
    {
        try {
            Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
            String sql = "UPDATE `user_data` SET `exercisePlan` =" + "'Vshape'" + " WHERE (`username`='" + selectedUser.getUsername() + "');";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Selected exercise plan: V-Shaphe!\nEnjoy yourself!");
            alert.show();
        }
        catch (SQLException throwables) { throwables.printStackTrace(); }
    }

    @FXML
    void setPlanYoga()
    {
        try {
            Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
            String sql = "UPDATE `user_data` SET `exercisePlan` =" + "'Yoga'" + " WHERE (`username`='" + selectedUser.getUsername() + "');";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Selected exercise plan: Yoga!\nEnjoy yourself!");
            alert.show();
        }
        catch (SQLException throwables) { throwables.printStackTrace(); }
    }

    @FXML
    public void backButtonAction()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/TrainerToolsExercise.fxml"));
            setNode(changedPane);

        }
        catch (IOException e) { e.printStackTrace(); }
    }

    private void checkTrainer()
    {
        TrainerData trainerData = db.getTrainerDataByUsername(CtrlLogin.user.getUsername());

        switch (trainerData.getSpecialization())
        {
            case "GYM":
                aerobics.setDisable(true);
                yoga.setDisable(true);
                break;
            case "Aerobics":
                athlete.setDisable(true);
                vShape.setDisable(true);
                yoga.setDisable(true);
                break;
            case "Yoga":
                athlete.setDisable(true);
                vShape.setDisable(true);
                aerobics.setDisable(true);
                break;
        }
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
