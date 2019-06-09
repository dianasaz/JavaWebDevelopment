package entity;

import java.awt.*;

public enum VersionType {
    TABLETS,
    POWDER,
    CAPSULE,
    DRAGEE;

    public static VersionType setVersion(String group){
        VersionType result;
        switch (group){
            case "TABLETS":{
                result = TABLETS;
                break;
            }
            case "POWDER":{
                result = POWDER;
                break;
            }
            case "DRAGEE":{
                result = DRAGEE;
                break;
            }
            case "CAPSULE":{
                result = CAPSULE;
                break;
            }
            default: result = POWDER;
        }
        return result;
    }

}
