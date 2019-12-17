package com.soldier.models.sys.action.teachingResult;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.soldier.common.vo.PageBean;
import com.soldier.common.vo.R;
import com.soldier.models.sys.model.*;
import com.soldier.models.sys.service.competitionPrizeGrade.CompetitionPrizeGradeService;
import com.soldier.models.sys.service.competitionPrizeLevel.CompetitionPrizeLevelService;
import com.soldier.models.sys.service.teacher.TeacherService;
import com.soldier.models.sys.service.teachingResult.TeachingResultService;
import com.soldier.models.sys.service.teachingResultAnnex.TeachingResultAnnexService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author soldier
 * @title: TeachingResultAction
 * @projectName teacher_files
 * @date 19-7-5下午5:16
 * @Email： 583403411@qq.com
 * @description:
 */
public class TeachingResultAction extends ActionSupport implements ModelDriven<TeachingResultEntity> {

    @Autowired
    private TeachingResultService teachingResultService;
    @Autowired
    private TeachingResultAnnexService teachingResultAnnexService;
    @Autowired
    private CompetitionPrizeLevelService prizeLevelService;
    @Autowired
    private CompetitionPrizeGradeService prizeGradeService;
    @Autowired
    private TeacherService teacherService;
    //日志
    private static Logger logger = Logger.getLogger(TeachingResultAction.class);
    //模型驱动
    private TeachingResultEntity teachingResultEntity = new TeachingResultEntity();
    //分页
    private PageBean<TeachingResultEntity> pageBean = new PageBean<>();
    //返回集
    private R r = new R();
    //搜索值
    private String key;
    //当前页
    private Integer page;
    //大小
    private Integer limit;
    //批量删除id
    private String ids;
    //主持人id
    private Integer teacherId;
    //主持人工号
    private String itemPersonCode;
    //获奖级别
    private Integer prizeLevelId;
    //获奖等次
    private Integer prizeGradeId;
    //竞赛项目附件--可能是多个pdf或图片
    private String manyFilePath;
    //竞赛项目附件原名称
    private String manyFileName;
    //竞赛项目附件类型（后缀）
    private String manyFileType;
    //导出标识 1-系统管理员导出整个表 2-部门领导和部门负责人导出本学院全部 3-教师个人导出全部 4-根据id导出
    private Integer exportCode;

    @Override
    public TeachingResultEntity getModel() {
        return teachingResultEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

        //获奖信息
        CompetitionPrizeLevelEntity prizeLevel_result = new CompetitionPrizeLevelEntity();
        CompetitionPrizeGradeEntity prizeGrade_result = new CompetitionPrizeGradeEntity();
        prizeLevel_result.setId(prizeLevelId);
        prizeGrade_result.setId(prizeGradeId);
        prizeLevel_result = prizeLevelService.findById(prizeLevel_result);
        prizeGrade_result = prizeGradeService.findById(prizeGrade_result);
        teachingResultEntity.setPrizeLevel(prizeLevel_result);
        teachingResultEntity.setPrizeGrade(prizeGrade_result);

        //指导老师的姓名及部门信息
        TeacherEntity teacher_result = new TeacherEntity();
        teacher_result.setTeacherCode(itemPersonCode);
        teacher_result = teacherService.findByTeacherCode(teacher_result);
        teachingResultEntity.setDeptId(teacher_result.getDeptId());
//        teachingResultEntity.setDeptName(teacher_result.getDeptName());
        teachingResultEntity.setItemPerson(teacher_result);

        teachingResultService.save(teachingResultEntity);

        // 附件
        if (manyFileName != null && !"".equals(manyFileName)) {
            TeachingResultAnnexEntity teachingResultAnnexEntity;
            String[] filePath = manyFilePath.split(",");
            String[] fileName = manyFileName.split(",");
            String[] fileType = manyFileType.split(",");
            for (int i = 0; i < fileName.length; i++) {
                teachingResultAnnexEntity = new TeachingResultAnnexEntity();
                teachingResultAnnexEntity.setFilePath(filePath[i]);  //文件现在的路径
                teachingResultAnnexEntity.setFileName(fileName[i]);  //文件原来的名称
                teachingResultAnnexEntity.setFileType(fileType[i]);  //文件后缀，如: .pdf .jpg
                teachingResultAnnexEntity.setResultId(teachingResultEntity.getResultId());  //2、获取竞教学成果id
                teachingResultAnnexService.save(teachingResultAnnexEntity);   //3、再保存附件
            }
        }

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        teachingResultService.delete(teachingResultEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        //获奖信息
        CompetitionPrizeLevelEntity prizeLevel_result = new CompetitionPrizeLevelEntity();
        CompetitionPrizeGradeEntity prizeGrade_result = new CompetitionPrizeGradeEntity();
        prizeLevel_result.setId(prizeLevelId);
        prizeGrade_result.setId(prizeGradeId);
        prizeLevel_result = prizeLevelService.findById(prizeLevel_result);
        prizeGrade_result = prizeGradeService.findById(prizeGrade_result);
        teachingResultEntity.setPrizeLevel(prizeLevel_result);
        teachingResultEntity.setPrizeGrade(prizeGrade_result);

        //指导老师的姓名及部门信息
        TeacherEntity teacher_result = new TeacherEntity();
        teacher_result.setTeacherCode(itemPersonCode);
        teacher_result = teacherService.findByTeacherCode(teacher_result);
        teachingResultEntity.setDeptId(teacher_result.getDeptId());
//        teachingResultEntity.setDeptName(teacher_result.getDeptName());
        teachingResultEntity.setItemPerson(teacher_result);

        teachingResultService.update(teachingResultEntity);

        //用户重新上传的附件
        if (!"haveFile".equals(manyFilePath)) {  //定义规则，如果当前项目有附件，它就会被赋值，防止xx-add.html验证规则出错
            if (manyFileName != null && !"".equals(manyFileName)) {
                TeachingResultAnnexEntity teachingResultAnnexEntity;
                String[] filePath = manyFilePath.split(",");
                String[] fileName = manyFileName.split(",");
                String[] fileType = manyFileType.split(",");
                for (int i = 0; i < fileName.length; i++) {
                    teachingResultAnnexEntity = new TeachingResultAnnexEntity();
                    teachingResultAnnexEntity.setFilePath(filePath[i]);  //文件现在的路径
                    teachingResultAnnexEntity.setFileName(fileName[i]);  //文件原来的名称
                    teachingResultAnnexEntity.setFileType(fileType[i]);  //文件后缀，如: .pdf .jpg
                    teachingResultAnnexEntity.setResultId(teachingResultEntity.getResultId());  //2、获取竞教学成果id
                    teachingResultAnnexService.save(teachingResultAnnexEntity);   //3、再保存附件
                }
            }
        }

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     * @return
     */
    public String findById() {

        TeachingResultEntity byId = teachingResultService.findById(teachingResultEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = teachingResultService.findByPage(key, new PageBean<TeachingResultEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 查询--部门领导
     */
    public String findByDept() {

        PageBean byPage = teachingResultService.findByDept(teachingResultEntity.getDeptId(), key, new PageBean<TeachingResultEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 老师查询--主持人
     */
    public String findByPerson() {

        PageBean byPerson = teachingResultService.findByPerson(teacherId, key, new PageBean<TeachingResultEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPerson.getRows()).put("count", byPerson.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 部门管理员和领导查询本部门所有教师竞赛
     */
    public String findByMember() {

        PageBean byPage = teachingResultService.findByMember(teachingResultEntity.getMemberCode(), key, new PageBean<TeachingResultEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 列表批量导出
     */
    public String findByExport() {

        /**
         * 1-系统管理员导出整个表 2-部门领导和部门负责人导出本学院全部 3-教师个人导出全部指导 4-根据id导出
         */
        Long deptId =null;   //部门约束
        String[] id = null;     //主键约束
        Integer thisTeacherId = null;  //指导老师约束
        if (exportCode == 1) {
            deptId = null;
            id = null;
            thisTeacherId = null;
        } else if (exportCode == 2) {
            deptId = teachingResultEntity.getDeptId();
            id = null;
            thisTeacherId = null;
        } else if (exportCode == 3) {
            deptId = null;
            id = null;
            thisTeacherId = teacherId;
        } else if (exportCode == 4) {
            deptId = null;
            id = ids.split(",");
            thisTeacherId = null;
        }

        List<TeachingResultEntity> list = teachingResultService.findByExport(deptId, id, thisTeacherId);

        r = R.ok().put("data", list);

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch() {

        String[] id = ids.split(",");

        teachingResultService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    /////////////////////////////////////////

    public TeachingResultEntity getTeachingResultEntity() {
        return teachingResultEntity;
    }
    public void setTeachingResultEntity(TeachingResultEntity teachingResultEntity) {
        this.teachingResultEntity = teachingResultEntity;
    }

    public PageBean<TeachingResultEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<TeachingResultEntity> pageBean) {
        this.pageBean = pageBean;
    }

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getItemPersonCode() {
        return itemPersonCode;
    }

    public void setItemPersonCode(String itemPersonCode) {
        this.itemPersonCode = itemPersonCode;
    }

    public Integer getPrizeLevelId() {
        return prizeLevelId;
    }

    public void setPrizeLevelId(Integer prizeLevelId) {
        this.prizeLevelId = prizeLevelId;
    }

    public Integer getPrizeGradeId() {
        return prizeGradeId;
    }

    public void setPrizeGradeId(Integer prizeGradeId) {
        this.prizeGradeId = prizeGradeId;
    }

    public String getManyFilePath() {
        return manyFilePath;
    }

    public void setManyFilePath(String manyFilePath) {
        this.manyFilePath = manyFilePath;
    }

    public String getManyFileName() {
        return manyFileName;
    }

    public void setManyFileName(String manyFileName) {
        this.manyFileName = manyFileName;
    }

    public String getManyFileType() {
        return manyFileType;
    }

    public void setManyFileType(String manyFileType) {
        this.manyFileType = manyFileType;
    }

    public Integer getExportCode() {
        return exportCode;
    }

    public void setExportCode(Integer exportCode) {
        this.exportCode = exportCode;
    }
}
