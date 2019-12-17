package com.soldier.models.sys.service.teachingMaterial.impl;

import com.soldier.common.IDUtils;
import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.dao.teachingMaterial.TeachingMaterialDao;
import com.soldier.models.sys.model.TeachingMaterialEntity;
import com.soldier.models.sys.service.teachingMaterial.TeachingMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: teacher_files
 * @author: soldier
 * @Email: 583403411@qq.com
 * @create 19-8-3 下午5:58
 * @Describe:
 **/
@Service
public class TeachingMaterialServiceImpl implements TeachingMaterialService {

    @Autowired
    private TeachingMaterialDao teachingMaterialDao;

    @Override
    public void save(TeachingMaterialEntity teachingMaterialEntity) {
        teachingMaterialEntity.setMaterialId(IDUtils.genItemId());
        teachingMaterialDao.save(teachingMaterialEntity);
    }

    @Override
    public void delete(TeachingMaterialEntity teachingMaterialEntity) {
        teachingMaterialDao.delete(teachingMaterialEntity);
    }

    @Override
    public void update(TeachingMaterialEntity teachingMaterialEntity) {
        teachingMaterialDao.update(teachingMaterialEntity);
    }

    @Override
    public TeachingMaterialEntity findById(TeachingMaterialEntity teachingMaterialEntity) {
        return teachingMaterialDao.findById(teachingMaterialEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<TeachingMaterialEntity> pageBean) {
        return teachingMaterialDao.findByPage(key, pageBean);
    }

    @Override
    public PageBean findByEditor(Integer teacherId, String key, PageBean<TeachingMaterialEntity> pageBean) {
        return teachingMaterialDao.findByEditor(teacherId, key, pageBean);
    }

    @Override
    public PageBean findByDept(Long deptId, String key, PageBean<TeachingMaterialEntity> pageBean) {
        return teachingMaterialDao.findByDept(deptId, key, pageBean);
    }

    @Override
    public List<TeachingMaterialEntity> findByExport(Long deptId, String[] Ids, Integer teacherId) {
        return teachingMaterialDao.findByExport(deptId, Ids, teacherId);
    }

    @Override
    public void deleteBatch(String[] Ids) {
        teachingMaterialDao.deleteBatch(Ids);
    }
}
