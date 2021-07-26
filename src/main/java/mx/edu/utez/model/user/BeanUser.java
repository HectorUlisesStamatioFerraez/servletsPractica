package mx.edu.utez.model.user;

import mx.edu.utez.model.person.BeanPerson;
import mx.edu.utez.model.role.BeanRole;

public class BeanUser {
    private long id;
    private String email;
    private String password;
    private int status;
    private BeanPerson idPerson;
    private BeanRole idRole;

    public BeanUser() {
    }

    public BeanUser(long id, String email, String password, int status, BeanPerson idPerson, BeanRole idRole) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.status = status;
        this.idPerson = idPerson;
        this.idRole = idRole;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BeanPerson getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(BeanPerson idPerson) {
        this.idPerson = idPerson;
    }

    public BeanRole getIdRole() {
        return idRole;
    }

    public void setIdRole(BeanRole idRole) {
        this.idRole = idRole;
    }
}
