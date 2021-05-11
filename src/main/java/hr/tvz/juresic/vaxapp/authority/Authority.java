package hr.tvz.juresic.vaxapp.authority;

import hr.tvz.juresic.vaxapp.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="Authority")
public class Authority {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    //@ManyToMany(targetEntity = User.class, mappedBy = "user")
    @ManyToMany(mappedBy = "authorities")
    private List<User> userList;
}
