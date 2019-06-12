package parser;

import entity.Medicine;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Builder {
    protected List<Medicine> medicines = new ArrayList<>();

    public List<Medicine> getMedicines(){
        return medicines;
    }

    public abstract void buildList();
}
