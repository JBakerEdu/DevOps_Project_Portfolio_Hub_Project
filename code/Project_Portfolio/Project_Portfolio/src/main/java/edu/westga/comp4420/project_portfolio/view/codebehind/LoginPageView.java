package edu.westga.comp4420.project_portfolio.view.codebehind;

import edu.westga.comp4420.project_portfolio.model.Session;
import edu.westga.comp4420.project_portfolio.model.User;
import edu.westga.comp4420.project_portfolio.model.AccountManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * CodeBehind To Handle Processing for the Login Page
 *
 * @author	Jacob Baker
 * @version Spring 2025
 */
public class LoginPageView {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label errorNotCorrectPassword;

    @FXML
    private Label errorNotValidUsername;

    @FXML
    private PasswordField passwordTextFeild;

    @FXML
    private TextField userNameTextFeild;

    @FXML
    void handleHomeClick(MouseEvent event) {

    }

    @FXML
    void handleLoginButtonClick(ActionEvent event) {
		String username = this.userNameTextFeild.getText();
		String password = this.passwordTextFeild.getText();
    
		User user = AccountManager.validateLogin(username, password);
		if (user != null) {
			Session.getInstance().login(user);
			GuiHelper.switchView(this.anchorPane, Views.ACCOUNT);
		} else {
			this.errorNotCorrectPassword.setVisible(true);
		}
    }

    @FXML
    void handleNoAccountClick(MouseEvent event) {
		GuiHelper.switchView(this.anchorPane, Views.CREATE_ACCOUNT);
    }

    @FXML
    void handlePersonalAccountClick(MouseEvent event) {

    }

    @FXML
    void handleSearchBar(ActionEvent event) {

    }

    @FXML
    void handleSearchButtoneClick(MouseEvent event) {

    }

}
