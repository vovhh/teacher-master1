package com.soldier.models.sys.dao.sysmenuChildren.impl;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.dao.sysmenuChildren.MenuChildrenDao;
import com.soldier.models.sys.model.SysMenuChildrenEntity;
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
 * @title: MenuDaoImpl
 * @projectName teacher_files
 * @date 19-7-5上午10:57
 */
@Component
public class MenuChildrenDaoImpl implements MenuChildrenDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(SysMenuChildrenEntity sysMenuChildrenEntity) {
        sessionFactory.getCurrentSession().save(sysMenuChildrenEntity);
    }

    @Override
    public void delete(SysMenuChildrenEntity sysMenuChildrenEntity) {
        sessionFactory.getCurrentSession().delete(sysMenuChildrenEntity);
    }

    @Override
    public void update(SysMenuChildrenEntity sysMenuChildrenEntity) {
        sessionFactory.getCurrentSession().update(sysMenuChildrenEntity);
    }

    @Override
    public SysMenuChildrenEntity findById(SysMenuChildrenEntity sysMenuChildrenEntity) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(SysMenuChildrenEntity.class);

        List list = criteria.add(Restrictions.eq("id", sysMenuChildrenEntity.getId())).list();

        session.close();

        return list != null && list.size() > 0 ? (SysMenuChildrenEntity) list.get(0) : null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<SysMenuChildrenEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(SysMenuChildrenEntity.class);

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
    public List<SysMenuChildrenEntity> findAll() {
        return (List<SysMenuChildrenEntity>) hibernateTemplate.find("from com.soldier.models.sys.model.SysMenuChildrenEntity");
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<SysMenuChildrenEntity> list = new ArrayList<>();

        for (String id : Ids) {
            SysMenuChildrenEntity sysMenuChildrenEntity = new SysMenuChildrenEntity();
            sysMenuChildrenEntity.setId(Integer.parseInt(id));
            list.add(sysMenuChildrenEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
