package finalProjectGame;

import java.io.*;
import java.util.*;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class GameSystem.
 */
public class GameSystem extends Application {

	private SecondWindow win;

	/** The gamelist. */
	private ArrayList<Game> gamelist = new ArrayList<>();;

	/** The menu bar. */
	private MenuBar menuBar;

	/** The file menu. */
	private Menu fileMenu;

	/** The save item. */
	private MenuItem exitItem, saveItem;

	/** The combo box. */
	private ComboBox comboBox;

	/** The txt period. */
	private TextField txtAddId, txtAddTitle, txtAddProvider, txtRemoveId,
			txtSearchId, txtSearchTitle, txtRentId, txtReturnId, txtPeriod;

	/** The rbt title. */
	private RadioButton rbtId, rbtTitle;

	/** The group. */
	private ToggleGroup group;

	/** The btn end. */
	private Button btnAdd, btnAddClear, btnRemove, btnRemoveClear, btnSearch,
			btnSearchClear, btnRent, btnRentClear, btnReturn, btnReturnClear,
			btnStatus, btnTxtAreaClear, btnSave, btnEnd, btnEdit;

	/** The txa display. */
	private TextArea txaDisplay;

	/** The games. */
	private Game[] games;

	/** The go. */
	private Game go;

	/** The alert. */
	Alert alert = new Alert(Alert.AlertType.ERROR);

	/**
	 * Instantiates a new game system.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public GameSystem() throws IOException {

		games = new Game[] { new Old("Old"), new BrandNew("BrandNew"),
				new Recent("Recent") };

		readFile();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) {
		// TODO Auto-generated method stub
		GridPane root = new GridPane();
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setHgap(5);
		root.setVgap(5);

		menuBar = new MenuBar();
		fileMenu = new Menu("_File");
		saveItem = new MenuItem("S_ave"); // create a MenuItem object
		saveItem.setMnemonicParsing(true);
		saveItem.setAccelerator(new KeyCodeCombination(KeyCode.S,
				KeyCombination.SHORTCUT_DOWN));

		exitItem = new MenuItem("E_xit");
		exitItem.setMnemonicParsing(true);
		exitItem.setAccelerator(new KeyCodeCombination(KeyCode.T,
				KeyCombination.SHORTCUT_DOWN));

		fileMenu.getItems().add(saveItem); // add MenuItem to Menu
		fileMenu.getItems().add(exitItem);

		menuBar.getMenus().add(fileMenu);

		exitItem.setOnAction(e -> getButtonClicked(e));
		saveItem.setOnAction(e -> getButtonClicked(e));

		root.addRow(0, menuBar);

		Label lblGameSystem = new Label("GAME SYSTEM");

		root.add(lblGameSystem, 4, 1);
		root.setHalignment(lblGameSystem, HPos.CENTER);
		lblGameSystem.getStyleClass().add("label-title");

		Label lblAddGame = new Label("GAME_ADD:");

		comboBox = new ComboBox();
		comboBox.setPromptText("Type");
		ObservableList<Game> options = FXCollections.observableArrayList(games);
		comboBox.getItems().addAll(getGameTypes());

		Label lblAddId = new Label("Id:");
		txtAddId = new TextField();

		Label lblAddTitle = new Label("Title:");
		txtAddTitle = new TextField();
		Label lblAddProvider = new Label("Provider:");
		txtAddProvider = new TextField();

		btnAdd = new Button("Add");
		btnAddClear = new Button("Clear");

		root.addRow(2, lblAddGame, comboBox, lblAddId, txtAddId, lblAddTitle,
				txtAddTitle, lblAddProvider, txtAddProvider, btnAdd,
				btnAddClear);
		root.setHalignment(lblAddId, HPos.RIGHT);
		root.setHalignment(lblAddTitle, HPos.RIGHT);
		root.setHalignment(lblAddProvider, HPos.RIGHT);

		btnAdd.setOnAction(e -> getButtonClicked(e));
		btnAddClear.setOnAction(e -> getButtonClicked(e));

		Label lblRemoveGame = new Label("GAME_REMOVE:");
		Label lblRemoveId = new Label("Id:");
		txtRemoveId = new TextField();

		btnRemove = new Button("Remove");
		btnRemoveClear = new Button("Clear");

		btnRemove.setOnAction(e -> getButtonClicked(e));
		btnRemoveClear.setOnAction(e -> getButtonClicked(e));

		root.addRow(3, lblRemoveGame, lblRemoveId, txtRemoveId);
		root.setHalignment(lblRemoveId, HPos.RIGHT);
		root.add(btnRemove, 8, 3);
		root.add(btnRemoveClear, 9, 3);

		Label lblSearchGame = new Label("GAME_SEARCH:");
		rbtId = new RadioButton("Id");
		rbtTitle = new RadioButton("Title");

		group = new ToggleGroup();
		rbtId.setToggleGroup(group);
		rbtTitle.setToggleGroup(group);

		Label lblSearchId = new Label("Id:");
		txtSearchId = new TextField();
		Label lblSearchTitle = new Label("Title:");
		txtSearchTitle = new TextField();

		btnSearch = new Button("Search");
		btnSearchClear = new Button("Clear");

		root.addRow(4, lblSearchGame, rbtId, rbtTitle, lblSearchId,
				txtSearchId, lblSearchTitle, txtSearchTitle);
		root.setHalignment(rbtId, HPos.RIGHT);
		root.setHalignment(rbtTitle, HPos.RIGHT);
		root.setHalignment(lblSearchId, HPos.RIGHT);
		root.setHalignment(lblSearchTitle, HPos.RIGHT);
		root.add(btnSearch, 8, 4);
		root.add(btnSearchClear, 9, 4);

		btnSearch.setOnAction(e -> getButtonClicked(e));
		btnSearchClear.setOnAction(e -> getButtonClicked(e));

		Label lblRentGame = new Label("GAME_RENT:");
		Label lblRentId = new Label("Id:");
		txtRentId = new TextField();

		btnRent = new Button("Rent");
		btnRentClear = new Button("Clear");

		root.addRow(5, lblRentGame, lblRentId, txtRentId);
		root.setHalignment(lblRentId, HPos.RIGHT);
		root.add(btnRent, 8, 5);
		root.add(btnRentClear, 9, 5);

		btnRent.setOnAction(e -> getButtonClicked(e));
		btnRentClear.setOnAction(e -> getButtonClicked(e));

		Label lblReturnGame = new Label("GAME_RETURN:");
		Label lblReturnId = new Label("Id:");
		txtReturnId = new TextField();

		Label lblLendingPeriod = new Label("LendingPeriod:");
		txtPeriod = new TextField();

		btnReturn = new Button("Return");
		btnReturnClear = new Button("Clear");

		root.addRow(6, lblReturnGame, lblReturnId, txtReturnId);
		root.add(lblLendingPeriod, 4, 6);
		root.setHalignment(lblLendingPeriod, HPos.RIGHT);
		root.setHalignment(lblReturnId, HPos.RIGHT);
		root.add(txtPeriod, 5, 6);
		root.add(btnReturn, 8, 6);
		root.add(btnReturnClear, 9, 6);

		btnReturn.setOnAction(e -> getButtonClicked(e));
		btnReturnClear.setOnAction(e -> getButtonClicked(e));

		Label lblDisplayRecord = new Label("DISPALY_RECORD");
		btnStatus = new Button("STATUS OF AllGAMES");

		root.add(lblDisplayRecord, 0, 7);
		root.add(btnStatus, 7, 7);

		btnStatus.setOnAction(e -> getButtonClicked(e));

		txaDisplay = new TextArea("HELLO!!! GAME_SYSTEM IS READY:)\n");
		txaDisplay.setPrefColumnCount(80);
		txaDisplay.setPrefRowCount(15);
		root.setAlignment(Pos.CENTER);
		root.add(txaDisplay, 0, 8);

		root.setColumnSpan(txaDisplay, 10);

		btnTxtAreaClear = new Button("CLEAR_RECORD");
		btnSave = new Button("SAVE");
		btnEnd = new Button("END");

		root.add(btnTxtAreaClear, 0, 9);
		root.add(btnSave, 6, 9);
		root.add(btnEnd, 7, 9);
		root.setHalignment(btnEnd, HPos.RIGHT);
		root.setHalignment(btnSave, HPos.RIGHT);

		btnTxtAreaClear.setOnAction(e -> getButtonClicked(e));
		btnSave.setOnAction(e -> getButtonClicked(e));
		btnEnd.setOnAction(e -> getButtonClicked(e));

		btnEdit = new Button("EDIT");
		root.add(btnEdit, 5, 9);
		root.setHalignment(btnEdit, HPos.RIGHT);
		btnEdit.setOnAction(e -> getButtonClicked(e));

		Scene scene = new Scene(root, 1300, 480);
		scene.getStylesheets().add("application.css");
		primaryStage.setTitle("GAME_SYSTEM");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Gets the button clicked.
	 *
	 * @param e
	 *            the e
	 * @return the button clicked
	 */
	public void getButtonClicked(ActionEvent e) {

		if (e.getSource() == btnAdd) {
			addGame();
			comboBox.setValue(null);
			txtAddId.clear();
			txtAddTitle.clear();
			txtAddProvider.clear();
		}

		if (e.getSource() == btnAddClear) {

			comboBox.setValue(null);
			txtAddId.clear();
			txtAddTitle.clear();
			txtAddProvider.clear();

		}

		if (e.getSource() == btnRemove) {

			delGame();
			txtRemoveId.clear();
		}

		if (e.getSource() == btnRemoveClear) {

			txtRemoveId.clear();
		}

		if (e.getSource() == btnSearch) {

			searchGame();
			rbtId.setSelected(false);
			rbtTitle.setSelected(false);
			txtSearchId.clear();
			txtSearchTitle.clear();
		}

		if (e.getSource() == btnSearchClear) {

			rbtId.setSelected(false);
			rbtTitle.setSelected(false);
			txtSearchId.clear();
			txtSearchTitle.clear();

		}

		if (e.getSource() == btnRent) {

			loanGame();
			txtRentId.clear();
		}

		if (e.getSource() == btnRentClear) {

			txtRentId.clear();
		}

		if (e.getSource() == btnReturn) {

			returnGame();
			txtPeriod.clear();
			txtReturnId.clear();
		}

		if (e.getSource() == btnReturnClear) {

			txtReturnId.clear();
			txtPeriod.clear();
		}

		if (e.getSource() == btnStatus) {

			try {
				statusGame();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		if (e.getSource() == btnTxtAreaClear) {
			txaDisplay.clear();
		}

		if (e.getSource() == exitItem || e.getSource() == btnEnd) {
			fileWriter();
			System.exit(0);
		}

		if (e.getSource() == saveItem || e.getSource() == btnSave) {
			fileWriter();
			txaDisplay
					.appendText("\nAll Inforamtion is saved susseccfully!!!!\n");
		}

		if (e.getSource() == btnEdit) {
			win = new SecondWindow();
			win.showAndWait(); // waits until we get back
		}
	}

	/**
	 * Adds the game.
	 */
	public void addGame() {

		boolean type = getGame();

		if (type) {

			String s = txtAddId.getText();

			if (validateInput(s)) {

				if (findGameInfo(s)) {

					alert.setTitle("Data Input Error");
					alert.setHeaderText("Same ID existed");
					alert.setContentText("Start begining.!!!!");
					alert.showAndWait();

					getGamelist().remove(getGamelist().size() - 1);

				} else {

					getGamelist().get(getGamelist().size() - 1).setGameId(s);

					s = txtAddTitle.getText();
					getGamelist().get(getGamelist().size() - 1).setTitle(s);

					s = txtAddProvider.getText();
					getGamelist().get(getGamelist().size() - 1).setProvider(s);
					getGamelist().get(getGamelist().size() - 1)
							.setRented(false);

					txaDisplay.appendText("New book is added.!!!!\n");

				}
			} else {
				getGamelist().remove(getGamelist().size() - 1);
				errorMsg();
			}
		} else {

			alert.setTitle("Data Selection Error");
			alert.setHeaderText("Type is not selected");
			alert.setContentText("Please Select Type!!!!");
			alert.showAndWait();
		}
	}

	/**
	 * Prep string field.
	 *
	 * @param value
	 *            the value
	 * @param size
	 *            the size
	 * @return the string
	 */
	public String prepStringField(String value, int size) {

		if (value.length() < size) {

			int numSpaces = size - value.length();
			for (int i = 0; i < numSpaces; i++) {
				value += " ";
			}

		} else {
			value = value.substring(0, size);

		}
		return value;
	}

	/**
	 * Del game.
	 */
	public void delGame() {

		if (getGamelist().size() == 0) {
			errorMsg1();
		}

		String s = txtRemoveId.getText();

		if (validateInput(s)) {

			int idx = findListIndex(s);

			if (idx == -1) {

				errorMsg1();
			} else {

				getGamelist().remove(idx);
				txaDisplay.appendText("ID : " + s + "\t is deleted.!!!!\n");

			}
		} else {
			errorMsg();
		}

	}

	/**
	 * Search game.
	 */
	public void searchGame() {

		if (rbtId.isSelected()) {

			String s = txtSearchId.getText();

			if (validateInput(s)) {

				int idx = findListIndex(s);

				if (idx != -1) {
					txaDisplay.appendText("Result of Search:\n");
					txaDisplay.appendText("ID : "
							+ getGamelist().get(idx).getGameId() + "\t");
					txaDisplay.appendText("Type : "
							+ getGamelist().get(idx).getType() + "\t");
					txaDisplay.appendText("\tTitle : "
							+ getGamelist().get(idx).getTitle() + "\t");
					txaDisplay.appendText("\tProvider : "
							+ getGamelist().get(idx).getProvider() + "\t");
					txaDisplay
							.appendText("\tStaus of rental : "
									+ (getGamelist().get(idx).isRented() ? "Not Available\n"
											: "Available\n"));

				} else {
					errorMsg1();
				}

			} else {
				errorMsg();
			}
		}

		if (rbtTitle.isSelected()) {

			String s = txtSearchTitle.getText();
			int idx = findListIndex(s);

			if (idx != -1) {
				txaDisplay.appendText("Result of Search:\n");
				txaDisplay.appendText("ID : "
						+ getGamelist().get(idx).getGameId() + "\t");
				txaDisplay.appendText("Type : "
						+ getGamelist().get(idx).getType() + "\t");
				txaDisplay.appendText("\tTitle : "
						+ getGamelist().get(idx).getTitle() + "\t");
				txaDisplay.appendText("\tProvider : "
						+ getGamelist().get(idx).getProvider() + "\t");
				txaDisplay
						.appendText("\tStaus of rental : "
								+ (getGamelist().get(idx).isRented() ? "Not Available\n"
										: "Available\n"));

			} else {
				errorMsg1();
			}
		}

	}

	/**
	 * Loan game.
	 */
	public void loanGame() {

		String s = txtRentId.getText();

		if (validateInput(s)) {
			int idx = findListIndex(s);

			if (idx == -1)
				errorMsg1();

			else {

				if (getGamelist().get(idx).isRented()) {

					alert.setTitle("Data Error");
					alert.setHeaderText("The game is not available");
					alert.setContentText("Please rent it next time!!!!");
					alert.showAndWait();

				} else {

					txaDisplay
							.appendText("\nPossible to rent.\nThank you for using.\n");
					getGamelist().get(idx).setRented(true);

				}
			}
		} else {
			errorMsg();
		}

	}

	/**
	 * Return game.
	 */
	public void returnGame() {

		String s = txtReturnId.getText();
		String temp = txtPeriod.getText();
		if (validateInput(s) && validateInput(temp)) {

			int p = Integer.parseInt(temp);
			int idx = findListIndex(s);

			if (idx == -1)

				errorMsg1();

			else {

				if (getGamelist().get(idx).isRented()) {
					String t = getGamelist().get(idx).getType();
					txaDisplay.appendText("\nThank you for using.\n");
					txaDisplay.appendText("You rent it for\t" + p
							+ " days and Your rent fee is $"
							+ getGamelist().get(idx).getFees(t, p) + "\n");
					getGamelist().get(idx).setRented(false);

				} else

					errorMsg1();

			}

		} else {
			errorMsg();
		}

	}

	/**
	 * Status game.
	 * 
	 * @throws FileNotFoundException
	 */
	public void statusGame() throws FileNotFoundException {

		String msg = "\nSTAUS OF ALL GAMES.\n";

		//readFile();

		if (getGamelist().isEmpty()) {

			errorMsg1();
		}

		txaDisplay.appendText(msg);

		for (Game i : getGamelist()) {

			txaDisplay.appendText("ID : " + i.getGameId() + "\t");
			txaDisplay.appendText("Type : " + i.getType() + "\t");
			txaDisplay.appendText("\tTitle : " + i.getTitle() + "\t");
			txaDisplay.appendText("\tProvider : " + i.getProvider() + "\t");
			txaDisplay.appendText("\tStaus of rental : "
					+ (i.isRented() ? "Not Available\n" : "Available\n"));

		}

	}

	/**
	 * Find list index.
	 *
	 * @param id
	 *            the id
	 * @return the int
	 */
	public int findListIndex(String id) {

		for (int i = 0; i < getGamelist().size(); i++) {

			if (getGamelist().get(i).equals(id))
				return i;

		}

		return -1;

	}

	/**
	 * Find game info.
	 *
	 * @param gameInfo
	 *            the game info
	 * @return true, if successful
	 */
	public boolean findGameInfo(String gameInfo) {

		for (int i = 0; i < getGamelist().size() - 1; i++) {

			if (getGamelist().get(i).equals(gameInfo)) {

				return true;
			}
		}
		return false;

	}

	/**
	 * Gets the game.
	 *
	 * @return the game
	 */
	public boolean getGame() {
		int i = comboBox.getSelectionModel().getSelectedIndex();

		if (i == 0) {
			getGamelist().add(games[0]);
			return true;
		} else if (i == 1) {
			getGamelist().add(games[1]);
			return true;
		} else if (i == 2) {
			getGamelist().add(games[2]);
			return true;
		} else
			return false;

	}

	/**
	 * Gets the game types.
	 *
	 * @return the game types
	 */
	public String[] getGameTypes() {

		String[] gameTypes = new String[games.length];

		for (int i = 0; i < gameTypes.length; i++) {

			gameTypes[i] = games[i].getType();

		}

		return gameTypes;
	}

	/**
	 * Validate input.
	 *
	 * @param s
	 *            the s
	 * @return true, if successful
	 */
	public boolean validateInput(String s) {

		return s.matches("^-?[0-9]+(\\\\.[0-9]+)?$");
	}

	/**
	 * Error msg1.
	 */
	public void errorMsg1() {

		alert.setTitle("Data Input Error");
		alert.setHeaderText("There is no game in record");
		alert.setContentText("Please add games!!!!");
		alert.showAndWait();
	}

	/**
	 * Error msg.
	 */
	public void errorMsg() {

		alert.setTitle("Data Entry Error");
		alert.setHeaderText("Invalid Value Entered");
		alert.setContentText("ID must be Numeric!!.");
		alert.showAndWait();

	}

	/**
	 * File writer.
	 */
	public void fileWriter() {

		try {

			FileWriter writer = new FileWriter("./record.dat");

			for (int i = 0; i < getGamelist().size(); i++) {
				String str = getGamelist().get(i).toString();
				writer.write(str);

				// This prevent creating a blank like at the end of the file
				if (i < getGamelist().size() - 1)
					writer.write("\n");
			}
			writer.close();

		} catch (IOException ex) {
			// TODO Auto-generated catch block
			alert.setTitle("File Error");
			alert.setHeaderText("Can not find the file");
			alert.setContentText("Please check the file !!!!");
			alert.showAndWait();
		}

	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	public void readFile() throws FileNotFoundException {
		gamelist.clear();
		
		Scanner s = new Scanner(new File("./record.dat"));

		while (s.hasNext()) {

			go = new Game();

			String id = s.next();
			String type = s.next();
			String title = s.next();
			String provider = s.next();
			boolean rented = s.nextBoolean();

			go.setGameId(id);
			go.setType(type);
			go.setTitle(title);
			go.setProvider(provider);

			if (rented)
				go.setRented(true);
			else
				go.setRented(false);

			getGamelist().add(go);
		}
		s.close();
	}

	public ArrayList<Game> getGamelist() {
		return gamelist;
	}

	public void setGamelist(ArrayList<Game> gamelist) {
		this.gamelist = gamelist;
	}

}
