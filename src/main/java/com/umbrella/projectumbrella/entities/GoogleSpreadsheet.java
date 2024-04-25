package com.umbrella.projectumbrella.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Task4")
public class GoogleSpreadsheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "InternName")
    private String internName;

    @Column(name = "Rating1")
    private float rating1;

    @Column(name = "URL", length = 255)
    private String URL;

    @Column(name = "Remark", length = 500)
    private String remark;

}
