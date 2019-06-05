package service.parser;

import entity.Employee;
import entity.Job;
import entity.JobFactory;
import entity.JobFunction;
import entity.MainSkill;
import exceptions.InvalidDataException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.validation.Validation;
import service.validation.ValidationEmployee;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TextParser implements Parser<Employee> {
    private final Logger logger = LogManager.getLogger(TextParser.class);
    private Validation validation = new ValidationEmployee();

    @Override
    public List<String> read(String string) throws NoSuchFieldException {
        List<String> lines = new ArrayList<>();
        try{
            lines = Files.readAllLines(Paths.get(string), Charset.forName("UTF-8"));
            logger.log(Level.INFO, "file read");
        }
        catch (IOException e){
            throw new NoSuchFieldException("File doesn't exist");
        }
        if (lines == null){
            logger.log(Level.INFO, "NULL IN FILE");
        }
        return lines;
    }

    public List<Employee> load(List<String> strings) throws InvalidDataException, NullPointerException {
            if (strings == null) {
                logger.log(Level.INFO, "file = null");
            }
            List<Employee> employees = new ArrayList<>();
            try {
                if (validation.isValid(strings)) {
                    for (int i = 0; i < strings.size(); i++) {
                        Employee employee = createEmployeeFromString(strings.get(i));
                        logger.log(Level.INFO, "employee" + employee);
                        employees.add(employee);
                    }
                    return employees;
                }
                else throw new InvalidDataException("file is not valid, check your information");
            } catch (InvalidDataException e) {
                logger.log(Level.ERROR, "Error in file");
                throw new InvalidDataException("error in file");
            }
    }

    public static Employee createEmployeeFromString(String string) throws InvalidDataException {
        Logger logger =  LogManager.getLogger(TextParser.class);
        Employee result = null;
        String[] parsed = parseEmployee(string);
        result = validateFromString(parsed);
        logger.log(Level.INFO, "result"+result);
        return result;
    }

    public static String[] parseEmployee(String string) {
        String[] res = string.split(", ");
        return res;
    }

    public static Employee validateFromString(String[] strings) throws InvalidDataException {
        Logger logger = LogManager.getLogger(TextParser.class);
        Employee result;
        Job job = Job.setJob(strings[3]);
        String name =strings[1];
        JobFunction jobFunction = JobFunction.setJobFunction(strings[2]);
        MainSkill mainSkill = MainSkill.setMainSkill(strings[0]);
        result = JobFactory.getType(job, name, jobFunction, mainSkill);
        logger.log(Level.INFO, "result" + result);
        return result;
    }




}
