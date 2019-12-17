package com.soldier.models.sys.service.teachingResultAnnex.impl;

import com.soldier.common.IDUtils;
import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.dao.teachingResultAnnex.TeachingResultAnnexDao;
import com.soldier.models.sys.model.TeachingResultAnnexEntity;
import com.soldier.models.sys.service.teachingResultAnnex.TeachingResultAnnexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author soldier
 * @title: TeachingResultAnnexDaoImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:29
 */
@Component
public class TeachingResultAnnexServiceImpl implements TeachingResultAnnexService {

    @Autowired
    private TeachingResultAnnexDao teachingResultAnnexDao;

    @Override
    public void save(TeachingResultAnnexEntity teachingResultAnnexEntity) {
        teachingResultAnnexEntity.setAnnexId(IDUtils.genItemId());
        teachingResultAnnexDao.save(teachingResultAnnexEntity);
    }

    @Override
    public void delete(TeachingResultAnnexEntity TeachingResultAnnexEntity) {
        teachingResultAnnexDao.delete(TeachingResultAnnexEntity);
    }

    @Override
    public void update(TeachingResultAnnexEntity TeachingResultAnnexEntity) {
        teachingResultAnnexDao.update(TeachingResultAnnexEntity);
    }

    @Override
    public TeachingResultAnnexEntity findById(TeachingResultAnnexEntity TeachingResultAnnexEntity) {
        return teachingResultAnnexDao.findById(TeachingResultAnnexEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<TeachingResultAnnexEntity> pageBean) {
        return teachingResultAnnexDao.findByPage(key, pageBean);
    }

    @Override
    public List<TeachingResultAnnexEntity> findByResultId(Long materialId) {
        return teachingResultAnnexDao.findByResultId(materialId);
    }

    @Override
    public List<TeachingResultAnnexEntity> findAll() {
        return teachingResultAnnexDao.findAll();
    }

    @Override
    public void deleteBatch(String[] Ids) {
        teachingResultAnnexDao.deleteBatch(Ids);
    }
}
