package com.example.one.Dto;

import com.example.one.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String  tag;
    private long gmtCreate;
    private long gmtModified;
    private long  creator;
    private Integer likeCount;
    private Integer commentCount;
    private Integer viewCount;
    private User user;
}
