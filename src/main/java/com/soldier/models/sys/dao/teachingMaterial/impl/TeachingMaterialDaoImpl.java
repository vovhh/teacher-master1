package com.soldier.models.sys.dao.teachingMaterial.impl;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.dao.teachingMaterial.TeachingMaterialDao;
import com.soldier.models.sys.model.TeachingMaterialEntity;
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
 * @title: TeachingMaterialDaoImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:29
 */
@Component
public class TeachingMaterialDaoImpl implements TeachingMaterialDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(TeachingMaterialEntity teachingMaterialEntity) {
        hibernateTemplate.save(teachingMaterialEntity);
    }

    @Override
    public void delete(TeachingMaterialEntity teachingMaterialEntity) {
        sessionFactory.getCurrentSession().delete(teachingMaterialEntity);
    }

    @Override
    public void update(TeachingMaterialEntity teachingMaterialEntity) {
        sessionFactory.getCurrentSession().update(teachingMaterialEntity);
    }

    @Override
    public TeachingMaterialEntity findById(TeachingMaterialEntity teachingMaterialEntity) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeachingMaterialEntity.class);

        List list = criteria.add(Restrictions.eq("materialId", teachingMaterialEntity.getMaterialId())).list();

        session.close();

        return list!=null&&list.size()>0? (TeachingMaterialEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<TeachingMaterialEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeachingMaterialEntity.class);

        if (key != null && !key.equals("")) {

            //搜索
            List list = criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("materialName", key, MatchMode.ANYWHERE)),
                            Restrictions.or(Restrictions.like("press", key, MatchMode.ANYWHERE))))
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {
            pageBean.setRows(
                    criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                            .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(TeachingMaterialEntity.class).setProjection(Projections.rowCount()).uniqueResult()));


        session.close();

        return pageBean;
    }

    /**
     * 0:根据名称等String类型查询
     * 1：年度
     */
    @Override
    public PageBean findByEditor(Integer teacherId, String key, PageBean<TeachingMaterialEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeachingMaterialEntity.class);

        //TODO 外键查询
        criteria.createAlias("teacher", "teacher").
                add(Restrictions.eq("teacher.teacherId", teacherId));

        if (key != null && !key.equals("")) {

            //搜索--实现：where teacherId=? and (TeachingMaterial like ? or TeachingMaterial like ?...)
            Disjunction dis = Restrictions.disjunction();//多个or可以拼接

            String[] thisKey = key.split(",");
            if ("0".equals(thisKey[0])) {
                if (thisKey.length == 2) {
                    dis.add(Restrictions.like("materialName", thisKey[1], MatchMode.ANYWHERE));
                    dis.add(Restrictions.like("press", thisKey[1], MatchMode.ANYWHERE));
                    criteria.add(dis);
                }
            } else if ("1".equals(thisKey[0])) {
                if (thisKey.length == 2) {
                    criteria.add(Restrictions.like("publishTime", thisKey[1], MatchMode.ANYWHERE));
                }
            }
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


    /**
     * 0:根据名称等String类型查询
     * 1：年度
     * 2：根据主编id查询
     * 3：根据主编所属教研室--teacher.unitIds
     */
    @Override
    public PageBean findByDept(Long deptId, String key, PageBean<TeachingMaterialEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeachingMaterialEntity.class);

        //伪外键查询--首要条件
        criteria.add(Restrictions.eq("deptId", deptId));

        if (key != null && !key.equals("")) {

            //搜索--实现：where teacherId=? and (TeachingMaterial like ? or TeachingMaterial like ?...)
            Disjunction dis = Restrictions.disjunction();//多个or可以拼接

            String[] thisKey = key.split(",");
            if ("0".equals(thisKey[0])) {
                if (thisKey.length == 2) {
                    dis.add(Restrictions.like("materialName", thisKey[1], MatchMode.ANYWHERE));
                    dis.add(Restrictions.like("press", thisKey[1], MatchMode.ANYWHERE));
                    criteria.add(dis);
                }
            } else if ("1".equals(thisKey[0])) {
                if (thisKey.length == 2) {
                    criteria.add(Restrictions.like("publishTime", thisKey[1], MatchMode.ANYWHERE));
                }
            } else if ("2".equals(thisKey[0])) {
                try {
                    Integer newKey = Integer.valueOf(thisKey[1]);
                    criteria.add(Restrictions.eq("teacher.teacherId", newKey));
                } catch (Exception e) {
                    System.out.println("2，key:"+key+",不能转换为数字！");
                }
            } else if ("3".equals(thisKey[0])) {
                criteria.createAlias("teacher", "teacher").
                        add(Restrictions.like("teacher.unitIds", thisKey[1], MatchMode.ANYWHERE));
            }
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
    public List<TeachingMaterialEntity> findByExport(Long deptId, String[] Ids, Integer teacherId) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeachingMaterialEntity.class);

        //是否添加部门约束。系统管理员可以导出全部
        if (deptId != null) {
            criteria.add(Restrictions.eq("deptId", deptId));
        }

        //是否根据id导出
        if (Ids!=null && Ids.length!=0) {
            //转Object数组
            Object[] ids = new Object[Ids.length];
            for(int i=0;i<Ids.length;i++){
                ids[i] = Long.valueOf(Ids[i]);
            }
            criteria.add(Restrictions.in("materialId", ids));
        }

        //是否添加教师外键约束
        if (teacherId != null) {
            //TODO 外键查询
            criteria.createAlias("teacher", "teacher").
                    add(Restrictions.eq("teacher.teacherId", teacherId));
        }

        return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<TeachingMaterialEntity> list = new ArrayList<>();

        for (String id : Ids) {
            TeachingMaterialEntity TeachingMaterialEntity = new TeachingMaterialEntity();
            TeachingMaterialEntity.setMaterialId(Long.valueOf(id));
            list.add(TeachingMaterialEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
