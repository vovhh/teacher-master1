package com.soldier.models.sys.dao.dept.impl;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.dao.dept.DeptDao;
import com.soldier.models.sys.model.DeptEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author soldier
 * @title: DeptDaoImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:39
 */
@Component
public class DeptDaoImpl implements DeptDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(DeptEntity deptEntity) {
        sessionFactory.getCurrentSession().save(deptEntity);
    }

    @Override
    public void delete(DeptEntity deptEntity) {
        sessionFactory.getCurrentSession().delete(deptEntity);
    }

    @Override
    public void update(DeptEntity deptEntity) {
        sessionFactory.getCurrentSession().update(deptEntity);
    }

    @Override
    public DeptEntity findById(DeptEntity deptEntity) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(DeptEntity.class);

        List list = criteria.add(Restrictions.eq("deptId", deptEntity.getDeptId())).list();

        session.close();

        return list!=null&&list.size()>0? (DeptEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<DeptEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(DeptEntity.class);

        if (key != null && !key.equals("")) {
            //搜索
            List list = criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("deptName", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("deptPerson", key, MatchMode.ANYWHERE))))
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {
            pageBean.setRows(criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(DeptEntity.class).setProjection(Projections.rowCount()).uniqueResult()));


        session.close();

        return pageBean;
    }

    @Override
    public PageBean findByDept(Long deptId, String key, PageBean<DeptEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(DeptEntity.class);

        //TODO 外键查询
        criteria.createAlias("parent", "parent").add(Restrictions.eq("parent.deptId", deptId));

        if (key != null && !key.equals("")) {
            //搜索--实现：where deptId=? and (xxx like ? or xxx like ?...)
            Disjunction dis = Restrictions.disjunction();//多个or可以拼接
            dis.add(Restrictions.or(Restrictions.like("deptName", key, MatchMode.ANYWHERE)));
            dis.add(Restrictions.or(Restrictions.like("deptPerson", key, MatchMode.ANYWHERE)));
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
    public List<DeptEntity> findAll() {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(DeptEntity.class);

        List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        session.close();

        return list;
    }

    @Override
    public List<DeptEntity> findAllParent() {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(DeptEntity.class);

        // 外键自关联条件，parent为空，即查询所有一级部门
//        criteria.createAlias("parent", "parent").add(Restrictions.isNotNull("parent"));
        criteria.add(Restrictions.isNull("parent"));

        List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        session.close();

        return list;
//        return (List<DeptEntity>) hibernateTemplate.find("from com.soldier.models.sys.model.DeptEntity where parent_dept_id is null ");
    }

    @Override
    public List<DeptEntity> findSubordinate(Long parentId) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(DeptEntity.class);

        //TODO 外键查询
        criteria.createAlias("parent", "parent").add(Restrictions.eq("parent.deptId", parentId));

        List list = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        session.close();

        return list;
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<DeptEntity> list = new ArrayList<>();

        for (String id : Ids) {
            DeptEntity deptEntity = new DeptEntity();
            deptEntity.setDeptId(Long.valueOf(id));
            list.add(deptEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
