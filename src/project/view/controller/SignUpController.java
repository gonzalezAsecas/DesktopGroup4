/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.view.controller;

import exceptions.EmailNotUniqueException;
import exceptions.LoginExistingException;
import java.io.IOException;
import java.sql.Timestamp;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import project.logic.Logic;
import message.Privilege;
import message.Status;
import message.User;

/**
 * Clase que define los manejadores de eventos de la interfaz
 * @author Gorka
 */
public class SignUpController {
    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger("view.controller.SignUpController");
    /**
     * Campo nombre completo
     */
    @FXML
    private TextField txtFName;
    /**
     * Campo email
     */
    @FXML
    private TextField txtFEmail;
    /**
     * Campo usuario
     */
    @FXML
    private TextField txtFUser;
    /**
     * Campo contraseña visible
     */
    @FXML
    private TextField txtFPassword;
    /**
     * Campo repite contraseña visible
     */
    @FXML
    private TextField txtFRpPassword;
    /**
     * Campo contraseña
     */
    @FXML
    private PasswordField pwPassword;
    /**
     * Campo repite contraseña
     */
    @FXML
    private PasswordField pwRpPassword;
    /**
     * Boton registrase
     */
    @FXML
    private Button btnSignUp;
    /**
     * Boton cancelar
     */
    @FXML
    private Button btnCancel;
    /**
     * Boton ver contraseña
     */
    @FXML
    private Button btnPassShow;
    /**
     * Boton ver contraseña repetida
     */
    @FXML
    private Button btnRpPassShow;
    /**
     * Label email
     */
    @FXML
    private Label lblEmail;
    /**
     * Label usuario
     */
    @FXML
    private Label lblUser;
    /**
     * Label contraseña
     */
    @FXML
    private Label lblPassword;
    /**
     * Label repetir contraseña
     */
    @FXML
    private Label lblRpPassword;
    
    /**
     * Atributo logica
     */
    private Logic logic;
    public void setLogic(Logic logic) {
        this.logic=logic;
    }
    
    /**
     * Atributo ventana
     */
    private Stage stage;
    public void setStage(Stage stage){
        this.stage=stage;
    }
    
    /**
     * Inilization of the window
     * @param root Contenedor
     */
    public void initStage(Parent root){
        LOG.info("Initializing Signup stage");
        //Create a scene associated to the node grap root
        Scene scene = new Scene(root);
        //Associate scene to Stage(Window)
        stage.setScene(scene);
        //Set window properties
        stage.setTitle("Sign up");
        stage.setResizable(false);
        //Set window's event handlers
        stage.setOnShowing(this::handleWindowShowing);
        //Set control events
        btnSignUp.setOnAction(this::pushSignUp);
        btnCancel.setOnAction(this::pushCancel);
        btnPassShow.setOnMousePressed(this::pressPassShow);
        btnPassShow.setOnMouseReleased(this::relasePassShow);
        btnRpPassShow.setOnMousePressed(this::pressPassShow);
        btnRpPassShow.setOnMouseReleased(this::relasePassShow);
        txtFName.textProperty().addListener(this::textChanged);
        txtFEmail.textProperty().addListener(this::textChanged);
        txtFUser.textProperty().addListener(this::textChanged);
        pwPassword.textProperty().addListener(this::textChanged);
        pwRpPassword.textProperty().addListener(this::textChanged);
        //Show window
        stage.show();
    }
    
    /**
     * Creates a mnemonic to SignUp and Cancel button
     * @param event Un evento
     */
    public void handleWindowShowing(WindowEvent event){
        LOG.info("Beginning handleWindowShowing");
        //Set the mnemonic parse
        btnSignUp.setMnemonicParsing(true);
        btnCancel.setMnemonicParsing(true);
        //Set the mnemonic character and the text
        btnSignUp.setText("_SignUp");
        btnCancel.setText("_Cancel");
    }
    
    /**
     * Text changed of the fields Full name, Email, Username, Password y
     * Repeat Password
     * @param observable Parametro que comprueba si el valor viejo cambia
     * @param oldValue Valor viejo
     * @param newValue Valor nuevo
     */
     private void textChanged(ObservableValue observable, String oldValue,
            String newValue) {
        //If all textfields are not empty, set enable the SignUp button
        if(!txtFName.getText().trim().isEmpty() && !txtFEmail.getText().trim().isEmpty()
               && !txtFUser.getText().trim().isEmpty() && !pwPassword.getText().trim().isEmpty()
               && !pwRpPassword.getText().trim().isEmpty()){
           btnSignUp.setDisable(false);
        }else {
           btnSignUp.setDisable(true);
        }
        
        //Test if the name is too long
        if(txtFName.getLength() == 101){
            LOG.info("Name too long");
            txtFName.setText(txtFName.getText().substring(0,100));
            new Alert(AlertType.INFORMATION,"Name too long",ButtonType.OK).show();
        }
        
        //Test if the email is too long
        if(txtFEmail.getLength() == 51){
            LOG.info("Email too long");
            txtFEmail.setText(txtFEmail.getText().substring(0,50));
            new Alert(AlertType.INFORMATION,"Email too long",ButtonType.OK).show();
        }
        
        //Test if the username is too long
        if(txtFUser.getLength() == 21){
            LOG.info("User too long");
            txtFUser.setText(txtFUser.getText().substring(0,20));
            new Alert(AlertType.INFORMATION,"User too long",ButtonType.OK).show();
        }
        
        //Test if the password is too long
        if(pwPassword.getLength() == 17){
            LOG.info("Password too long");
            pwPassword.setText(pwPassword.getText().substring(0,16));
            new Alert(AlertType.INFORMATION,"Password too long",ButtonType.OK).show();
        }
        
        //Test if the repeat password is too long
        if(pwRpPassword.getLength() == 17){
            LOG.info("Password too long");
            pwRpPassword.setText(pwRpPassword.getText().substring(0,16));
            new Alert(AlertType.INFORMATION,"Password too long",ButtonType.OK).show();
        } 
     }
    
    /**
     * Pulse SignUp button
     * @param event Un evento
     */
    private void pushSignUp(ActionEvent event){
        if(verifyEmail() && verifyUser() && verifyPassword()){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            User user = new User(Status.enabled, Privilege.user);
            user.setFullName(txtFName.getText());
            user.setLogin(txtFUser.getText());
            user.setEmail(txtFEmail.getText());
            user.setPassword(pwPassword.getText());
            user.setLastAcces(timestamp);
            user.setLastPasswordChange(timestamp);
            try {
                logic.signUpUser(user);
                LOG.info("Your registration has been completed successfully");
                new Alert(AlertType.CONFIRMATION,"Your registration has been "
                        + "completed successfully",ButtonType.OK).showAndWait();
                openLogIn();
            } catch (LoginExistingException lee) {
                LOG.log(Level.SEVERE, lee.getMESSAGE());
                lblUser.setTextFill(Color.web("#FF0000"));
                new Alert(AlertType.ERROR,lee.getMESSAGE(), ButtonType.OK).showAndWait();
                txtFUser.requestFocus();
            } catch (EmailNotUniqueException enue) {
                LOG.log(Level.SEVERE, enue.getMESSAGE());
                lblEmail.setTextFill(Color.web("#FF0000"));
                new Alert(AlertType.ERROR,enue.getMESSAGE(), ButtonType.OK).showAndWait();
                txtFEmail.requestFocus();
            } catch (Exception ex) {
                LOG.log(Level.SEVERE, ex.getMessage());
            }
        }
    }
    
    /**
     * Mouse down PassShow button
     * @param event Un evento
     */
    private void pressPassShow(MouseEvent event){
        if(btnPassShow.isPressed()){
            txtFPassword.setText(pwPassword.getText());
            txtFPassword.setVisible(true);
            pwPassword.setVisible(false);
        }else if(btnRpPassShow.isPressed()){
            txtFRpPassword.setText(pwRpPassword.getText());
            txtFRpPassword.setVisible(true);
            pwRpPassword.setVisible(false);
        }
    }
    
    /**
     * Mouse up PassShow button
     * @param event Un evento
     */
    private void relasePassShow(MouseEvent event){
        pwPassword.setVisible(true);
        txtFPassword.setVisible(false);
        
        pwRpPassword.setVisible(true);
        txtFRpPassword.setVisible(false);
    }
    
    /**
     * Pulse Cancel button
     * @param event An event
     */
    private void pushCancel(ActionEvent event){
        openLogIn();
    }
    
    /**
     * Method to open LogIn window and close SignUp
     */
    private void openLogIn() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
            "/project/view/xml/LogInXML.fxml"));
            Parent root = (Parent)loader.load();
            Stage logInStage=new Stage();
            LogInController controller = ((LogInController)loader.getController());
            controller.setStage(logInStage);
            controller.setLogic(logic);
            controller.initStage(root);
            stage.hide();
            LOG.info("LogIn window opened successfully");
        }catch(IOException e){
            LOG.log(Level.SEVERE, e.getMessage());
            new Alert(AlertType.ERROR,e.getMessage(),ButtonType.OK).show();
        }
    }
    
    /**
     * Method to validate email
     * @return boolean Es un tipo de dato de true or false
     */
    private boolean verifyEmail() {
        boolean a = false;
        boolean b = false;
        for(int i = 0; i < txtFEmail.getLength(); i++){
            if(!a && txtFEmail.getText().substring(i, i+1).equalsIgnoreCase("@")){
               a = true; 
            }
            if(a && txtFEmail.getText().substring(i, i+1).equalsIgnoreCase(".")){
                b = true;
            }
        }
        if(b){
            lblEmail.setTextFill(Color.web("#237bf7"));
            return true;
        }else {
            LOG.info("Your email have wrong format. Correct format is: "
                    + "example@example.example");
            lblEmail.setTextFill(Color.web("#FF0000"));
            new Alert(AlertType.ERROR,"Your email have wrong format. Correct "
                    + "format is: example@example.example",ButtonType.OK).showAndWait();
            txtFEmail.requestFocus();
            return false;
        }
    }
    
    /**
     * Method to validate user
     * @return boolean Es un tipo de dato de true or false
     */
    private boolean verifyUser() {
        if(txtFUser.getLength() >= 4){
            lblUser.setTextFill(Color.web("#237bf7"));
            return true;
        }else {
            LOG.info("Username too short");
            lblUser.setTextFill(Color.web("#FF0000"));
            new Alert(AlertType.ERROR,"Username too short",ButtonType.OK).showAndWait();
            txtFUser.requestFocus();
            return false;
        }
    }
    
    /**
     * Method to validate password
     * @return boolean Es un tipo de dato de true or false
     */
    private boolean verifyPassword() {
        if(pwPassword.getLength() >= 4){
            lblPassword.setTextFill(Color.web("#237bf7"));
            if(pwPassword.getText().equals(pwRpPassword.getText())){
                lblRpPassword.setTextFill(Color.web("#237bf7"));
                return true;
            }else {
                LOG.info("Password do not match");
                lblRpPassword.setTextFill(Color.web("#FF0000"));
                new Alert(AlertType.ERROR,"Password do not match",ButtonType.OK).showAndWait();
                pwRpPassword.requestFocus();
                return false;
            }
        }else {
            LOG.info("Password too short");
            lblPassword.setTextFill(Color.web("#FF0000"));
            lblRpPassword.setTextFill(Color.web("#FF0000"));
            new Alert(AlertType.ERROR,"Password too short",ButtonType.OK).showAndWait();
            pwPassword.requestFocus();
            return false;
        }
    }
}
