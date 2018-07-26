package net.hiskarma.stellarTest.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.config.Projection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userid;
    @JsonIgnore
    private String password;
    private String email;

    protected User() {}

    public User(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }
}

@Projection(name = "password", types = { User.class })
interface PasswordProjection {
    String getPassword();
}
