package com.example.one.cache;

import com.example.one.Dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDTO> get() {
        List<TagDTO> tagDTOs = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("js", "python", "html", "c#", "c/c++", "json", "java"));
        tagDTOs.add(program);

        TagDTO frameWork = new TagDTO();
        frameWork.setCategoryName("平台框架");
        frameWork.setTags(Arrays.asList("spring", "springboot", "react", "vue", "flask", "django", "express"));
        tagDTOs.add(frameWork);

        TagDTO service = new TagDTO();
        service.setCategoryName("服务器");
        service.setTags(Arrays.asList("linux", "centOs", "windows", "docker", "unix", "apache", "tomcat"));
        tagDTOs.add(service);

        TagDTO dataBase = new TagDTO();
        dataBase.setCategoryName("数据库");
        dataBase.setTags(Arrays.asList("mysql", "oracle", "sql", "redis", "noSql", "sqlServer", "mongodb"));
        tagDTOs.add(dataBase);

        TagDTO tools = new TagDTO();
        tools.setCategoryName("开发工具");
        tools.setTags(Arrays.asList("git", "IDEA", "github", "websStorm", "mysql", "eclipse", "maven"));
        tagDTOs.add(tools);
        return tagDTOs;
    }

    public static String filterInvalid(String tags) {
        String[] split = StringUtils.split(tags, " ");
        List<TagDTO> tagDTOs = get();
        List<String> tagList = tagDTOs.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        return Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(" "));
    }
}
