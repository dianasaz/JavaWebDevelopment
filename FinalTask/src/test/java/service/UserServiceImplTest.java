package service;

import dao.DaoException;
import dao.connectionPool.ConnectionPoolException;
import dao.transaction.TransactionFactory;
import entity.User;
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

        Assert.assertEquals(3, users.size());
    }

}