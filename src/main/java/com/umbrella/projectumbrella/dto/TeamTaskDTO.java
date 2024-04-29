package com.umbrella.projectumbrella.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamTaskDTO {

    private int id;
    private String internName;
    private float rating1;
    private float rating2;
    private float rating3;
    private float rating4;

    @JsonProperty("URL")
    private String URL;
    private String remark;

}
