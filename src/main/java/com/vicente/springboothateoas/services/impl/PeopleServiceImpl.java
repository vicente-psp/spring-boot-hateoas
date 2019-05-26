package com.vicente.springboothateoas.services.impl;

import com.vicente.springboothateoas.entities.People;
import com.vicente.springboothateoas.repositories.PeopleRepository;
import com.vicente.springboothateoas.services.PeopleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired private PeopleRepository repository;

    @Override
    public People post(People entity) {
        try {
            logger.debug("\tMétodo POST executado.");
            logger.debug("\tMétodo POST invocado");
            logger.debug(String.format("\tValor recebido: %s", entity.toString()));

            People entitySaved = repository.save(entity);

            logger.info(String.format("\tValor persistido: %s", entitySaved.toString()));
            return entitySaved;
        } catch (Exception e) {
            logger.error(String.format("Error ao persistir registro. \nMensagem:%s", e.getMessage()));
            return null;
        }
    }

    @Override
    public People get(Long id) {
        return null;
    }

    @Override
    public void put(People entity) {

    }

    @Override
    public void delete(People entity) {

    }

    @Override
    public void patch(People entity) {

    }

    @Override
    public List<People> post(List<People> entities) {
        return null;
    }

    @Override
    public List<People> get() {
        return null;
    }

    @Override
    public void put(List<People> entities) {

    }

    @Override
    public void delete(List<People> entities) {

    }

    @Override
    public void patch(List<People> entities) {

    }
}
