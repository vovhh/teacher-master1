package com.soldier.models.sys.dao.teacherItemCategory.impl;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.dao.teacherItemCategory.TeacherItemCategoryDao;
import com.soldier.models.sys.model.TeacherItemCategoryEntity;
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
public class TeacherItemCategoryDaoImpl implements TeacherItemCategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(TeacherItemCategoryEntity teacherItemCategoryEntity) {
        hibernateTemplate.save(teacherItemCategoryEntity);
    }

    @Override
    public void delete(TeacherItemCategoryEntity teacherItemCategoryEntity) {
        sessionFactory.getCurrentSession().delete(teacherItemCategoryEntity);
    }

    @Override
    public void update(TeacherItemCategoryEntity teacherItemCategoryEntity) {
        sessionFactory.getCurrentSession().update(teacherItemCategoryEntity);
    }

    @Override
    public TeacherItemCategoryEntity findById(TeacherItemCategoryEntity teacherItemCategoryEntity) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherItemCategoryEntity.class);

        List list = criteria.add(Restrictions.eq("id", teacherItemCategoryEntity.getId())).list();

        session.close();

        return list!=null&&list.size()>0? (TeacherItemCategoryEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<TeacherItemCategoryEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherItemCategoryEntity.class);

        if (key != null && !key.equals("")) {

            criteria.add(Restrictions.or(
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
    public List<TeacherItemCategoryEntity> findAll() {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherItemCategoryEntity.class);

        List list = criteria.list();

        session.close();

        return list;
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<TeacherItemCategoryEntity> list = new ArrayList<>();

        for (String id : Ids) {
            TeacherItemCategoryEntity teacherItemCategoryEntity = new TeacherItemCategoryEntity();
            teacherItemCategoryEntity.setId(Integer.parseInt(id));
            list.add(teacherItemCategoryEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
