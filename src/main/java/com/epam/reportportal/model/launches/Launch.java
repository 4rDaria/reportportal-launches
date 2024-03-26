package com.epam.reportportal.model.launches;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class Launch {
    @JsonProperty("owner")
    private String owner;
    @JsonProperty("description")
    private String description;
    @JsonProperty("id")
    private int id;
    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("name")
    private String name;
    @JsonProperty("number")
    private int number;
    @JsonProperty("startTime")
    private long startTime;
    @JsonProperty("endTime")
    private long endTime;
    @JsonProperty("lastModified")
    private long lastModified;
    @JsonProperty("status")
    private String status;
    @JsonProperty("statistics")
    private Statistics statistics;
    @JsonProperty("attributes")
    private List<Attribute> attributes;
    @JsonProperty("mode")
    private String mode;
    @JsonProperty("analysing")
    private List<String> analysing;
    @JsonProperty("approximateDuration")
    private double approximateDuration;
    @JsonProperty("hasRetries")
    private boolean hasRetries;
    @JsonProperty("rerun")
    private boolean rerun;
    @JsonProperty("metadata")
    private Map<String, String> metadata;
}
