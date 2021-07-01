package io.bumsoft.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ROLE")
public class Role implements BumsoftEntity {
    @Id
    @Column(name = "ROLE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_sequence")
    @SequenceGenerator(name = "role_sequence", sequenceName = "ROLE_SEQ")
    private Long id;

    @Column(name = "ROLE_NAME")
    private String name;

    @Column(name = "ROLE_DESC")
    private String description;

    // List of operation a user belonging to this group can perform
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "ROLE_OPERATION",
        joinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID"),
        inverseJoinColumns = @JoinColumn(name = "OP_ID", referencedColumnName = "OP_ID")
    )
    private List<Operation> operations;
}
