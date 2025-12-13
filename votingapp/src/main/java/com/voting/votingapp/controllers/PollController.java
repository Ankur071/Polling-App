package com.voting.votingapp.controllers;

import com.voting.votingapp.entity.Poll;
import com.voting.votingapp.request.Vote;
import com.voting.votingapp.services.PollService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polls")
@CrossOrigin(origins = "*")
public class PollController {

    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    // http://localhost:8080/api/polls - POST Method
    @PostMapping
    public Poll createPoll(@RequestBody Poll poll) {
        return pollService.createPoll(poll);
    }

    // http://localhost:8080/api/polls - GET Method
    @GetMapping
    public List<Poll> getAllPolls() {
        return pollService.getAllPolls();
    }

    // http://localhost:8080/api/polls/id - GET Method
    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable Long id) {
        return pollService.getPollById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST -> VOTE -> Service
    @PostMapping("/vote")
    public void vote(@RequestBody Vote vote) {
        pollService.vote(vote.getPollId(), vote.getOptionIndex());
    }
}