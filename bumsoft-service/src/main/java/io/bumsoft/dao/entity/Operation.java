package io.bumsoft.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "OPERATION")
public class Operation implements BumsoftEntity {
    @Id
    @Column(name = "OP_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operation_sequence")
    @SequenceGenerator(name = "operation_sequence", sequenceName = "OPERATION_SEQ")
    private Long id;

    @Column(name = "OP_NAME")
    private String name;

    @Column(name = "OP_DESC")
    private String description;

    // List of roles that can perform a particular operation
    @ManyToMany(mappedBy = "operations", fetch = FetchType.LAZY)
    private List<Role> roles;
}