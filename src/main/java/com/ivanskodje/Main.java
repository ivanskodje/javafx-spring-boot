package com.ivanskodje;

import com.ivanskodje.preloader.AppPreloader;
import com.sun.javafx.application.LauncherImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		LauncherImpl.launchApplication(JavaFXSpringBoot.class, AppPreloader.class, args);
	}
}