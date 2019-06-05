package entity;

import exceptions.InvalidDataException;

public enum Job {
    DEVELOPER,
    TESTER,
    MANAGER;

    public static Job setJob(String string) throws InvalidDataException{
        Job job = null;
        switch (string){
            case "DEVELOPER":{
                job = DEVELOPER;
                break;
            }
            case "TESTER":{
                job = TESTER;
                break;
            }
            case "MANAGER":{
                job = MANAGER;
                break;
            }
            default:{
                throw new InvalidDataException("invalid information");
            }
        }
        return job;
    }
/*
    public static boolean contain(Job job){

    }*/
}
