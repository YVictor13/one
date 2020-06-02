package com.example.one.cache;

import com.example.one.Dto.TagDTO;

import java.util.ArrayList;
import java.util.List;

public class TagCache {
    public List<TagDTO>  get(){
        List<TagDTO> tagDTOs = new ArrayList<>();
        TagDTO tagDTO = new TagDTO();
        tagDTO.setCategoryName("开发语言");
        tagDTO.setTags();
        tagDTOs.add(tagDTO );
        return tagDTOs;
    }
}
