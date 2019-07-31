package com.pyrojoke.mtg_decks_ru.repositories;

import com.pyrojoke.mtg_decks_ru.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
