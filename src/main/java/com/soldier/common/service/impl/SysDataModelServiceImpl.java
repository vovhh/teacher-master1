package com.soldier.common.service.impl;

import com.soldier.common.service.SysDataModelService;
import com.soldier.common.vo.SysDataModelBean;
import com.soldier.models.sys.dao.academicPaper.AcademicPaperDao;
import com.soldier.models.sys.dao.competition.CompetitionDao;
import com.soldier.models.sys.dao.sysuser.UserDao;
import com.soldier.models.sys.dao.teacherHonor.TeacherHonorDao;
import com.soldier.models.sys.dao.teacherItem.TeacherItemDao;
import com.soldier.models.sys.dao.teachingMaterial.TeachingMaterialDao;
import com.soldier.models.sys.dao.teachingResult.TeachingResultDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author soldier
 * @title: SysDataServiceImpl
 * @projectName teacher
 * @create:19-11-10下午6:56
 */
@Service
public class SysDataModelServiceImpl implements SysDataModelService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private CompetitionDao competitionDao;
    @Autowired
    private TeacherItemDao teacherItemDao;
    @Autowired
    private AcademicPaperDao academicPaperDao;
    @Autowired
    private TeacherHonorDao teacherHonorDao;
    @Autowired
    private TeachingMaterialDao teachingMaterialDao;
    @Autowired
    private TeachingResultDao teachingResultDao;

    @Override
    public SysDataModelBean selectCount(Long deptId) {

        SysDataModelBean dataModelBean = new SysDataModelBean();
        dataModelBean.setUserAllCount(userDao.getUserAllCount());
        dataModelBean.setCompetitionCount(competitionDao.getCompetitionCount(deptId));
        dataModelBean.setTeacherItemCount(teacherItemDao.getTeacherItemCount(deptId));

        return dataModelBean;
    }
}
