package com.dervla.kpopapi.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name = "COMPANY")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDate creationDate;

    @Column(name = "NUMBER_OF_GROUPS", nullable = false)
    private long numberOfGroups;

    @Column(name = "NUMBER_OF_MEMBERS", nullable = false)
    private long numberOfMembers;
}
