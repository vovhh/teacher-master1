package com.soldier.models.sys.dao.teachingMaterialAnnex.impl;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.dao.teachingMaterialAnnex.TeachingMaterialAnnexDao;
import com.soldier.models.sys.model.TeachingMaterialAnnexEntity;
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
 * @title: TeachingMaterialAnnexDaoImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:29
 */
@Component
public class TeachingMaterialAnnexDaoImpl implements TeachingMaterialAnnexDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(TeachingMaterialAnnexEntity teachingMaterialAnnexEntity) {
        hibernateTemplate.save(teachingMaterialAnnexEntity);
    }

    @Override
    public void delete(TeachingMaterialAnnexEntity TeachingMaterialAnnexEntity) {
        sessionFactory.getCurrentSession().delete(TeachingMaterialAnnexEntity);
    }

    @Override
    public void update(TeachingMaterialAnnexEntity teachingMaterialAnnexEntity) {
        sessionFactory.getCurrentSession().update(teachingMaterialAnnexEntity);
    }

    @Override
    public List<TeachingMaterialAnnexEntity> findByMaterialId(Long materialId) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeachingMaterialAnnexEntity.class);

        List list = criteria.add(Restrictions.eq("materialId", materialId)).list();

        session.close();

        return list;
    }

    @Override
    public TeachingMaterialAnnexEntity findById(TeachingMaterialAnnexEntity teachingMaterialAnnexEntity) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeachingMaterialAnnexEntity.class);

        List list = criteria.add(Restrictions.eq("annexId", teachingMaterialAnnexEntity.getAnnexId())).list();

        session.close();

        return list!=null&&list.size()>0? (TeachingMaterialAnnexEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<TeachingMaterialAnnexEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeachingMaterialAnnexEntity.class);

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
    public List<TeachingMaterialAnnexEntity> findAll() {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeachingMaterialAnnexEntity.class);

        List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        session.close();

        return list;
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<TeachingMaterialAnnexEntity> list = new ArrayList<>();

        for (String id : Ids) {
            TeachingMaterialAnnexEntity TeachingMaterialAnnexEntity = new TeachingMaterialAnnexEntity();
            TeachingMaterialAnnexEntity.setAnnexId(Long.valueOf(id));
            list.add(TeachingMaterialAnnexEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
