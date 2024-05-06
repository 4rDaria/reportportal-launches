package com.epam.reportportal.api.dto.common;

import com.epam.reportportal.api.dto.response.StatisticsDto;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LaunchDto implements Serializable {
    private String owner;
    private String description;
    private int id;
    private String uuid;
    private String name;
    private int number;
    private long startTime;
    private long endTime;
    private long lastModified;
    private String status;
    private StatisticsDto statistics;
    private List<AttributeDto> attributes;
    private String mode;
    private List<String> analysing;
    private double approximateDuration;
    private boolean hasRetries;
    private boolean rerun;
    private Map<String, String> metadata;
}
