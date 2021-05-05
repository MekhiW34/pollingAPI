package com.mekhi.pollingapi.services;

import com.mekhi.pollingapi.Repositories.VoteRepository;
import com.mekhi.pollingapi.classes.OptionCount;
import com.mekhi.pollingapi.classes.Vote;
import com.mekhi.pollingapi.dto.VoteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ComputeResultService {

    @Autowired
    VoteRepository voteRepository;
    public VoteResult computeResult (Long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);
        //algorithm to count votes
        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<>();
        for (Vote v : allVotes) {
            totalVotes ++;
            OptionCount optionCount = tempMap.get(v.getOption().getId());
            if (optionCount == null) {
                optionCount = new OptionCount();
                optionCount.setOptionId(v.getOption().getId());
                tempMap.put(v.getOption().getId(), optionCount);
            }
            optionCount.setCount(optionCount.getCount() + 1);
            voteResult.setTotalValues(totalVotes);
            voteResult.settotalVotes(totalVotes);
            voteResult.setResults(tempMap.values());
        }
        return voteResult;
    }




}
