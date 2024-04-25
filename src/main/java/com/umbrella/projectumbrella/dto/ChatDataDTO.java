package com.umbrella.projectumbrella.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatDataDTO {
    private double responsibility = 0.0;
    private double abilityToLearn = 0.0;
    private double researchSkills = 0.0;
    private double qualityOfWork = 0.0;
    private double communicationSkills = 0.0;
    private double planningAndProblemSolving = 0.0;
}
