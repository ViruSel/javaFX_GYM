package controllers;

import animatefx.animation.SlideInLeft;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import database.DB;
import database.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static controllers.CtrlProfileTrainers.selectedProfileUserData;

public class CtrlProfileTrainerProfile implements Initializable
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
    private Label ratingLabel;
    @FXML
    private Rating rating;
    @FXML
    private JFXButton reviewButton;
    @FXML
    private JFXTextArea textArea;
    @FXML
    private JFXButton back;
    @FXML
    private JFXButton doneButton;

    private final DB db = new DB();
    private final User user = db.getUserByUsername(selectedProfileUserData.getUsername());

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        back.getStylesheets().add("assets/virus_titlebar.css");
        doneButton.getStylesheets().add("assets/virus_titlebar.css");
        reviewButton.getStylesheets().add("assets/virus_titlebar.css");
        textArea.setStyle("-fx-text-inner-color: #ffffff; -fx-background-color: #181818");

        name.setText(selectedProfileUserData.getName());
        lastName.setText(selectedProfileUserData.getLastName());
        age.setText(String.valueOf(selectedProfileUserData.getAge()));
        gender.setText(selectedProfileUserData.getGender());
        tel.setText(selectedProfileUserData.getTel());
        setEmail();
    }

    @FXML
    public void backButtonAction(ActionEvent event)
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/ProfileTrainers.fxml"));
            setNode(changedPane);

        } catch (IOException e) { e.printStackTrace(); }
    }

    @FXML
    void reviewButtonAction()
    {

    }

    @FXML
    void doneButtonAction()
    {

    }

    private void setEmail ()
    {
        if(selectedProfileUserData.getEmail().equals(""))
            email.setText("No Email");
        else
            email.setText(selectedProfileUserData.getEmail());
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
