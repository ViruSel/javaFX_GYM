package controllers;

import animatefx.animation.SlideInLeft;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CtrlHomeSubscriptions implements Initializable
{
    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane changedPane;
    @FXML
    private Label adminTextTop;
    @FXML
    private Label adminTextDown;
    @FXML
    private JFXRadioButton day1Sub;
    @FXML
    private JFXRadioButton month1Sub;
    @FXML
    private JFXRadioButton month3Sub;
    @FXML
    private JFXRadioButton month6Sub;
    @FXML
    private JFXButton back;
    @FXML
    private ToggleGroup tg = new ToggleGroup();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        back.getStylesheets().add("assets/virus_titlebar.css");
        day1Sub.getStylesheets().add("src/assets/virus.css");
        month1Sub.getStylesheets().add("assets/virus.css");
        month3Sub.getStylesheets().add("assets/virus.css");
        month6Sub.getStylesheets().add("assets/virus.css");

        day1Sub.setToggleGroup(tg);
        month1Sub.setToggleGroup(tg);
        month3Sub.setToggleGroup(tg);
        month6Sub.setToggleGroup(tg);
        adminTextTop.setVisible(false);
        adminTextDown.setVisible(false);

        checkSub();
        checkUser();
    }

    @FXML
    public void subButtonAction()
    {
        if(day1Sub.isSelected())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Our payment services are offline right now,\nPlease try again later!");
            alert.show();
            day1Sub.setSelected(false);
        }
        else if(month1Sub.isSelected())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Our payment services are offline right now,\nPlease try again later!");
            alert.show();
            month1Sub.setSelected(false);
        }
        else if(month3Sub.isSelected())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Our payment services are offline right now,\nPlease try again later!");
            alert.show();
            month3Sub.setSelected(false);
        }
        else if(month6Sub.isSelected())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Our payment services are offline right now,\nPlease try again later!");
            alert.show();
            month6Sub.setSelected(false);
        }
    }

    @FXML
    private void backButtonAction()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("src/scenes/HomePage.fxml"));
            setNode(changedPane);

        }
        catch (IOException e) { e.printStackTrace(); }
    }

    private void setNode(Node node)
    {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        SlideInLeft slideInLeft = new SlideInLeft(changedPane);
        slideInLeft.setNode(node);
        slideInLeft.setCycleCount(1);
        slideInLeft.setSpeed(1);
        slideInLeft.play();
    }

    private void checkSub()
    {
        switch (CtrlLogin.user.getSubscription())
        {
            case "1 Day Subscription":
                day1Sub.setDisable(true);
                break;
            case "1 Month Subscription":
                month1Sub.setDisable(true);
                break;
            case "3 Month Subscription":
                month3Sub.setDisable(true);
                break;
            case "6 Month Subscription":
                month6Sub.setDisable(true);
                break;
        }
    }

    private void checkUser()
    {
        if(CtrlLogin.user.isManager() || CtrlLogin.user.isTrainer())
        {
            adminTextTop.setVisible(true);
            adminTextDown.setVisible(true);
            day1Sub.setDisable(true);
            month1Sub.setDisable(true);
            month3Sub.setDisable(true);
            month6Sub.setDisable(true);
        }
    }
}
