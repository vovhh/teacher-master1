package com.soldier.models.sys.service.competition.impl;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.dao.competition.CompetitionDao;
import com.soldier.models.sys.model.CompetitionEntity;
import com.soldier.models.sys.model.DeptEntity;
import com.soldier.models.sys.service.competition.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author soldier
 * @title: CompetitionServiceImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:48
 */
@Service
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionDao competitionDao;

    @Override
    public void save(CompetitionEntity competitionEntity) {
        competitionDao.save(competitionEntity);
    }

    @Override
    public void delete(CompetitionEntity competitionEntity) {
        competitionDao.delete(competitionEntity);
    }

    @Override
    public void update(CompetitionEntity competitionEntity) {
        competitionDao.update(competitionEntity);
    }

    @Override
    public void updateCompetitionDeptName(DeptEntity deptEntity) {
        competitionDao.updateCompetitionDeptName(deptEntity);
    }

    @Override
    public CompetitionEntity findById(CompetitionEntity competitionEntity) {
        return competitionDao.findById(competitionEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<CompetitionEntity> pageBean) {
        return competitionDao.findByPage(key, pageBean);
    }

    @Override
    public PageBean findByTeacher(Integer teacherId,Integer matchAttribute, String key, PageBean<CompetitionEntity> pageBean) {
        return competitionDao.findByTeacher(teacherId, matchAttribute, key, pageBean);
    }

    @Override
    public PageBean findByCaptain(Integer teacherId, String key, PageBean<CompetitionEntity> pageBean) {
        return competitionDao.findByCaptain(teacherId, key, pageBean);
    }

    @Override
    public PageBean findByJoin(String teacherName, String key, PageBean<CompetitionEntity> pageBean) {
        return competitionDao.findByJoin(teacherName, key, pageBean);
    }

    @Override
    public PageBean findByDeptTea(Long deptId, String key, PageBean<CompetitionEntity> pageBean) {
        return competitionDao.findByDeptTea(deptId, key, pageBean);
    }

    @Override
    public PageBean findByDeptStu(Long deptId, String key, PageBean<CompetitionEntity> pageBean) {
        return competitionDao.findByDeptStu(deptId, key, pageBean);
    }

    @Override
    public List<CompetitionEntity> findByExport(Long deptId, String[] Ids, Integer teacherId) {
        return competitionDao.findByExport(deptId, Ids, teacherId);
    }

    @Override
    public void deleteBatch(String[] Ids) {
        competitionDao.deleteBatch(Ids);
    }
}
