package controllers;

import animatefx.animation.SlideInLeft;
import com.jfoenix.controls.JFXButton;
import main.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CtrlMenu implements Initializable
{
    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane changedPane;
    @FXML
    private Label welcomeText;
    @FXML
    private HBox titleBar;
    @FXML
    private JFXButton trainerTools;
    @FXML
    private JFXButton managerTools;
    @FXML
    private JFXButton minimize;
    @FXML
    private JFXButton maximize;
    @FXML
    private JFXButton xButton;
    @FXML
    private JFXButton logOut;

    private double xOffset = 0;
    private double yOffset = 0;

    private static Main main;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        checkUser();
        makeStageDraggable();
        minimize.getStylesheets().add("assets/virus_titlebar.css");
        maximize.getStylesheets().add("assets/virus_titlebar.css");
        xButton.getStylesheets().add("assets/virus.css");
        welcomeText.setText("Henlo, " + CtrlLogin.user.getUsername() + "!");
        welcomeText.setMaxWidth(Double.MAX_VALUE);
    }

    @FXML
    private void homeButtonAction() { setPageHome(); }

    @FXML
    private void profileButtonAction() { setPageProfile(); }

    @FXML
    private void contactInfoButtonAction() { setPageContactInfo(); }

    @FXML
    private void aboutUsButtonAction() { setPageAboutUs(); }

    @FXML
    private void settingsButtonAction() { setPageSettings(); }

    @FXML
    private void trainerToolsButtonAction() { setPageTrainerTools(); }

    @FXML
    private void managerToolsButtonAction() { setPageManagerTools(); }

    @FXML
    private void logOutAction()
    {
        Stage mainStage = (Stage) logOut.getScene().getWindow();
        mainStage.close();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../scenes/Main.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage loginStage = new Stage();
            this.main.stage = loginStage;
            loginStage.initStyle(StageStyle.UNDECORATED);
            loginStage.setScene(new Scene(root));
            loginStage.setResizable(false);
            loginStage.show();

        } catch (IOException e) { e.printStackTrace(); }
    }

    @FXML
    private void minimizeAction()
    {
        Stage mainStage = (Stage) minimize.getScene().getWindow();
        mainStage.setIconified(true);
    }

    @FXML
    private void maximizeAction()
    {
        if(Main.stage.isMaximized())
        {
            Main.stage.setMaximized(false);
            holderPane.getTransforms().clear();
        }
        else
        {
            Main.stage.setMaximized(true);
            Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
            double width = resolution.getWidth();
            double height = resolution.getHeight();
            double w = width/1216;
            double h = height/684;
            Scale scale = new Scale(w, h, 0, 0);
            holderPane.getTransforms().add(scale);
        }
    }

    @FXML
    public void xButtonAction() { Platform.exit(); }

    public void checkUser()
    {
        if(CtrlLogin.user.isTrainer())
        {
            trainerTools.setVisible(true);
            managerTools.setVisible(false);
        }
        else if(CtrlLogin.user.isManager())
        {
            trainerTools.setVisible(false);
            managerTools.setVisible(true);
        }
        else
        {
            trainerTools.setVisible(false);
            managerTools.setVisible(false);
        }
    }

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

    private void setPageHome()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/Home.fxml"));
            setNode(changedPane);

        }
        catch (IOException e) { e.printStackTrace(); }
    }

    private void setPageProfile()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/Profile.fxml"));
            setNode(changedPane);
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    private void setPageContactInfo()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/ContactInfo.fxml"));
            setNode(changedPane);
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    private void setPageAboutUs()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/AboutUs.fxml"));
            setNode(changedPane);

        }
        catch (IOException e) { e.printStackTrace(); }
    }

    private void setPageSettings()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/Settings.fxml"));
            setNode(changedPane);

        }
        catch (IOException e) { e.printStackTrace(); }
    }

    private void setPageTrainerTools()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/TrainerTools.fxml"));
            setNode(changedPane);

        }
        catch (IOException e) { e.printStackTrace(); }
    }

    private void setPageManagerTools()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/ManagerTools.fxml"));
            setNode(changedPane);

        }
        catch (IOException e) { e.printStackTrace(); }
    }
}
