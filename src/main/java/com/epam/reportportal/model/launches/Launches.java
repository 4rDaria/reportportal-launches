package com.epam.reportportal.model.launches;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class Launches {
    @JsonProperty
    List<Launch> content;
    @JsonProperty
    Page page;
}
