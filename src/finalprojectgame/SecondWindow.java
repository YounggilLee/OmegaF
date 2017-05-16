package finalProjectGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//extending stage
public class SecondWindow extends Stage {
	
	private Button btnSearchId, btnEdit, btnSave,btnClose;
	private TextField txtSearchId,txtEditId, txtEditType, txtEditTitle, txtEditProvider;
	private TextArea txaDisplay ;
	private Alert alert = new Alert(Alert.AlertType.ERROR);
	private String s;
	private int idx;
	private RadioButton rbtOld, rbtRecent, rbtBrandNew;
	private ToggleGroup group;
	private Game go;
	
	public SecondWindow() {
		super();
		// code to create components and do all the layout

		GridPane root = new GridPane();
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setHgap(5);
		root.setVgap(5);

		Label lblSearchId = new Label("	SEARCH THE GAME BY ID:");
		 txtSearchId = new TextField();
		 btnSearchId = new Button("Search");
		
		btnSearchId.setOnAction(e -> getButtonClicked(e));

		Label lblEditId = new Label("	EDIT_ID:");
		 txtEditId = new TextField();
		 rbtOld = new RadioButton("Old");
		 rbtRecent = new RadioButton("Recent");
		 rbtBrandNew = new RadioButton("BrandNew");
		 
		 group = new ToggleGroup();
		 rbtOld.setToggleGroup(group);
		 rbtRecent.setToggleGroup(group);
		 rbtBrandNew.setToggleGroup(group);
		 
		 
		Label lblSeclectype = new Label("	EDIT_TYPE:");
		 //txtEditType = new TextField();
		Label lblEditTitle = new Label("	EDIT_TITLE:");
		 txtEditTitle = new TextField();
		Label lblEditProvider = new Label("	EDIT_PROVIDER:");
		 txtEditProvider = new TextField();

		 txaDisplay = new TextArea();
		txaDisplay.setPrefColumnCount(100);
		txaDisplay.setPrefRowCount(20);

		btnEdit = new Button("Edit");
		
		btnClose = new Button("Close");
				
		btnEdit.setOnAction(e -> getButtonClicked(e));
		
		btnClose.setOnAction(e -> getButtonClicked(e));

		root.add(lblSearchId, 0, 0);
		root.add(txtSearchId, 1, 0);
		root.add(btnSearchId, 2, 0);
		
		root.add(lblSeclectype,0,1);
		root.add(rbtOld,1,1);
		root.add(rbtRecent,2,1);
		root.add(rbtBrandNew,3,1);
		root.setHalignment(rbtOld, HPos.RIGHT);
		root.setHalignment(rbtRecent, HPos.LEFT);
		root.setHalignment(rbtBrandNew, HPos.LEFT);
		
		root.add(lblEditTitle, 0, 3);
		root.add(txtEditTitle, 1, 3);
		
		root.add(lblEditProvider, 0, 4);
		root.add(txtEditProvider, 1, 4);
		
		root.addRow(5, txaDisplay);
		
		root.add(btnEdit, 1, 6);
		root.setHalignment(btnEdit, HPos.LEFT);
	
		root.add(btnClose, 3, 6);

		root.setColumnSpan(txaDisplay, 4);

		Scene scene = new Scene(root, 610, 350);
		scene.getStylesheets().add("application.css");
		this.setScene(scene);
		setTitle("EDIT GAME");
		// use initModality if you want this Stage to block the app
		initModality(Modality.APPLICATION_MODAL);
	}

	private void getButtonClicked(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == btnSearchId) {
			try {
				search();
				txtSearchId.clear();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
		
		if (e.getSource() == btnEdit) {
			
			try {
				edit();				
				rbtOld.setSelected(false);
				rbtRecent.setSelected(false);
				rbtBrandNew.setSelected(false);
				txtEditTitle.clear();
				txtEditProvider.clear();
				txtSearchId.clear();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
				
		
		if (e.getSource() == btnClose) {	
		
	
					close();
		}
		
		
		
	}
	
	public void search() throws IOException {
		
		GameSystem gamesys = new GameSystem();
		
		this.s = txtSearchId.getText();
		
		if (gamesys.validateInput(s)) {
		
		this.idx = gamesys.findListIndex(s);

		if (idx != -1) {
			
			txaDisplay.appendText("Result of Search:\n");
			txaDisplay.appendText("ID : "
					+ gamesys.getGamelist().get(idx).getGameId() + "\t");
			txaDisplay.appendText("Type : "
					+ gamesys.getGamelist().get(idx).getType() + "\t");
			txaDisplay.appendText("\tTitle : "
					+ gamesys.getGamelist().get(idx).getTitle() + "\t");
			txaDisplay.appendText("\tProvider : "
					+ gamesys.getGamelist().get(idx).getProvider() + "\t");
			txaDisplay.appendText("\tStaus of rental : "
					+ (gamesys.getGamelist().get(idx).isRented() ? "Not Available\n"
							: "Available\n"));

		} else {
			gamesys.errorMsg1();
		}
	
		} else {
			gamesys.errorMsg();
		}
	 
	 	 
	
	}
	
	
	public void edit() throws IOException {
		
		GameSystem gamesys1 = new GameSystem();
		
		String title =  txtEditTitle.getText();
		String provider = txtEditProvider.getText();
		
		int temp1=0;
		int temp2=0;
			
		if(rbtOld.isSelected())
			gamesys1.getGamelist().get(idx).setType("Old");
		
		if(rbtRecent.isSelected())
			gamesys1.getGamelist().get(idx).setType("Recent");
		
		if(rbtBrandNew.isSelected())
			gamesys1.getGamelist().get(idx).setType("BrandNew");
								
				
			if(title != null){
				gamesys1.getGamelist().get(idx).setTitle(title);
				temp1=1;
			}
			if(provider != null){
				gamesys1.getGamelist().get(idx).setProvider(provider);
				temp1=2;
			}
		
		
		txaDisplay.appendText("Result of Edit:\n");
		txaDisplay.appendText("ID : "
				+ gamesys1.getGamelist().get(idx).getGameId() + "\t");
		txaDisplay.appendText("Type : "
				+ gamesys1.getGamelist().get(idx).getType() + "\t");
		txaDisplay.appendText("\tTitle : "
				+ gamesys1.getGamelist().get(idx).getTitle() + "\t");
		txaDisplay.appendText("\tProvider : "
				+ gamesys1.getGamelist().get(idx).getProvider() + "\t");
		txaDisplay.appendText("\tStaus of rental : "
				+ (gamesys1.getGamelist().get(idx).isRented() ? "Not Available\n"
						: "Available\n"));
		try {

			FileWriter writer = new FileWriter("./record.dat");

			for (int i = 0; i < gamesys1.getGamelist().size(); i++) {
				String str = gamesys1.getGamelist().get(i).toString();
				writer.write(str);

				// This prevent creating a blank like at the end of the file
				if (i < gamesys1.getGamelist().size() - 1)
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
	
	
	
}