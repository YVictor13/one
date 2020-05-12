package com.example.one.Dto;

import java.util.List;

public class PaginationDTO {
    private List<QuestionDTO> question;
    private boolean showPrePage;
    private boolean showNext;
    private boolean showFirstPage;
    private boolean showEndPage;
    private Integer CurrentPage;
    private List<Integer> showPages;
}
