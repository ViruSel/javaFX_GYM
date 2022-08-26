package controllers;

import animatefx.animation.SlideInLeft;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import database.DB;
import database.TrainerData;
import database.User;
import database.UserData;
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
import java.sql.*;
import java.util.ResourceBundle;

public class CtrlManagerToolsHireAndFire implements Initializable
{
    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane changedPane;
    @FXML
    private JFXListView<UserData> customerList;
    @FXML
    private JFXListView<UserData> trainerList;
    @FXML
    private JFXButton back;

    private final DB db = new DB();
    public static UserData userData = new UserData();
    private final TrainerData trainerData = new TrainerData();

    ObservableList<UserData> customerListObs = FXCollections.observableArrayList();
    ObservableList<UserData> trainerListObs = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        back.getStylesheets().add("assets/virus_titlebar.css");
        customerList.getStylesheets().add("assets/virus_table.css");
        trainerList.getStylesheets().add("assets/virus_table.css");

        setListCustomers(customerList);
        setListTrainer(trainerList);

        customerList.setItems(customerListObs);
        trainerList.setItems(trainerListObs);

        showCustomers();
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

    private void showCustomers()
    {
        try{
            Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());

            ResultSet rs = con.createStatement().executeQuery("Select * FROM user WHERE `rank` = 'Customer'");

            while(rs.next())
            {
                User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("rank"), rs.getString("subscription"));
                UserData userData = db.getUserDataByUsername(user.getUsername());
                customerListObs.add(userData);
            }
        }
        catch (SQLException throwables) { throwables.printStackTrace(); }
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

    class CellCustomers extends ListCell<UserData>
    {
        HBox hBox = new HBox();
        JFXButton makeTrainer = new JFXButton("Make Trainer");
        Label label = new Label();
        Pane pane = new Pane();
        Image profilePic = new Image("assets/userPic LLQ.png");
        ImageView img = new ImageView(profilePic);

        public CellCustomers()
        {
            super();

            hBox.getChildren().addAll(img,label,pane, makeTrainer);
            HBox.setHgrow(pane, Priority.ALWAYS);
        }

        public void updateItem(UserData userData, boolean empty)
        {
            super.updateItem(userData, empty);
            setText(null);
            setGraphic(null);

            if(userData!=null && !empty)
            {
                label.setStyle("-fx-text-fill: white; -fx-font-size: 20");
                label.setText(userData.getName()+" "+userData.getLastName());
                makeTrainer.setStyle("-fx-background-color: #960001; -fx-text-fill: white");
                makeTrainer.setOnAction(event -> makeCustomerTrainer(userData));

                setGraphic(hBox);
            }
        }
    }

    private void setListCustomers(JFXListView<UserData> list)
    {
        list.setCellFactory(param -> new CellCustomers());
    }

    class CellTrainers extends ListCell<UserData>
    {
        HBox hBox = new HBox();
        JFXButton makeCustomer = new JFXButton("Make Customer");
        Label label = new Label();
        Pane pane = new Pane();
        Image profilePic = new Image("assets/trainer material LLQ.png");
        ImageView img = new ImageView(profilePic);

        public CellTrainers()
        {
            super();

            hBox.getChildren().addAll(img,label,pane, makeCustomer);
            HBox.setHgrow(pane, Priority.ALWAYS);
        }

        public void updateItem(UserData userData, boolean empty)
        {
            super.updateItem(userData, empty);
            setText(null);
            setGraphic(null);

            if(userData!=null && !empty)
            {
                label.setStyle("-fx-text-fill: white; -fx-font-size: 20");
                label.setText(userData.getName()+" "+userData.getLastName());
                makeCustomer.setStyle("-fx-background-color: #960001; -fx-text-fill: white");
                makeCustomer.setOnAction(event -> makeTrainerCustomer(userData));

                setGraphic(hBox);
            }
        }
    }

    private void setListTrainer(JFXListView<UserData> list)
    {
        list.setCellFactory(param -> new CellTrainers());
    }

    private void makeCustomerTrainer(UserData userData)
    {
        customerList.getItems().remove(userData);

        try {
            Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
            String sql = "UPDATE `user` SET `rank` =" + "'Trainer'" + " WHERE (`username`='" + userData.getUsername() + "');";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();

            User user = db.getUserByUsername(userData.getUsername());
            user.setRank("Trainer");

            trainerList.getItems().add(userData);
        }
        catch (SQLException throwables) { throwables.printStackTrace(); }

    }

    private void makeTrainerCustomer(UserData userData)
    {
        trainerList.getItems().remove(userData);

        try {
            Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
            String sql = "UPDATE `user` SET `rank` =" + "'Customer'" + " WHERE (`username`='" + userData.getUsername() + "');";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();

            User user = db.getUserByUsername(userData.getUsername());
            user.setRank("Trainer");

            customerList.getItems().add(userData);
        }
        catch (SQLException throwables) { throwables.printStackTrace(); }

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
