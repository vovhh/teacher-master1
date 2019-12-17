package com.soldier.models.sys.dao.teachingResult;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.model.TeachingResultEntity;
import com.soldier.models.sys.model.DeptEntity;

import java.util.List;

/**
 * @author soldier
 * @title: TeachingResultDao
 * @projectName teacher_files
 * @date 19-7-5上午11:14
 */
public interface TeachingResultDao {

    /**
     * 添加
     * @param teachingResultEntity
     */
    public void save(TeachingResultEntity teachingResultEntity);

    /**
     * 删除
     * @param teachingResultEntity
     */
    public void delete(TeachingResultEntity teachingResultEntity);

    /**
     * 修改
     * @param teachingResultEntity
     */
    public void update(TeachingResultEntity teachingResultEntity);


    /**
     * 查询
     * @param TeachingResultEntity
     * @return
     */
    public TeachingResultEntity findById(TeachingResultEntity TeachingResultEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<TeachingResultEntity> pageBean);

    /**
     * 老师查询--主持人
     * @param teacherId
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPerson(Integer teacherId, String key, PageBean<TeachingResultEntity> pageBean);


    /**
     * 老师查询--成员
     * @param teacherCode
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByMember(String teacherCode, String key, PageBean<TeachingResultEntity> pageBean);

    /**
     * 部门管理员和领导查询本部门所有
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByDept(Long deptId, String key, PageBean<TeachingResultEntity> pageBean);

    /**
     * 批量导出
     * @param deptId
     * @param Ids
     * @param teacherId
     * @return
     */
    public List<TeachingResultEntity> findByExport(Long deptId, String[] Ids, Integer teacherId);
    
    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

}
