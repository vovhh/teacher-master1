package com.soldier.models.sys.dao.sysmenu.impl;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.dao.sysmenu.MenuDao;
import com.soldier.models.sys.model.SysMenuEntity;
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
 * @title: MenuDaoImpl
 * @projectName teacher_files
 * @date 19-7-5上午10:57
 */
@Component
public class MenuDaoImpl implements MenuDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public List<SysMenuEntity> getMenuByRoleType(Integer roleId) {

        System.out.println("角色类型："+roleId);

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(SysMenuEntity.class);

        //TODO 外键查询
        criteria.createAlias("roleSet", "role");
        Disjunction dis = Restrictions.disjunction();
        dis.add(Restrictions.eq("role.roleId", roleId));

        List list = criteria.add(dis).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        session.close();

        return list;
    }

    @Override
    public void save(SysMenuEntity sysMenuEntity) {
        sessionFactory.getCurrentSession().save(sysMenuEntity);
    }

    @Override
    public void delete(SysMenuEntity sysMenuEntity) {
        sessionFactory.getCurrentSession().delete(sysMenuEntity);
    }

    @Override
    public void update(SysMenuEntity sysMenuEntity) {
        sessionFactory.getCurrentSession().update(sysMenuEntity);
    }

    @Override
    public SysMenuEntity findById(SysMenuEntity sysMenuEntity) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(SysMenuEntity.class);

        List list = criteria.add(Restrictions.eq("menuId", sysMenuEntity.getMenuId())).list();

        session.close();

        return list != null && list.size() > 0 ? (SysMenuEntity) list.get(0) : null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<SysMenuEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(SysMenuEntity.class);

        if (key != null && !key.equals("")) {

            criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("icon", key, MatchMode.ANYWHERE)),
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
    public List<SysMenuEntity> findAll() {
        return (List<SysMenuEntity>) hibernateTemplate.find("from com.soldier.models.sys.model.SysMenuEntity");
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<SysMenuEntity> list = new ArrayList<>();

        for (String id : Ids) {
            SysMenuEntity sysMenuEntity = new SysMenuEntity();
            sysMenuEntity.setMenuId(Integer.parseInt(id));
            list.add(sysMenuEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
