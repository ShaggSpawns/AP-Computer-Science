package main;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane pane = (Pane)FXMLLoader.load(getClass().getResource("View.fxml"));
        Scene scene = new Scene(pane, 1280, 800);
		primaryStage.setTitle("AP Computer Science");
        primaryStage.setScene(scene);
        primaryStage.show();
		/*BorderPane layout = new BorderPane();
		layout.setLeft(buildTree());
		layout.setTop(buildMenu());
		layout.setCenter(buildTextArea());
		layout.setBottom(buildUtilBox());
		*/
	}
	
	private GridPane buildUtilBox() {
		GridPane grid = new GridPane();
		grid.add(new Button("Button"), 0, 0);
		grid.add(new Button("Button"), 0, 1);
		grid.add(new Button("Button"), 0, 2);
		grid.add(new Button("Button"), 0, 3);
		grid.add(new Button("Button"), 0, 4);
		return grid;
	}
	
	private TabPane buildTextArea() {
		TabPane tabPane = new TabPane();
		
		//Instructions
		Tab instructions = new Tab("Instructions");
		tabPane.getTabs().add(instructions);
		/* TODO
		 * Make folder for .PDFs or text, if one is missing and available, then download from Moodle
		 * Make login window prompt for Moodle when downloading
		 */
		
		//Source Code
		Tab code = new Tab("Source Code");
		code.setContent(new TextArea());
		tabPane.getTabs().add(code);
		//RichTextFX
		
		//Javadocs
		Tab jDocs = new Tab("Javadocs");
		jDocs.setContent(buildWeb());
		tabPane.getTabs().add(jDocs);
		return tabPane;
	}
	
	private WebView buildWeb() {
		WebView web = new WebView();
		web.getEngine().getLoadWorker().stateProperty().addListener( new ChangeListener<State>() {
            public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, State oldState, State newState) {
                if (newState == State.SUCCEEDED) {
                	System.out.println("Succeeded");
                }
            }
        });
		web.getEngine().load("https://docs.oracle.com/javase/8/docs/api/");
		return web;
	}
	
	private MenuBar buildMenu() {
		//File menu
        Menu fileMenu = new Menu("File");
        MenuItem newFile = new MenuItem("New");
        newFile.setOnAction(e -> System.out.println("Create a new file..."));
        fileMenu.getItems().add(newFile);
        fileMenu.getItems().add(new MenuItem("Open..."));
        fileMenu.getItems().add(new MenuItem("Save..."));
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(new MenuItem("Settings..."));
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(new MenuItem("Exit..."));

        //Edit menu
        Menu editMenu = new Menu("_Edit");
        editMenu.getItems().add(new MenuItem("Cut"));
        editMenu.getItems().add(new MenuItem("Copy"));
        MenuItem paste = new MenuItem("Paste");
        paste.setOnAction(e -> System.out.println("Pasting some crap"));
        paste.setDisable(true);
        editMenu.getItems().add(paste);

        //Help menu
        Menu helpMenu = new Menu("Help");
        CheckMenuItem showLines = new CheckMenuItem("Show Line Numbers");
        showLines.setOnAction(e -> {
            if(showLines.isSelected())
                System.out.println("Program will now display line numbers");
            else
                System.out.println("Hiding line number");
        });
        CheckMenuItem autoSave = new CheckMenuItem("Enable Autosave");
        autoSave.setSelected(true);
        helpMenu.getItems().addAll(showLines, autoSave);

        //Difficulty RadioMenuItems
        Menu difficultyMenu = new Menu("Difficulty");
        ToggleGroup difficultyToggle = new ToggleGroup();

        RadioMenuItem easy = new RadioMenuItem("Easy");
        RadioMenuItem medium = new RadioMenuItem("Medium");
        RadioMenuItem hard = new RadioMenuItem("Hard");

        easy.setToggleGroup(difficultyToggle);
        medium.setToggleGroup(difficultyToggle);
        hard.setToggleGroup(difficultyToggle);

        difficultyMenu.getItems().addAll(easy, medium, hard);

        //Main menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu, difficultyMenu);
        return menuBar;
	}
	
	private TreeView<String> buildTree() {
		TreeView<String> tree = new TreeView<String>();
		TreeItem<String> root, practice, lab, project;
		
		//root
		root = new TreeItem<>("AP Computer Science");
		root.setExpanded(true);
		
		//Practice
		practice = makeBranch("Practice", root);
		makeBranch("joe", practice);
		
		//Lab
		lab = makeBranch("Labs", root);
		makeBranch("jim", lab);
		
		//Project
		project = makeBranch("Projects", root);
		makeBranch("bob", project);
		
		//Create Tree
		tree = new TreeView<>(root);
		tree.setShowRoot(true);
		return tree;
	}
	
	private TreeItem<String> makeBranch(String name, TreeItem<String> parent) {
		TreeItem<String> item = new TreeItem<>(name);
		item.setExpanded(false);
		parent.getChildren().add(item);
		return item;
	}
}
