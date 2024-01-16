import com.josejavier.model.Classroom;
import jakarta.persistence.*;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "petition")
public class Petition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "message", length = 256, nullable = false)
    private String message;

    @Column(name = "state", nullable = false)
    private String state;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_classroom", nullable = false)
    private Classroom classroom;

    // Constructor, getters y setters

    public Petition() {
        this.id = -1;
        this.message = "";
        this.state = "";
        this.date = new Date();
        this.user = new User();
        this.classroom = new Classroom();
    }

    public Petition(int id, String message, String state, Date date, User user, Classroom classroom) {
        this.id = id;
        this.message = message;
        this.state = state;
        this.date = date;
        this.user = user;
        this.classroom = classroom;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
