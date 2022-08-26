package controllers;

import animatefx.animation.SlideInLeft;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CtrlProfileSubscriptions implements Initializable
{
    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane changedPane;
    @FXML
    private JFXButton back;
    @FXML
    private Label subscriptionText;
    @FXML
    private Label eligibleText;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        subscriptionText.setText("Subscription: "+CtrlLogin.user.getSubscription());
        back.getStylesheets().add("assets/virus_titlebar.css");
        isEligible();
    }

    @FXML
    private void backButtonAction(ActionEvent event)
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/ProfilePage.fxml"));
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

    private void isEligible()
    {
        if(CtrlLogin.user.getSubscription().equals("Free"))
            eligibleText.setText("You are currently not eligible to use our GYM's.\n Please purchase a subscription from Home Page!");
        else
            eligibleText.setText("You are currently very eligible to use our GYM's");
    }


}
