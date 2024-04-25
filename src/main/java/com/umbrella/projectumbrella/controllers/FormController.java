package com.umbrella.projectumbrella.controllers;

import com.umbrella.projectumbrella.dto.*;
import com.umbrella.projectumbrella.entities.*;
import com.umbrella.projectumbrella.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormController {

    private final AttendanceRepository attendanceRepository;
    private final GoogleSpreadsheetRepository googleSpreadsheetRepository;
    private final GroupDiscussionRepository groupDiscussionRepository;
    private final OrigamiRepository origamiRepository;
    private final PersonalDescriptionRepository personalDescriptionRepository;
    private final PersonalityTypeRepository personalityTypeRepository;
    private final PunctualityRepository punctualityRepository;
    private final SearchEngineQuestionareRepository searchEngineQuestionareRepository;
    private final TeachATopicRepository teachATopicRepository;
    private final TeamTaskRepository teamTaskRepository;

    @Autowired
    public FormController(
            AttendanceRepository attendanceRepository,
            GoogleSpreadsheetRepository googleSpreadsheetRepository,
            GroupDiscussionRepository groupDiscussionRepository,
            OrigamiRepository origamiRepository,
            PersonalDescriptionRepository personalDescriptionRepository,
            PersonalityTypeRepository personalityTypeRepository,
            PunctualityRepository punctualityRepository,
            SearchEngineQuestionareRepository searchEngineQuestionareRepository,
            TeachATopicRepository teachATopicRepository,
            TeamTaskRepository teamTaskRepository
    ) {
        this.attendanceRepository = attendanceRepository;
        this.googleSpreadsheetRepository = googleSpreadsheetRepository;
        this.groupDiscussionRepository = groupDiscussionRepository;
        this.origamiRepository = origamiRepository;
        this.personalDescriptionRepository = personalDescriptionRepository;
        this.personalityTypeRepository = personalityTypeRepository;
        this.punctualityRepository = punctualityRepository;
        this.searchEngineQuestionareRepository = searchEngineQuestionareRepository;
        this.teachATopicRepository = teachATopicRepository;
        this.teamTaskRepository = teamTaskRepository;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/submit-attendance")
    public AttendenceDTO handleFormSubmit(@RequestBody AttendenceDTO requestBody) {
        AttendenceEntity entity = new AttendenceEntity();
        entity.setInternName(requestBody.getInternName());
        entity.setRating1(requestBody.getRating1());
        AttendenceEntity savedEntity = attendanceRepository.save(entity);
        return new AttendenceDTO(savedEntity.getId(), savedEntity.getInternName(), savedEntity.getRating1());
    }

    @PostMapping("/submit-google-spreadsheet")
    public GoogleSpreadsheetDTO handleFormSubmit(@RequestBody GoogleSpreadsheetDTO requestBody) {
        GoogleSpreadsheet entity = new GoogleSpreadsheet();
        entity.setInternName(requestBody.getInternName());
        entity.setRating1(requestBody.getRating1());
        entity.setURL(requestBody.getURL());
        entity.setRemark(requestBody.getRemark());

        GoogleSpreadsheet savedEntity = googleSpreadsheetRepository.save(entity);
        return new GoogleSpreadsheetDTO(savedEntity.getId(), savedEntity.getInternName(), savedEntity.getRating1(), savedEntity.getURL(), savedEntity.getRemark());
    }

    @PostMapping("/submit-group-discussion")
    public GroupDiscussionDTO handleGroupDiscussionSubmit(@RequestBody GroupDiscussionDTO requestBody) {
        GroupDiscussion entity = new GroupDiscussion();
        entity.setInternName(requestBody.getInternName());
        entity.setRating1(requestBody.getRating1());
        entity.setRating2(requestBody.getRating2());
        entity.setRating3(requestBody.getRating3());
        entity.setURL(requestBody.getURL());
        entity.setRemark(requestBody.getRemark());

        GroupDiscussion savedEntity = groupDiscussionRepository.save(entity);
        return new GroupDiscussionDTO(
                savedEntity.getId(),
                savedEntity.getInternName(),
                savedEntity.getRating1(),
                savedEntity.getRating2(),
                savedEntity.getRating3(),
                savedEntity.getURL(),
                savedEntity.getRemark()
        );
    }

    @PostMapping("/submit-origami")
    public OrigamiDTO handleOrigamiSubmit(@RequestBody OrigamiDTO requestBody) {
        Origami entity = new Origami();
        entity.setInternName(requestBody.getInternName());
        entity.setRating1(requestBody.getRating1());

        Origami savedEntity = origamiRepository.save(entity);
        return new OrigamiDTO(
                savedEntity.getId(),
                savedEntity.getInternName(),
                savedEntity.getRating1()
        );
    }

    @PostMapping("/submit-personal-description")
    public PersonalDescriptionDTO handlePersonalDescriptionSubmit(@RequestBody PersonalDescriptionDTO requestBody) {
        PersonalDescription entity = new PersonalDescription();
        entity.setInternName(requestBody.getInternName());
        entity.setPersonalDescription(requestBody.getPersonalDescription());

        PersonalDescription savedEntity = personalDescriptionRepository.save(entity);
        return new PersonalDescriptionDTO(
                savedEntity.getId(),
                savedEntity.getInternName(),
                savedEntity.getPersonalDescription()
        );
    }

    @PostMapping("/submit-personality-type")
    public PersonalityTypeDTO handlePersonalityTypeSubmit(@RequestBody PersonalityTypeDTO requestBody) {
        PersonalityType entity = new PersonalityType();
        entity.setInternName(requestBody.getInternName());
        entity.setPersonalityType(requestBody.getPersonalityType());

        PersonalityType savedEntity = personalityTypeRepository.save(entity);
        return new PersonalityTypeDTO(
                savedEntity.getId(),
                savedEntity.getInternName(),
                savedEntity.getPersonalityType()
        );
    }


    @PostMapping("/submit-punctuality")
    public PunctualityDTO handlePunctualitySubmit(@RequestBody PunctualityDTO requestBody) {
        Punctuality entity = new Punctuality();
        entity.setInternName(requestBody.getInternName());
        entity.setRating1(requestBody.getRating1());

        Punctuality savedEntity = punctualityRepository.save(entity);
        return new PunctualityDTO(
                savedEntity.getId(),
                savedEntity.getInternName(),
                savedEntity.getRating1()
        );
    }


    @PostMapping("/submit-search-engine-questionnaire")
    public SearchEngineQuestionareDTO handleSearchEngineQuestionnaireSubmit(@RequestBody SearchEngineQuestionareDTO requestBody) {
        SearchEngineQuestionare entity = new SearchEngineQuestionare();
        entity.setInternName(requestBody.getInternName());
        entity.setRating1(requestBody.getRating1());
        entity.setRating2(requestBody.getRating2());
        entity.setRating3(requestBody.getRating3());
        entity.setURL(requestBody.getURL());
        entity.setRemark(requestBody.getRemark());

        SearchEngineQuestionare savedEntity = searchEngineQuestionareRepository.save(entity);
        return new SearchEngineQuestionareDTO(
                savedEntity.getId(),
                savedEntity.getInternName(),
                savedEntity.getRating1(),
                savedEntity.getRating2(),
                savedEntity.getRating3(),
                savedEntity.getURL(),
                savedEntity.getRemark()
        );
    }

    @PostMapping("/submit-teach-a-topic")
    public TeachATopicDTO handleTeachATopicSubmit(@RequestBody TeachATopicDTO requestBody) {
        TeachATopic entity = new TeachATopic();
        entity.setInternName(requestBody.getInternName());
        entity.setRating1(requestBody.getRating1());
        entity.setRating2(requestBody.getRating2());
        entity.setRating3(requestBody.getRating3());
        entity.setRating4(requestBody.getRating4());
        entity.setURL(requestBody.getURL());
        entity.setRemark(requestBody.getRemark());

        TeachATopic savedEntity = teachATopicRepository.save(entity);
        return new TeachATopicDTO(
                savedEntity.getId(),
                savedEntity.getInternName(),
                savedEntity.getRating1(),
                savedEntity.getRating2(),
                savedEntity.getRating3(),
                savedEntity.getRating4(),
                savedEntity.getURL(),
                savedEntity.getRemark()
        );
    }

    @PostMapping("/submit-team-task")
    public TeamTaskDTO handleTeamTaskSubmit(@RequestBody TeamTaskDTO requestBody) {
        TeamTask entity = new TeamTask();
        entity.setInternName(requestBody.getInternName());
        entity.setRating1(requestBody.getRating1());
        entity.setRating2(requestBody.getRating2());
        entity.setRating3(requestBody.getRating3());
        entity.setRating4(requestBody.getRating4());
        entity.setURL(requestBody.getURL());
        entity.setRemark(requestBody.getRemark());

        TeamTask savedEntity = teamTaskRepository.save(entity);
        return new TeamTaskDTO(
                savedEntity.getId(),
                savedEntity.getInternName(),
                savedEntity.getRating1(),
                savedEntity.getRating2(),
                savedEntity.getRating3(),
                savedEntity.getRating4(),
                savedEntity.getURL(),
                savedEntity.getRemark()
        );
    }


}
