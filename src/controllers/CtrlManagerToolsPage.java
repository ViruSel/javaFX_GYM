package controllers;

import animatefx.animation.SlideInRight;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CtrlManagerToolsPage implements Initializable
{
    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane changedPane;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    @FXML
    void hallsButtonAction() { setPageHalls(); }

    @FXML
    void hireAndFireButtonAction() { setPageHireAndFire(); }

    @FXML
    void managerFeedbackButtonAction() { setPageManagerFeedback(); }

    @FXML
    void trainerFeedbackButtonAction() { setPageTrainerFeedback(); }

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

    @FXML
    public void setPageHalls()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/ManagerToolsHalls.fxml"));
            setNode(changedPane);
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    @FXML
    public void setPageHireAndFire()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/ManagerToolsHireAndFire.fxml"));
            setNode(changedPane);
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    @FXML
    public void setPageManagerFeedback()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/ManagerToolsManagerFeedback.fxml"));
            setNode(changedPane);
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    @FXML
    public void setPageTrainerFeedback()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/ManagerToolsTrainerFeedback.fxml"));
            setNode(changedPane);
        }
        catch (IOException e) { e.printStackTrace(); }
    }
}
