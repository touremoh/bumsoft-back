package io.bumsoft.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BUMSOFT_USER")
public class BumsoftUser implements BumsoftEntity {

    @Id
    @Column(name = "BS_USR_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "USER_SEQ")
    private Long id;

    @Column(name = "BS_USR_FIRST_NAME")
    private String firstName;

    @Column(name = "BS_USR_LAST_NAME")
    private String lastName;

    @Column(name = "BS_USR_EMAIL")
    private String email;

    @Column(name = "BS_USR_PASSWORD")
    private String password;

    @Column(name = "BS_USR_ENABLED")
    private Boolean enabled;


    @Column(name = "BS_USR_CREATED_AT")
    private LocalDate createdAt;

    @Column(name = "BS_USR_UPDATED_AT")
    private LocalDate updatedAt;

    @Column(name = "BS_USR_DELETED_AT")
    private LocalDate deletedAt;

    // List of all accounts belonging to the user
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Account> accounts;

    // The group the user belongs to. Defines what he can do what he can't
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
       name = "USER_ROLE",
       joinColumns = @JoinColumn(name = "BS_USR_ID", referencedColumnName = "BS_USR_ID"),
       inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
    )
    private List<Role> roles;
}
