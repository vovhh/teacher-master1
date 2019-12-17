package com.soldier.models.sys.action.teachingResultAnnex;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.soldier.common.vo.PageBean;
import com.soldier.common.vo.R;
import com.soldier.models.sys.model.TeachingResultAnnexEntity;
import com.soldier.models.sys.service.teachingResultAnnex.TeachingResultAnnexService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author soldier
 * @title: TeachingResultAnnexAction
 * @projectName teacher_files
 * @date 19-7-22下午7:31
 * @Email： 583403411@qq.com
 * @description:
 */
public class TeachingResultAnnexAction extends ActionSupport implements ModelDriven<TeachingResultAnnexEntity> {

    @Autowired
    private TeachingResultAnnexService teachingResultAnnexService;
    //日志
    private static Logger logger = Logger.getLogger(TeachingResultAnnexAction.class);
    //模型驱动
    private TeachingResultAnnexEntity teachingResultAnnexEntity = new TeachingResultAnnexEntity();
    //分页
    private PageBean<TeachingResultAnnexEntity> pageBean = new PageBean<>();
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
    public TeachingResultAnnexEntity getModel() {
        return teachingResultAnnexEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

        teachingResultAnnexService.save(teachingResultAnnexEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        teachingResultAnnexService.delete(teachingResultAnnexEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        teachingResultAnnexService.update(teachingResultAnnexEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     *
     * @return
     */
    public String findById() {

        TeachingResultAnnexEntity byId = teachingResultAnnexService.findById(teachingResultAnnexEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = teachingResultAnnexService.findByPage(key, new PageBean<TeachingResultAnnexEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 根据教学成果查询所有附件
     */
    public String findByResultId() {

        List<TeachingResultAnnexEntity> byResultId = teachingResultAnnexService.findByResultId(teachingResultAnnexEntity.getResultId());

        r = R.ok().put("data", byResultId);

        logger.info("查询附件列表：" + r);

        return SUCCESS;
    }


    /**
     * 批量删除
     */
    public String deleteBatch() {

        String[] id = ids.split(",");

        teachingResultAnnexService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    /////////////////////////////////////////
    public TeachingResultAnnexEntity getTeachingResultAnnexEntity() {
        return teachingResultAnnexEntity;
    }

    public void setTeachingResultAnnexEntity(TeachingResultAnnexEntity teachingResultAnnexEntity) {
        this.teachingResultAnnexEntity = teachingResultAnnexEntity;
    }

    public PageBean<TeachingResultAnnexEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<TeachingResultAnnexEntity> pageBean) {
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