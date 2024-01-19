/* com.josejavier.model;

import jakarta.persistence.*;

@Entity
@Table(name = "classroom")
public class Classroom {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;

    @Column(name = "description", length = 256, nullable = false)
    private String description;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "Category")
    private String category;

    @Column(name = "location")
    private String location;

    @Column(name = "direction")
    private String direction;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "province")
    private String province;

    @ManyToOne
    @JoinColumn(name = "id_teacher", nullable = false)
    private Teacher teacher;

    public Classroom() {
        this(-1, "", "", "", "", "", "", "", new Teacher());
    }

    public Classroom(int id, String description, String type, String category, String location,
                     String direction, String postalCode, String province, Teacher teacher) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.category = category;
        this.location = location;
        this.direction = direction;
        this.postalCode = postalCode;
        this.province = province;
        this.teacher = teacher;
    }

    // Getters and setters

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
*/