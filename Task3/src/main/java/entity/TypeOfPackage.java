package entity;

public enum TypeOfPackage {
    HARD,
    SEMIHARD,
    SOFT;

    public static TypeOfPackage setTypeOfPackage(String type){
        TypeOfPackage result = null;
        switch (type){
            case "HARD":{
                result = HARD;
                break;
            }
            case "SEMIHARD":{
                result = SEMIHARD;
                break;
            }
            case "SOFT":{
                result = SOFT;
                break;
            }
        }
        return result;
    }

}
