package com.soldier.models.sys.dao.teacherHonorAnnex;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.model.TeacherHonorAnnexEntity;

import java.util.List;

/**
 * @author soldier
 * @title: TeacherHonorAnnexDao
 * @projectName teacher_files
 * @date 19-7-5上午11:14
 */
public interface TeacherHonorAnnexDao {

    /**
     * 添加
     * @param teacherHonorAnnexEntity
     */
    public void save(TeacherHonorAnnexEntity teacherHonorAnnexEntity);

    /**
     * 删除
     * @param teacherHonorAnnexEntity
     */
    public void delete(TeacherHonorAnnexEntity teacherHonorAnnexEntity);

    /**
     * 修改
     * @param teacherHonorAnnexEntity
     */
    public void update(TeacherHonorAnnexEntity teacherHonorAnnexEntity);

    /**
     * 根据教师荣誉查询所有附件
     * @param honorId
     */
    public List<TeacherHonorAnnexEntity> findByHonorId(Long honorId);

    /**
     * 查询
     * @param teacherHonorAnnexEntity
     */
    public TeacherHonorAnnexEntity findById(TeacherHonorAnnexEntity teacherHonorAnnexEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     */
    public PageBean findByPage(String key, PageBean<TeacherHonorAnnexEntity> pageBean);


    /**
     * 查询
     * @return
     */
    public List<TeacherHonorAnnexEntity> findAll();
    
    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

}
