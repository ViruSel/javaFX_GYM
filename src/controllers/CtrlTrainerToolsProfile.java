package controllers;

import animatefx.animation.SlideInLeft;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CtrlTrainerToolsProfile implements Initializable
{
    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane changedPane;
    @FXML
    private JFXButton back;
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

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        back.getStylesheets().add("assets/virus_titlebar.css");

        name.setText(CtrlTrainerToolsAccept.CustomerData.getName());
        lastName.setText(CtrlTrainerToolsAccept.CustomerData.getLastName());
        age.setText(String.valueOf(CtrlTrainerToolsAccept.CustomerData.getAge()));
        gender.setText(CtrlTrainerToolsAccept.CustomerData.getGender());
        tel.setText(CtrlTrainerToolsAccept.CustomerData.getTel());

        setEmail();

    }

    @FXML
    public void backButtonAction()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/TrainerToolsAccept.fxml"));
            setNode(changedPane);

        } catch (IOException e) { e.printStackTrace(); }

    }

    private void setEmail ()
    {
        if(CtrlTrainerToolsAccept.CustomerData.getEmail().equals(""))
            email.setText("No Email");
        else
            email.setText(CtrlTrainerToolsAccept.CustomerData.getEmail());
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