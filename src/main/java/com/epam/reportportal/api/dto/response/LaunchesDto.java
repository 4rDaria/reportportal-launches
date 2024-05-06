package com.epam.reportportal.api.dto.response;

import com.epam.reportportal.api.dto.common.LaunchDto;
import com.epam.reportportal.model.launches.Page;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public class LaunchesDto implements Serializable {
    List<LaunchDto> content;
    Page page;
}
