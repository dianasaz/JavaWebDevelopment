package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.PetDao;
import by.sazanchuk.finalTask.entity.Pet;

import java.util.List;

public class PetServiceImpl extends ServiceImpl implements PetService{
    @Override
    public List<Pet> findAll() throws DaoException {
        PetDao petDao = transaction.createDao(PetDao.class);
        return petDao.read();
    }

    @Override
    public Pet findByIdentity(Integer identity) throws DaoException {
        PetDao petDao = transaction.createDao(PetDao.class);
        return petDao.read(identity);
    }

    @Override
    public int save(Pet pet) throws DaoException {
        PetDao petDao = transaction.createDao(PetDao.class);
        petDao.update(pet);
        return pet.getIdentity();
    }

    @Override
    public void delete(Integer identity) throws DaoException {
        PetDao petDao = transaction.createDao(PetDao.class);
        petDao.delete(identity);
    }

    public List<Pet> getPetsOfOneUser(Integer userId) throws DaoException {
        PetDao petDao = transaction.createDao(PetDao.class);
        return petDao.readPetsWithOneUser(userId);
    }
}
