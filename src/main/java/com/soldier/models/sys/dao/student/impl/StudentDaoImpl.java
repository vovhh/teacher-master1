package com.soldier.models.sys.dao.student.impl;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.dao.student.StudentDao;
import com.soldier.models.sys.model.StudentEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
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
 * @title: StudentServiceImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:48
 */
@Component
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(StudentEntity studentEntity) {
        sessionFactory.getCurrentSession().save(studentEntity);
    }

    @Override
    public void delete(StudentEntity studentEntity) {
        sessionFactory.getCurrentSession().delete(studentEntity);
    }

    @Override
    public void update(StudentEntity studentEntity) {
        sessionFactory.getCurrentSession().update(studentEntity);
    }

    @Override
    public StudentEntity findById(StudentEntity studentEntity) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(StudentEntity.class);

        List list = criteria.add(Restrictions.eq("studentId", studentEntity.getStudentId())).list();

        session.close();

        return list != null && list.size() > 0 ? (StudentEntity) list.get(0) : null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<StudentEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(StudentEntity.class);

        if (key != null && !key.equals("")) {

            //搜索
            List list = criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("studentCode", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("studentName", key, MatchMode.ANYWHERE))))
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {
            pageBean.setRows(criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(StudentEntity.class).setProjection(Projections.rowCount()).uniqueResult()));

        session.close();

        return pageBean;
    }

    @Override
    public PageBean findByRace(Integer itemId, String key, PageBean<StudentEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(StudentEntity.class);

        // 外键查询--因为是根据竞赛项目id来获取所有成员的
//            CompetitionEntity studentRaceEntity = session.get(CompetitionEntity.class, Integer.valueOf(raceItemId));
//            criteria.add(Restrictions.eq("raceItem", studentRaceEntity));
        criteria.add(Restrictions.eq("itemId", itemId));

        if (key != null && !key.equals("")) {

            //搜索--实现：where teacherId=? and (xxx like ? or xxx like ?...)
            Disjunction dis = Restrictions.disjunction();//多个or可以拼接
            dis.add(Restrictions.like("studentCode", key, MatchMode.ANYWHERE));
            dis.add(Restrictions.like("studentName", key, MatchMode.ANYWHERE));
            criteria.add(dis);
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
    public void deleteBatch(String[] Ids) {

        List<StudentEntity> list = new ArrayList<>();

        for (String id : Ids) {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setStudentId(Integer.parseInt(id));
            list.add(studentEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
