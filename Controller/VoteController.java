package com.mekhi.pollingapi.Controller;

import com.mekhi.pollingapi.Repositories.VoteRepository;
import com.mekhi.pollingapi.classes.Poll;
import com.mekhi.pollingapi.classes.ResourceNotFoundException;
import com.mekhi.pollingapi.classes.Vote;
import com.mekhi.pollingapi.services.PollService;
import com.mekhi.pollingapi.services.VoteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@RestController
public class VoteController {

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private VoteServices voteServices;
    @Autowired
    private PollService pollService;

    public void verifyPoll(Long pollId) throws ResourceNotFoundException {
        Optional<Poll> poll = pollService.getPoll(pollId);
        if (poll.isEmpty()){
            throw new ResourceNotFoundException("poll with id " + pollId + " not found");
        }
    }

    @RequestMapping(value="/polls/{pollId}/votes", method= RequestMethod.POST)
    public ResponseEntity<?> createVote(@PathVariable Long pollId, @RequestBody Vote vote) {
        voteServices.createVote(vote);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").buildAndExpand(vote.getId()).toUri());
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value="/polls/{pollId}/votes", method=RequestMethod.GET)
    public Iterable<Vote> getAllVotes(@PathVariable Long pollId) {
        return voteServices.getAllVotes();
    }

}
