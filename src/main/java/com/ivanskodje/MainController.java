package com.ivanskodje;

import com.ivanskodje.service.ItemService;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainController implements Initializable
{
    @Autowired
    ItemService itemService;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        System.out.println("# MainController - initialize() start");
    }
}
