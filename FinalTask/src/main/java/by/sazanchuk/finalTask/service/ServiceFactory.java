package by.sazanchuk.finalTask.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import by.sazanchuk.finalTask.dao.DaoException;
import by.sazanchuk.finalTask.dao.connectionPool.ConnectionPoolException;
import by.sazanchuk.finalTask.dao.transaction.Transaction;
import by.sazanchuk.finalTask.dao.transaction.TransactionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServiceFactory{
    private static Logger logger = LogManager.getLogger(ServiceFactory.class);

    private TransactionFactory factory;

    private static final Map<Class<? extends Service>, Class<? extends ServiceImpl>> SERVICES = new ConcurrentHashMap<>();

    static {
        SERVICES.put(UserService.class, UserServiceImpl.class);
    }

    public ServiceFactory() throws DaoException, ConnectionPoolException {
        factory = new TransactionFactory();
    }


    public <Type extends Service> Type getService(Class<Type> key) throws DaoException {
        Class<? extends ServiceImpl> value = SERVICES.get(key);
        if(value != null) {
            try {
                ClassLoader classLoader = value.getClassLoader();
                Class<?>[] interfaces = {key};//??????????????????????????
                Transaction transaction = factory.createTransaction();
                ServiceImpl service = value.newInstance();
                service.setTransaction(transaction);
                InvocationHandler handler = new ServiceInvocationHandlerImpl(service);
                return (Type)Proxy.newProxyInstance(classLoader, interfaces, handler);
            } catch(DaoException e) {
                throw e;
            } catch(InstantiationException | IllegalAccessException e) {
                logger.error("It is impossible to instance by.sazanchuk.finalTask.service class", e);
                throw new DaoException(e);
            }
        }
        return null;
    }

    public void close() {
        factory.close();
    }
}

