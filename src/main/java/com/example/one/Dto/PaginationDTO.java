package com.example.one.Dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrePage;
    private boolean showNextPage;
    private boolean showReturnFirstPage;
    private boolean showReturnEndPage;
    private Integer CurrentPage;
    private List<Integer> showPages = new ArrayList<>();
    private Integer TotalPage;


    public void setPagination(Integer totalCount, Integer page, Integer size) {

        int TotalPage;
        if (totalCount % size == 0) {
            this.TotalPage = totalCount / size;
        } else {
            this.TotalPage = totalCount / size + 1;
        }
//        容错处理

        if(page<1){
            page=1;
        }
        if(page>this.TotalPage){
            page=this.TotalPage;
        }
        this.CurrentPage = page;
        showPages.add(page);
        for(int i=1;i<=3;i++){
            if(page-i>0){
                showPages.add(0,page-i);
            }

            if(page+i<=this.TotalPage){
                showPages.add(page+i);
            }
        }

        //        是否展示上一页的分页功能
        if (page ==1) {
            showPrePage = false;
        } else {
            showPrePage = true;
        }

    //        是否展示下一页
        if (page == this.TotalPage) {
            showNextPage = false;
        } else {
            showNextPage = true;
        }

        //是否展示返回首页
        if(showPages.contains(1)){
            showReturnFirstPage=false;
        }else {
            showReturnFirstPage=true;
        }
        //是否展示返回最后一页
        if(showPages.contains(this.TotalPage)){
            showReturnEndPage=false;
        }else {
            showReturnEndPage=true;
        }
    }
}
