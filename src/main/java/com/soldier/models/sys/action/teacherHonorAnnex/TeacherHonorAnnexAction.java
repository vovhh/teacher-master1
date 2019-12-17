package com.soldier.models.sys.action.teacherHonorAnnex;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.soldier.common.vo.PageBean;
import com.soldier.common.vo.R;
import com.soldier.models.sys.model.TeacherHonorAnnexEntity;
import com.soldier.models.sys.service.teacherHonorAnnex.TeacherHonorAnnexService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author soldier
 * @title: TeacherHonorAnnexAction
 * @projectName teacher_files
 * @date 19-7-22下午7:31
 * @Email： 583403411@qq.com
 * @description:
 */
public class TeacherHonorAnnexAction extends ActionSupport implements ModelDriven<TeacherHonorAnnexEntity> {

    @Autowired
    private TeacherHonorAnnexService teacherHonorAnnexService;
    //日志
    private static Logger logger = Logger.getLogger(TeacherHonorAnnexAction.class);
    //模型驱动
    private TeacherHonorAnnexEntity teacherHonorAnnexEntity = new TeacherHonorAnnexEntity();
    //分页
    private PageBean<TeacherHonorAnnexEntity> pageBean = new PageBean<>();
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

    @Override
    public TeacherHonorAnnexEntity getModel() {
        return teacherHonorAnnexEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

        teacherHonorAnnexService.save(teacherHonorAnnexEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        teacherHonorAnnexService.delete(teacherHonorAnnexEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        teacherHonorAnnexService.update(teacherHonorAnnexEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     *
     * @return
     */
    public String findById() {

        TeacherHonorAnnexEntity byId = teacherHonorAnnexService.findById(teacherHonorAnnexEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = teacherHonorAnnexService.findByPage(key, new PageBean<TeacherHonorAnnexEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 根据教师荣誉查询所有附件
     */
    public String findByHonorId() {

        List<TeacherHonorAnnexEntity> byResultId = teacherHonorAnnexService.findByHonorId(teacherHonorAnnexEntity.getHonorId());

        r = R.ok().put("data", byResultId);

        logger.info("查询附件列表：" + r);

        return SUCCESS;
    }


    /**
     * 批量删除
     */
    public String deleteBatch() {

        String[] id = ids.split(",");

        teacherHonorAnnexService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    /////////////////////////////////////////
    public TeacherHonorAnnexEntity getTeacherHonorAnnexEntity() {
        return teacherHonorAnnexEntity;
    }

    public void setTeacherHonorAnnexEntity(TeacherHonorAnnexEntity teacherHonorAnnexEntity) {
        this.teacherHonorAnnexEntity = teacherHonorAnnexEntity;
    }

    public PageBean<TeacherHonorAnnexEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<TeacherHonorAnnexEntity> pageBean) {
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

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}