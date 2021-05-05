package com.mekhi.pollingapi.Controller;

import com.mekhi.pollingapi.Repositories.VoteRepository;
import com.mekhi.pollingapi.classes.Poll;
import com.mekhi.pollingapi.classes.ResourceNotFoundException;
import com.mekhi.pollingapi.classes.Vote;
import com.mekhi.pollingapi.dto.VoteResult;
import com.mekhi.pollingapi.services.ComputeResultService;
import com.mekhi.pollingapi.services.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ComputeResultController {

    @Autowired
    private ComputeResultService computeResultService;
    @Autowired
    private PollService pollService;

    public void verifyPoll(Long pollId) throws ResourceNotFoundException {
        Optional<Poll> poll = pollService.getPoll(pollId);
        if (poll.isEmpty()) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }
    }
    @RequestMapping(value = "/computeresult", method = RequestMethod.GET)
    public ResponseEntity<?> computeResult (@RequestParam Long pollId) {
        verifyPoll(pollId);
        return new ResponseEntity<>(computeResultService.computeResult(pollId), HttpStatus.OK);
    }


}
