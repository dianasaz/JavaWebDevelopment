package entity;

public enum ServiceList {
    VACCINATION,
    CONSULTATION,
    STERILIZATION,
    CASTRATION,
    DENTISTRY,
    MICROCHIPPING,
    WELLNESS_EXAM,
    ANALYSIS;


    public Integer getIdentity() {
        return ordinal();
    }

    public static ServiceList getById(Integer id){
        return ServiceList.values()[id];
    }
}
