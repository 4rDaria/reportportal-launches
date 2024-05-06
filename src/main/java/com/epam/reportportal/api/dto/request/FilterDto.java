package com.epam.reportportal.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
