package by.sazanchuk.finalTask.service;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.entity.Doctor;
import by.sazanchuk.finalTask.entity.Service;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DoctorServiceImplTest {

    @Test
    public void testOne() throws ConnectionPoolException, DaoException {
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
    public void testTwo() throws ConnectionPoolException, DaoException {
        ServiceFactory factory = new ServiceFactory();
        DoctorService service = factory.getService(DoctorService.class);
        ServiceService serviceS = factory.getService(ServiceService.class);
       // Doctor doctor = service.findByIdentity(5);
        //for (int i = 0; i < doctor.getService().size(); i++){
          //  System.out.println(doctor.getService().get(i).getName() + ", ");
     /*   Doctor doctor = new Doctor();
        doctor.setName("Dmitry");
        Service service1 = serviceS.searchServiceByName("Microchiping");
        Service service2 = serviceS.searchServiceByName("Strizka");
        doctor.addService(service1);
        doctor.addService(service2);
        for (int i = 0; i < doctor.getService().size(); i++) {
            System.out.println(doctor.getService().get(i).getName() + ", ");
        }
        service.save(doctor, service1);

        Doctor d = new Doctor();
        d.setName("Popa");
        String[] services = {"Microchiping", "Strizka"};
        for (String s : services) {
            Service service1 = serviceS.searchServiceByName(s);
            if (service1 != null) {
                d.addService(service1);
                service.save(d, service1);
            }
        }*/
        List<Doctor> doctors = service.findAll();
        for (int i = 0; i < doctors.size(); i++){
            System.out.println(doctors.get(i).getService() + ", ");
            //for (int j = 0; j < doctors.get(i).getService().size(); j++)
            //System.out.println(doctors.get(i).getService().get(j) + ", ");
        }
    }


    }

