package colligence.io.persistence.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;
    private LocalDateTime createDateTime;
    private LocalDateTime modifyDateTime;

    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.name = email;
    }
}