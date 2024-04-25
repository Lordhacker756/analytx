package com.umbrella.projectumbrella.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CombinedDetailsDTO {

    private AttendenceDTO attendance;
    private GoogleSpreadsheetDTO googleSpreadsheet;
    private GroupDiscussionDTO groupDiscussion;
    private OrigamiDTO origami;
    private PersonalDescriptionDTO personalDescription;
    private PersonalityTypeDTO personalityType;
    private PunctualityDTO punctuality;
    private SearchEngineQuestionareDTO searchEngineQuestionnaire;
    private TeachATopicDTO teachATopic;
    private TeamTaskDTO teamTask;
    private ChatDataDTO chartData;
    private DocsDataDTO docsData;
    private TableDataDTO tableData;

    // Constructors, getters, setters if needed

}
