package com.application.clasementAPI.controllers;

import com.application.clasementAPI.helpers.MessageModel;
import com.application.clasementAPI.pojos.FinishUpdateMatch;
import com.application.clasementAPI.pojos.MatchStore;
import com.application.clasementAPI.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/matchs")
public class MatchsController {

    @Autowired
    private MatchService matchService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<MessageModel> getMatchs(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        return matchService.getMatchs(page, size);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<MessageModel> showMatch(@PathVariable(value = "id") Long id) {
        return matchService.showMatch(id);
    }

    @RequestMapping(value = "/addMatch", method = RequestMethod.POST)
    public ResponseEntity<MessageModel> addMatch(@Valid @RequestBody MatchStore matchStore) {
        return matchService.addMatch(matchStore);
    }

    @RequestMapping(value = "/finish-match", method = RequestMethod.POST)
    public ResponseEntity<MessageModel> finishUpdateMatch(@Valid @RequestBody FinishUpdateMatch finishUpdateMatch) {
        return matchService.finishUpdateMatch(finishUpdateMatch);
    }

}
