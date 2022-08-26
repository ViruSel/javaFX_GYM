package controllers;

import animatefx.animation.SlideInLeft;
import com.jfoenix.controls.JFXButton;
import database.DB;
import database.TrainerData;
import database.UserData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import static controllers.CtrlHomeTrainers.selectedUserData;

public class CtrlHomeTrainerProfile implements Initializable
{
    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane changedPane;
    @FXML
    private Label name;
    @FXML
    private Label lastName;
    @FXML
    private Label age;
    @FXML
    private Label gender;
    @FXML
    private Label tel;
    @FXML
    private Label email;
    @FXML
    private Label trainerInfo;
    @FXML
    private Label ratingLabel;
    @FXML
    private Rating rating;
    @FXML
    private JFXButton assignTrainerButton;
    @FXML
    private JFXButton back;

    private final DB db = new DB();
    private final TrainerData trainerData = db.getTrainerDataByUsername(selectedUserData.getUsername());
    private final UserData userData = db.getUserDataByUsername(CtrlLogin.user_data.getUsername());

    public void initialize(URL location, ResourceBundle resources)
    {
        back.getStylesheets().add("assets/virus_titlebar.css");
        assignTrainerButton.getStylesheets().add("assets/virus_titlebar.css");

        name.setText(selectedUserData.getName());
        lastName.setText(selectedUserData.getLastName());
        age.setText(String.valueOf(selectedUserData.getAge()));
        gender.setText(selectedUserData.getGender());
        tel.setText(selectedUserData.getTel());

        setEmail();
        setTrainerInfo();
    }

    @FXML
    void assignTrainerButtonAction()
    {
        try {
            Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
            String updateUserDataGymRequest = "UPDATE `user_data` SET `requestGymTrainer` =" + "'" + trainerData.getUsername() + "'" + " WHERE (`username`='" + userData.getUsername() + "');";
            String updateUserDataAerobicsRequest = "UPDATE `user_data` SET `requestAerobicsTrainer` =" + "'" + trainerData.getUsername() + "'" + " WHERE (`username`='" + userData.getUsername() + "');";
            String updateUserDataYogaRequest = "UPDATE `user_data` SET `requestYogaTrainer` =" + "'" + trainerData.getUsername() + "'" + " WHERE (`username`='" + userData.getUsername() + "');";
            PreparedStatement updateGymRequest = con.prepareStatement(updateUserDataGymRequest);
            PreparedStatement updateAerobicsRequest = con.prepareStatement(updateUserDataAerobicsRequest);
            PreparedStatement updateYogaRequest = con.prepareStatement(updateUserDataYogaRequest);

            switch (trainerData.getSpecialization())
            {
                case "GYM":
                    if(!userData.getGymTrainer().equals(""))
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("You already have GYM trainer");
                        alert.show();
                    }
                    else if(Objects.equals(userData.getRequestGymTrainer(), trainerData.getUsername()))
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("You already sent a request to this GYM trainer!");
                        alert.show();
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("You successfully sent a request to this GYM trainer!");
                        alert.show();

                        updateGymRequest.executeUpdate(updateUserDataGymRequest);
                        userData.setRequestGymTrainer(trainerData.getUsername());
                    }
                    break;
                case "Aerobics":
                    if(!userData.getAerobicsTrainer().equals(""))
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("You already have Aerobics trainer");
                        alert.show();
                    }
                    else if(Objects.equals(userData.getRequestAerobicsTrainer(), trainerData.getUsername()))
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("You already sent a request to this Aerobics trainer!");
                        alert.show();
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("You've successfully sent a request to this Aerobics trainer!");
                        alert.show();

                        updateAerobicsRequest.executeUpdate(updateUserDataAerobicsRequest);
                        userData.setRequestAerobicsTrainer(trainerData.getUsername());
                    }
                    break;
                case "Yoga":
                    if(!userData.getYogaTrainer().equals(""))
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("You already have Aerobics trainer");
                        alert.show();
                    }
                    else if(Objects.equals(userData.getRequestYogaTrainer(), trainerData.getUsername()))
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("You already sent a request to this Yoga trainer!");
                        alert.show();
                    }
                    else
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("You've successfully sent a request to this Yoga trainer!");
                        alert.show();

                        updateYogaRequest.executeUpdate(updateUserDataYogaRequest);
                        userData.setRequestYogaTrainer(trainerData.getUsername());
                    }
                    break;
            }
        }
        catch (SQLException throwables) { throwables.printStackTrace(); }
    }

    @FXML
    public void backButtonAction()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/HomeTrainers.fxml"));
            setNode(changedPane);
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    private void setTrainerInfo()
    {
        if(trainerData.getCustomer1() == null || trainerData.getCustomer2() == null || trainerData.getCustomer3() == null || trainerData.getCustomer1() != null || trainerData.getCustomer2() != null || trainerData.getCustomer3() != null)
            trainerInfo.setText("- At least one Slot free! -");
        else
            trainerInfo.setText("- This Trainer is kinda busy, try again later! -");
    }

    private void setEmail ()
    {
        if(selectedUserData.getEmail().equals(""))
            email.setText("No Email");
        else
            email.setText(selectedUserData.getEmail());
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
