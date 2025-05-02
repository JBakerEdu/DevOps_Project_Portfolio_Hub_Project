package edu.westga.comp4420.project_portfolio.view.codebehind;

/**
 * Stores the different view locations
 * 
 * @author Jacob Baker
 * @version Spring 2025
 */
public enum Views {
	HOMEPAGE("/edu/westga/comp4420/project_portfolio/view/codebehind/HomePageView.fxml"),
	ACCOUNT("/edu/westga/comp4420/project_portfolio/view/codebehind/AccountPortfolioPageView.fxml"),
	PROJECTS("/edu/westga/comp4420/project_portfolio/view/codebehind/ProjectPageView.fxml"),
	LOGIN("/edu/westga/comp4420/project_portfolio/view/codebehind/LoginPageView.fxml"),
	CREATE_ACCOUNT("/edu/westga/comp4420/project_portfolio/view/codebehind/CreateAccountPageView.fxml");
	
	private String fileLocation;
	
	Views(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	
	/**
	 * Get the file location of the enum
	 * @return the file location associated with the enum
	 */
	public String location() {
		return this.fileLocation;
	}
}