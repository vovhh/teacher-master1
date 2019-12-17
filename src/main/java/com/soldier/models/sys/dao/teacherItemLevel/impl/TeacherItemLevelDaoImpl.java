package com.soldier.models.sys.dao.teacherItemLevel.impl;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.dao.teacherItemLevel.TeacherItemLevelDao;
import com.soldier.models.sys.model.TeacherItemLevelEntity;
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
 * @title: AcademicPaperAnnexDaoImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:29
 */
@Component
public class TeacherItemLevelDaoImpl implements TeacherItemLevelDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(TeacherItemLevelEntity teacherItemLevelEntity) {
        hibernateTemplate.save(teacherItemLevelEntity);
    }

    @Override
    public void delete(TeacherItemLevelEntity teacherItemLevelEntity) {
        sessionFactory.getCurrentSession().delete(teacherItemLevelEntity);
    }

    @Override
    public void update(TeacherItemLevelEntity teacherItemLevelEntity) {
        sessionFactory.getCurrentSession().update(teacherItemLevelEntity);
    }

    @Override
    public TeacherItemLevelEntity findById(TeacherItemLevelEntity teacherItemLevelEntity) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherItemLevelEntity.class);

        List list = criteria.add(Restrictions.eq("id", teacherItemLevelEntity.getId())).list();

        session.close();

        return list!=null&&list.size()>0? (TeacherItemLevelEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<TeacherItemLevelEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherItemLevelEntity.class);

        if (key != null && !key.equals("")) {

            criteria.add(
                    Restrictions.or(
//                            Restrictions.or(Restrictions.like("path", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("title", key, MatchMode.ANYWHERE))));
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
    public List<TeacherItemLevelEntity> findAll() {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherItemLevelEntity.class);

        List list = criteria.list();

        session.close();

        return list;
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<TeacherItemLevelEntity> list = new ArrayList<>();

        for (String id : Ids) {
            TeacherItemLevelEntity teacherItemLevelEntity = new TeacherItemLevelEntity();
            teacherItemLevelEntity.setId(Integer.parseInt(id));
            list.add(teacherItemLevelEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
