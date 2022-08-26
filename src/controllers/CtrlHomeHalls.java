package controllers;

import animatefx.animation.SlideInLeft;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import database.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CtrlHomeHalls implements Initializable
{
    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane changedPane;
    @FXML
    private JFXButton back;
    @FXML
    private JFXRadioButton mainHall;
    @FXML
    private JFXRadioButton secondHall;
    @FXML
    private JFXRadioButton yogaHall;
    @FXML
    private JFXRadioButton aerobicsHall;
    @FXML
    private JFXRadioButton spaHall;
    @FXML
    private JFXRadioButton saunaHall;

    private final ToggleGroup tg = new ToggleGroup();

    private DB db = new DB();

    public static String hallSelected = "";

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        back.getStylesheets().add("assets/virus_titlebar.css");
        mainHall.getStylesheets().add("assets/virus.css");
        secondHall.getStylesheets().add("assets/virus.css");
        yogaHall.getStylesheets().add("assets/virus.css");
        aerobicsHall.getStylesheets().add("assets/virus.css");
        spaHall.getStylesheets().add("assets/virus.css");
        saunaHall.getStylesheets().add("assets/virus.css");

        mainHall.setToggleGroup(tg);
        secondHall.setToggleGroup(tg);
        yogaHall.setToggleGroup(tg);
        aerobicsHall.setToggleGroup(tg);
        spaHall.setToggleGroup(tg);
        saunaHall.setToggleGroup(tg);
    }

    @FXML
    void subHall()
    {
        checkHallSub();

        if(mainHall.isSelected() || secondHall.isSelected() || yogaHall.isSelected() || aerobicsHall.isSelected() || spaHall.isSelected() || saunaHall.isSelected())
            try{
                Connection con = DriverManager.getConnection(db.getHOST(),db.getUSERNAME(),db.getPASSWORD());
                String sql = "UPDATE `test`.`user_data` SET `hall` = '"+ hallSelected +"' WHERE (`username` = '"+CtrlLogin.user.getUsername()+"');";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.executeUpdate(sql);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Hall was selected successfully!");
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

    private void checkHallSub()
    {
        if(mainHall.isSelected())
            hallSelected = "Main Hall";
        else if(secondHall.isSelected())
            hallSelected = "Second Hall";
        else if(yogaHall.isSelected())
            hallSelected = "Yoga Hall";
        else if(aerobicsHall.isSelected())
            hallSelected = "Aerobics Hall";
        else if(spaHall.isSelected())
            hallSelected = "SPA";
        else if(saunaHall.isSelected())
            hallSelected = "Sauna";
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
