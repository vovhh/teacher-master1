package com.soldier.models.sys.service.teacherHonorAnnex.impl;

import com.soldier.common.IDUtils;
import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.dao.teacherHonorAnnex.TeacherHonorAnnexDao;
import com.soldier.models.sys.model.TeacherHonorAnnexEntity;
import com.soldier.models.sys.service.teacherHonorAnnex.TeacherHonorAnnexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author soldier
 * @title: TeacherHonorAnnexDaoImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:29
 */
@Component
public class TeacherHonorAnnexServiceImpl implements TeacherHonorAnnexService {

    @Autowired
    private TeacherHonorAnnexDao teacherHonorAnnexDao;

    @Override
    public void save(TeacherHonorAnnexEntity teacherHonorAnnexEntity) {
        teacherHonorAnnexEntity.setAnnexId(IDUtils.genItemId());
        teacherHonorAnnexDao.save(teacherHonorAnnexEntity);
    }

    @Override
    public void delete(TeacherHonorAnnexEntity TeacherHonorAnnexEntity) {
        teacherHonorAnnexDao.delete(TeacherHonorAnnexEntity);
    }

    @Override
    public void update(TeacherHonorAnnexEntity TeacherHonorAnnexEntity) {
        teacherHonorAnnexDao.update(TeacherHonorAnnexEntity);
    }

    @Override
    public List<TeacherHonorAnnexEntity> findByHonorId(Long honorId) {
        return teacherHonorAnnexDao.findByHonorId(honorId);
    }

    @Override
    public TeacherHonorAnnexEntity findById(TeacherHonorAnnexEntity TeacherHonorAnnexEntity) {
        return teacherHonorAnnexDao.findById(TeacherHonorAnnexEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<TeacherHonorAnnexEntity> pageBean) {
        return teacherHonorAnnexDao.findByPage(key, pageBean);
    }

    @Override
    public List<TeacherHonorAnnexEntity> findAll() {
        return teacherHonorAnnexDao.findAll();
    }

    @Override
    public void deleteBatch(String[] Ids) {
        teacherHonorAnnexDao.deleteBatch(Ids);
    }
}
