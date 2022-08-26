package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class CtrlContactInfo implements Initializable
{
    @FXML
    private Hyperlink fb;
    @FXML
    private Hyperlink insta;
    @FXML
    private Hyperlink yt;
    @FXML
    private Hyperlink twitch;
    @FXML
    private Hyperlink spotify;

    Desktop desktop = Desktop.getDesktop();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        fb.getStylesheets().add("assets/virus.css");
        insta.getStylesheets().add("assets/virus.css");
        yt.getStylesheets().add("assets/virus.css");
        twitch.getStylesheets().add("assets/virus.css");
        spotify.getStylesheets().add("assets/virus.css");
    }

    @FXML
    void fbButtonAction() { openBrowser(fb); }

    @FXML
    void instaButtonAction() { openBrowser(insta); }

    @FXML
    void spotifyButtonAction() { openBrowser(spotify); }

    @FXML
    void twitchButtonAction() { openBrowser(twitch); }

    @FXML
    void ytButtonAction() { openBrowser(yt); }

    private void openBrowser(Hyperlink link)
    {
        if (desktop.isSupported(Desktop.Action.BROWSE))
        {
            try { desktop.browse(new URI(link.getText()));
            } catch (IOException | URISyntaxException ignored) {}
        }
    }
}
