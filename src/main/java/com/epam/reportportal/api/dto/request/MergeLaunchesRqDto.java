package com.epam.reportportal.api.dto.request;

import com.epam.reportportal.api.dto.common.AttributeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MergeLaunchesRqDto implements Serializable {
    private List<AttributeDto> attributes;
    private String description;
    private String endTime;
    private boolean extendSuitesDescription;
    private List<Integer> launches;
    private String mergeType;
    private String mode;
    private String name;
    private String startTime;
}
