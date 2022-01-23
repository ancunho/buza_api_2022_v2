package com.buza.server.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Getter
@Setter
@Slf4j
public class BaseDto implements Serializable {

    private int rn;
    private int page;
    private int limit;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String sessionId;
    private String ipAddr;
    private String userAgent;
    private String referer;

}
