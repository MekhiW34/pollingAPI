package com.mekhi.pollingapi.Repositories;

import com.mekhi.pollingapi.classes.Poll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends CrudRepository<Poll, Long> {





}
