package com.example.one.Model;

import lombok.Data;


@Data
public class Question {
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

}
