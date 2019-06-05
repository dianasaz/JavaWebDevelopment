package entity;

import exceptions.InvalidDataException;

import java.util.Optional;

public enum JobFunction {
    JUNIOR,
    MIDDLE,
    SENIOR;

    private int salary;

    public int getSalary(JobFunction jobFunc) {
        switch (jobFunc) {
            case JUNIOR: {
                salary = 1000;
                break;
            }
            case MIDDLE: {
                salary = 2000;
                break;
            }
            case SENIOR: {
                salary = 3000;
                break;
            }
        }
        return salary;
    }

    public static JobFunction setJobFunction(String string) throws InvalidDataException {
        JobFunction jobFunction = null;
        switch (string) {
            case "JUNIOR": {
                jobFunction = JUNIOR;
                break;
            }
            case "MIDDLE": {
                jobFunction = MIDDLE;
                break;
            }
            case "SENIOR": {
                jobFunction = SENIOR;
                break;
            }
            default: {
                throw new InvalidDataException("invalid information");
            }
        }
        return jobFunction;
    }
}
