package com.soldier.models.sys.action.teachingMaterial;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.soldier.common.vo.PageBean;
import com.soldier.common.vo.R;
import com.soldier.models.sys.model.*;
import com.soldier.models.sys.service.teacher.TeacherService;
import com.soldier.models.sys.service.teachingMaterial.TeachingMaterialService;
import com.soldier.models.sys.service.teachingMaterialAnnex.TeachingMaterialAnnexService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @author soldier
 * @title: TeachingMaterialAction
 * @projectName teacher_files
 * @date 19-7-5下午5:16
 * @Email： 583403411@qq.com
 * @description:
 */
public class TeachingMaterialAction extends ActionSupport implements ModelDriven<TeachingMaterialEntity> {

    @Autowired
    private TeachingMaterialService teachingMaterialService;
    @Autowired
    private TeachingMaterialAnnexService teachingMaterialAnnexService;
    @Autowired
    private TeacherService teacherService;
    //日志
    private static Logger logger = Logger.getLogger(TeachingMaterialAction.class);
    //模型驱动
    private TeachingMaterialEntity teachingMaterialEntity = new TeachingMaterialEntity();
    //分页
    private PageBean<TeachingMaterialEntity> pageBean = new PageBean<>();
    //返回集
    private R r = new R();
    //搜索值
    private String key;
    //当前页
    private Integer page;
    //大小
    private Integer limit;
    //指导老师id
    private Integer teacherId;
    //批量删除id
    private String ids;
    //竞赛项目附件--可能是多个pdf或图片
    private String manyFilePath;
    //竞赛项目附件原名称
    private String manyFileName;
    //竞赛项目附件类型（后缀）
    private String manyFileType;
    //导出标识 1-系统管理员导出整个表 2-部门领导和部门负责人导出本学院全部 3-教师个人导出全部 4-根据id导出
    private Integer exportCode;

    @Override
    public TeachingMaterialEntity getModel() {
        return teachingMaterialEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

        //指导老师的姓名及部门信息
        TeacherEntity teacher_result = new TeacherEntity();
        teacher_result.setTeacherId(teacherId);
        teacher_result = teacherService.findById(teacher_result);
        teachingMaterialEntity.setDeptId(teacher_result.getDeptId());
//        teachingMaterialEntity.setDeptName(teacher_result.getDeptName());
        teachingMaterialEntity.setTeacher(teacher_result);
        teachingMaterialService.save(teachingMaterialEntity);

        // 附件
        if (manyFileName != null && !"".equals(manyFileName)) {
            TeachingMaterialAnnexEntity teachingMaterialAnnexEntity;
            String[] filePath = manyFilePath.split(",");
            String[] fileName = manyFileName.split(",");
            String[] fileType = manyFileType.split(",");
            for (int i = 0; i < fileName.length; i++) {
                teachingMaterialAnnexEntity = new TeachingMaterialAnnexEntity();
                teachingMaterialAnnexEntity.setFilePath(filePath[i]);  //文件现在的路径
                teachingMaterialAnnexEntity.setFileName(fileName[i]);  //文件原来的名称
                teachingMaterialAnnexEntity.setFileType(fileType[i]);  //文件后缀，如: .pdf .jpg
                teachingMaterialAnnexEntity.setMaterialId(teachingMaterialEntity.getMaterialId());  //2、获取教材id
                teachingMaterialAnnexService.save(teachingMaterialAnnexEntity);   //3、再保存附件
            }
        }

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        teachingMaterialService.delete(teachingMaterialEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        //指导老师的姓名及部门信息
        TeacherEntity teacher_result = new TeacherEntity();
        teacher_result.setTeacherId(teacherId);
        teacher_result = teacherService.findById(teacher_result);
        teachingMaterialEntity.setDeptId(teacher_result.getDeptId());
//        teachingMaterialEntity.setDeptName(teacher_result.getDeptName());
        teachingMaterialEntity.setTeacher(teacher_result);
        teachingMaterialService.update(teachingMaterialEntity);

        //用户重新上传的附件
        if (!"haveFile".equals(manyFilePath)) {  //定义规则，如果当前项目有附件，它就会被赋值，防止xx-add.html验证规则出错
            if (manyFileName != null && !"".equals(manyFileName)) {
                TeachingMaterialAnnexEntity teachingMaterialAnnexEntity;
                String[] filePath = manyFilePath.split(",");
                String[] fileName = manyFileName.split(",");
                String[] fileType = manyFileType.split(",");
                for (int i = 0; i < fileName.length; i++) {
                    teachingMaterialAnnexEntity = new TeachingMaterialAnnexEntity();
                    teachingMaterialAnnexEntity.setFilePath(filePath[i]);  //文件现在的路径
                    teachingMaterialAnnexEntity.setFileName(fileName[i]);  //文件原来的名称
                    teachingMaterialAnnexEntity.setFileType(fileType[i]);  //文件后缀，如: .pdf .jpg
                    teachingMaterialAnnexEntity.setMaterialId(teachingMaterialEntity.getMaterialId());  //2、获取竞赛项目id
                    teachingMaterialAnnexService.save(teachingMaterialAnnexEntity);   //3、再保存附件
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

        TeachingMaterialEntity byId = teachingMaterialService.findById(teachingMaterialEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = teachingMaterialService.findByPage(key, new PageBean<TeachingMaterialEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 主编查询指导的所有比赛
     */
    public String findByEditor() {

        PageBean byEditor = teachingMaterialService.findByEditor(teacherId, key, new PageBean<TeachingMaterialEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byEditor.getRows()).put("count", byEditor.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 部门管理员和领导查询本部门所有教师竞赛
     */
    public String findByDept() {

        PageBean byPage = teachingMaterialService.findByDept(teachingMaterialEntity.getDeptId(), key, new PageBean<TeachingMaterialEntity>().setCurrPage(page).setPageSize(limit));

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
            deptId = teachingMaterialEntity.getDeptId();
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

        List<TeachingMaterialEntity> list = teachingMaterialService.findByExport(deptId, id, thisTeacherId);

        r = R.ok().put("data", list);

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch() {

        String[] id = ids.split(",");

        teachingMaterialService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    /////////////////////////////////////////

    public TeachingMaterialEntity getTeachingMaterialEntity() {
        return teachingMaterialEntity;
    }
    public void setTeachingMaterialEntity(TeachingMaterialEntity teachingMaterialEntity) {
        this.teachingMaterialEntity = teachingMaterialEntity;
    }

    public PageBean<TeachingMaterialEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<TeachingMaterialEntity> pageBean) {
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
