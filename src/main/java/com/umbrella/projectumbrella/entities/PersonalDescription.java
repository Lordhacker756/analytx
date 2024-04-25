package com.umbrella.projectumbrella.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Task10")
public class PersonalDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "InternName")
    private String internName;

    @Column(name = "PersonalDescription",  length = 500)
    private String personalDescription;

}
