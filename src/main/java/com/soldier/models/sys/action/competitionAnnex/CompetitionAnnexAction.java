package com.soldier.models.sys.action.competitionAnnex;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.soldier.common.vo.PageBean;
import com.soldier.common.vo.R;
import com.soldier.models.sys.action.academicPaper.AcademicPaperAction;
import com.soldier.models.sys.model.CompetitionAnnexEntity;
import com.soldier.models.sys.service.competitionAnnex.CompetitionAnnexService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author soldier
 * @title: CompetitionAnnexAction
 * @projectName teacher_files
 * @date 19-7-22下午7:26
 * @Email： 583403411@qq.com
 * @description:
 */
public class CompetitionAnnexAction extends ActionSupport implements ModelDriven<CompetitionAnnexEntity> {
    @Autowired
    private CompetitionAnnexService competitionAnnexService;
    //日志
    private static Logger logger = Logger.getLogger(AcademicPaperAction.class);
    //模型驱动
    private CompetitionAnnexEntity competitionAnnexEntity = new CompetitionAnnexEntity();
    //分页
    private PageBean<CompetitionAnnexEntity> pageBean = new PageBean<>();
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
    public CompetitionAnnexEntity getModel() {
        return competitionAnnexEntity;
    }

    /////////////////////////////////////////
    /**
     * 添加
     */
    public String save() {

        competitionAnnexService.save(competitionAnnexEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 删除
     */
    public String delete() {

        competitionAnnexService.delete(competitionAnnexEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 修改
     */
    public String update() {

        competitionAnnexService.update(competitionAnnexEntity);

        r = R.ok();

        return SUCCESS;
    }

    /**
     * 查询
     * @return
     */
    public String findById() {

        CompetitionAnnexEntity byId = competitionAnnexService.findById(competitionAnnexEntity);

        r = R.ok().put("data", byId);

        return SUCCESS;
    }

    /**
     * 查询
     */
    public String findByPage() {

        PageBean byPage = competitionAnnexService.findByPage(key, new PageBean<CompetitionAnnexEntity>().setCurrPage(page).setPageSize(limit));

        r = R.ok().put("data", byPage.getRows()).put("count", byPage.getTotal());

        logger.info("查询列表：" + r);

        return SUCCESS;
    }

    /**
     * 根据竞赛查询所有附件
     */
    public String findByCompetitionId() {

        List<CompetitionAnnexEntity> byCompetitionId = competitionAnnexService.findByCompetitionId(competitionAnnexEntity.getCompetitionId());

        r = R.ok().put("data", byCompetitionId);

        logger.info("查询附件列表：" + r);

        return SUCCESS;
    }

    /**
     * 批量删除
     */
    public String deleteBatch() {

        String[] id = ids.split(",");

        competitionAnnexService.deleteBatch(id);

        r = R.ok();

        return SUCCESS;
    }

    /////////////////////////////////////////

    public CompetitionAnnexEntity getCompetitionAnnexEntity() {
        return competitionAnnexEntity;
    }

    public void setCompetitionAnnexEntity(CompetitionAnnexEntity competitionAnnexEntity) {
        this.competitionAnnexEntity = competitionAnnexEntity;
    }

    public PageBean<CompetitionAnnexEntity> getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean<CompetitionAnnexEntity> pageBean) {
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
