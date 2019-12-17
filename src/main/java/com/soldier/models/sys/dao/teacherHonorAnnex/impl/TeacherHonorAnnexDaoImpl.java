package com.soldier.models.sys.dao.teacherHonorAnnex.impl;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.dao.teacherHonorAnnex.TeacherHonorAnnexDao;
import com.soldier.models.sys.model.TeacherHonorAnnexEntity;
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
 * @title: TeacherHonorAnnexDaoImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:29
 */
@Component
public class TeacherHonorAnnexDaoImpl implements TeacherHonorAnnexDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(TeacherHonorAnnexEntity teacherHonorAnnexEntity) {
        hibernateTemplate.save(teacherHonorAnnexEntity);
    }

    @Override
    public void delete(TeacherHonorAnnexEntity teacherHonorAnnexEntity) {
        sessionFactory.getCurrentSession().delete(teacherHonorAnnexEntity);
    }

    @Override
    public void update(TeacherHonorAnnexEntity teacherHonorAnnexEntity) {
        sessionFactory.getCurrentSession().update(teacherHonorAnnexEntity);
    }

    @Override
    public List<TeacherHonorAnnexEntity> findByHonorId(Long honorId) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherHonorAnnexEntity.class);

        List list = criteria.add(Restrictions.eq("honorId", honorId)).list();

        session.close();

        return list;
    }

    @Override
    public TeacherHonorAnnexEntity findById(TeacherHonorAnnexEntity teacherHonorAnnexEntity) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherHonorAnnexEntity.class);

        List list = criteria.add(Restrictions.eq("annexId", teacherHonorAnnexEntity.getAnnexId())).list();

        session.close();

        return list!=null&&list.size()>0? (TeacherHonorAnnexEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<TeacherHonorAnnexEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherHonorAnnexEntity.class);

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
    public List<TeacherHonorAnnexEntity> findAll() {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherHonorAnnexEntity.class);

        List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        session.close();

        return list;
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<TeacherHonorAnnexEntity> list = new ArrayList<>();

        for (String id : Ids) {
            TeacherHonorAnnexEntity TeacherHonorAnnexEntity = new TeacherHonorAnnexEntity();
            TeacherHonorAnnexEntity.setAnnexId(Long.valueOf(id));
            list.add(TeacherHonorAnnexEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
