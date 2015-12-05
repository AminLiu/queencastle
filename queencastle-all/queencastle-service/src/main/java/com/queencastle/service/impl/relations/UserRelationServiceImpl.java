package com.queencastle.service.impl.relations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.queencastle.dao.PageInfo;
import com.queencastle.dao.mapper.relations.UserRelationMapper;
import com.queencastle.dao.model.relations.RelationType;
import com.queencastle.dao.model.relations.UserRelation;
import com.queencastle.service.interf.relations.UserRelationService;

@Service
public class UserRelationServiceImpl implements UserRelationService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserRelationMapper userRelationMapper;

    @Override
    public List<UserRelation> getByParentIdAndType(String parentId, RelationType type) {
        if (StringUtils.isNoneBlank(parentId) && type != null) {
            return userRelationMapper.getByParentIdAndType(parentId, type);
        }

        return null;
    }

    @Override
    public int insert(UserRelation userRelation) {
        return userRelationMapper.insert(userRelation);
    }

    @Override
    public UserRelation getById(String id) {
        if (StringUtils.isNoneBlank(id)) {
            userRelationMapper.getById(id);
        }
        return null;
    }

    @Override
    public boolean checkAndInsert(UserRelation userRelation) {
        // TODO:类型区分
        try {
            String pid = userRelation.getParentId();
            String uid = userRelation.getUserId();
            // 子节点不可能重复
            Integer cnt = userRelationMapper.getCntByUserId(uid);
            if (cnt > 0) {
                return false;
            }
            // 获取父节点左侧等级
            Integer plevel = userRelationMapper.getByParentId(pid);
            if (plevel == null) {
                // 左侧等级不存在，在右侧找到等级
                Integer subLevel = userRelationMapper.getLevelByUserId(pid);
                // 在右侧找到等级
                if (subLevel != null) {
                    // 判断子节点是否有等级存在,比较两个等级
                    Integer level = userRelationMapper.getByParentId(uid);
                    if ((level != null && subLevel < level) || level == null) {
                        subLevel += 1;
                        userRelation.setLayer(subLevel);
                    }
                } else {
                    // 在右侧没有找到等级
                    // 判断子节点是否有等级存在,比较两个等级，如果等级已经存在，违反会员的先后规则
                    Integer level = userRelationMapper.getByParentId(uid);
                    if (level == null) {
                        userRelation.setLayer(1);
                    }
                }
            } else {
                // 左侧等级存在
                // 子节点等级不能存在
                Integer level = userRelationMapper.getByParentId(uid);
                if (level == null) {
                    userRelation.setLayer(plevel);
                }

            }
            if (userRelation.getLayer() != null) {
                insert(userRelation);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    @Override
    public List<String> getUserIdByParentId(String parentId) {
        if (StringUtils.isNoneBlank(parentId)) {
            return userRelationMapper.getUserIdByParentId(parentId);
        }
        return null;
    }

    @Override
    public PageInfo<UserRelation> getAgencyByParams(int page, int rows, Map<String, Object> map) {

        PageInfo<UserRelation> pageInfo = new PageInfo<UserRelation>();
        pageInfo.setPage(page);
        Integer count = userRelationMapper.getAgencyCountByParams(map);
        if (count == null || count == 0) {
            pageInfo.setTotal(0);
            pageInfo.setRows(new ArrayList<UserRelation>());
            return pageInfo;
        }
        pageInfo.setTotal(count);
        page = (page <= 1) ? 1 : page;
        Pageable pageable = new PageRequest(page - 1, rows);

        List<UserRelation> list = userRelationMapper.getAgencyByParams(pageable, map);
        pageInfo.setRows(list);
        return pageInfo;

    }

    @Override
    public UserRelation getByUserId(String userId) {
        if (StringUtils.isNoneBlank(userId)) {
            return userRelationMapper.getByUserId(userId);
        }
        return null;
    }

}
