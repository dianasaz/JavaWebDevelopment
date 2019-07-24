package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.Pet;
import by.sazanchuk.finalTask.entity.PetList;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class PetServiceImplTest {

    @Test
    public void testOne() throws ConnectionPoolException, DaoException, ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.yyyy");

        ServiceFactory factory = new ServiceFactory();

        PetService service = factory.getService(PetService.class);
        Pet u = service.findByNameAndUserId("pet", 1);

        if (u != null) service.delete(u.getIdentity());


        Pet pet = new Pet();

        pet.setName("pet");
        pet.setUser_identity(1);
        pet.setKind(PetList.setPet("cat"));
        pet.setDateOfBirth(dateFormat.parse("12.12.2012"));

        Integer pet_Id = service.save(pet);
        pet.setIdentity(pet_Id);

        Assert.assertEquals(pet_Id, pet.getIdentity());

    }

}