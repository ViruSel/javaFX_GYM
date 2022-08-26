package controllers;

import animatefx.animation.SlideInLeft;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import database.DB;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CtrlHomeFeedback implements Initializable
{
    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane changedPane;
    @FXML
    private JFXButton back;
    @FXML
    private JFXButton send;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private JFXTextArea textArea;
    @FXML
    private JFXRadioButton trainer;
    @FXML
    private JFXRadioButton manager;
    @FXML
    private ToggleGroup tg = new ToggleGroup();

    private DB db = new DB();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        textArea.setStyle("-fx-text-inner-color: #ffffff; -fx-background-color: #181818");
        back.getStylesheets().add("assets/virus_titlebar.css");
        trainer.getStylesheets().add("assets/virus.css");
        trainer.setToggleGroup(tg);
        manager.getStylesheets().add("assets/virus.css");
        manager.setToggleGroup(tg);
        checkUser();
    }

    @FXML
    public void sendButtonAction()
    {
        if(trainer.isSelected() || manager.isSelected())
        try{
            Connection con = DriverManager.getConnection(db.getHOST(),db.getUSERNAME(),db.getPASSWORD());
            String sql = "INSERT INTO `feedback` (`forWho`, `text`) VALUES ('"+findForWho()+"'"+","+"'"+textArea.getText()+"');";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate(sql);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Feedback sent successfully!");
            alert.show();
        }
        catch (SQLException throwables) { throwables.printStackTrace(); }
    }

    @FXML
    public void backButtonAction()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/HomePage.fxml"));
            setNode(changedPane);

        }
        catch (IOException e) { e.printStackTrace(); }
    }

    private String findForWho()
    {
        String forWho = "";

        if(trainer.isSelected())
            forWho = "Trainer";

        if(manager.isSelected())
            forWho = "Manager";

        return forWho;
    }

    private void checkUser()
    {
        if(CtrlLogin.user.isManager() || CtrlLogin.user.isTrainer())
        {
            label1.setText("The feedback option is not for you!");
            label2.setText("Please go back and mind your bussines!");
            textArea.setPromptText("I SAID THE FEEDBACK OPTION IS NOT FOR YOU!");
            textArea.setDisable(true);
            trainer.setDisable(true);
            manager.setDisable(true);
            send.setDisable(true);
        }
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
}
