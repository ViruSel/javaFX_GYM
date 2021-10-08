package controllers;

import animatefx.animation.SlideInRight;
import gym.Main;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import com.jfoenix.controls.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import database.*;

public class CtrlLogin implements Initializable
{
    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane changedPane;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXCheckBox rememberMe;
    @FXML
    private JFXButton login;
    @FXML
    private ImageView loading;

    private static Main main;

    // SQL //
    private MySQLdb db = new MySQLdb();
    public static MySQLuser user = new MySQLuser();
    public static MySQLuser_data user_data = null;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        loading.setVisible(false);
        username.setStyle("-fx-text-inner-color: white;");
        password.setStyle("-fx-text-inner-color: white;");
    }

    @FXML
    public void loginAction()
    {
        loading.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(1));
        pt.setOnFinished(e -> {
            loading.setVisible(false);
            validateProfile();
        });
        pt.play();
    }

    @FXML
    public void signUpAction() { setPageSignUp(); }

    private void setNodeSignUp(Node node)
    {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        SlideInRight slideInRight = new SlideInRight(changedPane);
        slideInRight.setNode(node);
        slideInRight.setCycleCount(1);
        slideInRight.setSpeed(1);
        slideInRight.play();
    }

    private void setPageSignUp()
    {
        try
        {
            changedPane = FXMLLoader.load(getClass().getResource("../scenes/SignUp.fxml"));
            setNodeSignUp(changedPane);
        }
        catch (IOException e) { e.printStackTrace();}
    }

    private void validateProfile()
    {
        try {
            Connection con = DriverManager.getConnection(db.getHOST(), db.getUSERNAME(), db.getPASSWORD());
            String sql = "SELECT * FROM user WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username.getText());
            ps.setString(2, password.getText());
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                user = db.getUserByUsername(username.getText());
                user_data = db.getUserDataByUsername(user.getUsername());

                System.out.println(user.toString());
                System.out.println(user_data.toString());

                Stage loginStage = (Stage) login.getScene().getWindow();
                loginStage.close();
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../scenes/Menu.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    Stage menuStage = new Stage();
                    this.main.stage = menuStage;
                    menuStage.initStyle(StageStyle.UNDECORATED);
                    menuStage.setScene(new Scene(root));
                    menuStage.show();

                } catch (IOException e) { e.printStackTrace(); }
            }
            else
            {
                user = new MySQLuser(0, null,null,null,null);
                user_data = new MySQLuser_data(null,null,0,null,null,null,null);

                loading.setVisible(false);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Incorrect username or password");
                alert.show();
            }

        }
        catch (SQLException e) { e.printStackTrace(); }
    }
}
