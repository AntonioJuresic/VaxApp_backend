package hr.tvz.juresic.vaxapp.user;

import hr.tvz.juresic.vaxapp.authority.Authority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="user")
public class User implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue
    private Long id;

    @Column(name="username")
    String username;

    @Column(name="password")
    String password;

    @Column(name="first_name")
    String firstName;

    @Column(name="last_name")
    String lastName;

    //@ManyToMany(targetEntity = Authority.class, mappedBy = "authority")
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_authority",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "authority_id") }
    )
    private List<Authority> authorities;
}

