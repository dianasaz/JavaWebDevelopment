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

    public static PetList getById(Integer id){
        return PetList.values()[id];
    }
}
