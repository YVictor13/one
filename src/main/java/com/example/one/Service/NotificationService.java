package com.example.one.Service;

import com.example.one.Dto.NotificationDTO;
import com.example.one.Dto.PaginationDTO;
import com.example.one.Dto.QuestionDTO;
import com.example.one.mapper.NotificationMapper;
import com.example.one.mapper.UserMapper;
import com.example.one.model.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired(required = false)
    private NotificationMapper notificationMapper;

    @Autowired(required = false)
    private UserMapper userMapper;

    public PaginationDTO list(Long id, Integer page, Integer size) {
        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();

        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(id);
        Integer totalCount = (int) notificationMapper.countByExample(example);


//        Integer totalCount = questionMapper.profileCount(id);

        paginationDTO.setPagination(totalCount, page, size);

        //size(page-1)
        Integer offset = size * (paginationDTO.getCurrentPage() - 1);
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(id);
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(notificationExample,
                new RowBounds(offset, size));

        if(notifications.size() ==0){
           return paginationDTO;
        }
        Set<Long> disUserIds = notifications.stream().map(notify -> notify.getNotifier()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList<>(disUserIds);
        UserExample userExample =new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long,User> userMap = users.stream().collect(Collectors.toMap(u->u.getId(), u->u));


        List<NotificationDTO> notificationDTOList = new ArrayList<>();
        paginationDTO.setData(notificationDTOList);
        return paginationDTO;
    }
}
