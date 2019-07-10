package entity;

public enum Role {
    ADMINISTRATOR("administrator"),
    VISITOR("visitor");

    private String name;

    private Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Role getById(Integer id) {
        return Role.values()[id];
    }

    public static Role setRole(String role){
        Role r = null;
        if (role.equalsIgnoreCase("administrator")) r = ADMINISTRATOR;
        if (role.equalsIgnoreCase("visitor")) r = VISITOR;
        return r;
    }

    public Integer getIdentity() {
        return ordinal();
    }

    @Override
    public String toString() {
        return name;
    }
}