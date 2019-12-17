package com.soldier.models.sys.service.teachingMaterialAnnex;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.model.TeachingMaterialAnnexEntity;

import java.util.List;

/**
 * @author soldier
 * @title: TeachingMaterialAnnexService
 * @projectName teacher_files
 * @date 19-7-22下午7:15
 * @Email： 583403411@qq.com
 * @description:
 */
public interface TeachingMaterialAnnexService {

    /**
     * 添加
     * @param teachingMaterialAnnexEntity
     */
    public void save(TeachingMaterialAnnexEntity teachingMaterialAnnexEntity);

    /**
     * 删除
     * @param teachingMaterialAnnexEntity
     */
    public void delete(TeachingMaterialAnnexEntity teachingMaterialAnnexEntity);

    /**
     * 修改
     * @param teachingMaterialAnnexEntity
     */
    public void update(TeachingMaterialAnnexEntity teachingMaterialAnnexEntity);

    /**
     * 根据教材查询所有附件
     * @param materialId
     */
    public List<TeachingMaterialAnnexEntity> findByMaterialId(Long materialId);

    /**
     * 查询
     * @param teachingMaterialAnnexEntity
     */
    public TeachingMaterialAnnexEntity findById(TeachingMaterialAnnexEntity teachingMaterialAnnexEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     */
    public PageBean findByPage(String key, PageBean<TeachingMaterialAnnexEntity> pageBean);


    /**
     * 查询
     * @return
     */
    public List<TeachingMaterialAnnexEntity> findAll();

    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);
}
