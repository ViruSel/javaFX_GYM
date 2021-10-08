package controllers;

import animatefx.animation.SlideInLeft;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import database.MySQLdb;
import database.MySQLuser;
import database.MySQLuser_data;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CtrlHomeTrainers implements Initializable
{
    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane changedPane;
    @FXML
    private JFXButton back;
    @FXML
    private JFXTreeTableView<MySQLuser_data> table;

    private final MySQLdb db = new MySQLdb();

    JFXTreeTableColumn<MySQLuser_data, String> name = new JFXTreeTableColumn<>("Name");
    JFXTreeTableColumn<MySQLuser_data, String> lastName = new JFXTreeTableColumn<>("Last Name");
    JFXTreeTableColumn<MySQLuser_data, String> age = new JFXTreeTableColumn<>("Age");
    JFXTreeTableColumn<MySQLuser_data, String> gender = new JFXTreeTableColumn<>("Gender");
    JFXTreeTableColumn<MySQLuser_data, String> tel = new JFXTreeTableColumn<>("Tel.");
    JFXTreeTableColumn<MySQLuser_data, String> email = new JFXTreeTableColumn<>("Email");

    ObservableList<MySQLuser_data> trainerList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        back.getStylesheets().add("assets/virus_titlebar.css");

        name.setPrefWidth(100);
        lastName.setPrefWidth(100);
        age.setPrefWidth(100);
        gender.setPrefWidth(100);
        tel.setPrefWidth(100);
        email.setPrefWidth(200);

        setCells();
        checkTrainer();
    }

    @FXML
    private void backButtonAction()
    {
        try {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/HomePage.fxml"));
            setNode(changedPane);

        }
        catch (IOException e) { e.printStackTrace(); }
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

    private void checkTrainer()
    {
        try{
            Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());

            ResultSet rs = con.createStatement().executeQuery("Select * FROM user WHERE `rank` = 'Trainer'");

            while(rs.next())
            {
                MySQLuser user = new MySQLuser(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("rank"), rs.getString("subscription"));
                MySQLuser_data user_data = db.getUserDataByUsername(user.getUsername());
                trainerList.add(user_data);
            }

            final TreeItem<MySQLuser_data> root = new RecursiveTreeItem<>(trainerList, RecursiveTreeObject::getChildren);
            table.getColumns().setAll(name,lastName,age,gender,tel,email);
            table.setRoot(root);
            table.setShowRoot(false);
        }
        catch (SQLException throwables) { throwables.printStackTrace(); }
    }

    private void setCells()
    {
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<MySQLuser_data, String>, ObservableValue<String>>()
        {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<MySQLuser_data, String> param) { return param.getValue().getValue().getNameObs(); }
        });

        lastName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<MySQLuser_data, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<MySQLuser_data, String> param) { return param.getValue().getValue().getLastNameObs(); }
        });

        age.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<MySQLuser_data, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<MySQLuser_data, String> param) { return param.getValue().getValue().getAgeObs(); }
        });

        gender.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<MySQLuser_data, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<MySQLuser_data, String> param) { return param.getValue().getValue().getGenderObs(); }
        });

        tel.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<MySQLuser_data, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<MySQLuser_data, String> param) { return param.getValue().getValue().getTelObs(); }
        });

        email.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<MySQLuser_data, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<MySQLuser_data, String> param) { return param.getValue().getValue().getEmailObs(); }
        });
    }
}
