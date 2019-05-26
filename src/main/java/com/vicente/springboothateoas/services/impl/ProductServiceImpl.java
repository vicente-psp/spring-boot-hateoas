package com.vicente.springboothateoas.services.impl;

import com.vicente.springboothateoas.entities.Product;
import com.vicente.springboothateoas.repositories.ProductRepository;
import com.vicente.springboothateoas.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository repository;

    @Override
    public Product post(Product entity) {
        try {
            logger.debug("\tMétodo POST executado.");
            logger.debug("\tMétodo POST invocado");
            logger.debug(String.format("\tValor recebido: %s", entity.toString()));

            entity.setCreationDate(new Date());
            Product entitySaved = repository.save(entity);

            logger.info(String.format("\tValor persistido: %s", entitySaved.toString()));
            return entitySaved;
        } catch (Exception e) {
            logger.error(String.format("Error ao persistir registro. \nMensagem:%s", e.getMessage()));
            return null;
        }
    }

    @Override
    public Product get(Long id) {
        try {
            Optional<Product> optional = repository.findById(id);
            return optional.get();
        } catch (Exception e) {
            logger.error("Error ao recuperar método GET.");
            return null;
        }
    }

    @Override
    public void put(Product entity) {
        try {
            repository.save(entity);
        } catch (Exception e) {
            logger.error("Error ao persistir registro.");
        }
    }

    @Override
    public void delete(Product entity) {
        try {
            repository.delete(entity);
        } catch (Exception e) {
            logger.error("Error ao deletar registro.");
        }
    }

    @Override
    public void patch(Product entity) {
        try {
            repository.save(entity);
        } catch (Exception e) {
            logger.error("Error ao persistir registro.");
        }
    }

    @Override
    public List<Product> post(List<Product> entities) {
        try {
            List<Product> entitiesSaved = new ArrayList<>();
            for (Product entity: entities) {
                entity.setCreationDate(new Date());
                repository.save(entity);
                entities.add(entity);
            }
            return entitiesSaved;
        } catch (Exception e) {
            logger.error("Error ao persistir registro.");
        }
        return null;
    }

    @Override
    public List<Product> get() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            logger.error("Error ao recuperar registro." + e.getMessage());
        }
        return null;
    }

    @Override
    public void put(List<Product> entities) {
        try {
            for (Product entity: entities) {
                repository.save(entity);
            }
        } catch (Exception e) {
            logger.error("Error ao persistir registro.");
        }
    }

    @Override
    public void delete(List<Product> entities) {
        try {
            for (Product entity: entities) {
                repository.delete(entity);
            }
        } catch (Exception e) {
            logger.error("Error ao deletar registro.");
        }
    }

    @Override
    public void patch(List<Product> entities) {
        try {
            for (Product entity: entities) {
                repository.save(entity);
            }
        } catch (Exception e) {
            logger.error("Error ao persistir registro.");
        }
    }

}
