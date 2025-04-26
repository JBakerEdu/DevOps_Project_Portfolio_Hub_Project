package edu.westga.comp4420.project_portfolio.view.codebehind;

import edu.westga.comp4420.project_portfolio.model.Session;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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
    private Label accountHeader;

    @FXML
    private Button backButton;

    @FXML
    private TextArea codeTextArea;

    @FXML
    private ScrollPane codeTextAreaPane;

    @FXML
    private Button editButton;

    @FXML
    private ListView<String> folderListView;

    @FXML
    private ImageView profileImage;

    @FXML
    private ImageView profilePicture;
	
	@FXML
    private TextArea descriptionTextArea;

    @FXML
    private Hyperlink projectHyperlink;
	
	@FXML
    private Button saveButton;

    @FXML
    private Button uploadButton;

    @FXML
    private Label userName;
	
	@FXML
    private TextField projectNameTextField;

    @FXML
    private Label projectTitleLabel;
	
	@FXML
    private Label lastEditedLabel;
	
	@FXML
    private TextField hyperLinkTextField;


    @FXML
	void handleBackButtonClick(ActionEvent event) {
		if (this.initialDirectory == null || this.currentDirectory == null) {
			this.showAlert("Info", "No file uploaded.");
			return;
		}
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
		this.codeTextAreaPane.setVisible(false);
		this.folderListView.setVisible(true);
		this.uploadButton.setVisible(true);
		this.saveButton.setVisible(true);
		this.projectNameTextField.setVisible(true);
		this.projectTitleLabel.setVisible(false);
		this.editButton.setVisible(false);
		this.descriptionTextArea.setEditable(true);
		this.projectHyperlink.setVisible(false);
		this.hyperLinkTextField.setEditable(true);
    }
	
	@FXML
    void handleSaveButtonClick(ActionEvent event) {
		this.codeTextAreaPane.setVisible(false);
		this.folderListView.setVisible(true);
		this.uploadButton.setVisible(false);
		this.saveButton.setVisible(false);
		this.projectNameTextField.setVisible(false);
		this.projectTitleLabel.setVisible(true);
		this.editButton.setVisible(true);
		this.descriptionTextArea.setEditable(false);
		this.projectHyperlink.setVisible(true);
		this.hyperLinkTextField.setEditable(false);
		this.hyperLinkTextField.clear();
		
		LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDateTime.format(formatter);
        this.lastEditedLabel.setText("Last Edited: " + formattedDate);
		this.projectTitleLabel.setText(this.projectNameTextField.getText());
		
		String link = this.hyperLinkTextField.getText();
		if (link != null && !link.trim().isEmpty()) {
			this.projectHyperlink.setText(link);
			
		} else {
			this.projectHyperlink.setText("No link provided");
		}
    }

    @FXML
    void handleHomeClick(MouseEvent event) {
		GuiHelper.switchView(this.anchorPane, Views.HOMEPAGE);
    }

    @FXML
    void handlePersonalAccountClick(MouseEvent event) {
		if (Session.getInstance().getCurrentUser() != null) {
			GuiHelper.switchView(this.anchorPane, Views.ACCOUNT);
		} else {
			GuiHelper.switchView(this.anchorPane, Views.LOGIN);
		}
    }

    @FXML
	void handleProjectHyperlinkClick(ActionEvent event) {
		String link = this.projectHyperlink.getText();
		if (link != null && !link.trim().isEmpty()) {
			try {
				java.awt.Desktop.getDesktop().browse(java.net.URI.create(link));
			} catch (IOException e) {
				e.printStackTrace();
				this.showAlert("Error", "Failed to open the link.");
			}
		} else {
			this.showAlert("Error", "No link provided.");
		}
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
		if (Session.getInstance().getCurrentUser() != null) {
			String username = Session.getInstance().getCurrentUser().getUsername();
			this.accountHeader.setText(username);
		} else {
			GuiHelper.switchView(this.anchorPane, Views.LOGIN);
			this.accountHeader.setText("Account");
		}
		this.codeTextAreaPane.setVisible(false);
		this.folderListView.setVisible(true);
		this.uploadButton.setVisible(false);
		this.saveButton.setVisible(false);
		this.projectNameTextField.setVisible(false);
		this.projectTitleLabel.setVisible(true);
		this.editButton.setVisible(true);
		this.lastEditedLabel.setText("Last Edited: Not yet saved");
		this.projectHyperlink.setText("No link provided");
		
		
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
