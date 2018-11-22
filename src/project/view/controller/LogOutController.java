package project.view.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;    
import javafx.stage.WindowEvent;
import project.logic.Logic;
import message.User;


/**
 * 
 * @author Ion Mikel
 */
public class LogOutController {
    
    private static final Logger LOG = 
            Logger.getLogger("view.controller.LogOutController");
    //Declaramos todos los label y botones que necesitamos.
    @FXML 
    private Button btnLogOut;
    
    @FXML
    private Label lblMessage;
   
    private User user;
    
    private Stage stage;
    
    private Logic logic;
    
    /**
     * setter del user
     * @param user usuario
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * Set stage for the logOut 
     * @param stage  setter del stage
     */
    public void setStage(Stage stage) {
        this.stage = stage; 
    }
    
    /**
     * Set the logic for communication with the next layer
     * @param logic setter del logic
     */
    public void setLogic(Logic logic){
        this.logic=logic;
    }
    
    /**
     * Set and initialize the stage and its properties.
     * y mostramos los atributos de user.
     * @param root root
     */
    public void initStage(Parent root){
        Date date = null;
        date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        LOG.info("INICIANDO INITSTAGE DE EL LOGOUT");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("LogOut");
        stage.setResizable(false);
        //Set window's event handlers
        stage.setOnShowing(this::handleWindowShowing);
        btnLogOut.setOnAction(this::pushLogOut);
        lblMessage.setText("Welcome " + user.getLogin() + "\n" + "Email: " +user.getEmail() + "\n" + "Name: " +user.getFullName() + "\n" + "Password: " + user.getPassword() + "\n" + "Status: " + user.getStatus().toString() + "\n" + "Privilege: " +user.getPrivilege().toString() + "\n" + 
            "LastAccess: "+ sdf.format(user.getLastAcces()) + "\n"  + "LastPWChange: " +  sdf.format(user.getLastPasswordChange()));
        stage.show();
        LOG.info("FINALIZANDO INITSTAGE DE EL LOGOUT");
    }
    
    /**
     * We put the mnemonico on logOut button
     * @param event evento
     */
    public void handleWindowShowing(WindowEvent event){
        LOG.info("Beginning handleWindowShowing");
        //Set the mnemonic parse
        btnLogOut.setMnemonicParsing(true);
        //Set the mnemonic character and the text
        btnLogOut.setText("_LogOut");
        stage.addEventHandler(KeyEvent.KEY_PRESSED, ev ->{
        if(ev.getCode()==KeyCode.ENTER){
            logOut();
        }
        });
    }
    
    /** 
     * when u press logout
     * Show an alert for confirm the exit
     * @param event evento
     */
    private void pushLogOut(ActionEvent event){
        
        Alert alert = new Alert(AlertType.WARNING, 
            "¿Are you sure?",ButtonType.YES, ButtonType.NO);
        
        Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.YES){ 
                 Platform.exit();
            }
    }
    
    /**
     * method para cerrar la aplicacion
     */
    
   
    private void logOut() {
           Alert alert = new Alert(AlertType.WARNING,
            "¿Are you sure?",ButtonType.YES, ButtonType.NO);
           alert.setHeaderText("LOGOUT");
        
        Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.YES){ 
                Platform.exit();
            }
    }
    

    
    

    
    

}
