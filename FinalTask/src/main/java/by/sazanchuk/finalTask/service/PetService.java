package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.entity.Pet;

import java.util.List;

public interface PetService  extends Service{

    List<Pet> findAll() throws ServiceException;

    by.sazanchuk.finalTask.entity.Pet findByIdentity(Integer identity) throws ServiceException;

    int save(by.sazanchuk.finalTask.entity.Pet service) throws ServiceException;

    void delete(Integer identity) throws ServiceException;

    List<Pet> getPetsOfOneUser(Integer userId) throws ServiceException;

    Pet findByNameAndUserId(String name, Integer user_id) throws ServiceException;
}
