package com.umbrella.projectumbrella.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Task6")
public class TeamTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "InternName")
    private String internName;

    @Column(name = "Rating1")
    private float rating1;

    @Column(name = "Rating2")
    private float rating2;

    @Column(name = "Rating3")
    private float rating3;

    @Column(name = "Rating4")
    private float rating4;

    @Column(name = "URL", length = 255)
    private String URL;

    @Column(name = "Remark", length = 500)
    private String remark;

}
