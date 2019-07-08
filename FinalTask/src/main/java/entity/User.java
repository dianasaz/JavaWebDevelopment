package entity;

import java.util.List;

public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private String email;
    private Gender gender;
    private int phoneNumber;
    private String address;
    private List<Integer> petList;
    private Role role;

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPetList(List<Integer> petList) {
        this.petList = petList;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Gender getGender() {
        return gender;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public List<Integer> getPetList() {
        return petList;
    }
}
