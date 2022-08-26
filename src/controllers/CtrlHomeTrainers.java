package controllers;

import animatefx.animation.SlideInLeft;
import animatefx.animation.SlideInRight;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CtrlHomeTrainers implements Initializable
{
    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane changedPane;
    @FXML
    private Pane adminPane;
    @FXML
    private Label adminText;
    @FXML
    private JFXButton back;
    @FXML
    private JFXListView<UserData> gymList;
    @FXML
    private JFXListView<UserData> aerobicsList;
    @FXML
    private JFXListView<UserData> yogaList;

    private final DB db = new DB();
    public static UserData selectedUserData = new UserData();

    ObservableList<UserData> gymTrainerList = FXCollections.observableArrayList();
    ObservableList<UserData> aerobicsTrainerList = FXCollections.observableArrayList();
    ObservableList<UserData> yogaTrainerList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        back.getStylesheets().add("assets/virus_titlebar.css");
        gymList.getStylesheets().add("assets/virus_table.css");
        aerobicsList.getStylesheets().add("assets/virus_table.css");
        yogaList.getStylesheets().add("assets/virus_table.css");
        gymList.setStyle("-fx-border-color: #000000");

        setGymList(gymList);
        setGymList(aerobicsList);
        setGymList(yogaList);

        gymList.setItems(gymTrainerList);
        aerobicsList.setItems(aerobicsTrainerList);
        yogaList.setItems(yogaTrainerList);

        adminPane.setVisible(false);
        adminText.setVisible(false);

        checkTrainer();
        showTrainers();
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

    private void viewTrainer(UserData userData)
    {
        selectedUserData = userData;

        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/HomeTrainerProfile.fxml"));
            setNodeTrainers(changedPane);

        }
        catch (IOException e) { e.printStackTrace(); }
    }

    private void checkTrainer()
    {
        if(CtrlLogin.user.isTrainer())
        {
            adminPane.setVisible(true);
            adminText.setVisible(true);
            adminText.setText("You are a trainer, why would someone train you?"+'\n'+"Train yourself!");
        }
    }

    private void showTrainers()
    {
        try{
            Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());

            ResultSet rs = con.createStatement().executeQuery("Select * FROM user WHERE `rank` = 'Trainer'");

            while(rs.next())
            {
                User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("rank"), rs.getString("subscription"));
                TrainerData trainerData = db.getTrainerDataByUsername(user.getUsername());
                UserData userData = db.getUserDataByUsername(user.getUsername());

                checkTrainerSpecialization(trainerData, userData);
            }

        }
        catch (SQLException throwables) { throwables.printStackTrace(); }
    }

    private void checkTrainerSpecialization(TrainerData trainerData, UserData userData)
    {
        switch (trainerData.getSpecialization())
        {
            case "GYM":
                gymTrainerList.add(userData);
                break;
            case "Aerobics":
                aerobicsTrainerList.add(userData);
                break;
            case "Yoga":
                yogaTrainerList.add(userData);
                break;
        }
    }

    class Cell extends ListCell<UserData>
    {
        HBox hBox = new HBox();
        JFXButton viewButton = new JFXButton("View");
        Label label = new Label();
        Pane pane = new Pane();
        Image profilePic = new Image("assets/trainer material LLQ.png");
        ImageView img = new ImageView(profilePic);

        public Cell()
        {
            super();

            hBox.getChildren().addAll(img,label,pane, viewButton);
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
                viewButton.setStyle("-fx-background-color: #960001; -fx-text-fill: white");
                viewButton.setOnAction(event -> viewTrainer(userData));
                label.setText(" "+userData.getName()+" "+userData.getLastName());
                setGraphic(hBox);
            }
        }
    }

    private void setGymList(JFXListView<UserData> list) { list.setCellFactory(param -> new Cell()); }

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

    private void setNodeTrainers(Node node)
    {
        holderPane.getChildren().clear();
        holderPane.getChildren().add(node);

        SlideInRight slideInRight = new SlideInRight(changedPane);
        slideInRight.setNode(node);
        slideInRight.setCycleCount(1);
        slideInRight.setSpeed(1);
        slideInRight.play();
    }
}
