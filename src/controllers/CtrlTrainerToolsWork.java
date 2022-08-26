package controllers;

import animatefx.animation.SlideInLeft;
import com.jfoenix.controls.JFXButton;
import database.DB;
import database.TrainerData;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CtrlTrainerToolsWork implements Initializable
{
    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane changedPane;
    @FXML
    private JFXButton startButton;
    @FXML
    private JFXButton stopButton;
    @FXML
    private Label workTime;
    @FXML
    private Label timeLabel;

    private final DB db = new DB();
    private final TrainerData trainerData = db.getTrainerDataByUsername(CtrlLogin.user.getUsername());
    Time sqlTime = (trainerData.getActivity());
    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;

    private Timeline timeline;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        sqlTime = trainerData.getActivity();
        workTime.setText("Today's worked hours: " + sqlTime.getHours() +":" + sqlTime.getMinutes() + ":" + sqlTime.getSeconds());
        timeLabel.setText(0 +":" + 0 + ":" + 0);

        timeline = new Timeline(new KeyFrame(
                (Duration.ZERO),
                actionEvent ->
                {
                    checkTime();
                    timeLabel.setText(hours + ":" + minutes + ":" + seconds);
                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    @FXML
    void startButtonAction()
    {
        timeline.play();
        timeline.setCycleCount(Animation.INDEFINITE);

        startButton.setVisible(false);
        stopButton.setVisible(true);
    }

    @FXML
    void stopButtonAction()
    {
        timeline.stop();
        startButton.setVisible(true);
        stopButton.setVisible(false);
        sqlTime.setSeconds(sqlTime.getSeconds()+seconds);
        sqlTime.setMinutes(sqlTime.getMinutes()+minutes);
        sqlTime.setHours(sqlTime.getHours()+hours);
        getWorkHours();
        seconds = 0;
        minutes = 0;
        hours = 0;
        timeLabel.setText(0 +":" + 0 + ":" + 0);
        workTime.setText("Today's worked hours: " + sqlTime.getHours() +":" + sqlTime.getMinutes() + ":" + sqlTime.getSeconds());
    }

    @FXML
    public void backButtonAction()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/TrainerToolsPage.fxml"));
            setNode(changedPane);
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    private void getWorkHours()
    {
        try {
            Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
            String sql = "UPDATE `trainer_data` SET `activity` =" + "'" + sqlTime +"'"+ " WHERE (`username`='" + trainerData.getUsername() + "');";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate(sql);
            trainerData.setActivity(sqlTime);
        }
        catch (SQLException throwables) { throwables.printStackTrace(); }

    }

    private void checkTime()
    {
        if (seconds > 60)
        {
            seconds = 0;
            minutes++;
        }
        if (minutes > 60)
        {
            seconds = 0;
            minutes = 0;
            hours++;
        }
        seconds++;
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
