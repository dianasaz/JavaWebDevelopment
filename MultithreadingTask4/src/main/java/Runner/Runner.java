package Runner;

import controller.Read;
import entity.Changer;
import entity.Element;
import entity.Matrix;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Runner {

    public static void main(String... args) throws NoSuchFieldException {
        final Logger logger = LogManager.getLogger(Runner.class);

        Element[][] elements;
        Matrix matrix;
        Read read = new Read();
        elements = read.read("G:\\courses\\JavaWebDevelopment\\MultithreadingTask4\\src\\main\\resources\\file.txt");
        matrix = new Matrix(elements);

        Runnable changer = new Changer(4, matrix);
        Runnable changer2 = new Changer(21, matrix);
        Runnable changer3 = new Changer(2, matrix);
        Runnable changer4 = new Changer(45, matrix);
        Runnable changer5 = new Changer(7, matrix);

        Thread thread = new Thread(changer);
        Thread thread2 = new Thread(changer2);
        Thread thread3 = new Thread(changer3);
        Thread thread4 = new Thread(changer4);
        Thread thread5 = new Thread(changer5);

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
