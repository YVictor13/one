package com.example.one.Dto;

import lombok.Data;

@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private String outerTitle;
    private Integer user;
    private String type;
}
