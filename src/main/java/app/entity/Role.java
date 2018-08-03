package app.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.ALL},targetEntity = User.class)
    @JoinTable(name = "permissions",joinColumns = {@JoinColumn(name = "role_id",nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "user_id",nullable = false)})
    private List<User> users;

    @Override
    public String getAuthority() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
