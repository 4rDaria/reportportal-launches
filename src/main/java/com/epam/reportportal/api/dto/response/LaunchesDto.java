package com.epam.reportportal.api.dto.response;

import com.epam.reportportal.api.dto.common.LaunchDto;
import com.epam.reportportal.model.launches.Page;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LaunchesDto implements Serializable {
    List<LaunchDto> content;
    Page page;
}
