package mx.edu.utez.model.person;

public class BeanPerson {
    private long id;
    private String name;
    private String lastname;
    private int edad;

    public BeanPerson() {
    }

    public BeanPerson(long id, String name, String lastname, int edad) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.edad = edad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
