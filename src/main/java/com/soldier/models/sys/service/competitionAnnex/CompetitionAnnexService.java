package com.soldier.models.sys.service.competitionAnnex;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.model.CompetitionAnnexEntity;

import java.util.List;

/**
 * @author soldier
 * @title: CompetitionAnnexService
 * @projectName teacher_files
 * @date 19-7-22下午7:15
 * @Email： 583403411@qq.com
 * @description:
 */
public interface CompetitionAnnexService {

    /**
     * 添加
     * @param competitionAnnexEntity
     */
    public void save(CompetitionAnnexEntity competitionAnnexEntity);

    /**
     * 删除
     * @param competitionAnnexEntity
     */
    public void delete(CompetitionAnnexEntity competitionAnnexEntity);

    /**
     * 修改
     * @param competitionAnnexEntity
     */
    public void update(CompetitionAnnexEntity competitionAnnexEntity);

    /**
     * 根据竞赛查询所有获奖证书
     * @param competitionId
     */
    public List<CompetitionAnnexEntity> findByCompetitionId(Integer competitionId);

    /**
     * 查询
     * @param competitionAnnexEntity
     * @return
     */
    public CompetitionAnnexEntity findById(CompetitionAnnexEntity competitionAnnexEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<CompetitionAnnexEntity> pageBean);

    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);
}
