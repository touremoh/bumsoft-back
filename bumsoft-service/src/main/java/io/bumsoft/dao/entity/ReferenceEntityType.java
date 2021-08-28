package io.bumsoft.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "REFERENCE_ENTITY_TYPE", schema = "bumsoftdb")
public class ReferenceEntityType implements BumsoftEntity {
    @Id
    @Column(name = "REF_TYP_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reference_entity_sequence")
    @SequenceGenerator(name = "reference_entity_sequence", sequenceName = "REFERENCE_ENTITY_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "REF_TYP_GROUP")
    private String group;

    @Column(name = "REF_TYP_NAME")
    private String name;

    @Column(name = "REF_TYP_DESC")
    private String description;
}
