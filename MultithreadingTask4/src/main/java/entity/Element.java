package entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.ReentrantLock;

public class Element{
    private final Logger logger = LogManager.getLogger(Element.class);
    private int el;
    private ReentrantLock lock;

    public Element(int element){
        this.el = element;
        this.lock = new ReentrantLock();
    }

    public void setEl(int element) {
        lock.lock();
        try {
            this.el = element;
        } finally {
            lock.unlock();
        }
    }

    public int getEl() {
        return el;
    }

    @Override
    public String toString() {
        return String.valueOf(el);
    }
}
