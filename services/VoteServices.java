package com.mekhi.pollingapi.services;

import com.mekhi.pollingapi.Repositories.VoteRepository;
import com.mekhi.pollingapi.classes.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoteServices {


    @Autowired
    VoteRepository voteRepository;

    public void createVote(Vote vote){
        voteRepository.save(vote);
    }

    public List<Vote> getAllVotes(){
        List<Vote> votes = new ArrayList<>();
        voteRepository.findAll().forEach(votes::add);
        return votes;
    }
}
