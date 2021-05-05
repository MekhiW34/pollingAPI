package com.mekhi.pollingapi.services;

import com.mekhi.pollingapi.Repositories.PollRepository;
import com.mekhi.pollingapi.classes.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PollService {

    @Autowired
    PollRepository pollRepository;

    public void getAllPolls(){
        Iterable<Poll> allPolls = pollRepository.findAll();
    }

    public Optional<Poll> getPoll(Long pollId){
        return pollRepository.findById(pollId);
    }

    public void deletePoll(Long pollId){
      pollRepository.deleteById(pollId);
    }

    public void updatePolls(Poll poll){
        pollRepository.save(poll);
    }

    public void createPoll(Poll poll){
        pollRepository.save(poll);
    }

}
