package com.soldier.models.sys.service.competitionAnnex.impl;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.dao.competitionAnnex.CompetitionAnnexDao;
import com.soldier.models.sys.model.CompetitionAnnexEntity;
import com.soldier.models.sys.service.competitionAnnex.CompetitionAnnexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author soldier
 * @title: CompetitionAnnexServiceImpl
 * @projectName teacher_files
 * @date 19-7-22下午7:17
 * @Email： 583403411@qq.com
 * @description:
 */
@Service
public class CompetitionAnnexServiceImpl implements CompetitionAnnexService {

    @Autowired
    private CompetitionAnnexDao CompetitionAnnexDao;

    @Override
    public void save(CompetitionAnnexEntity competitionAnnexEntity) {
        CompetitionAnnexDao.save(competitionAnnexEntity);
    }

    @Override
    public void delete(CompetitionAnnexEntity competitionAnnexEntity) {
        CompetitionAnnexDao.delete(competitionAnnexEntity);
    }

    @Override
    public void update(CompetitionAnnexEntity competitionAnnexEntity) {
        CompetitionAnnexDao.update(competitionAnnexEntity);
    }

    @Override
    public List<CompetitionAnnexEntity> findByCompetitionId(Integer competitionId) {
        return CompetitionAnnexDao.findByCompetitionId(competitionId);
    }

    @Override
    public CompetitionAnnexEntity findById(CompetitionAnnexEntity competitionAnnexEntity) {
        return CompetitionAnnexDao.findById(competitionAnnexEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<CompetitionAnnexEntity> pageBean) {
        return CompetitionAnnexDao.findByPage(key, pageBean);
    }

    @Override
    public void deleteBatch(String[] Ids) {
        CompetitionAnnexDao.deleteBatch(Ids);
    }
}
