package com.soldier.models.sys.dao.teacher.impl;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.dao.teacher.TeacherDao;
import com.soldier.models.sys.model.DeptEntity;
import com.soldier.models.sys.model.TeacherEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
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
 * @title: TeacherDao
 * @projectName teacher_files
 * @date 19-7-5上午11:14
 */
@Component
public class TeacherDaoImpl implements TeacherDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(TeacherEntity teacherEntity) {
        sessionFactory.getCurrentSession().save(teacherEntity);
    }

    @Override
    public void delete(TeacherEntity teacherEntity) {
        sessionFactory.getCurrentSession().delete(teacherEntity);
    }

    @Override
    public void update(TeacherEntity teacherEntity) {
        sessionFactory.getCurrentSession().update(teacherEntity);
    }

    @Override
    public void updateTeacherDeptName(DeptEntity deptEntity) {

        String hql = "update com.soldier.models.sys.model.TeacherEntity t set t.deptName=? where t.deptId=?";

        Query query=sessionFactory.getCurrentSession().createQuery(hql);

        query.setString(0, deptEntity.getDeptName());
        query.setLong(1, deptEntity.getDeptId());

        query.executeUpdate();

    }

    @Override
    public TeacherEntity findById(TeacherEntity teacherEntity) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherEntity.class);

        List list = criteria.add(Restrictions.eq("teacherId", teacherEntity.getTeacherId())).list();

        session.close();

        return list!=null&&list.size()>0? (TeacherEntity) list.get(0) :null;
    }

    @Override
    public TeacherEntity findByTeacherCode(TeacherEntity teacherEntity) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherEntity.class);

        List list = criteria.add(Restrictions.eq("teacherCode", teacherEntity.getTeacherCode())).list();

        session.close();

        return list!=null&&list.size()>0? (TeacherEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<TeacherEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherEntity.class);
        criteria.add(Restrictions.eq("canLook", 1));//是否允许除系统管理员之外的角色查看

        if (key != null && !key.equals("")) {
            //搜索
            List list = criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("teacherCode", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("teacherName", key, MatchMode.ANYWHERE))))
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {
            pageBean.setRows(criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(TeacherEntity.class)
                .add(Restrictions.eq("canLook", 1))
                .setProjection(Projections.rowCount()).uniqueResult()));

        session.close();

        return pageBean;
    }

    @Override
    public PageBean findByDept(Long deptId, String key, PageBean<TeacherEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherEntity.class);

        //部门管理员查看部门教师；系统管理员不需要限制，可以查看所有
        if (deptId != null) {
            criteria.add(Restrictions.eq("canLook", 1));//是否允许除系统管理员之外的角色查看
            //TODO 外键查询
            criteria.add(Restrictions.eq("deptId", deptId));
        }

        if (key != null && !key.equals("")) {

            //搜索--实现：where deptId=? and (xxx like ? or xxx like ?...)
            Disjunction dis = Restrictions.disjunction();//多个or可以拼接
            dis.add(Restrictions.like("teacherCode", key, MatchMode.ANYWHERE));
            dis.add(Restrictions.like("teacherName", key, MatchMode.ANYWHERE));
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
    public PageBean findDeptAdmin(String key, PageBean<TeacherEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherEntity.class);
        criteria.add(Restrictions.eq("roleId", 5));//角色为部门管理员

        if (key != null && !key.equals("")) {
            //搜索
            List list = criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("teacherCode", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("teacherName", key, MatchMode.ANYWHERE))))
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
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
    public List<TeacherEntity> findAll() {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherEntity.class);
        criteria.add(Restrictions.eq("canLook", 1));//是否允许除系统管理员之外的角色查看

        List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        session.close();

        return list;
    }

    @Override
    public List<TeacherEntity> findAllByDept(Long deptId) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherEntity.class);
        criteria.add(Restrictions.eq("canLook", 1));//是否允许除系统管理员之外的角色查看
        criteria.add(Restrictions.eq("deptId", deptId));

        List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        session.close();

        return list;
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<TeacherEntity> list = new ArrayList<>();

        for (String id : Ids) {
            TeacherEntity teacherEntity = new TeacherEntity();
            teacherEntity.setTeacherId(Integer.parseInt(id));
            list.add(teacherEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
