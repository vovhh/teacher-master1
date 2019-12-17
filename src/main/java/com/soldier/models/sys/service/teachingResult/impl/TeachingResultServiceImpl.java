package com.soldier.models.sys.service.teachingResult.impl;

import com.soldier.common.IDUtils;
import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.dao.teachingResult.TeachingResultDao;
import com.soldier.models.sys.model.TeachingResultEntity;
import com.soldier.models.sys.service.teachingResult.TeachingResultService;
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
public class TeachingResultServiceImpl implements TeachingResultService {

    @Autowired
    private TeachingResultDao teachingResultDao;

    @Override
    public void save(TeachingResultEntity teachingResultEntity) {
        teachingResultEntity.setResultId(IDUtils.genItemId());
        teachingResultDao.save(teachingResultEntity);
    }

    @Override
    public void delete(TeachingResultEntity teachingResultEntity) {
        teachingResultDao.delete(teachingResultEntity);
    }

    @Override
    public void update(TeachingResultEntity teachingResultEntity) {
        teachingResultDao.update(teachingResultEntity);
    }

    @Override
    public TeachingResultEntity findById(TeachingResultEntity teachingResultEntity) {
        return teachingResultDao.findById(teachingResultEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<TeachingResultEntity> pageBean) {
        return teachingResultDao.findByPage(key, pageBean);
    }

    @Override
    public PageBean findByPerson(Integer teacherId, String key, PageBean<TeachingResultEntity> pageBean) {
        return teachingResultDao.findByPerson(teacherId, key, pageBean);
    }

    @Override
    public PageBean findByMember(String teacherCode, String key, PageBean<TeachingResultEntity> pageBean) {
        return teachingResultDao.findByMember(teacherCode, key, pageBean);
    }

    @Override
    public PageBean findByDept(Long deptId, String key, PageBean<TeachingResultEntity> pageBean) {
        return teachingResultDao.findByDept(deptId, key, pageBean);
    }

    @Override
    public List<TeachingResultEntity> findByExport(Long deptId, String[] Ids, Integer teacherId) {
        return teachingResultDao.findByExport(deptId, Ids, teacherId);
    }

    @Override
    public void deleteBatch(String[] Ids) {
        teachingResultDao.deleteBatch(Ids);
    }
}
