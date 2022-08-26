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

public class CtrlTrainerToolsDiet implements Initializable
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
    public static UserData userDataToAddDiet = new UserData();
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
        JFXButton addDiet = new JFXButton("Add");
        JFXButton deleteDiet = new JFXButton("Delete");
        Label label = new Label();
        Pane pane = new Pane();
        Image profilePic = new Image("assets/userPic LLQ.png");
        ImageView img = new ImageView(profilePic);

        public Cell()
        {
            super();

            hBox.getChildren().addAll(img,label,pane, addDiet, deleteDiet);
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
                addDiet.setStyle("-fx-background-color: #960001; -fx-text-fill: white");
                deleteDiet.setStyle("-fx-background-color: #960001; -fx-text-fill: white");
                addDiet.setOnAction(event -> viewDiets(userData));
                deleteDiet.setOnAction(event -> deleteDiets(userData));

                if(userData.getDiet().equals(""))
                {
                    label.setText(" "+userData.getName()+" "+userData.getLastName()+" - Doesn't have a diet");
                    deleteDiet.setDisable(true);
                }
                else
                {
                    label.setText(" "+userData.getName()+" "+userData.getLastName()+" - "+userData.getDiet());
                    addDiet.setDisable(true);
                }

                setGraphic(hBox);
            }
        }
    }

    private void setGymList(JFXListView<UserData> list)
    {
        list.setCellFactory(param -> new CtrlTrainerToolsDiet.Cell());
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

    private void setNodeAddDiet(Node node)
    {
        holderPane.getChildren().clear();
        holderPane.getChildren().add(node);

        SlideInRight slideInRight = new SlideInRight(changedPane);
        slideInRight.setNode(node);
        slideInRight.setCycleCount(1);
        slideInRight.setSpeed(1);
        slideInRight.play();
    }

    private void viewDiets(UserData userData)
    {
        userDataToAddDiet = userData;

        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/TrainerToolsDietAdd.fxml"));
            setNodeAddDiet(changedPane);

        }
        catch (IOException e) { e.printStackTrace(); }
    }

    private void deleteDiets(UserData userData)
    {
        try {
            Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
            String sql = "UPDATE `user_data` SET `diet` =" + "''" + " WHERE (`username`='" + userData.getUsername() + "');";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }
        catch (SQLException throwables) { throwables.printStackTrace(); }
    }
}
