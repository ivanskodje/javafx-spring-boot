package com.ivanskodje;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JavaFXSpringBoot extends javafx.application.Application {
	private static final String FXML_PATH = "/fxml/Main.fxml";
	private ConfigurableApplicationContext configurableApplicationContext;

	@Override
	public void init() {
		try {
			initializeSpring();
			simulateWork(); // TODO: Add preloading in place of this method
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.exit(0);
		}
	}

	@Override
	public void start(Stage primaryStage) {
		System.out.println("JavaFXSPringBoot.java start");
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
		setLoadPercentage(1);
		configurableApplicationContext = SpringApplication.run(Main.class);
		setLoadPercentage(10);
	}

	private void simulateWork() throws InterruptedException {
		Long waitTime = 3000L;
		Long numberOfUpdates = 10L;
		for (int i = 1; i < numberOfUpdates; i++) {
			setLoadPercentage(i * 10);
			Thread.sleep(waitTime / numberOfUpdates);
		}
	}

	private void setLoadPercentage(int progressPercentage) {
		notifyPreloader(new Preloader.ProgressNotification(progressPercentage / 100.0));
	}

	private FXMLLoader getFxmlLoader() {
		return new FXMLLoader(getClass().getResource(FXML_PATH));
	}

	private void setupPrimaryStage(Stage primaryStage, FXMLLoader fxmlLoader) throws IOException {
		primaryStage.setScene(new Scene(fxmlLoader.load()));
		primaryStage.show();
	}
}
