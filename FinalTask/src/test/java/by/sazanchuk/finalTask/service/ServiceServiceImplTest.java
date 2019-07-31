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

       /* ServiceFactory factory = new ServiceFactory();

        ServiceService service = factory.getService(ServiceService.class);

        Service service1 = service.searchServiceByName("Microchipping");
        if (service1 != null) service.delete(service1.getIdentity());
        Service s = new Service();
        s.setName("Microchipping");
        s.setPrice(500);

        //---------------------
        Service ser = new Service();
        ser.setName("Castrirovanie");
        ser.setPrice(2000);
        service.save(ser);
        //---------------------
       // int id = service.save(s);

        Assert.assertEquals(3, 3);
*/
    }

    @Test
    public void testTwo() throws ConnectionPoolException, DaoException {
        ServiceFactory factory = new ServiceFactory();

        ServiceService service = factory.getService(ServiceService.class);

        Service service1 = service.searchServiceByName("Microchipping");

        assertNotNull(service1);
    }

    @Test
    public void testThree() throws ConnectionPoolException, DaoException {
        ServiceFactory factory = new ServiceFactory();

        ServiceService service = factory.getService(ServiceService.class);

        Service service1 = service.searchServiceByName("j");
        if (service1 != null) service.delete(service1.getIdentity());

    }

}