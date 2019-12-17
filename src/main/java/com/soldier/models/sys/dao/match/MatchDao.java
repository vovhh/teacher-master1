package com.soldier.models.sys.dao.match;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.model.MatchEntity;

import java.util.List;

/**
 * @author soldier
 * @title: MatchDao
 * @projectName teacher_files
 * @date 19-7-5上午11:14
 */
public interface MatchDao {

    /**
     * 添加
     * @param matchEntity
     */
    public void save(MatchEntity matchEntity);

    /**
     * 删除
     * @param matchEntity
     */
    public void delete(MatchEntity matchEntity);

    /**
     * 修改
     * @param matchEntity
     */
    public void update(MatchEntity matchEntity);

    /**
     * 查询
     * @param matchEntity
     * @return
     */
    public MatchEntity findById(MatchEntity matchEntity);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByPage(String key, PageBean<MatchEntity> pageBean);

    /**
     * 查询
     * @param key
     * @param pageBean
     * @return
     */
    public PageBean findByTeacher(Integer teacherId, String key, PageBean<MatchEntity> pageBean);

    /**
     * 查询
     * @return
     */
    public List<MatchEntity> findAll();
    
    /**
     * 批量删除
     * @param Ids
     */
    public void deleteBatch(String[] Ids);

    /**
     * 判断当前赛事是否存在
     * @param matchEntity
     * @return
     */
    public boolean checkMatch(MatchEntity matchEntity);

}
