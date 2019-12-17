package com.soldier.models.sys.service.teachingMaterial;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.model.TeachingMaterialEntity;

import java.util.List;

/**
 * @author soldier
 * @title: TeachingMaterialService
 * @projectName teacher_files
 * @date 19-7-5上午11:14
 */
public interface TeachingMaterialService {

    /**
     * 添加
     * @param teachingMaterialEntity
     */
    public void save(TeachingMaterialEntity teachingMaterialEntity);

    /**
     * 删除
     * @param teachingMaterialEntity
     */
    public void delete(TeachingMaterialEntity teachingMaterialEntity);

    /**
     * 修改
     * @param teachingMaterialEntity
     */
    public void update(TeachingMaterialEntity teachingMaterialEntity);

    /**
     * 查询
     * @param teachingMaterialEntity
     * @return
     */
    public TeachingMaterialEntity findById(TeachingMaterialEntity teachingMaterialEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<TeachingMaterialEntity> pageBean);

    /**
     * 主编
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByEditor(Integer teacherId, String key, PageBean<TeachingMaterialEntity> pageBean);

    /**
     * 部门管理员和领导查询本部门所有
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByDept(Long deptId, String key, PageBean<TeachingMaterialEntity> pageBean);

    /**
     * 批量导出
     * @param deptId
     * @param Ids
     * @param teacherId
     * @return
     */
    public List<TeachingMaterialEntity> findByExport(Long deptId, String[] Ids, Integer teacherId);

    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

}
