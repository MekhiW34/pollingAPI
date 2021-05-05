package com.mekhi.pollingapi.Repositories;

import com.mekhi.pollingapi.classes.Option;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends CrudRepository<Option, Long> {
}
