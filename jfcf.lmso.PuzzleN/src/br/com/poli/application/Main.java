package br.com.poli.application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	
	//cria stage,scene e arquivo staticos para manipulacao no controlador
	public static Stage stage;
	public static Scene cenaTelaInicial;
  

	@Override
	public void start(Stage primaryStage) throws IOException {
		
		Pane telaInicial = FXMLLoader.load(getClass().getResource("TelaInicial.fxml"));
		Image image = new Image("/images/design.png");
		cenaTelaInicial = new Scene(telaInicial,400,300);
		stage = primaryStage;
		stage.setTitle("Puzzle-N");
		stage.getIcons().add(image);
		stage.setScene(cenaTelaInicial);
		stage.setResizable(false);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
