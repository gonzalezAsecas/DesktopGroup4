/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controller;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
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
        //too short
        clickOn("#txtFUser");
        eraseText(20);
        clickOn("#btnSignUp");
    }
    
    @Test
    public void test5_handleEmailRestriction(){
        clickOn("#txtFName");
        write("Lander Lluvia");
        clickOn("#txtFUser");
        write("landerlluv");
        clickOn("#pwPassword");
        write("landerlluv");
        clickOn("#pwRpPassword");
        write("landerlluv");
        
        clickOn("#txtFEmail");
        write("lander");
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);
        clickOn("#txtFEmail");
        write("@gmail");
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);
        clickOn("#txtFEmail");
        write(".");
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);
        clickOn("#txtFEmail");
        write("com");
        clickOn("#btnSignUp");
    }
    
    @Test
    public void test6_handlePasswordRestriction(){
        clickOn("#txtFName");
        write("Lander Lluvia");
        clickOn("#txtFUser");
        write("landerlluv");
        clickOn("#pwRpPassword");
        write("landerlluv");
        clickOn("#txtFEmail");
        write("lander");
        
        clickOn("#pwPassword");
        write("lan");
        clickOn("#pwRpPassword");
        write("lan");
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);
        clickOn("#pwPassword");
        write("aaaaaaaaaaaaaa");
        push(KeyCode.ENTER);
        clickOn("#pwRpPassword");
        write("aa");
        clickOn("#btnSignUp");
        push(KeyCode.ENTER);
        clickOn("#pwRpPassword");
        write("aaaaaaaaaaaa");
        clickOn("#btnSignUp");
        
        
        
        
    }
}
