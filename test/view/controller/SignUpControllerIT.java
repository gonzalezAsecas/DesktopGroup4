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
import org.junit.Ignore;
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
    
    /**
     * Tests initial state
     */
    /*@Test
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
    }*/
    
    /**
     * Tests if the signup button is still disabled after writing in the
     * fields one by one.
     */
    /*@Test
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
        write("1234");
        verifyThat("#btnSignUp", isDisabled());
        eraseText(4);
        clickOn("#pwRpPassword");
        write("1234");
        verifyThat("#btnSignUp", isDisabled());
        eraseText(4);
    }*/
    
   /**
     * Tests if the signup button enables after filling all the fields
     */
    /*@Test
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
        write("1234");
        verifyThat("#btnSignUp", isDisabled());
        clickOn("#pwRpPassword");
        write("12345");
        verifyThat("#btnSignUp", isEnabled());
    }*/
    
    /**
     * Tests the maximum and minimum size of all the fields.
     */
    /*@Test
    public void test4_handleFieldsSize(){
        //too long name
        clickOn("#txtFName");
        for(int i = 1; i <= 101; i++){
            write("a");
        }
        push(KeyCode.ENTER);
        //too long email
        clickOn("#txtFEmail");
        for(int i = 1; i <= 51; i++){
            write("a");
        }
        push(KeyCode.ENTER);
        //too long login
        clickOn("#txtFUser");
        for(int i = 1; i <= 21; i++){
            write("a");
        }
        push(KeyCode.ENTER);
        //too long password
        clickOn("#pwPassword");
        for(int i = 1; i <= 17; i++){
            write("a");
        }
        push(KeyCode.ENTER);
        //too long repeat password
        clickOn("#pwRpPassword");
        for(int i = 1; i <= 17; i++){
            write("a");
        }
        push(KeyCode.ENTER);
        
        //too short login
        doubleClickOn("#txtFEmail");
        write("a@gmail.com");
        doubleClickOn("#txtFUser");
        write("a");
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);
        //too short password
        write("gorka");
        doubleClickOn("#pwPassword");
        write("11");
        doubleClickOn("#pwRpPassword");
        write("12");
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);
        //error solution
        write("ok");
    }*/
    
    /**
     * Checks the restrictions of the email field
     */
    /*@Test
    public void test5_handleEmailRestriction(){
        clickOn("#txtFName");
        write("Lander Lluvia");
        clickOn("#txtFUser");
        write("lander");
        clickOn("#pwPassword");
        write("1234");
        clickOn("#pwRpPassword");
        write("1234");
        //email restriction
        clickOn("#txtFEmail");
        write("lander");
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);
        write("lander@gmail");
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);
        write("gmail.com");
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);
        //error solution
        write("ok");
    }*/
    
    /**
     * Checks if the password and repeat password fields aren't equals and if 
     * they are equals.
     */
    /*@Test
    public void test6_handlePasswordRestriction(){
        clickOn("#txtFName");
        write("Lander Lluvia");
        clickOn("#txtFEmail");
        write("lander@gmail.com");
        clickOn("#txtFUser");
        write("lander");
        //password restriction
        clickOn("#pwPassword");
        write("1234");
        clickOn("#pwRpPassword");
        write("1235");
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);
        //error solution
        write("ok");
    }*/
    
   /**
     * Checks if the show password, repeat password and cancel 
     * buttons work properly.
     */
    /*@Test
    public void test7_showPasswordAndCancel() throws InterruptedException{
        clickOn("#pwPassword");
        write("gorka");
        moveTo("#btnPassShow");
        press(MouseButton.PRIMARY);
        //verifyThat("#txtFPassword", isVisible());
        //verifyThat("#pwPassword", isInvisible());
        release(MouseButton.PRIMARY);
        clickOn("#pwRpPassword");
        write("gorka");
        moveTo("#btnRpPassShow");
        press(MouseButton.PRIMARY);
        //verifyThat("#txtFRpPassword", isVisible());
        //verifyThat("#pwRpPassword", isInvisible());
        release(MouseButton.PRIMARY);
        clickOn("#btnCancel");
    }*/
    
    /**
     * Checks if the user or email fields exist in the database or not.
     */
    @Test
    public void test8_UserOrEmailExist(){
        //User exist
        clickOn("#txtFName");
        write("Gorka Redondo");
        clickOn("#txtFEmail");
        write("gorka@gmail.com");
        clickOn("#txtFUser");
        write("lander");
        clickOn("#pwPassword");
        write("1234");
        clickOn("#pwRpPassword");
        write("1234");
        //clickOn("#btnSignUp");
        //push(KeyCode.ENTER);
        //Email exist
        //write("gorka");
        /*clickOn("#txtFEmail");
        eraseText(15);
        write("lander@gmail.com");
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);
        //All correct
        write("gorka@gmail.com");*/
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);
    }
    
    
}

