package project.view.controller;

import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;    
import project.logic.Logic;
//---CAMBIAR----//
import message.User;


/**
 * 
 * @author Ion Mikel
 */
public class LogOutController {
    
    @FXML 
    private Button btnLogOut;
    
    @FXML
    private Label lblMessage;
    
    private User user;
    
    private Stage stage;
    
    private Logic logic;
    
    /**
     * 
     * @param user 
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * Set stage for the logOut 
     * @param stage 
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    /**
     * Set the logic for communication with the next layer
     * @param logic 
     */
    public void setLogic(Logic logic){
        this.logic=logic;
    }
    
    /**
     * Set and initialize the stage and its properties.
     * @param root 
     */
    public void initStage(Parent root){
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("LogOut");
        stage.setResizable(false);
        btnLogOut.setOnAction(this::pushLogOut);
        lblMessage.setText("Welcome " + user.getLogin());
        stage.show();
    }
    
    /**
     * Show an alert for confirm the exit
     * @param event 
     */
    private void pushLogOut(ActionEvent event){
        
        Alert alert = new Alert(AlertType.WARNING, 
            "¿Are you sure?",ButtonType.YES, ButtonType.NO);
        
        Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.YES){ 
                Platform.exit();
            }
    }
    

    
    

    
    

}
