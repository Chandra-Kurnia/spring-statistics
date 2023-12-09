package com.application.clasementAPI.controllers;

import com.application.clasementAPI.helpers.MessageModel;
import com.application.clasementAPI.pojos.TeamStore;
import com.application.clasementAPI.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/teams")
public class TeamsController {

    @Autowired
    private TeamService teamService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<MessageModel> getTeams(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        return teamService.getTeams(page, size);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<MessageModel> showTeam(@PathVariable(value = "id") Long id) {
        return teamService.showTeam(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<MessageModel> addTeams(@Valid @RequestBody TeamStore payload) {
        return teamService.addTeam(payload);
    }
}
