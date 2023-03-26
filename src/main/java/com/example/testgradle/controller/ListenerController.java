package com.example.testgradle.controller;

import com.example.testgradle.data.entity.ListenerEntity;
import com.example.testgradle.service.ListenerService;
import com.example.testgradle.service.impl.ListenerServiceImpl;
import java.net.http.WebSocket.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/listener")
public class ListenerController {

    private ListenerService listenerService;

    private final Logger LOGGER = LoggerFactory.getLogger(ListenerController.class);

    @Autowired
    public ListenerController(ListenerService listenerService){
        this.listenerService = listenerService;
    }

    @GetMapping
    public String getListener(Long id){
        LOGGER.info("[getListener] called!");
        listenerService.getEntity(id);

        return "OK";
    }

    @PostMapping
    public void saveListener(String name){
        LOGGER.info("[saveListener] called!");
        ListenerEntity listener = new ListenerEntity();
        listener.setName(name);

        listenerService.saveEntity(listener);
    }

    @PutMapping
    public void updateListener(Long id, String name){
        LOGGER.info("[updateListener] called!");
        ListenerEntity listener = new ListenerEntity();
        listener.setId(id);
        listener.setName(name);

        listenerService.updateEntity(listener);
    }

    @DeleteMapping
    public void deleteListener(Long id){
        LOGGER.info("[deleteListener] called!");
        ListenerEntity listener = listenerService.getEntity(id);

        listenerService.removeEntity(listener);
    }

}
