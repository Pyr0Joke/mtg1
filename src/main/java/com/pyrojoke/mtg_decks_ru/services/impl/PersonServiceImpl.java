package com.pyrojoke.mtg_decks_ru.services.impl;

import com.pyrojoke.mtg_decks_ru.model.Person;
import com.pyrojoke.mtg_decks_ru.repositories.PersonRepository;
import com.pyrojoke.mtg_decks_ru.services.PersonService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Set<Person> findAll() {
        Set<Person> persons = new HashSet<>();
        personRepository.findAll().forEach(persons::add);
        return persons;
    }

    @Override
    public Person findById(Long aLong) {
        return personRepository.findById(aLong).orElse(null);
    }

    @Override
    public Person save(Person object) {
        return personRepository.save(object);
    }

    @Override
    public void delete(Person object) {
        personRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        personRepository.deleteById(aLong);
    }
}
