package com.epam.reportportal.api.dto.response;

import com.epam.reportportal.model.launches.Defects;
import com.epam.reportportal.model.launches.Executions;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDto implements Serializable {
    private Executions executions;
    private Defects defects;
}
