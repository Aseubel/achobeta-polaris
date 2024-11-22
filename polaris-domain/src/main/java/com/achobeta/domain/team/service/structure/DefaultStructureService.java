package com.achobeta.domain.team.service.structure;

import cn.hutool.core.collection.CollectionUtil;
import com.achobeta.domain.team.adapter.repository.IPositionRepository;
import com.achobeta.domain.team.model.bo.TeamBO;
import com.achobeta.domain.team.model.entity.PositionEntity;
import com.achobeta.domain.team.service.IStructureService;
import com.achobeta.types.enums.BizModule;
import com.achobeta.types.enums.GlobalServiceStatusCode;
import com.achobeta.types.exception.AppException;
import com.achobeta.types.support.postprocessor.AbstractFunctionPostProcessor;
import com.achobeta.types.support.postprocessor.PostContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author yangzhiyao
 * @description 查看团队组织架构的默认实现
 * @date 2024/11/8
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultStructureService extends AbstractFunctionPostProcessor<TeamBO> implements IStructureService {

    private final IPositionRepository repository;

    @Override
    public PositionEntity queryStructure(String teamId) {
        PostContext<TeamBO> postContext = buildPostContext(teamId);
        postContext = super.doPostProcessor(postContext, ViewStructurePostProcessor.class,
                new AbstractPostProcessorOperation<TeamBO>() {
                    @Override
                    public PostContext<TeamBO> doMainProcessor(PostContext<TeamBO> postContext) {
                        TeamBO teamBO = postContext.getBizData();
                        PositionEntity positionEntity = teamBO.getPositionEntity();
                        String teamId = positionEntity.getTeamId();

                        // 判断团队是否存在
                        if (!repository.isTeamExists(teamId)) {
                            log.error("团队不存在！teamId：{}", teamId);
                            throw new AppException(String.valueOf(GlobalServiceStatusCode.TEAM_NOT_EXIST.getCode()),
                                    GlobalServiceStatusCode.TEAM_NOT_EXIST.getMessage());
                        }

                        // 查询团队组织架构
                        positionEntity.setPositionId(teamId);
                        Queue<PositionEntity> queue = new LinkedList<>();
                        queue.add(positionEntity);
                        while(!queue.isEmpty()) {
                            PositionEntity tempPosition = queue.poll();
                            List<PositionEntity> subordinates = repository.querySubordinatePosition(tempPosition.getPositionId(), teamId);
                            tempPosition.setSubordinates(subordinates);
                            if(!CollectionUtil.isEmpty(subordinates)) {
                                queue.addAll(subordinates);
                            }
                        }

                        postContext.setBizData(TeamBO.builder().positionEntity(positionEntity).build());
                        return postContext;
                    }
                });
        return postContext.getBizData().getPositionEntity();
    }

    private static PostContext<TeamBO> buildPostContext(String teamId) {
        return PostContext.<TeamBO>builder()
                .bizName(BizModule.TEAM.getName())
                .bizData(TeamBO.builder()
                        .positionEntity(PositionEntity.builder().teamId(teamId).build())
                        .build())
                .build();
    }

}
