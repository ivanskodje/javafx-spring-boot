package com.ivanskodje;

import com.ivanskodje.preloader.AppPreloader;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Main extends Application {
	private static final String FXML_PATH = "/fxml/Main.fxml";
	private ConfigurableApplicationContext configurableApplicationContext;

	public static void main(String[] args) {
		LauncherImpl.launchApplication(Main.class, AppPreloader.class, args);
	}

	@Override
	public void init() // TODO: Do preloading work here
	{
		try {
			initializeSpring();
			simulateWork();
		} catch (Exception ex) {
			System.out.println("Database may already be in use. Please close any other applications that may be using it.");
			System.exit(0);
		}
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = getFxmlLoader();
			fxmlLoader.setControllerFactory(configurableApplicationContext::getBean);
			setupPrimaryStage(primaryStage, fxmlLoader);
		} catch (IOException ex) {
			System.err.println("Unable to load '" + FXML_PATH + "'");
		}
	}

	@Override
	public void stop() {
		configurableApplicationContext.close();
	}

	private void initializeSpring() {
		configurableApplicationContext = SpringApplication.run(Main.class);
	}

	private void simulateWork() throws InterruptedException {
		Long waitTime = 1000L;
		Long numberOfUpdates = 10L;
		for (int i = 0; i < numberOfUpdates; i++) {
			updateLoading(numberOfUpdates / i);
			Thread.sleep(waitTime / numberOfUpdates);
		}
	}

	private void updateLoading(Long progress) {
		notifyPreloader(new Preloader.ProgressNotification(progress));
	}

	private FXMLLoader getFxmlLoader() {
		return new FXMLLoader(getClass().getResource(FXML_PATH));
	}

	private void setupPrimaryStage(Stage primaryStage, FXMLLoader fxmlLoader) throws IOException {
		primaryStage.setScene(new Scene(fxmlLoader.load()));
		primaryStage.show();
	}
}
