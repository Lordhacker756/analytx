package com.umbrella.projectumbrella.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableDataDTO {
    private Map<String, Float> searchEngineQuestionnare = new HashMap<String, Float>();

    private Map<String, Float> teachATopic = new HashMap<String, Float>();

    private Map<String, Float> groupDiscussion = new HashMap<String, Float>();

    private Map<String, Float> googleSpreadsheet = new HashMap<String, Float>();

    private Map<String, Float> origami = new HashMap<String, Float>();

    private Map<String, Float> teamTask = new HashMap<String, Float>();

    private Map<String, Float> punctuality = new HashMap<String, Float>();

    private Map<String, Float> attendance = new HashMap<String, Float>();

    private List<Double> taskAverages = new ArrayList<>();

    private List<Double> attributeAverages = new ArrayList<>();
 }
