package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.Pet;
import by.sazanchuk.finalTask.entity.PetList;
import by.sazanchuk.finalTask.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PetServiceImplTest {

    @Test
    public void testOne() throws ConnectionPoolException, DaoException, ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        ServiceFactory factory = new ServiceFactory();

        PetService service = factory.getService(PetService.class);

        Pet pet = new Pet();

        Pet u = service.findByNameAndUserId("kiko", 7);
        if (u != null) service.delete(u.getIdentity());

        pet.setName("koooo");
        pet.setUser_identity(7);
        pet.setKind(PetList.setPet("cat"));
        pet.setDateOfBirth(dateFormat.parse("07.12.2012"));

        Integer pet_Id = service.save(pet);
        pet.setIdentity(pet_Id);

        Assert.assertEquals(pet_Id, pet.getIdentity());

    }

    @Test
    public void testTwo() throws ConnectionPoolException, DaoException {

        ServiceFactory factory = new ServiceFactory();

        UserService serviceUser = factory.getService(UserService.class);
        PetService service = factory.getService(PetService.class);

        User u = serviceUser.findByLoginAndPassword("diana", "user");
        List<Pet> pets = new ArrayList<>();

        pets = service.getPetsOfOneUser(u.getId());
        //System.out.println(pets.toString());

        Assert.assertEquals(2, pets.size());

    }

}