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

public class CtrlProfilePage implements Initializable {

    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane changedPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    @FXML
    private void subscriptionButtonAction() { setPageSubscriptions(); }

    @FXML
    private void trainerButtonAction() { setPageTrainers(); }

    @FXML
    private void hallsButtonAction() { setPageHalls(); }

    @FXML
    private void mapButtonAction() { setPageMap(); }

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

    private void setPageSubscriptions()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/ProfileSubscriptions.fxml"));
            setNode(changedPane);

        } catch (IOException e) { e.printStackTrace(); }
    }

    private void setPageTrainers()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/ProfileTrainers.fxml"));
            setNode(changedPane);

        } catch (IOException e) { e.printStackTrace(); }
    }

    private void setPageHalls()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/ProfileHalls.fxml"));
            setNode(changedPane);

        } catch (IOException e) { e.printStackTrace(); }
    }

    private void setPageMap()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/ProfileMap.fxml"));
            setNode(changedPane);

        } catch (IOException e) { e.printStackTrace(); }
    }
}
