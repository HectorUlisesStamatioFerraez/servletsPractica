package mx.edu.utez.model.role;

public class BeanRole {
    private int id;
    private String description;

    public BeanRole() {
    }

    public BeanRole(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
