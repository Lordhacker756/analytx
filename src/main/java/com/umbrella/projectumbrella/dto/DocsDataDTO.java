package com.umbrella.projectumbrella.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocsDataDTO {

    private Map<String, String> searchEngineQuestionare = new HashMap<String, String>() {{
        put("URL", "");
        put("Remark", "");
    }};

    private Map<String, String> teachATopic = new HashMap<String, String>() {{
        put("URL", "");
        put("Remark", "");
    }};

    private Map<String, String> GroupDiscussion = new HashMap<String, String>() {{
        put("URL", "");
        put("Remark", "");
    }};

    private Map<String, String> googleSpreadSheet = new HashMap<String, String>() {{
        put("URL", "");
        put("Remark", "");
    }};

    private Map<String, String> teamTask = new HashMap<String, String>() {{
        put("URL", "");
        put("Remark", "");
    }};

    private String personalityType;

    private String personalSuggestion;

}
