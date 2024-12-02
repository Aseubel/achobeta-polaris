package com.achobeta.domain.team.service;

import com.achobeta.domain.user.model.entity.UserEntity;

import java.util.List;

/**
 * @author yangzhiyao
 * @description 成员服务接口
 * @date 2024/11/17
 */
public interface IMemberService {

    /**
     * 删除成员
     * @param userId 发出操作的用户id
     * @param memberId 被删除的成员id
     * @param teamId 团队id
     * @date 2024/11/21
     */
   void deleteMember(String userId, String memberId, String teamId);

    /**
     * 添加成员方法接口
     * @param userEntity
     * @param userId
     */
    UserEntity addMember(UserEntity userEntity, String userId);

    /**
     * 修改团队成员信息
     * @param teamId 团队id
     * @param userEntity 用户实体
     * @return 修改后的用户实体
     */
    UserEntity modifyMember(String userId, String teamId, UserEntity userEntity);

    /**
     * 查询成员信息详情
     * @author yangzhiyao
     * @date 2024/11/19
     * @param userId 用户ID
     * @param memberId 成员ID
     * @return 查询到的成员信息
     */
    UserEntity queryMemberInfo(String userId, String memberId);

    /**
     * 查看团队成员列表
     * @param teamId 团队ID
     * @param lastId 上次查询的最后一条记录ID
     * @param limit 单次查询一页的记录数
     * @return 成员列表
     */
    List<UserEntity> queryMembers(String teamId,String lastId, Integer limit);

}