package com.soldier.models.sys.action.teachingMaterialAnnex;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.soldier.common.vo.PageBean;
import com.soldier.common.vo.R;
import com.soldier.models.sys.model.TeachingMaterialAnnexEntity;
import com.soldier.models.sys.service.teachingMaterialAnnex.TeachingMaterialAnnexService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author soldier
 * @title: TeachingMaterialAnnexAction
 * @projectName teacher_files
 * @date 19-7-22下午7:31
 * @Email： 583403411@qq.com
 * @description:
 */
public class TeachingMaterialAnnexAction extends ActionSupport implements ModelDriven<TeachingMaterialAnnexEntity> {

    @Autowired
    private TeachingMaterialAnnexService teachingMaterialAnnexService;
    //日志
    private static Logger logger = Logger.getLogger(TeachingMaterialAnnexAction.class);
    //模型驱动
    private TeachingMaterialAnnexEntity teachingMaterialAnnexEntity = new TeachingMaterialAnnexEntity();
    //分页
    private PageBean<TeachingMaterialAnnexEntity> pageBean = new PageBean<>();
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
    public TeachingMaterialAnnexEntity getModel() {
        return teachingMaterialAnnexEntity;
    }

    /////////////////////////////////////////

    /**
     * 添加
     */
    public String save() {

        teachingMaterialAnnexService.save(teachingMaterialAnnexEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        teachingMaterialAnnexService.delete(teachingMaterialAnnexEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        teachingMaterialAnnexService.update(teachingMaterialAnnexEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     *
     * @return
     */
    public String findById() {

        TeachingMaterialAnnexEntity byId = teachingMaterialAnnexService.findById(teachingMaterialAnnexEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = teachingMaterialAnnexService.findByPage(key, new PageBean<TeachingMaterialAnnexEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 根据教材id查询所有附件
     */
    public String findByMaterialId() {

        List<TeachingMaterialAnnexEntity> byMaterialId = teachingMaterialAnnexService.findByMaterialId(teachingMaterialAnnexEntity.getMaterialId());

        r = R.ok().put("data", byMaterialId);

        logger.info("查询附件列表：" + r);

        return SUCCESS;
    }


    /**
     * 批量删除
     */
    public String deleteBatch() {

        String[] id = ids.split(",");

        teachingMaterialAnnexService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    /////////////////////////////////////////
    public TeachingMaterialAnnexEntity getTeachingMaterialAnnexEntity() {
        return teachingMaterialAnnexEntity;
    }

    public void setTeachingMaterialAnnexEntity(TeachingMaterialAnnexEntity teachingMaterialAnnexEntity) {
        this.teachingMaterialAnnexEntity = teachingMaterialAnnexEntity;
    }

    public PageBean<TeachingMaterialAnnexEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<TeachingMaterialAnnexEntity> pageBean) {
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