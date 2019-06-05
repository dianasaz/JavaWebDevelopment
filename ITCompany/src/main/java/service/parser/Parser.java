package service.parser;

import exceptions.InvalidDataException;

import java.util.ArrayList;
import java.util.List;

public interface Parser<T> {
    List<String> read(String string) throws NoSuchFieldException;
    List<T> load(List<String> strings) throws InvalidDataException;
}
