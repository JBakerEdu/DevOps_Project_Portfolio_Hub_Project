package edu.westga.comp4420.project_portfolio.view.codebehind;

import edu.westga.comp4420.project_portfolio.model.Session;
import edu.westga.comp4420.project_portfolio.model.User;
import edu.westga.comp4420.project_portfolio.model.AccountManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * CodeBehind To Handle Processing for the Create Account Page
 *
 * @author	Jacob Baker
 * @version Spring 2025
 */
public class CreateAccountPageView {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private PasswordField confirmPasswordTextFeild;

    @FXML
    private TextField emailTextFeild;

    @FXML
    private Label errorNotConfirmedPassword;

    @FXML
    private Label errorNotCorrectPassword;

    @FXML
    private Label errorNotValidEmail;

    @FXML
    private Label errorNotValidUsername;
	
	@FXML
    private Label accountHeader;

    @FXML
    private PasswordField passwordTextFeild;

    @FXML
    private TextField userNameTextFeild;
	
	@FXML
	private Button createAccountSubmitButton;

    @FXML
    void handleCreateAccountButtonClick(ActionEvent event) {
		this.hideAllErrors();

        String username = this.userNameTextFeild.getText();
        String password = this.passwordTextFeild.getText();
        String confirmPassword = this.confirmPasswordTextFeild.getText();
        String email = this.emailTextFeild.getText();


        if (!password.equals(confirmPassword)) {
            this.errorNotConfirmedPassword.setVisible(true);
            return;
        }
        if (!email.contains("@") || !email.contains(".")) {
            this.errorNotValidEmail.setVisible(true);
            return;
        }
        boolean success = AccountManager.createAccount(username, password, email);
        if (success) {
            User newUser = AccountManager.validateLogin(username, password);
            Session.getInstance().login(newUser);
            GuiHelper.switchView(this.anchorPane, Views.ACCOUNT);
        } else {
            this.errorNotValidUsername.setVisible(true);
        }
    }

    @FXML
    void handleHasAccountClick(MouseEvent event) {
		GuiHelper.switchView(this.anchorPane, Views.LOGIN);
    }

    @FXML
    void handleHomeClick(MouseEvent event) {
		GuiHelper.switchView(this.anchorPane, Views.HOMEPAGE);
    }

    @FXML
    void handlePersonalAccountClick(MouseEvent event) {
		GuiHelper.switchView(this.anchorPane, Views.ACCOUNT);
    }

    @FXML
    void handleSearchBar(ActionEvent event) {
		// Not implemented yet
    }

    @FXML
    void handleSearchButtoneClick(MouseEvent event) {
		// Not implemented yet
    }
	
	@FXML
	void initialize() {
		if (Session.getInstance().getCurrentUser() != null) {
			String username = Session.getInstance().getCurrentUser().getUsername();
			this.accountHeader.setText(username);
		} else {
			this.accountHeader.setText("Account");
		}
		this.errorNotConfirmedPassword.setVisible(false);
		this.errorNotCorrectPassword.setVisible(false);
		this.errorNotValidEmail.setVisible(false);
		this.errorNotValidUsername.setVisible(false);

		this.createAccountSubmitButton.setDisable(true);

		this.userNameTextFeild.textProperty().addListener((observable, oldValue, newValue) -> {
			this.checkFieldsAndToggleButton();
		});

		this.passwordTextFeild.textProperty().addListener((observable, oldValue, newValue) -> {
			this.checkFieldsAndToggleButton();
		});

		this.confirmPasswordTextFeild.textProperty().addListener((observable, oldValue, newValue) -> {
			this.checkFieldsAndToggleButton();
		});

		this.emailTextFeild.textProperty().addListener((observable, oldValue, newValue) -> {
			this.checkFieldsAndToggleButton();
		});
	}

	private void checkFieldsAndToggleButton() {
		boolean allFieldsFilled = !this.userNameTextFeild.getText().trim().isEmpty()
				&& !this.passwordTextFeild.getText().trim().isEmpty()
				&& !this.confirmPasswordTextFeild.getText().trim().isEmpty()
				&& !this.emailTextFeild.getText().trim().isEmpty();

		this.createAccountSubmitButton.setDisable(!allFieldsFilled);
	}

    private void hideAllErrors() {
        this.errorNotConfirmedPassword.setVisible(false);
        this.errorNotCorrectPassword.setVisible(false);
        this.errorNotValidEmail.setVisible(false);
        this.errorNotValidUsername.setVisible(false);
    }
	
}
