package com.epam.reportportal.model.launches;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class Launch {
    @JsonProperty
    private String owner;
    @JsonProperty
    private String description;
    @JsonProperty
    private int id;
    @JsonProperty
    private String uuid;
    @JsonProperty
    private String name;
    @JsonProperty
    private int number;
    @JsonProperty
    private long startTime;
    @JsonProperty
    private long endTime;
    @JsonProperty
    private long lastModified;
    @JsonProperty
    private String status;
    @JsonProperty
    private Statistics statistics;
    @JsonProperty
    private List<Attribute> attributes;
    @JsonProperty
    private String mode;
    @JsonProperty
    private List<String> analysing;
    @JsonProperty
    private double approximateDuration;
    @JsonProperty
    private boolean hasRetries;
    @JsonProperty
    private boolean rerun;
    @JsonProperty
    private Map<String, String> metadata;
}
