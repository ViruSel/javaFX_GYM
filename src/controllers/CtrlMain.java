package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import animatefx.animation.SlideInLeft;
import animatefx.animation.SlideInRight;
import com.jfoenix.controls.JFXButton;
import gym.Main;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CtrlMain implements Initializable
{
    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane changedPane;
    @FXML
    private JFXButton login;
    @FXML
    private JFXButton signUp;
    @FXML
    private JFXButton xButton;
    @FXML
    private JFXButton minimize;
    @FXML
    private HBox titleBar;

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        makeStageDraggable();
        minimize.getStylesheets().add("assets/virus_titlebar.css");
        xButton.getStylesheets().add("assets/virus.css");
    }

    @FXML
    public void loginAction()
    {
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(.10));
        pt.setOnFinished(e -> setPageLogin());
        pt.play();
    }

    @FXML
    public void signUpAction()
    {
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(.10));
        pt.setOnFinished(e -> setPageSignUp());
        pt.play();
    }

    @FXML
    private void minimizeAction()
    {
        Stage mainStage = (Stage) minimize.getScene().getWindow();
        mainStage.setIconified(true);
    }

    @FXML
    public void xButtonAction() { Platform.exit(); }

    public void makeStageDraggable()
    {
        titleBar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        titleBar.setOnMouseDragged(event -> {
            Main.stage.setX(event.getScreenX() - xOffset);
            Main.stage.setY(event.getScreenY() - yOffset);
        });
    }

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

    private void setNodeSignUp(Node node)
    {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        SlideInRight slideInRight = new SlideInRight(changedPane);
        slideInRight.setNode(node);
        slideInRight.setCycleCount(1);
        slideInRight.setSpeed(1);
        slideInRight.play();
    }

    private void setPageLogin()
    {
        try
        {
            changedPane = FXMLLoader.load(getClass().getResource("src/scenes/Login.fxml"));
            setNodeLogin(changedPane);
        }
        catch (IOException e) { e.printStackTrace();}
    }
    private void setPageSignUp()
    {
        try
        {
            changedPane = FXMLLoader.load(getClass().getResource("src/scenes/SignUp.fxml"));
            setNodeSignUp(changedPane);
        }
        catch (IOException e) { e.printStackTrace();}
    }
}
