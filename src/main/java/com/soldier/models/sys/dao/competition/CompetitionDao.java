package com.soldier.models.sys.dao.competition;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.model.CompetitionEntity;
import com.soldier.models.sys.model.DeptEntity;

import java.util.List;

/**
 * @author soldier
 * @title: CompetitionDao
 * @projectName teacher_files
 * @date 19-7-5上午11:14
 */
public interface CompetitionDao {

    /**
     * 添加
     * @param competitionEntity
     */
    public void save(CompetitionEntity competitionEntity);

    /**
     * 删除
     * @param competitionEntity
     */
    public void delete(CompetitionEntity competitionEntity);

    /**
     * 修改
     * @param competitionEntity
     */
    public void update(CompetitionEntity competitionEntity);

    /**
     * 当所属部门修改时，修改赛事的部门信息
     * @param deptEntity
     */
    public void updateCompetitionDeptName(DeptEntity deptEntity);

    /**
     * 查询
     * @param competitionEntity
     * @return
     */
    public CompetitionEntity findById(CompetitionEntity competitionEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<CompetitionEntity> pageBean);

    /**
     * 教师查询指导的所有比赛
     * @param teacherId
     * @param matchAttribute
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByTeacher(Integer teacherId,Integer matchAttribute, String key, PageBean<CompetitionEntity> pageBean);

    /**
     * 获奖老师查询--主持人
     * @param teacherId
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByCaptain(Integer teacherId, String key, PageBean<CompetitionEntity> pageBean);

    /**
     * 获奖老师查询--参赛队员身份
     * @param teacherName
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByJoin(String teacherName, String key, PageBean<CompetitionEntity> pageBean);

    /**
     * 部门管理员和领导查询本部门所有教师竞赛
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByDeptTea(Long deptId, String key, PageBean<CompetitionEntity> pageBean);

    /**
     * 部门领导查询本部门所有比赛
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByDeptStu(Long deptId, String key, PageBean<CompetitionEntity> pageBean);

    /**
     * 批量导出
     * @param deptId
     * @param Ids
     * @param teacherId
     * @return
     */
    public List<CompetitionEntity> findByExport(Long deptId, String[] Ids, Integer teacherId);
    
    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

    public Long getCompetitionCount(Long deptId);

}
