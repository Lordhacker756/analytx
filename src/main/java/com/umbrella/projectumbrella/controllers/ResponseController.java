package com.umbrella.projectumbrella.controllers;

import com.umbrella.projectumbrella.dto.*;
import com.umbrella.projectumbrella.entities.*;
import com.umbrella.projectumbrella.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ResponseController {

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
    public ResponseController(
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

    @GetMapping("/get-attendence/{internName}")
    public AttendenceDTO getAttendenceDetails(@PathVariable String internName) {
        AttendenceEntity res = attendanceRepository.findAttendenceByInternName(internName);
        if(res == null)
            return null;
        else
            return new AttendenceDTO(res.getId(), res.getInternName(), res.getRating1());
    }

    @GetMapping("/get-google-spreadsheet/{internName}")
    public GoogleSpreadsheetDTO getGoogleSpreadsheetDetails(@PathVariable String internName) {
        GoogleSpreadsheet res = googleSpreadsheetRepository.findGoogleSpreadsheetByInternName(internName);

        if(res == null)
            return null;
        else
            return new GoogleSpreadsheetDTO(res.getId(), res.getInternName(), res.getRating1(), res.getURL(), res.getRemark());
    }

    // Corresponding response method for submit-group-discussion
    @GetMapping("/get-group-discussion/{internName}")
    public GroupDiscussionDTO getGroupDiscussionDetails(@PathVariable String internName) {
        GroupDiscussion res = groupDiscussionRepository.findGroupDiscussionByInternName(internName);
        return (res == null) ? null : new GroupDiscussionDTO(res.getId(), res.getInternName(), res.getRating1(), res.getRating2(), res.getRating3(), res.getURL(), res.getRemark());
    }

    // Corresponding response method for submit-origami
    @GetMapping("/get-origami/{internName}")
    public OrigamiDTO getOrigamiDetails(@PathVariable String internName) {
        Origami res = origamiRepository.findOrigamiByInternName(internName);
        return (res == null) ? null : new OrigamiDTO(res.getId(), res.getInternName(), res.getRating1());
    }

    // Corresponding response method for submit-personal-description
    @GetMapping("/get-personal-description/{internName}")
    public PersonalDescriptionDTO getPersonalDescriptionDetails(@PathVariable String internName) {
        PersonalDescription res = personalDescriptionRepository.findPersonalDescriptionByInternName(internName);
        return (res == null) ? null : new PersonalDescriptionDTO(res.getId(), res.getInternName(), res.getPersonalDescription());
    }

    // Corresponding response method for submit-personality-type
    @GetMapping("/get-personality-type/{internName}")
    public PersonalityTypeDTO getPersonalityTypeDetails(@PathVariable String internName) {
        PersonalityType res = personalityTypeRepository.findPersonalityTypeByInternName(internName);
        return (res == null) ? null : new PersonalityTypeDTO(res.getId(), res.getInternName(), res.getPersonalityType());
    }

    // Corresponding response method for submit-punctuality
    @GetMapping("/get-punctuality/{internName}")
    public PunctualityDTO getPunctualityDetails(@PathVariable String internName) {
        Punctuality res = punctualityRepository.findPunctualityByInternName(internName);
        return (res == null) ? null : new PunctualityDTO(res.getId(), res.getInternName(), res.getRating1());
    }

    // Corresponding response method for submit-search-engine-questionnaire
    @GetMapping("/get-search-engine-questionnaire/{internName}")
    public SearchEngineQuestionareDTO getSearchEngineQuestionnaireDetails(@PathVariable String internName) {
        SearchEngineQuestionare res = searchEngineQuestionareRepository.findSearchEngineQuestionnaireByInternName(internName);
        return (res == null) ? null : new SearchEngineQuestionareDTO(res.getId(), res.getInternName(), res.getRating1(), res.getRating2(), res.getRating3(), res.getURL(), res.getRemark());
    }

    // Corresponding response method for submit-teach-a-topic
    @GetMapping("/get-teach-a-topic/{internName}")
    public TeachATopicDTO getTeachATopicDetails(@PathVariable String internName) {
        TeachATopic res = teachATopicRepository.findTeachATopicByInternName(internName);
        return (res == null) ? null : new TeachATopicDTO(res.getId(), res.getInternName(), res.getRating1(), res.getRating2(), res.getRating3(), res.getRating4(), res.getURL(), res.getRemark());
    }

    // Corresponding response method for submit-team-task
    @GetMapping("/get-team-task/{internName}")
    public TeamTaskDTO getTeamTaskDetails(@PathVariable String internName) {
        TeamTask res = teamTaskRepository.findTeamTaskByInternName(internName);
        return (res == null) ? null : new TeamTaskDTO(res.getId(), res.getInternName(), res.getRating1(), res.getRating2(), res.getRating3(), res.getRating4(), res.getURL(), res.getRemark());
    }


    public ChatDataDTO getChartData(AttendenceDTO attendence, GoogleSpreadsheetDTO googleSpreadsheet, GroupDiscussionDTO groupDiscussion, OrigamiDTO origami,  PersonalDescriptionDTO personalDescription, PersonalityTypeDTO personalityType, PunctualityDTO punctuality, SearchEngineQuestionareDTO searchEngineQuestionare, TeachATopicDTO teachATopic, TeamTaskDTO teamTask) {
        List<Float> responsibilityList = new ArrayList<>();
        List<Float> abilityToLearnList = new ArrayList<>();
        List<Float> researchSkillsList = new ArrayList<>();
        List<Float> qualityOfWorkList = new ArrayList<>();
        List<Float> communicationSkillsList = new ArrayList<>();
        List<Float> planningAndProblemSolvingList = new ArrayList<>();

        ChatDataDTO chatDataDTO = new ChatDataDTO();

        // Adding Rating to their arrays
        responsibilityList.add(searchEngineQuestionare.getRating1());
        responsibilityList.add(teachATopic.getRating1());

        abilityToLearnList.add(googleSpreadsheet.getRating1());
        abilityToLearnList.add(origami.getRating1());

        researchSkillsList.add(searchEngineQuestionare.getRating2());
        researchSkillsList.add(teachATopic.getRating2());
        researchSkillsList.add(groupDiscussion.getRating1());
        researchSkillsList.add(teamTask.getRating1());

        qualityOfWorkList.add(searchEngineQuestionare.getRating3());
        qualityOfWorkList.add(teachATopic.getRating3());
        qualityOfWorkList.add(groupDiscussion.getRating2());
        qualityOfWorkList.add(teamTask.getRating2());

        communicationSkillsList.add(teachATopic.getRating4());
        communicationSkillsList.add(groupDiscussion.getRating3());
        communicationSkillsList.add(teamTask.getRating3());

        planningAndProblemSolvingList.add(teamTask.getRating4());
        planningAndProblemSolvingList.add(punctuality.getRating1());
        planningAndProblemSolvingList.add(attendence.getRating1());

        // Calculate average for each list and set them to the ChatDataDTO
        chatDataDTO.setResponsibility(calculateAverage(responsibilityList));
        chatDataDTO.setAbilityToLearn(calculateAverage(abilityToLearnList));
        chatDataDTO.setResearchSkills(calculateAverage(researchSkillsList));
        chatDataDTO.setQualityOfWork(calculateAverage(qualityOfWorkList));
        chatDataDTO.setCommunicationSkills(calculateAverage(communicationSkillsList));
        chatDataDTO.setPlanningAndProblemSolving(calculateAverage(planningAndProblemSolvingList));

        return chatDataDTO;
    }

    // Method to calculate average of a list of Floats
    private double calculateAverage(List<Float> list) {
        if (list.isEmpty()) {
            return 0.0; // Return 0 if list is empty to avoid division by zero
        }
        double sum = 0;
        for (Float value : list) {
            sum += value;
        }
        return sum / list.size();
    }

    @GetMapping("/get-all-details/{internName}")
    public CombinedDetailsDTO getAllDetails(@PathVariable String internName) {
        AttendenceDTO attendance = getAttendenceDetails(internName);
        GoogleSpreadsheetDTO googleSpreadsheet = getGoogleSpreadsheetDetails(internName);
        GroupDiscussionDTO groupDiscussion = getGroupDiscussionDetails(internName);
        OrigamiDTO origami = getOrigamiDetails(internName);
        PersonalDescriptionDTO personalDescription = getPersonalDescriptionDetails(internName);
        PersonalityTypeDTO personalityType = getPersonalityTypeDetails(internName);
        PunctualityDTO punctuality = getPunctualityDetails(internName);
        SearchEngineQuestionareDTO searchEngineQuestionnaire = getSearchEngineQuestionnaireDetails(internName);
        TeachATopicDTO teachATopic = getTeachATopicDetails(internName);
        TeamTaskDTO teamTask = getTeamTaskDetails(internName);
        ChatDataDTO chatDataDTO = getChartData(attendance, googleSpreadsheet, groupDiscussion, origami, personalDescription, personalityType, punctuality, searchEngineQuestionnaire, teachATopic, teamTask);
        DocsDataDTO docsDataDTO = getDocsData(searchEngineQuestionnaire, teachATopic, groupDiscussion, googleSpreadsheet, teamTask, personalDescription, personalityType);

        TableDataDTO tableDataDTO = getTableData(attendance, googleSpreadsheet, groupDiscussion, origami, punctuality, searchEngineQuestionnaire, teachATopic, teamTask, chatDataDTO);



        return new CombinedDetailsDTO(
                attendance,
                googleSpreadsheet,
                groupDiscussion,
                origami,
                personalDescription,
                personalityType,
                punctuality,
                searchEngineQuestionnaire,
                teachATopic,
                teamTask,
                chatDataDTO,
                docsDataDTO,
                tableDataDTO
        );
    }

    private TableDataDTO getTableData(AttendenceDTO attendance, GoogleSpreadsheetDTO googleSpreadsheet, GroupDiscussionDTO groupDiscussion, OrigamiDTO origami, PunctualityDTO punctuality, SearchEngineQuestionareDTO searchEngineQuestionnaire, TeachATopicDTO teachATopic, TeamTaskDTO teamTask, ChatDataDTO chatDataDTO) {
        TableDataDTO tableDataDTO = new TableDataDTO();

        Map<String, Float> searchEngineQuestionnaireDetails = new HashMap<>(){{
            put("responsibility", searchEngineQuestionnaire.getRating1());
            put("researchSkills", searchEngineQuestionnaire.getRating2());
            put("qualityOfWork", searchEngineQuestionnaire.getRating3());
        }};
        tableDataDTO.setSearchEngineQuestionnare(searchEngineQuestionnaireDetails);

        Map<String, Float> teachATopicDetails = new HashMap<>(){{
            put("responsibility", teachATopic.getRating1());
            put("researchSkills", teachATopic.getRating2());
            put("qualityOfWork", teachATopic.getRating3());
            put("communicationSkills", teachATopic.getRating4());

        }};
        tableDataDTO.setTeachATopic(teachATopicDetails);

        Map<String, Float> groupDiscussionDetails = new HashMap<>(){{
            put("researchSkills", groupDiscussion.getRating1());
            put("qualityOfWork", groupDiscussion.getRating2());
            put("communicationSkills", groupDiscussion.getRating3());
        }};
        tableDataDTO.setGroupDiscussion(groupDiscussionDetails);

        Map<String, Float> googleSpreadsheetDetails = new HashMap<>(){{
            put("abilityToLearn", googleSpreadsheet.getRating1());
        }};
        tableDataDTO.setGoogleSpreadsheet(googleSpreadsheetDetails);

        Map<String, Float> OrigamiDetails = new HashMap<>(){{
            put("abilityToLearn", origami.getRating1());
        }};
        tableDataDTO.setOrigami(OrigamiDetails);

        Map<String, Float> teamTaskDetails = new HashMap<>(){{
            put("researchSkills", teamTask.getRating1());
            put("qualityOfWork", teamTask.getRating2());
            put("communicationSkills", teamTask.getRating3());
            put("planningAndProblemSolving", teamTask.getRating3());
        }};
        tableDataDTO.setTeamTask(teamTaskDetails);

        Map<String, Float> punctualityDetails = new HashMap<>(){{
            put("planningAndProblemSolving", punctuality.getRating1());
        }};
        tableDataDTO.setPunctuality(punctualityDetails);

        Map<String, Float> attendanceDetails = new HashMap<>(){{
            put("planningAndProblemSolving", attendance.getRating1());
        }};
        tableDataDTO.setAttendance(attendanceDetails);

        tableDataDTO.setAttributeAverages(Arrays.asList(
                chatDataDTO.getResponsibility(),
                chatDataDTO.getAbilityToLearn(),
                chatDataDTO.getResearchSkills(),
                chatDataDTO.getQualityOfWork(),
                chatDataDTO.getCommunicationSkills(),
                chatDataDTO.getPlanningAndProblemSolving()
        ));

// Calculate averages
        double averageSearchEngine = calculateAverage(Arrays.asList(searchEngineQuestionnaire.getRating1(), searchEngineQuestionnaire.getRating2(), searchEngineQuestionnaire.getRating3()));
        double averageTeachATopic = calculateAverage(Arrays.asList(teachATopic.getRating1(), teachATopic.getRating2(), teachATopic.getRating3(), teachATopic.getRating4()));
        double averageGroupDiscussion = calculateAverage(Arrays.asList(groupDiscussion.getRating1(), groupDiscussion.getRating2(), groupDiscussion.getRating3()));
        double averageTeamTask = calculateAverage(Arrays.asList(teamTask.getRating1(), teamTask.getRating2(), teamTask.getRating3(), teamTask.getRating4()));

// Set task averages in tableDataDTO
        tableDataDTO.setTaskAverages(List.of(
                averageSearchEngine,
                averageTeachATopic,
                averageGroupDiscussion,
                (double) googleSpreadsheet.getRating1(), // Cast to double
                (double) origami.getRating1(), // Cast to double
                averageTeamTask,
                (double) punctuality.getRating1(), // Cast to double
                (double) attendance.getRating1() // Cast to double
        ));



        return tableDataDTO;
    }

    private DocsDataDTO getDocsData(SearchEngineQuestionareDTO searchEngineQuestionnaire, TeachATopicDTO teachATopic, GroupDiscussionDTO groupDiscussion, GoogleSpreadsheetDTO googleSpreadsheet, TeamTaskDTO teamTask, PersonalDescriptionDTO personalDescription, PersonalityTypeDTO personalityType) {
        DocsDataDTO docsDataDTO = new DocsDataDTO();

        // Setting data for searchEngineQuestionare
        Map<String, String> searchEngineQuestionareMap = new HashMap<String, String>() {{
            put("URL", searchEngineQuestionnaire.getURL());
            put("Remark", searchEngineQuestionnaire.getRemark());
        }};
        docsDataDTO.setSearchEngineQuestionare(searchEngineQuestionareMap);

        // Setting data for teachATopic
        Map<String, String> teachATopicMap = new HashMap<String, String>() {{
            put("URL", teachATopic.getURL());
            put("Remark", teachATopic.getRemark());
        }};
        docsDataDTO.setTeachATopic(teachATopicMap);

        // Setting data for GroupDiscussion
        Map<String, String> groupDiscussionMap = new HashMap<String, String>() {{
            put("URL", groupDiscussion.getURL());
            put("Remark", groupDiscussion.getRemark());
        }};
        docsDataDTO.setGroupDiscussion(groupDiscussionMap);

        // Setting data for googleSpreadSheet
        Map<String, String> googleSpreadSheetMap = new HashMap<String, String>() {{
            put("URL", googleSpreadsheet.getURL());
            put("Remark", googleSpreadsheet.getRemark());
        }};
        docsDataDTO.setGoogleSpreadSheet(googleSpreadSheetMap);

        // Setting data for teamTask
        Map<String, String> teamTaskMap = new HashMap<String, String>() {{
            put("URL", teamTask.getURL());
            put("Remark", teamTask.getRemark());
        }};
        docsDataDTO.setTeamTask(teamTaskMap);

        // Setting data for personalityType
        docsDataDTO.setPersonalityType(personalityType.getPersonalityType());

        // Setting data for personalSuggestion
        docsDataDTO.setPersonalSuggestion(personalDescription.getPersonalDescription());

        return docsDataDTO;
    }


}
