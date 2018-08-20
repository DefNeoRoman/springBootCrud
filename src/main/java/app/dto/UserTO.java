package app.dto;

import java.sql.Timestamp;
import java.util.Set;

public class UserTO {

    private Long id;


    private int age=123;


    private String name="user";


    private String email="user@email.com";


    private Timestamp createdDate = new Timestamp(System.currentTimeMillis());


    private String password = "user";


    private Boolean enabled = true;

    private Set<Long> rolesID;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Long> getRolesID() {
        return rolesID;
    }

    public void setRolesID(Set<Long> rolesID) {
        this.rolesID = rolesID;
    }

    public Long getId() {
        return id;
    }
}
