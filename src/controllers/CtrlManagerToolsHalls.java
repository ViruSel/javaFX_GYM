package controllers;

import animatefx.animation.SlideInLeft;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public class CtrlManagerToolsHalls implements Initializable
{
    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane changedPane;
    @FXML
    private JFXListView<Halls> hallList;
    @FXML
    private JFXListView<UserData> trainerList;
    @FXML
    private JFXButton back;

    private final DB db = new DB();

    ObservableList<UserData> trainerListObs = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        back.getStylesheets().add("assets/virus_titlebar.css");
        hallList.getStylesheets().add("assets/virus_table.css");
        trainerList.getStylesheets().add("assets/virus_table.css");

        setHallsList(hallList);
        setTrainerList(trainerList);

        hallList.setItems(db.getAllHalls());
        trainerList.setItems(trainerListObs);

        showTrainers();
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

    private void showTrainers()
    {
        try{
            Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());

            ResultSet rs = con.createStatement().executeQuery("Select * FROM user WHERE `rank` = 'Trainer'");

            while(rs.next())
            {
                User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("rank"), rs.getString("subscription"));
                UserData userData = db.getUserDataByUsername(user.getUsername());
                trainerListObs.add(userData);
            }
        }
        catch (SQLException throwables) { throwables.printStackTrace(); }
    }

    static class CellHalls extends ListCell<Halls>
    {
        HBox hBox = new HBox();
        Label label = new Label();
        Pane pane = new Pane();

        public CellHalls()
        {
            super();

            hBox.getChildren().addAll(label,pane);
            HBox.setHgrow(pane, Priority.ALWAYS);
        }

        public void updateItem(Halls halls, boolean empty)
        {
            super.updateItem(halls, empty);
            setText(null);
            setGraphic(null);

            if(halls!=null && !empty)
            {
                label.setStyle("-fx-text-fill: white; -fx-font-size: 20");
                label.setText(halls.getName()+" "+halls.getCapacity());
                setGraphic(hBox);
            }
        }
    }

    private void setHallsList(JFXListView<Halls> list)
    {
        list.setCellFactory(param -> new CellHalls());
    }

    class CellTrainers extends ListCell<UserData>
    {
        HBox hBox = new HBox();
        Label label = new Label();
        Pane pane = new Pane();
        Image profilePic = new Image("assets/trainer material LLQ.png");
        ImageView img = new ImageView(profilePic);

        public CellTrainers()
        {
            super();

            hBox.getChildren().addAll(img,label,pane);
            HBox.setHgrow(pane, Priority.ALWAYS);
        }

        public void updateItem(UserData userData, boolean empty)
        {
            super.updateItem(userData, empty);
            setText(null);
            setGraphic(null);

            if(userData!=null && !empty)
            {
                TrainerData trainerData = db.getTrainerDataByUsername(userData.getUsername());

                label.setStyle("-fx-text-fill: white; -fx-font-size: 20");
                label.setText(userData.getName()+" "+userData.getLastName()+" - "+ trainerData.getActivity());

                setGraphic(hBox);
            }
        }
    }

    private void setTrainerList(JFXListView<UserData> list)
    {
        list.setCellFactory(param -> new CellTrainers());
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
