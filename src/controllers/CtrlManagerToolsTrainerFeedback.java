package controllers;

import animatefx.animation.SlideInLeft;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import database.DB;
import database.Feedback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CtrlManagerToolsTrainerFeedback implements Initializable
{
    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane changedPane;
    @FXML
    private JFXButton back;
    @FXML
    private JFXListView<Feedback> feedbackList;

    private final DB db = new DB();

    ObservableList<Feedback> feedbackListObs = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        back.getStylesheets().add("assets/virus_titlebar.css");
        feedbackList.getStylesheets().add("assets/virus_table.css");
        setFeedbackList(feedbackList);
        feedbackList.setItems(feedbackListObs);
        showFeedback();
    }

    @FXML
    public void backButtonAction()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/ManagerToolsPage.fxml"));
            setNode(changedPane);
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    private void showFeedback()
    {
        try{
            Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());

            ResultSet rs = con.createStatement().executeQuery("Select * FROM feedback WHERE `forWho` = 'Trainer'");

            while(rs.next())
            {
                Feedback feedback = new Feedback(rs.getString("forWho"), rs.getString("text"));
                feedbackListObs.add(feedback);
            }
        }
        catch (SQLException throwables) { throwables.printStackTrace(); }
    }

    static class Cell extends ListCell<Feedback>
    {
        HBox hBox = new HBox();
        Label label = new Label();
        Pane pane = new Pane();

        public Cell()
        {
            super();

            hBox.getChildren().addAll(label,pane);
            HBox.setHgrow(pane, Priority.ALWAYS);
        }

        public void updateItem(Feedback feedback, boolean empty)
        {
            super.updateItem(feedback, empty);
            setText(null);
            setGraphic(null);

            if(feedback!=null && !empty)
            {
                label.setStyle("-fx-text-fill: white; -fx-font-size: 20");
                label.setText(feedback.getText());
                setGraphic(hBox);
            }
        }
    }

    private void setFeedbackList(JFXListView<Feedback> list)
    {
        list.setCellFactory(param -> new Cell());
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
