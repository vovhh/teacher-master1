package com.soldier.models.sys.dao.teachingResultAnnex.impl;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.dao.teachingResultAnnex.TeachingResultAnnexDao;
import com.soldier.models.sys.model.TeachingResultAnnexEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author soldier
 * @title: TeachingResultAnnexDaoImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:29
 */
@Component
public class TeachingResultAnnexDaoImpl implements TeachingResultAnnexDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(TeachingResultAnnexEntity teachingResultAnnexEntity) {
        hibernateTemplate.save(teachingResultAnnexEntity);
    }

    @Override
    public void delete(TeachingResultAnnexEntity teachingResultAnnexEntity) {
        sessionFactory.getCurrentSession().delete(teachingResultAnnexEntity);
    }

    @Override
    public void update(TeachingResultAnnexEntity teachingResultAnnexEntity) {
        sessionFactory.getCurrentSession().update(teachingResultAnnexEntity);
    }

    @Override
    public List<TeachingResultAnnexEntity> findByResultId(Long resultId) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeachingResultAnnexEntity.class);

        List list = criteria.add(Restrictions.eq("resultId", resultId)).list();

        session.close();

        return list;
    }

    @Override
    public TeachingResultAnnexEntity findById(TeachingResultAnnexEntity teachingResultAnnexEntity) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeachingResultAnnexEntity.class);

        List list = criteria.add(Restrictions.eq("annexId", teachingResultAnnexEntity.getAnnexId())).list();

        session.close();

        return list!=null&&list.size()>0? (TeachingResultAnnexEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<TeachingResultAnnexEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeachingResultAnnexEntity.class);

        if (key != null && !key.equals("")) {

            criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("path", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("fileName", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("fileType", key, MatchMode.ANYWHERE))));
        }

        // 获取记录数
        pageBean.setTotal(Math.toIntExact((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()));

        // 获取结果--将 Projection 设为空，再进行正常分页
        criteria.setProjection(null);
        pageBean.setRows(criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());

        session.close();

        return pageBean;
    }

    @Override
    public List<TeachingResultAnnexEntity> findAll() {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeachingResultAnnexEntity.class);

        List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        session.close();

        return list;
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<TeachingResultAnnexEntity> list = new ArrayList<>();

        for (String id : Ids) {
            TeachingResultAnnexEntity TeachingResultAnnexEntity = new TeachingResultAnnexEntity();
            TeachingResultAnnexEntity.setAnnexId(Long.valueOf(id));
            list.add(TeachingResultAnnexEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
