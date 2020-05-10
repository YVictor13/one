package com.example.one.Dto;

import com.example.one.Model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String  tag;
    private long gmtCreate;
    private long gmtModified;
    private Integer  creator;
    private Integer likeCount;
    private Integer commentCount;
    private Integer viewCount;
    private User user;


}
