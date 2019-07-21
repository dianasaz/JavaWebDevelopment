package by.sazanchuk.finalTask.entity;

public enum Gender {
    MEN("men"),
    WOMEN("women");

    private String name;

    private Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Role getById(Integer id) {
        return Role.values()[id];
    }

    public static Gender setGender(String gender){
        Gender g = null;
        if (gender.equalsIgnoreCase("men")) g = MEN;
        if (gender.equalsIgnoreCase("women")) g = WOMEN;
        return g;
    }

    public Integer getIdentity() {
        return ordinal();
    }

}
