package edu.westga.comp4420.project_portfolio.view.codebehind;

import edu.westga.comp4420.project_portfolio.model.GuiHelper;
import edu.westga.comp4420.project_portfolio.model.Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * CodeBehind To Handle Processing for the Accounts Portfolio Page
 *
 * @author	Jacob Baker
 * @version Spring 2025
 */
public class AccountPortfolioPageView {
	
	@FXML
    private AnchorPane anchorPane;

    @FXML
    private Button addProjectsButton;

    @FXML
    private Button delete1;

    @FXML
    private Button delete2;

    @FXML
    private Button delete3;

    @FXML
    private Button edit;

    @FXML
    private Button lastProjectsButton;

    @FXML
    private Button nextProjectsButton;

    @FXML
    private ImageView profileImage;

    @FXML
    private ImageView profilePicture;

    @FXML
    private TextArea projectDescription1;

    @FXML
    private TextArea projectDescription2;

    @FXML
    private TextArea projectDescription3;

    @FXML
    private TextField projectEdit1;

    @FXML
    private TextField projectEdit2;

    @FXML
    private TextField projectEdit3;

    @FXML
    private ImageView projectImage1;

    @FXML
    private ImageView projectImage2;

    @FXML
    private ImageView projectImage3;

    @FXML
    private Pane projectPane1;

    @FXML
    private Pane projectPane2;

    @FXML
    private Pane projectPane3;

    @FXML
    private Button projectView1;

    @FXML
    private Button projectView2;

    @FXML
    private Button projectView3;

    @FXML
    private Label userName;

    @FXML
    void handleAddProjectButtonClick(ActionEvent event) {

    }

    @FXML
    void handleDeleteProjectsButtonClick(ActionEvent event) {

    }

    @FXML
    void handleEditButtonClick(ActionEvent event) {

    }

    @FXML
    void handleHomeClick(MouseEvent event) {
		GuiHelper.switchView(this.anchorPane, Views.HOMEPAGE);
    }

    @FXML
    void handleLastProejctsButtonClick(ActionEvent event) {

    }

    @FXML
    void handleNextProjectsButtonClick(ActionEvent event) {

    }

    @FXML
    void handlePersonalAccountClick(MouseEvent event) {
		GuiHelper.switchView(this.anchorPane, Views.ACCOUNT);
    }

    @FXML
    void handleSearchBar(ActionEvent event) {

    }

    @FXML
    void handleSearchButtoneClick(MouseEvent event) {

    }

    @FXML
    void handleViewButtonClick(ActionEvent event) {

    }
	
	/**
	* this returns the anchor pane that will be changed 
	*
	* @return anchorPane that is in use to help change views
	*/
	public AnchorPane getAnchorPane() {
		return this.anchorPane;
	}

	/**
	*this sets the anchorPane that will be used as the view
	*
	* @param tempAnchorPane which it the anchor pane being set to
	*/
	public void setAnchorPane(AnchorPane tempAnchorPane) {
		this.anchorPane = tempAnchorPane;
	}

}
