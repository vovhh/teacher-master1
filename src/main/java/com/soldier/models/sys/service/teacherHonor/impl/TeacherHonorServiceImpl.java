package com.soldier.models.sys.service.teacherHonor.impl;

import com.soldier.common.IDUtils;
import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.dao.teacherHonor.TeacherHonorDao;
import com.soldier.models.sys.model.TeacherHonorEntity;
import com.soldier.models.sys.service.teacherHonor.TeacherHonorService;
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
public class TeacherHonorServiceImpl implements TeacherHonorService {

    @Autowired
    private TeacherHonorDao teacherHonorDao;

    @Override
    public void save(TeacherHonorEntity teacherHonorEntity) {
        teacherHonorEntity.setHonorId(IDUtils.genItemId());
        teacherHonorDao.save(teacherHonorEntity);
    }

    @Override
    public void delete(TeacherHonorEntity teacherHonorEntity) {
        teacherHonorDao.delete(teacherHonorEntity);
    }

    @Override
    public void update(TeacherHonorEntity teacherHonorEntity) {
        teacherHonorDao.update(teacherHonorEntity);
    }

    @Override
    public TeacherHonorEntity findById(TeacherHonorEntity teacherHonorEntity) {
        return teacherHonorDao.findById(teacherHonorEntity);
    }

    @Override
    public PageBean findByPage(String key, PageBean<TeacherHonorEntity> pageBean) {
        return teacherHonorDao.findByPage(key, pageBean);
    }

    @Override
    public PageBean findByPerson(Integer teacherId, String key, PageBean<TeacherHonorEntity> pageBean) {
        return teacherHonorDao.findByPerson(teacherId, key, pageBean);
    }

    @Override
    public PageBean findByMember(String teacherCode, String key, PageBean<TeacherHonorEntity> pageBean) {
        return teacherHonorDao.findByMember(teacherCode, key, pageBean);
    }

    @Override
    public PageBean findByDept(Long deptId, String key, PageBean<TeacherHonorEntity> pageBean) {
        return teacherHonorDao.findByDept(deptId, key, pageBean);
    }

    @Override
    public List<TeacherHonorEntity> findByExport(Long deptId, String[] Ids, Integer teacherId) {
        return teacherHonorDao.findByExport(deptId, Ids, teacherId);
    }

    @Override
    public void deleteBatch(String[] Ids) {
        teacherHonorDao.deleteBatch(Ids);
    }
}
