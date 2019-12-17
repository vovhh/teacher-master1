package com.soldier.models.sys.service.teacherHonor;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.model.TeacherHonorEntity;

import java.util.List;

/**
 * @author soldier
 * @title: TeacherHonorService
 * @projectName teacher_files
 * @date 19-7-5上午11:14
 */
public interface TeacherHonorService {

    /**
     * 添加
     * @param teacherHonorEntity
     */
    public void save(TeacherHonorEntity teacherHonorEntity);

    /**
     * 删除
     * @param teacherHonorEntity
     */
    public void delete(TeacherHonorEntity teacherHonorEntity);

    /**
     * 修改
     * @param teacherHonorEntity
     */
    public void update(TeacherHonorEntity teacherHonorEntity);


    /**
     * 查询
     * @param TeacherHonorEntity
     * @return
     */
    public TeacherHonorEntity findById(TeacherHonorEntity TeacherHonorEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<TeacherHonorEntity> pageBean);

    /**
     * 老师查询--主持人
     * @param teacherId
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPerson(Integer teacherId, String key, PageBean<TeacherHonorEntity> pageBean);


    /**
     * 老师查询--成员
     * @param teacherCode
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByMember(String teacherCode, String key, PageBean<TeacherHonorEntity> pageBean);

    /**
     * 部门管理员和领导查询本部门所有
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByDept(Long deptId, String key, PageBean<TeacherHonorEntity> pageBean);

    /**
     * 批量导出
     * @param deptId
     * @param Ids
     * @param teacherId
     * @return
     */
    public List<TeacherHonorEntity> findByExport(Long deptId, String[] Ids, Integer teacherId);

    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

}
