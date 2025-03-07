package edu.westga.comp4420.project_portfolio.view.codebehind;

import edu.westga.comp4420.project_portfolio.model.GuiHelper;
import edu.westga.comp4420.project_portfolio.model.Views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import javafx.stage.DirectoryChooser;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


/**
 * CodeBehind To Handle Processing for the Project Page
 *
 * @author	Jacob Baker
 * @version Spring 2025
 */
public class ProjectPageView {
	
	private File currentDirectory;
	private File initialDirectory;
	private String selectedItem;
	
	@FXML
    private AnchorPane anchorPane;

    @FXML
    private Button backButton;

    @FXML
    private TextArea codeTextArea;

    @FXML
    private ScrollPane codeTextAreaPane;

    @FXML
    private Button edit;

    @FXML
    private ListView<String> folderListView;

    @FXML
    private ImageView profileImage;

    @FXML
    private ImageView profilePicture;

    @FXML
    private Hyperlink projectHyperlink;
	
	@FXML
    private Button save;

    @FXML
    private Button upload;

    @FXML
    private Label userName;

    @FXML
    private Label userName1;

    @FXML
	void handleBackButtonClick(ActionEvent event) {
		if (this.codeTextAreaPane.isVisible()) {
			this.codeTextAreaPane.setVisible(false);
			this.folderListView.setVisible(true);
			return;
		}
		if (this.currentDirectory != null && this.currentDirectory.getParentFile() != null) {
			if (!this.currentDirectory.equals(this.initialDirectory)) {
				this.currentDirectory = this.currentDirectory.getParentFile();
				this.updateFolderList(this.currentDirectory);
			} else {
				this.showAlert("Info", "You are at the initial directory and cannot go back further.");
			}
		}
	}

    @FXML
    void handleEditButtonClick(ActionEvent event) {

    }
	
	@FXML
    void handleSaveButtonClick(ActionEvent event) {

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
    void handleProjectHyperlinkClick(ActionEvent event) {
		
    }

    @FXML
    void handleSearchBar(ActionEvent event) {
		
    }

    @FXML
    void handleSearchButtoneClick(MouseEvent event) {
		
    }

    @FXML
    void handleUploadButtonClick(ActionEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedFolder = directoryChooser.showDialog(new Stage());

        if (selectedFolder != null && selectedFolder.isDirectory()) {
            this.currentDirectory = selectedFolder;
            this.updateFolderList(this.currentDirectory);
			this.initialDirectory = this.currentDirectory;
        }
    }
	
	private void updateFolderList(File directory) {
        this.folderListView.getItems().clear();
        File[] files = directory.listFiles();
        
        if (files != null) {
            for (File file : files) {
                this.folderListView.getItems().add(file.getName());
            }
        }
    }
	
	private boolean isCodeFile(File file) {
        List<String> allowedExtensions = Arrays.asList(".txt", ".java", ".py", ".cpp");
        String fileName = file.getName().toLowerCase();
        
        return allowedExtensions.stream().anyMatch(fileName::endsWith);
    }
	
	private void displayFileContent(File file) {
        try {
            String content = Files.readString(file.toPath());
            this.codeTextArea.setText(content);
            this.codeTextAreaPane.setVisible(true);
            this.folderListView.setVisible(false);
        } catch (IOException e) {
            this.showAlert("Error", "Could not read file.");
        }
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
	
	private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
	
	@FXML
	public void initialize() {
		this.codeTextAreaPane.setVisible(false);
		this.bindPropertiesAndListners();
	}
	
	private void bindPropertiesAndListners() {
		this.folderListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				File selectedFile = new File(this.currentDirectory, newValue);

				if (selectedFile.isDirectory()) {
					this.currentDirectory = selectedFile;
					this.updateFolderList(this.currentDirectory);
				} else if (this.isCodeFile(selectedFile)) {
					this.displayFileContent(selectedFile);
				}
			}
		});
	}

}
