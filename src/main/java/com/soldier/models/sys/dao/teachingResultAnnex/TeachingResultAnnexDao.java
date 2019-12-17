package com.soldier.models.sys.dao.teachingResultAnnex;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.model.TeachingResultAnnexEntity;

import java.util.List;

/**
 * @author soldier
 * @title: TeachingResultAnnexDao
 * @projectName teacher_files
 * @date 19-7-5上午11:14
 */
public interface TeachingResultAnnexDao {

    /**
     * 添加
     * @param teachingResultAnnexEntity
     */
    public void save(TeachingResultAnnexEntity teachingResultAnnexEntity);

    /**
     * 删除
     * @param teachingResultAnnexEntity
     */
    public void delete(TeachingResultAnnexEntity teachingResultAnnexEntity);

    /**
     * 修改
     * @param teachingResultAnnexEntity
     */
    public void update(TeachingResultAnnexEntity teachingResultAnnexEntity);

    /**
     * 根据教学成果查询所有附件
     * @param resultId
     */
    public List<TeachingResultAnnexEntity> findByResultId(Long resultId);

    /**
     * 查询
     * @param teachingResultAnnexEntity
     */
    public TeachingResultAnnexEntity findById(TeachingResultAnnexEntity teachingResultAnnexEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     */
    public PageBean findByPage(String key, PageBean<TeachingResultAnnexEntity> pageBean);


    /**
     * 查询
     * @return
     */
    public List<TeachingResultAnnexEntity> findAll();
    
    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

}
