/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controllers;

import exceptions.LoginNotExistingException;
import exceptions.WrongPasswordException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.Logic;
import message.User;
import view.controller.LogOutController;
import view.controller.SignUpController;

/**
 *Login FXML Controller class
 *@author Jon Gonzalez
 *@version 1.0
 */
public class LogInController{

    @FXML
    private Label lblUser;
    @FXML
    private Label lblPass;
    @FXML
    private TextField txtFUser;
    @FXML
    private PasswordField pwPassword;
    @FXML
    private Button btnLogIn;
    @FXML
    private Hyperlink hlRegister;
    
    /* 
     * This  is the logger that it go to save information about the desktop
     * application
     */
    private static final Logger LOG = 
            Logger.getLogger("view.controller.LogInController");
    
    private Stage stage;
    
    private User user;
    
    private Logic logic;
    
    /**
     * Set the logic for communication with the next layer
     * @param logic 
     */
    public void setLogic(Logic logic){
        this.logic=logic;
    }
    
    /**
     * Set stage for the login 
     * @param stage 
     */
    public void setStage(Stage stage){
        this.stage=stage;
    }
    
    /**
     * Set and initialize the stage and its properties.
     * @param root 
     */
    public void initStage(Parent root){
        LOG.info("Initializing Login stage");
        //Ceate a scene associated to to the parent root
        Scene scene = new Scene(root);
        //Associate the scene with the stage
        stage.setScene(scene);
        //Set window's properties
        stage.setTitle("Login");
        stage.setResizable(false);
        //Set window's event handlers and the textfield's promptext
        stage.setOnShowing(this::handleWindowShowing);
        btnLogIn.setOnAction(this::handleClick);
        txtFUser.textProperty().addListener(this::handleTextChanged);
        txtFUser.setPromptText("Set the username...");
        pwPassword.textProperty().addListener(this::handleTextChanged);
        pwPassword.setPromptText("Set the password...");
        hlRegister.setOnAction(this::handleSignUp);
        //Show the LogIn window
        stage.show();
    }
    
    /**
     * Set atributes to the controls that it need in the window showing event
     * @param event
     */
    public void handleWindowShowing(WindowEvent event){
        LOG.info("Beginning handleWindowShowing");
        //Set the mnemonic parse to the login button
        btnLogIn.setMnemonicParsing(true);
        //Set the mnemonic character and the text
        btnLogIn.setText("_Login");
    }
    
    /**
     * Go to login method
     * @param event 
     */
    public void handleClick(ActionEvent event){
            logIn();
    }
    
    /**
     * Verify if there are empty fields and modify button login's setDisable 
     * method. If there are any field empty, disable the login button, else 
     * enable it. Also verify that the person who is writting don't write 
     * more than the maximum of characters.
     * @param observable
     * @param oldvalue
     * @param newvalue 
     */
    public void handleTextChanged(ObservableValue observable, 
            String oldvalue, String newvalue){
        LOG.info("Beginning handleTextChanged, the event when the text change.");
        //The verification of the emptiness of the fields
        if(txtFUser.getText().trim().isEmpty() || 
                pwPassword.getText().trim().isEmpty()){
            btnLogIn.setDisable(true);
        }else{
            btnLogIn.setDisable(false);
        }
        //The control of the maximum of characters
        if(txtFUser.getText().length()>20){
            String login = txtFUser.getText();
            login = login.substring(0, login.length()-1);
            txtFUser.setText(login);
            Alert alert= new Alert(AlertType.INFORMATION,
                    "You can´t write more characters than twenty in the login.",
                    ButtonType.OK);
            alert.showAndWait();
            txtFUser.requestFocus();
        }else if(pwPassword.getText().length()>16){
            String password= pwPassword.getText();
            password = password.substring(0, password.length()-1);
            pwPassword.setText(password);
            Alert alert= new Alert(AlertType.INFORMATION, 
                    "You can´t write more characters than sixteen in the "
                            + "password.", ButtonType.OK);
            alert.showAndWait();
            pwPassword.requestFocus();
        }
    }
    
    /**
     * Load the signUp xml and pass the control to it controller
     * @param event 
     */
    public void handleSignUp(ActionEvent event){
        //Create the loader for the signup xml
        FXMLLoader loader=new FXMLLoader(getClass()
                .getResource("/view/xml/SignUpXML.fxml"));
        //Create the parent and load the tree
        Parent root;
        try {
            root = (Parent) loader.load();
            //Create the Stage
            Stage signUpStage=new Stage();
            //Load de controller
            SignUpController controller = loader.getController();
            //Set the stage
            controller.setStage(signUpStage);
            //Pass the control to the controller
            controller.initStage(root);
            //Pass the communication with the next layer
            controller.setLogic(logic);
            //Hide this stage
            stage.hide();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "An input-output error in the logOut loader.", 
                    ex);
        }catch (Exception ex){
            LOG.log(Level.SEVERE, "An generic error in the logOut loader.", 
                    ex);
        }finally{
            Alert alert =new Alert(AlertType.ERROR, "A error have occurred.", 
                    ButtonType.OK);
            alert.showAndWait();
            txtFUser.requestFocus();
        }
    }
    
    
    
    /**
     * Send the user that it's write in the text fields to the logic a receive
     * the answer in form of or the user with all the information or 
     * an exception saying the problem 
     */
    public void logIn() {
        //Get the username and the password
        User us=new User(txtFUser.getText(), pwPassword.getText());
        try{
            //Send the user to compare the infomation with the database 
            //information and receive the user that it's login, 
            //with all infomation
            user = logic.loginUser(us);
            //if all in the login have gone right, go to the logOut method 
            logOut(user);
        //Run when the login isn't in the database
        }catch(LoginNotExistingException e1){
            LOG.log(Level.SEVERE, e1.getMESSAGE(), e1);
            lblUser.setTextFill(Color.web("#ff0000"));
            lblPass.setTextFill(Color.web("#237bf7"));
            Alert alert = new Alert(AlertType.ERROR,"The user not exist.", 
                    ButtonType.OK);
            //Show the alert
            alert.showAndWait();
        //Run when the login is correct but the password no
        }catch(WrongPasswordException e2){
            LOG.log(Level.SEVERE, e2.getMESSAGE(), e2);
            lblUser.setTextFill(Color.web("#237bf7"));
            lblPass.setTextFill(Color.web("#ff0000"));
            Alert alert = new Alert(AlertType.ERROR,"The password is wrong.", 
                    ButtonType.OK);
            //Show the alert
            alert.showAndWait();
        //The generic exception
        }catch(Exception e3){
            LOG.log(Level.SEVERE, e3.getMessage(), e3);
            lblUser.setTextFill(Color.web("#237bf7"));
            lblPass.setTextFill(Color.web("#237bf7"));
            Alert alert = new Alert(AlertType.ERROR,
                    "A error with the program have ocurred.", ButtonType.OK);
            alert.showAndWait();
        }finally{
            txtFUser.requestFocus();
        }
    }
    
    /**
     * Load the logOut xml and pass the control to it controller 
     * @param user 
     */
    public void logOut(User user){
        //Create the loader for the xml
        FXMLLoader loader=new FXMLLoader(getClass()
                .getResource("/view/xml/LogOutXML.fxml"));
        //Create the parent and load the tree
        Parent root;
        try{
            root = (Parent) loader.load();
            //Create the Stage
            Stage logOutStage=new Stage();
            //Load de controller
            LogOutController controller = loader.getController();
            //Set the stage and the user
            controller.setStage(logOutStage);
            
            controller.setUser(user);
            //Pass the communication with the next layer
            controller.setLogic(logic);
            //Pass the control to the controller
            controller.initStage(root);
            //Hide this stage
            stage.hide();
        }catch(IOException ex){
            LOG.log(Level.SEVERE, "An error in the logOut loader.", 
                    ex.getMessage());
            Alert alert= new Alert(AlertType.ERROR, "A error have ocurred in the login.", 
                    ButtonType.OK);
            alert.showAndWait();
            txtFUser.requestFocus();
        }catch(Exception ex){
            LOG.log(Level.SEVERE, "An error in the logOut loader.", 
                    ex.getMessage());
            Alert alert= new Alert(AlertType.ERROR, "A error have ocurred in the login.", 
                    ButtonType.OK);
            alert.showAndWait();
            txtFUser.requestFocus();
        }
    }
}
