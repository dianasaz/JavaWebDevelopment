package Runner;

import controller.Read;
import entity.Changer;
import entity.Matrix;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    public static void main(String... args) throws NoSuchFieldException {
        final Logger logger = LogManager.getLogger(Runner.class);
        ReentrantLock lock = new ReentrantLock();

        List<String> f = Read.read("G:\\courses\\JavaWebDevelopment\\MultithreadingTask4\\src\\main\\resources\\file.txt");
        System.out.println(f.toString());

        Matrix matrix = new Matrix(Matrix.makeMatrix(f), lock);

        String name = "4";
        String val = "21";
        String a = "2";
        String js = "45";
        String o = "7";

        Changer ch = new Changer(Integer.valueOf(name), matrix);
        Changer c = new Changer((Integer.valueOf(val)), matrix);
        Changer wq = new Changer(Integer.valueOf(a), matrix);
        Changer ui = new Changer((Integer.valueOf(js)), matrix);
        Changer p = new Changer(Integer.valueOf(o), matrix);

        Thread thread = new Thread(ch);
        Thread thread2 = new Thread(c);
        Thread thread3 = new Thread(wq);
        Thread thread4 = new Thread(ui);
        Thread thread5 = new Thread(p);

        thread.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        System.out.println(matrix.toString());



    }
}
