package com.snapp.dangidongi.service;

import com.snapp.dangidongi.entity.FriendGroupEntity;
import com.snapp.dangidongi.exception.NotFoundException;
import com.snapp.dangidongi.mapper.FriendGroupMapper;
import com.snapp.dangidongi.model.FriendGroupModel;
import com.snapp.dangidongi.repository.FriendGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FriendGroupService {


    private final FriendGroupRepository friendGroupRepository;
    private final FriendGroupMapper friendGroupMapper;

    public FriendGroupEntity save(FriendGroupModel friendGroupModel) {
        FriendGroupEntity friendGroup = friendGroupMapper.modelToEntity(friendGroupModel);
        return friendGroupRepository.save(friendGroup);
    }

    public FriendGroupModel findById(Long id) throws NotFoundException {
        FriendGroupEntity friendGroup = friendGroupRepository.findById(id).orElseThrow(NotFoundException::new);
        return friendGroupMapper.entityToModel(friendGroup);
    }


    public void deleteById(Long id) {
        friendGroupRepository.deleteById(id);
    }

    public Page<FriendGroupModel> findGroupByCreatorId(Long userId, Pageable pageable) {
        return friendGroupRepository.findByCreator_Id(userId, pageable).map(friendGroupMapper::entityToModel);
    }
}
