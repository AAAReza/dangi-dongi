package com.snapp.dangidongi.service;

import com.snapp.dangidongi.entity.FriendGroupEntity;
import com.snapp.dangidongi.entity.UserEntity;
import com.snapp.dangidongi.entity.UserFriendGroupEntity;
import com.snapp.dangidongi.exception.NotFoundException;
import com.snapp.dangidongi.mapper.FriendGroupMapper;
import com.snapp.dangidongi.model.FriendGroupCreateModel;
import com.snapp.dangidongi.model.FriendGroupModel;
import com.snapp.dangidongi.repository.FriendGroupRepository;
import com.snapp.dangidongi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FriendGroupService {


    private final FriendGroupRepository friendGroupRepository;
    private final FriendGroupMapper friendGroupMapper;
    private final UserRepository userRepository;

    public FriendGroupEntity save(FriendGroupCreateModel friendGroupCreateModel) {
        FriendGroupEntity friendGroup = friendGroupMapper.createModelToEntity(friendGroupCreateModel);
        friendGroup.setReferralLink(friendGroupCreateModel.getName().concat(friendGroupCreateModel.getCreator().toString()));
        return friendGroupRepository.save(friendGroup);
    }


    public FriendGroupEntity addUserToGroup(Long groupId, Long userId) throws NotFoundException {

        FriendGroupEntity friendGroupEntity = friendGroupRepository.findById(groupId).orElseThrow(() -> new NotFoundException("group"));
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user"));

        friendGroupEntity.getUserFriendGroups().add(UserFriendGroupEntity.builder().group(friendGroupEntity).user(userEntity).build());
        return friendGroupRepository.save(friendGroupEntity);
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
