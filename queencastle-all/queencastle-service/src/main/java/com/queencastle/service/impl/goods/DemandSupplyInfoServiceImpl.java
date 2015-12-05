package com.queencastle.service.impl.goods;

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
import com.queencastle.dao.mapper.goods.DemandSupplyInfoMapper;
import com.queencastle.dao.model.goods.DemandSupplyInfo;
import com.queencastle.dao.model.goods.PraiseInfo;
import com.queencastle.dao.model.goods.PraiseType;
import com.queencastle.dao.model.loggs.LogType;
import com.queencastle.dao.model.loggs.UserLog;
import com.queencastle.service.interf.goods.DemandSupplyInfoService;
import com.queencastle.service.interf.goods.PraiseInfoService;
import com.queencastle.service.interf.loggs.UserLogService;

@Service
public class DemandSupplyInfoServiceImpl implements DemandSupplyInfoService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DemandSupplyInfoMapper demandSupplyInfoMapper;
    @Autowired
    private PraiseInfoService praiseInfoService;
    @Autowired
    private UserLogService userLogService;

    @Override
    public int insert(DemandSupplyInfo dsInfo) {
        return demandSupplyInfoMapper.insert(dsInfo);
    }

    @Override
    public List<DemandSupplyInfo> getByParams(Map<String, Object> paramMap) {
        return demandSupplyInfoMapper.getByParams(paramMap);
    }

    @Override
    public DemandSupplyInfo getById(String id) {
        if (StringUtils.isNoneBlank(id)) {
            return demandSupplyInfoMapper.getById(id);
        }
        return null;
    }

    @Override
    public PageInfo<DemandSupplyInfo> getDemandSupplysByParams(int page, int rows,
            Map<String, Object> map) {
        PageInfo<DemandSupplyInfo> pageInfo = new PageInfo<DemandSupplyInfo>();
        pageInfo.setPage(page);
        Integer count = demandSupplyInfoMapper.getDemandSupplysCountByParams(map);
        if (count == null || count == 0) {
            pageInfo.setTotal(0);
            pageInfo.setRows(new ArrayList<DemandSupplyInfo>());
            return pageInfo;
        }
        pageInfo.setTotal(count);
        page = (page <= 1) ? 1 : page;
        Pageable pageable = new PageRequest(page - 1, rows);

        List<DemandSupplyInfo> list =
                demandSupplyInfoMapper.getDemandSupplysByParams(pageable, map);
        pageInfo.setRows(list);
        return pageInfo;
    }

    @Override
    public List<DemandSupplyInfo> getAllDemandSupplyInfo() {
        return demandSupplyInfoMapper.getAllDemandSupplyInfo();
    }

    @Override
    public boolean updateCheck(int result, String id) {

        return demandSupplyInfoMapper.updateCheck(result, id);
    }

    @Override
    public List<DemandSupplyInfo> getAllByType(String type) {
        if (StringUtils.isBlank(type)) {
            return getAllDemandSupplyInfo();
        } else {
            return demandSupplyInfoMapper.getAllByType(type);
        }
    }

    @Override
    public int addPaiseCnt(int score, String infoId, String userId) {
        int cnt = praiseInfoService.getCnt(infoId, userId);
        // 取消关注之前必须先关注
        // 如果是取消关注，必须有记录存在
        if (score < 0 && cnt == 0) {
            logger.error("非法操作");
            return 0;
        }
        // 已经关注或者取消，再次关注，更新状态
        if (score > 0 && cnt > 0) {
            logger.info("再次关注");
            // TODO: update
            praiseInfoService.update(infoId, userId, PraiseType.addPraise);
            // TODO: write into log
            UserLog ulog = new UserLog();
            ulog.setUserId(userId);
            ulog.setContent("用户再次关注");
            ulog.setObjectId(infoId);
            ulog.setLogType(LogType.ds);
            demandSupplyInfoMapper.addCnt(infoId);
            userLogService.insert(ulog);
            return 0;
        }
        if (score > 0 && cnt == 0) {
            // 没有这条记录，插入记录
            PraiseInfo info = new PraiseInfo();
            info.setType(PraiseType.addPraise);
            info.setInfoId(infoId);
            info.setUserId(userId);
            praiseInfoService.insert(info);

            UserLog ulog = new UserLog();
            ulog.setUserId(userId);
            ulog.setContent("用户关注");
            ulog.setObjectId(infoId);
            ulog.setLogType(LogType.ds);

            userLogService.insert(ulog);

            demandSupplyInfoMapper.addCnt(infoId);
            return 0;
        }
        if (score < 0 && cnt > 0) {
            // 更新记录
            PraiseInfo info = new PraiseInfo();
            info.setType(PraiseType.cancelPraise);
            info.setInfoId(infoId);
            info.setUserId(userId);
            // TODO: update
            praiseInfoService.update(infoId, userId, PraiseType.cancelPraise);
            // TODO: write into log
            UserLog ulog = new UserLog();
            ulog.setUserId(userId);
            ulog.setContent("用户取消");
            ulog.setObjectId(infoId);
            ulog.setLogType(LogType.ds);

            userLogService.insert(ulog);
            demandSupplyInfoMapper.minusCnt(infoId);
            return 0;
        }
        return 0;
    }

    @Override
    public List<DemandSupplyInfo> getAllByUserId(String userId) {

        return demandSupplyInfoMapper.getAllByUserId(userId);

    }

    @Override
    public List<DemandSupplyInfo> getByQueryParams(Map<String, Object> queryMap) {
        return demandSupplyInfoMapper.getByQueryParams(queryMap);
    }

    @Override
    public List<DemandSupplyInfo> getThreeByUserId(String userId, String infoId) {
        return demandSupplyInfoMapper.getThreeByUserId(userId, infoId);
    }

    @Override
    public int addAttention(int score, String infoId, String userId) {
        int cnt = praiseInfoService.getCnt(infoId, userId);
        if (cnt == 0) {
            PraiseInfo info = new PraiseInfo();
            info.setType(PraiseType.addPraise);
            info.setInfoId(infoId);
            info.setUserId(userId);
            praiseInfoService.insert(info);

            UserLog ulog = new UserLog();
            ulog.setUserId(userId);
            ulog.setContent("用户关注");
            ulog.setObjectId(infoId);
            ulog.setLogType(LogType.ds);

            userLogService.insert(ulog);

            demandSupplyInfoMapper.addCnt(infoId);
            return 0;
        }
        return -1;
    }

}
