package com.dervla.kpopapi.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "number_of_groups", nullable = false)
    private long numberOfGroups;

    @Column(name = "number_of_members", nullable = false)
    private long numberOfMembers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getNumberOfGroups() {
        return numberOfGroups;
    }

    public void setNumberOfGroups(long numberOfGroups) {
        this.numberOfGroups = numberOfGroups;
    }

    public long getNumberOfMembers() {
        return numberOfMembers;
    }

    public void setNumberOfMembers(long numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }
}
