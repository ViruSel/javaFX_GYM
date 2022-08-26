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
import javafx.scene.control.Alert;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class CtrlTrainerToolsAccept implements Initializable
{
    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane changedPane;
    @FXML
    private JFXButton back;
    @FXML
    private JFXListView<UserData> requestList;
    @FXML
    private JFXListView<UserData> customerList;

    private final DB db = new DB();
    public static UserData CustomerData = new UserData();
    private final TrainerData trainerData = db.getTrainerDataByUsername(CtrlLogin.user_data.getUsername());

    ObservableList<UserData> requests = FXCollections.observableArrayList();
    ObservableList<UserData> customers = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        back.getStylesheets().add("assets/virus_titlebar.css");
        requestList.getStylesheets().add("assets/virus_table.css");
        customerList.getStylesheets().add("assets/virus_table.css");

        requestList.setItems(requests);
        customerList.setItems(customers);

        requestList.setCellFactory(param -> new Cell());
        customerList.setCellFactory(param -> new CellCustomer());

        showCustomers();
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

    private void showCustomers()
    {
        try{
            Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());

            ResultSet rs = con.createStatement().executeQuery("Select * FROM user WHERE `rank` = 'Customer'");

            while(rs.next())
            {
                User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("rank"), rs.getString("subscription"));
                UserData userData = db.getUserDataByUsername(user.getUsername());

                checkTrainerForLists(userData);
            }
        }
        catch (SQLException throwables) { throwables.printStackTrace(); }
    }

    private void checkTrainerForLists(UserData userData)
    {
        switch (trainerData.getSpecialization())
        {
            case "GYM":
                if (Objects.equals(userData.getRequestGymTrainer(), trainerData.getUsername()))
                    requests.add(userData);
                if (Objects.equals(userData.getGymTrainer(), trainerData.getUsername()))
                    customers.add(userData);
                break;
            case "Aerobics":
                if (Objects.equals(userData.getRequestAerobicsTrainer(), trainerData.getUsername()))
                    requests.add(userData);
                if (Objects.equals(userData.getAerobicsTrainer(), trainerData.getUsername()))
                    customers.add(userData);
                break;
            case "Yoga":
                if (Objects.equals(userData.getRequestYogaTrainer(), trainerData.getUsername()))
                    requests.add(userData);
                if (Objects.equals(userData.getAerobicsTrainer(), trainerData.getUsername()))
                    customers.add(userData);
                break;
        }
    }

    private void addCustomer(UserData userData)
    {
        if (!trainerData.getCustomer1().equals("") && !trainerData.getCustomer2().equals("") && !trainerData.getCustomer3().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Your customer slots are full"+'\n'+"If you want to add another customer, replace him with one of your customers");
            alert.show();
        }
        else
        {
            try {
                Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());

                String updateUserDataGymTrainer = "UPDATE `user_data` SET `gymTrainer` =" + "'" + trainerData.getUsername() + "'" + " WHERE (`username`='" + userData.getUsername() + "');";
                String updateUserDataAerobicsTrainer = "UPDATE `user_data` SET `aerobicsTrainer` =" + "'" + trainerData.getUsername() + "'" + " WHERE (`username`='" + userData.getUsername() + "');";
                String updateUserDataYogaTrainer = "UPDATE `user_data` SET `yogaTrainer` =" + "'" + trainerData.getUsername() + "'" + " WHERE (`username`='" + userData.getUsername() + "');";
                String updateUserDataGymRequest = "UPDATE `user_data` SET `requestGymTrainer` =" + "''" + " WHERE (`username`='" + userData.getUsername() + "');";
                String updateUserDataAerobicsRequest = "UPDATE `user_data` SET `requestAerobicsTrainer` =" + "''" + " WHERE (`username`='" + userData.getUsername() + "');";
                String updateUserDataYogaRequest = "UPDATE `user_data` SET `requestYogaTrainer` =" + "''" + " WHERE (`username`='" + userData.getUsername() + "');";
                String addCustomer1SQL = "UPDATE `trainer_data` SET `customer1` =" + "'" + userData.getUsername() + "'" + " WHERE (`username`='" + trainerData.getUsername() + "');";
                String addCustomer2SQL = "UPDATE `trainer_data` SET `customer2` =" + "'" + userData.getUsername() + "'" + " WHERE (`username`='" + trainerData.getUsername() + "');";
                String addCustomer3SQL = "UPDATE `trainer_data` SET `customer3` =" + "'" + userData.getUsername() + "'" + " WHERE (`username`='" + trainerData.getUsername() + "');";

                PreparedStatement updateGymTrainer = con.prepareStatement(updateUserDataGymTrainer);
                PreparedStatement updateAerobicsTrainer = con.prepareStatement(updateUserDataAerobicsTrainer);
                PreparedStatement updateYogaTrainer = con.prepareStatement(updateUserDataYogaTrainer);
                PreparedStatement updateGymRequest = con.prepareStatement(updateUserDataGymRequest);
                PreparedStatement updateAerobicsRequest = con.prepareStatement(updateUserDataAerobicsRequest);
                PreparedStatement updateYogaRequest = con.prepareStatement(updateUserDataYogaRequest);
                PreparedStatement ps1 = con.prepareStatement(addCustomer1SQL);
                PreparedStatement ps2 = con.prepareStatement(addCustomer2SQL);
                PreparedStatement ps3 = con.prepareStatement(addCustomer3SQL);

                switch (trainerData.getSpecialization())
                {
                    case "GYM":
                        updateGymTrainer.executeUpdate(updateUserDataGymTrainer);
                        updateGymRequest.executeUpdate(updateUserDataGymRequest);

                        userData.setGymTrainer(trainerData.getUsername());
                        userData.setRequestGymTrainer("");

                        addCustomerToTrainer(userData, addCustomer1SQL, addCustomer2SQL, addCustomer3SQL, ps1, ps2, ps3);
                        break;
                    case "Aerobics":
                        updateAerobicsTrainer.executeUpdate(updateUserDataAerobicsTrainer);
                        updateAerobicsRequest.executeUpdate(updateUserDataAerobicsRequest);

                        userData.setAerobicsTrainer(trainerData.getUsername());
                        userData.setRequestAerobicsTrainer("");

                        addCustomerToTrainer(userData, addCustomer1SQL, addCustomer2SQL, addCustomer3SQL, ps1, ps2, ps3);
                        break;
                    case "Yoga":
                        updateYogaTrainer.executeUpdate(updateUserDataYogaTrainer);
                        updateYogaRequest.executeUpdate(updateUserDataYogaRequest);

                        userData.setYogaTrainer(trainerData.getUsername());
                        userData.setRequestYogaTrainer("");

                        addCustomerToTrainer(userData, addCustomer1SQL, addCustomer2SQL, addCustomer3SQL, ps1, ps2, ps3);
                        break;
                }

            } catch (SQLException throwables) { throwables.printStackTrace(); }
        }
    }

    private void deleteCustomer(UserData userData)
    {
        customerList.getItems().remove(userData);

        try {
            Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());

            String updateUserDataGymTrainer = "UPDATE `user_data` SET `gymTrainer` =" + "''" + " WHERE (`username`='" + userData.getUsername() + "');";
            String updateUserDataAerobicsTrainer = "UPDATE `user_data` SET `aerobicsTrainer` =" + "''" + " WHERE (`username`='" + userData.getUsername() + "');";
            String updateUserDataYogaTrainer = "UPDATE `user_data` SET `yogaTrainer` =" + "''" + " WHERE (`username`='" + userData.getUsername() + "');";
            String deleteCustomer1SQL = "UPDATE `trainer_data` SET `customer1` =" + "''" + " WHERE (`username`='" + trainerData.getUsername() + "');";
            String deleteCustomer2SQL = "UPDATE `trainer_data` SET `customer2` =" + "''" + " WHERE (`username`='" + trainerData.getUsername() + "');";
            String deleteCustomer3SQL = "UPDATE `trainer_data` SET `customer3` =" + "''" + " WHERE (`username`='" + trainerData.getUsername() + "');";

            PreparedStatement updateGymTrainer = con.prepareStatement(updateUserDataGymTrainer);
            PreparedStatement updateAerobicsTrainer = con.prepareStatement(updateUserDataAerobicsTrainer);
            PreparedStatement updateYogaTrainer = con.prepareStatement(updateUserDataYogaTrainer);
            PreparedStatement ps1 = con.prepareStatement(deleteCustomer1SQL);
            PreparedStatement ps2 = con.prepareStatement(deleteCustomer2SQL);
            PreparedStatement ps3 = con.prepareStatement(deleteCustomer3SQL);

            switch (trainerData.getSpecialization())
            {
                case "GYM":
                    updateGymTrainer.executeUpdate(updateUserDataGymTrainer);
                    deleteCustomerFromTrainer(userData, deleteCustomer1SQL, deleteCustomer2SQL, deleteCustomer3SQL, ps1, ps2, ps3);
                    userData.setGymTrainer("");
                    break;
                case "Aerobics":
                    updateAerobicsTrainer.executeUpdate(updateUserDataAerobicsTrainer);
                    deleteCustomerFromTrainer(userData, deleteCustomer1SQL, deleteCustomer2SQL, deleteCustomer3SQL, ps1, ps2, ps3);
                    userData.setAerobicsTrainer("");
                    break;
                case "Yoga":
                    updateYogaTrainer.executeUpdate(updateUserDataYogaTrainer);
                    deleteCustomerFromTrainer(userData, deleteCustomer1SQL, deleteCustomer2SQL, deleteCustomer3SQL, ps1, ps2, ps3);
                    userData.setYogaTrainer("");
                    break;
            }

            customerList.getItems().remove(userData);

        } catch (SQLException throwables) { throwables.printStackTrace(); }
    }

    private void addCustomerToTrainer(UserData userData, String addCustomer1SQL, String addCustomer2SQL, String addCustomer3SQL, PreparedStatement ps1, PreparedStatement ps2, PreparedStatement ps3) throws SQLException
    {
        if (trainerData.getCustomer1().equals(""))
        {
            ps1.executeUpdate(addCustomer1SQL);
            trainerData.setCustomer1(userData.getUsername());
            requestList.getItems().remove(userData);
            customerList.getItems().add(userData);
        }
        else if (trainerData.getCustomer2().equals(""))
        {
            ps2.executeUpdate(addCustomer2SQL);
            trainerData.setCustomer2(userData.getUsername());
            requestList.getItems().remove(userData);
            customerList.getItems().add(userData);
        }
        else if (trainerData.getCustomer3().equals(""))
        {
            ps3.executeUpdate(addCustomer3SQL);
            trainerData.setCustomer3(userData.getUsername());
            requestList.getItems().remove(userData);
            customerList.getItems().add(userData);
        }

    }

    private void deleteCustomerFromTrainer(UserData userData, String deleteCustomer1SQL, String deleteCustomer2SQL, String deleteCustomer3SQL, PreparedStatement ps1, PreparedStatement ps2, PreparedStatement ps3) throws SQLException
    {
        if (Objects.equals(userData.getUsername(), trainerData.getCustomer1()))
        {
            ps1.executeUpdate(deleteCustomer1SQL);
            trainerData.setCustomer1("");
        }
        else if (Objects.equals(userData.getUsername(), trainerData.getCustomer2()))
        {
            ps2.executeUpdate(deleteCustomer2SQL);
            trainerData.setCustomer2("");
        }
        else if (Objects.equals(userData.getUsername(), trainerData.getCustomer3()))
        {
            ps3.executeUpdate(deleteCustomer3SQL);
            trainerData.setCustomer3("");
        }
    }

    class Cell extends ListCell<UserData>
    {
        private final HBox hBox = new HBox();
        private final JFXButton viewButton = new JFXButton("View");
        private final JFXButton addCustomerButton = new JFXButton("Add");
        private final Label label = new Label();

        public Cell()
        {
            super();

            Pane pane = new Pane();
            Image profilePic = new Image("assets/trainer material LLQ.png");
            ImageView img = new ImageView(profilePic);
            hBox.getChildren().addAll(img,label, pane,addCustomerButton,viewButton);
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
                addCustomerButton.setStyle("-fx-background-color: #960001; -fx-text-fill: white");
                viewButton.setOnAction(event -> viewTrainer(userData));
                addCustomerButton.setOnAction(event -> addCustomer(userData));
                label.setText(" "+userData.getName()+" "+userData.getLastName());
                setGraphic(hBox);
            }
        }
    }

    class CellCustomer extends ListCell<UserData>
    {
        private final HBox hBox = new HBox();
        private final JFXButton viewButton = new JFXButton("View");
        private final JFXButton deleteButton = new JFXButton("Delete");
        private final Label label = new Label();

        public CellCustomer()
        {
            super();

            Pane pane = new Pane();
            Image profilePic = new Image("assets/trainer material LLQ.png");
            ImageView img = new ImageView(profilePic);
            hBox.getChildren().addAll(img,label, pane, deleteButton,viewButton);
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
                deleteButton.setStyle("-fx-background-color: #960001; -fx-text-fill: white");
                viewButton.setOnAction(event -> viewTrainer(userData));
                deleteButton.setOnAction(event -> deleteCustomer(userData));
                label.setText(" "+userData.getName()+" "+userData.getLastName());
                setGraphic(hBox);
            }
        }
    }

    private void viewTrainer(UserData userData)
    {
        CustomerData = userData;

        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/TrainerToolsProfile.fxml"));
            setNodeCustomers(changedPane);

        }
        catch (IOException e) { e.printStackTrace(); }
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

    private void setNodeCustomers(Node node)
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