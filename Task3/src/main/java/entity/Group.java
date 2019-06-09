package entity;

import java.util.Random;

public enum Group {
    ANTIBIOTICS,
    VITAMINS,
    PAINKILLERS,
    ANTIALLERGIAN;

    public static Group setGroup(String group){
        Group result;
        switch (group){
            case "ANTIBIOTICS":{
                result = ANTIBIOTICS;
                break;
            }
            case "VITAMINS":{
                result = VITAMINS;
                break;
            }
            case "ANTIALLERGIAN":{
                result = ANTIALLERGIAN;
                break;
            }
            case "PAINKILLERS":{
                result = PAINKILLERS;
                break;
            }
            default: result = PAINKILLERS;
        }
        return result;
    }

}
