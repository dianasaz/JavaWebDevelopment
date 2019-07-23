package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.entity.Pet;

import java.util.List;

public interface PetService  extends Service{

    List<Pet> findAll() throws DaoException;

    by.sazanchuk.finalTask.entity.Pet findByIdentity(Integer identity) throws DaoException;

    int save(by.sazanchuk.finalTask.entity.Pet service) throws DaoException;

    void delete(Integer identity) throws DaoException;
}
