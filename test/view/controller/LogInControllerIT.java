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
 * @author Jon Gonzalez
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LogInControllerIT extends ApplicationTest{
    /**
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        new Application().start(stage);
    }
    
    /**
     * 
     */
    
    @Test
    public void test1_initStage(){
        verifyThat("#btnLogIn", isDisabled());
        verifyThat("#hlRegister",isEnabled());
    }
    
    /**
     * 
     */
    
    @Test
    public void test2_handleTextChanged(){
        clickOn("#txtFUser");
        write("txikle");
        verifyThat("#btnLogIn", isDisabled());
        eraseText(6);
        clickOn("#pwPassword");
        write("1234");
        verifyThat("#btnLogIn", isDisabled());
        eraseText(4);
        clickOn("#txtFUser");
        write("txikle");
        clickOn("#pwPassword");
        write("1234");
        verifyThat("#btnLogIn", isEnabled());
        clickOn("#txtFUser");
        eraseText(6);
        clickOn("#pwPassword");
        eraseText(4);
        clickOn("#txtFUser");
        write("txikletxikletxikletxi");
        push(KeyCode.ENTER);
        eraseText(20);
        clickOn("#pwPassword");
        write("12345678901234567");
        push(KeyCode.ENTER);
    }
    @Test
    public void test3_handleSignUp(){
        clickOn("#hlRegister");
        clickOn("#btnCancel");
    }
    
    @Test
    public void test4_logOut(){
        clickOn("#txtFUser");
        write("txiklee");
        clickOn("#pwPassword");
        write("1234");
        clickOn("#btnLogIn");
        push(KeyCode.ENTER);
        clickOn("#txtFUser");
        eraseText(1);
        clickOn("#pwPassword");
        write("5");
        clickOn("#btnLogIn");
        push(KeyCode.ENTER);
        clickOn("#pwPassword");
        eraseText(1);
        clickOn("#btnLogIn");
        verifyThat("#btnLogOut", isEnabled());
        clickOn("#btnLogOut");
        push(KeyCode.ENTER);
    }

    
}
