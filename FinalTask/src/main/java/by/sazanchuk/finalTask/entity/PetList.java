package by.sazanchuk.finalTask.entity;


public enum PetList {
    CAT ("cat"),
    DOG ("dog"),
    TURTLE ("turtle"),
    PARROT ("parrot"),
    HAMSTER ("hamster");

    //TODO

    private String name;

    PetList(String name) {
        this.name = name;
    }

    public Integer getIdentity() {
        return ordinal();
    }

    public static PetList setPet(String pet){
        PetList r = null;
        if (pet.equalsIgnoreCase("cat")) r = CAT;
        if (pet.equalsIgnoreCase("dog")) r = DOG;
        if (pet.equalsIgnoreCase("turtle")) r = TURTLE;
        if (pet.equalsIgnoreCase("parrot")) r = PARROT;
        if (pet.equalsIgnoreCase("hamster")) r = HAMSTER;
        return r;
    }

    public static PetList getById(Integer id){
        return PetList.values()[id];
    }

    @Override
    public String toString() {
        return name;
    }
}
