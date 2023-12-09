package com.application.clasementAPI.controllers;

import com.application.clasementAPI.helpers.MessageModel;
import com.application.clasementAPI.services.ClasementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clasements")
public class ClasementController {

    @Autowired
    private ClasementService clasementService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<MessageModel> getClasements(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        return clasementService.getClasements(page, size);
    }
}
