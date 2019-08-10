package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.Doctor;
import by.sazanchuk.finalTask.entity.Service;
import org.junit.Test;

import javax.print.Doc;
import java.util.List;

import static org.junit.Assert.*;

public class DoctorServiceImplTest {

    @Test
    public void testOne() throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        DoctorService service = factory.getService(DoctorService.class);
        ServiceService serviceS = factory.getService(ServiceService.class);

       /* Doctor d = service.findByName("Natasha");
        Service service1 = serviceS.searchServiceByName("Sterilizacia");
        if (d == null) {
            d = new Doctor();
            d.setName("Natasha");
        }
        service.save(d, service1);*/
    }


    @Test
    public void testTwo() throws ServiceException {
        ServiceFactory factory = new ServiceFactory();
        DoctorService doctorService = factory.getService(DoctorService.class);
        Doctor doctor = doctorService.findByIdentity(4);
        System.out.println(doctor.getService().toString());
        doctor.removeServices();
        System.out.println(doctor.getService().toString());
    }


    }

