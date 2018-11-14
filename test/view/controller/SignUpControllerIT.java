/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import project.view.Application;

/**
 *
 * @author Gorka Redondo and Lander LLuvia
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpControllerIT extends ApplicationTest{
    //private static final Logger LOG = Logger.getLogger("view.controller.SignUpControllerIT");
    @Override
    public void start(Stage stage) throws Exception{
        new Application().start(stage);
        clickOn("#hlRegister");
    }
    
    @Test
    public void test1_InitialState(){
        verifyThat("#txtFName", isEnabled());
        verifyThat("#txtFEmail", isEnabled());
        verifyThat("#txtFUser", isEnabled());
        verifyThat("#pwPassword", isEnabled());
        verifyThat("#pwRpPassword", isEnabled());
        verifyThat("#btnPassShow", isEnabled());
        verifyThat("#btnRpPassShow", isEnabled());
        verifyThat("#btnSignUp", isDisabled());
        verifyThat("#btnCancel", isEnabled());
    }
    
    /**
     * Tests if the signup button is still disabled after writing in the
     * fields one by one.
     */
    @Test
    public void test2_handleTextChanged(){
        clickOn("#txtFName");
        write("a");
        verifyThat("#btnSignUp", isDisabled());
        eraseText(1);
        clickOn("#txtFEmail");
        write("a");
        verifyThat("#btnSignUp", isDisabled());
        eraseText(1);
        clickOn("#txtFUser");
        write("a");
        verifyThat("#btnSignUp", isDisabled());
        eraseText(1);
        clickOn("#pwPassword");
        write("a");
        verifyThat("#btnSignUp", isDisabled());
        eraseText(1);
        clickOn("#pwRpPassword");
        write("a");
        verifyThat("#btnSignUp", isDisabled());
    }
    
   /**
     * Tests if the signup button enables after filling all the fields
     */
    @Test
    public void test3_handleAllFields(){
        clickOn("#txtFName");
        write("a");
        verifyThat("#btnSignUp", isDisabled());
        clickOn("#txtFEmail");
        write("a");
        verifyThat("#btnSignUp", isDisabled());
        clickOn("#txtFUser");
        write("a");
        verifyThat("#btnSignUp", isDisabled());
        clickOn("#pwPassword");
        write("a");
        verifyThat("#btnSignUp", isDisabled());
        clickOn("#pwRpPassword");
        write("a");
        verifyThat("#btnSignUp", isEnabled());
    }
    
    /**
     * Tests the maximum and minimum size of all the fields.
     */
    @Test
    public void test4_handleFieldsSize(){
        //too long
        clickOn("#txtFName");
        for(int i = 1; i <= 101; i++){
            write("a");
        }
        push(KeyCode.ENTER);
        clickOn("#txtFEmail");
        for(int i = 1; i <= 51; i++){
            write("a");
        }
        push(KeyCode.ENTER);
        clickOn("#txtFUser");
        for(int i = 1; i <= 21; i++){
            write("a");
        }
        push(KeyCode.ENTER);
        clickOn("#pwPassword");
        for(int i = 1; i <= 17; i++){
            write("a");
        }
        push(KeyCode.ENTER);
        clickOn("#pwRpPassword");
        for(int i = 1; i <= 17; i++){
            write("a");
        }
        push(KeyCode.ENTER);
        
        //too short - user
        doubleClickOn("#txtFEmail");
        write("@.");
        doubleClickOn("#txtFUser");
        write("a");
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);
        clickOn("#txtFUser");
        write("LanderGorka");
        // password and repeat password
        clickOn("#pwPassword");
        eraseText(15);
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);
        /*clickOn("#pwPassword");
        write("1234");
        clickOn("#pwRpPassword");
        write("1234");
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);*/
    }
    
    /**
     * Checks the restrictions of the email field
     */
    @Test
    public void test5_handleEmailRestriction(){
        clickOn("#txtFName");
        write("Lander Lluvia");
        clickOn("#txtFUser");
        write("lander");
        clickOn("#pwPassword");
        write("1234");
        clickOn("#pwRpPassword");
        write("1234");
        
        clickOn("#txtFEmail");
        write("lander");
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);
        clickOn("#txtFEmail");
        write("lander@gmail");
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);
        clickOn("#txtFEmail");
        write("gmail.com");
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);
        /*clickOn("#txtFEmail");
        write("lander@gmail.com");
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);*/
    }
    
    /**
     * Checks if the password and repeat password fields aren't equals and if 
     * they are equals.
     */
    @Test
    public void test6_handlePasswordRestriction(){
        clickOn("#txtFName");
        write("Lander Lluvia");
        clickOn("#txtFEmail");
        write("lander@gmail.com");
        clickOn("#txtFUser");
        write("lander");
        
        
        clickOn("#pwPassword");
        write("derlluv");
        clickOn("#pwRpPassword");
        write("derllug");
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);
        /*clickOn("#pwPassword");
        write("derlluv");
        clickOn("#pwRpPassword");
        write("derlluv");
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);*/
    }
   /**
     * Checks if the show password, repeat password and cancel 
     * buttons work properly.
     */
    @Test
    public void test7_showPasswordAndCancel(){
        clickOn("#pwPassword");
        write("hola");
        moveTo("#btnPassShow");
        press(MouseButton.PRIMARY);
        verifyThat("#txtFPassword", isVisible());
        verifyThat("#pwPassword", isInvisible());
        release(MouseButton.PRIMARY);
        clickOn("#pwRpPassword");
        write("hola");
        moveTo("#btnRpPassShow");
        press(MouseButton.PRIMARY);
        verifyThat("#txtFRpPassword", isVisible());
        verifyThat("#pwRpPassword", isInvisible());
        release(MouseButton.PRIMARY);
        clickOn("#btnCancel");
    }
    
    /**
     * Checks if the user or email fields exist in the database or not.
     */
    @Test
    public void test8_UserOrEmailExist(){
        //User exist
        clickOn("#txtFName");
        write("Lander Lluvia");
        clickOn("#txtFEmail");
        write("gorka@gmail.com");
        clickOn("#txtFUser");
        write("lander");
        clickOn("#pwPassword");
        write("derlluv");
        clickOn("#pwRpPassword");
        write("derlluv");
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);
        //Email exist
        doubleClickOn("#txtFEmail");
        write("lander@gmail.com");
        clickOn("#txtFUser");
        write("gorka");
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);
        //All correct
        clickOn("#txtFEmail");
        write("gorka@gmail.com");
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);
    }
}

