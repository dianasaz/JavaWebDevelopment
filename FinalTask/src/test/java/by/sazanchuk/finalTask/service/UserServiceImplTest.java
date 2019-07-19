package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.dao.transaction.TransactionFactory;
import by.sazanchuk.finalTask.entity.Gender;
import by.sazanchuk.finalTask.entity.Role;
import by.sazanchuk.finalTask.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class UserServiceImplTest {

    @org.junit.Test
    public void Test() throws DaoException, ConnectionPoolException {

        ServiceFactory factory = new ServiceFactory(new TransactionFactory());

        UserService service = factory.getService(UserService.class);

        User user = null;

        boolean t;

        user = service.findByLoginAndPassword("diana", "user");

        if (user == null) {
            t = false;
        }
        else t = true;

        Assert.assertEquals(true, t);
    }

    @Test
    public void TestTwo() throws DaoException, ConnectionPoolException {

        ServiceFactory factory = new ServiceFactory(new TransactionFactory());

        UserService service = factory.getService(UserService.class);

        User user = null;

        user = service.findByIdentity(2);

        Assert.assertEquals("diana", user.getLogin());
    }

    @Test
    public void TestThree() throws DaoException, ConnectionPoolException {

        ServiceFactory factory = new ServiceFactory(new TransactionFactory());

        UserService service = factory.getService(UserService.class);

        List<User> users = service.findAll();

        //Assert.assertEquals(3, users.size());
    }

    @Test
    public void TestFour() throws DaoException, ConnectionPoolException {


        ServiceFactory factory = new ServiceFactory(new TransactionFactory());

        UserService service = factory.getService(UserService.class);

        User u = service.findByLoginAndPassword("User", "1073277");

        service.delete(u.getId());

        User user = new User();

        user.setName("User");
        user.setPhoneNumber(12894378);
        user.setRole(Role.VISITOR);
        user.setGender(Gender.WOMEN);
        user.setAddress("njsnka skan 34-3");
        user.setLogin("User");
        user.setPassword("1073277");
        user.setEmail("gosna@mail.ru");


        Integer userId = service.save(user);
        user.setId(userId);

        Assert.assertEquals(user.getId(), userId);
    }

}