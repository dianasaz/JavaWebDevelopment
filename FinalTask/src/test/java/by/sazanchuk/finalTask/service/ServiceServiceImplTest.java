package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.Service;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceServiceImplTest {

    @Test
    public void testOne() throws ConnectionPoolException, DaoException {

        ServiceFactory factory = new ServiceFactory();

        ServiceService service = factory.getService(ServiceService.class);

        Service s = new Service();
        s.setName("Microchipping");
        s.setPrice(500);

        int id = service.save(s);

        Assert.assertEquals(1, id);

    }

}