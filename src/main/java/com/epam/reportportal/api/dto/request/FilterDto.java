package com.epam.reportportal.api.dto.request;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class FilterDto implements Serializable {

    private String attributeKey;
    private String attributeValue;
    private String endTime;
    private boolean hasRetries;
    private int id;
    private String name;
    private int number;
    private String startTime;
    private String status;
    private String user;
    private String uuid;

}
