package com.soldier.models.sys.service.teachingMaterialAnnex.impl;

import com.soldier.common.IDUtils;
import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.dao.teachingMaterialAnnex.TeachingMaterialAnnexDao;
import com.soldier.models.sys.model.TeachingMaterialAnnexEntity;
import com.soldier.models.sys.service.teachingMaterialAnnex.TeachingMaterialAnnexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author soldier
 * @title: TeachingMaterialAnnexDaoImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:29
 */
@Component
public class TeachingMaterialAnnexServiceImpl implements TeachingMaterialAnnexService {

    @Autowired
    private TeachingMaterialAnnexDao teachingMaterialAnnexDao;

    @Override
    public void save(TeachingMaterialAnnexEntity teachingMaterialAnnexEntity) {
        teachingMaterialAnnexEntity.setAnnexId(IDUtils.genItemId());
        teachingMaterialAnnexDao.save(teachingMaterialAnnexEntity);
    }

    @Override
    public void delete(TeachingMaterialAnnexEntity teachingMaterialAnnexEntity) {
        teachingMaterialAnnexDao.delete(teachingMaterialAnnexEntity);
    }

    @Override
    public void update(TeachingMaterialAnnexEntity teachingMaterialAnnexEntity) {
        teachingMaterialAnnexDao.update(teachingMaterialAnnexEntity);
    }

    @Override
    public TeachingMaterialAnnexEntity findById(TeachingMaterialAnnexEntity teachingMaterialAnnexEntity) {
        return teachingMaterialAnnexDao.findById(teachingMaterialAnnexEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<TeachingMaterialAnnexEntity> pageBean) {
        return teachingMaterialAnnexDao.findByPage(key, pageBean);
    }

    @Override
    public List<TeachingMaterialAnnexEntity> findByMaterialId(Long materialId) {
        return teachingMaterialAnnexDao.findByMaterialId(materialId);
    }

    @Override
    public List<TeachingMaterialAnnexEntity> findAll() {
        return teachingMaterialAnnexDao.findAll();
    }

    @Override
    public void deleteBatch(String[] Ids) {
        teachingMaterialAnnexDao.deleteBatch(Ids);
    }
}
