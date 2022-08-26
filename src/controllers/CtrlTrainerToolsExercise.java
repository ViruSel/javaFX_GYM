package controllers;

import animatefx.animation.SlideInLeft;
import animatefx.animation.SlideInRight;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import database.DB;
import database.TrainerData;
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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CtrlTrainerToolsExercise implements Initializable
{
    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane changedPane;
    @FXML
    private JFXButton back;
    @FXML
    private JFXListView<UserData> customerList;

    private final DB db = new DB();
    public static UserData userDataToAddExercise = new UserData();
    private final TrainerData trainerData = db.getTrainerDataByUsername(CtrlLogin.user.getUsername());

    ObservableList<UserData> customerListObs = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        back.getStylesheets().add("assets/virus_titlebar.css");
        customerList.getStylesheets().add("assets/virus_table.css");
        setGymList(customerList);
        customerList.setItems(customerListObs);
        getCustomers();
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

    private void getCustomers()
    {
        if(!trainerData.getCustomer1().equals(""))
        {
            UserData userData = db.getUserDataByUsername(trainerData.getCustomer1());
            customerListObs.add(userData);
        }
        if(!trainerData.getCustomer2().equals(""))
        {
            UserData userData = db.getUserDataByUsername(trainerData.getCustomer2());
            customerListObs.add(userData);
        }
        if(!trainerData.getCustomer3().equals(""))
        {
            UserData userData = db.getUserDataByUsername(trainerData.getCustomer3());
            customerListObs.add(userData);
        }
    }

    class Cell extends ListCell<UserData>
    {
        HBox hBox = new HBox();
        JFXButton addPlan = new JFXButton("Add");
        JFXButton deletePlan = new JFXButton("Delete");
        Label label = new Label();
        Pane pane = new Pane();
        Image profilePic = new Image("assets/userPic LLQ.png");
        ImageView img = new ImageView(profilePic);

        public Cell()
        {
            super();

            hBox.getChildren().addAll(img,label,pane, addPlan, deletePlan);
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
                addPlan.setStyle("-fx-background-color: #960001; -fx-text-fill: white");
                deletePlan.setStyle("-fx-background-color: #960001; -fx-text-fill: white");
                addPlan.setOnAction(event -> viewExercises(userData));
                deletePlan.setOnAction(event -> deleteExercise(userData));

                if(userData.getExercisePlan().equals(""))
                {
                    label.setText(" "+userData.getName()+" "+userData.getLastName()+" - Doesn't have an exercise plan!");
                    deletePlan.setDisable(true);
                }
                else
                {
                    label.setText(" "+userData.getName()+" "+userData.getLastName()+" - "+userData.getExercisePlan());
                    addPlan.setDisable(true);
                }

                setGraphic(hBox);
            }
        }
    }

    private void setGymList(JFXListView<UserData> list)
    {
        list.setCellFactory(param -> new Cell());
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

    private void setNodeAddExercise(Node node)
    {
        holderPane.getChildren().clear();
        holderPane.getChildren().add(node);

        SlideInRight slideInRight = new SlideInRight(changedPane);
        slideInRight.setNode(node);
        slideInRight.setCycleCount(1);
        slideInRight.setSpeed(1);
        slideInRight.play();
    }

    private void viewExercises(UserData userData)
    {
        userDataToAddExercise = userData;

        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/TrainerToolsExerciseAdd.fxml"));
            setNodeAddExercise(changedPane);

        }
        catch (IOException e) { e.printStackTrace(); }
    }

    private void deleteExercise(UserData userData)
    {
        try {
            Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
            String sql = "UPDATE `user_data` SET `exercisePlan` =" + "''" + " WHERE (`username`='" + userData.getUsername() + "');";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }
        catch (SQLException throwables) { throwables.printStackTrace(); }
    }
}
