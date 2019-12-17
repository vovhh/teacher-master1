package com.soldier.models.sys.dao.teacherHonor.impl;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.dao.teacherHonor.TeacherHonorDao;
import com.soldier.models.sys.model.TeacherHonorEntity;
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
 * @title: TeacherHonorDaoImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:29
 */
@Component
public class TeacherHonorDaoImpl implements TeacherHonorDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(TeacherHonorEntity teacherHonorEntity) {
        hibernateTemplate.save(teacherHonorEntity);
    }

    @Override
    public void delete(TeacherHonorEntity teacherHonorEntity) {
        sessionFactory.getCurrentSession().delete(teacherHonorEntity);
    }

    @Override
    public void update(TeacherHonorEntity teacherHonorEntity) {
        sessionFactory.getCurrentSession().update(teacherHonorEntity);
    }

    @Override
    public TeacherHonorEntity findById(TeacherHonorEntity teacherHonorEntity) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherHonorEntity.class);

        List list = criteria.add(Restrictions.eq("honorId", teacherHonorEntity.getHonorId())).list();

        session.close();

        return list!=null&&list.size()>0? (TeacherHonorEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<TeacherHonorEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherHonorEntity.class);

        if (key != null && !key.equals("")) {

            //搜索
            List list = criteria.add(
                    Restrictions.or(
                            Restrictions.or(Restrictions.like("honorName", key, MatchMode.ANYWHERE))))
                    .setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize() )
                    .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            pageBean.setRows(list);
        } else {
            pageBean.setRows(
                    criteria.setFirstResult((pageBean.getCurrPage() - 1) * pageBean.getPageSize())
                            .setMaxResults(pageBean.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

        pageBean.setTotal(Math.toIntExact((Long) session.createCriteria(TeacherHonorEntity.class).setProjection(Projections.rowCount()).uniqueResult()));


        session.close();

        return pageBean;
    }

    /**
     * 0:根据名称等String类型查询
     * 1：年度
     * 2：根据指导老师所属教研室--itemPerson.unitIds
     * 3：根据获奖等次查询--prizeGrade
     * 4：根据获奖级别查询--prizeLevel
     */
    @Override
    public PageBean findByPerson(Integer teacherId, String key, PageBean<TeacherHonorEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherHonorEntity.class);

        //TODO 外键查询
        criteria.add(Restrictions.eq("itemPerson.teacherId", teacherId));

        if (key != null && !key.equals("")) {

            //搜索--实现：where teacherId=? and (TeacherHonor like ? or TeacherHonor like ?...)
            Disjunction dis = Restrictions.disjunction();//多个or可以拼接

            String[] thisKey = key.split(",");
            if ("0".equals(thisKey[0])) {
                if (thisKey.length == 2) {
                    dis.add(Restrictions.like("honorName", thisKey[1], MatchMode.ANYWHERE));
                    criteria.add(dis);
                }
            } else if ("1".equals(thisKey[0])) {
                if (thisKey.length == 2) {
                    criteria.add(Restrictions.like("honorTime", thisKey[1], MatchMode.ANYWHERE));
                }
            } else if ("2".equals(thisKey[0])) {
                criteria.createAlias("itemPerson", "itemPerson").
                        add(Restrictions.like("itemPerson.unitIds", thisKey[1], MatchMode.ANYWHERE));
            } else if ("3".equals(thisKey[0])) {
                try {
                    Integer newKey = Integer.valueOf(thisKey[1]);
                    criteria.add(Restrictions.eq("prizeGrade.id", newKey));
                } catch (Exception e) {
                    System.out.println("2，key:"+key+",不能转换为数字！");
                }
            } else if ("4".equals(thisKey[0])) {
                try {
                    Integer newKey = Integer.valueOf(thisKey[1]);
                    criteria.add(Restrictions.eq("prizeLevel.id", newKey));
                } catch (Exception e) {
                    System.out.println("3，key:"+key+",不能转换为数字！");
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

    @Override
    public PageBean findByMember(String teacherCode, String key, PageBean<TeacherHonorEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherHonorEntity.class);
        //伪外键查询--首要条件
        criteria.add(Restrictions.like("memberCode", teacherCode,  MatchMode.ANYWHERE));

        if (key != null && !key.equals("")) {

            //搜索--实现：where teacherId=? and (TeacherHonor like ? or TeacherHonor like ?...)
            Disjunction dis = Restrictions.disjunction();//多个or可以拼接

            String[] thisKey = key.split(",");
            if ("0".equals(thisKey[0])) {
                if (thisKey.length == 2) {
                    dis.add(Restrictions.like("honorName", thisKey[1], MatchMode.ANYWHERE));
                    criteria.add(dis);
                }
            } else if ("1".equals(thisKey[0])) {
                if (thisKey.length == 2) {
                    criteria.add(Restrictions.like("honorTime", thisKey[1], MatchMode.ANYWHERE));
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
     * 2：根据指导老师所属教研室--itemPerson.unitIds
     * 3：根据获奖等次查询--prizeGrade
     * 4：根据获奖级别查询--prizeLevel
     */
    @Override
    public PageBean findByDept(Long deptId, String key, PageBean<TeacherHonorEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherHonorEntity.class);

        //伪外键查询--首要条件
        criteria.add(Restrictions.eq("deptId", deptId));

        if (key != null && !key.equals("")) {

            //搜索--实现：where teacherId=? and (TeacherHonor like ? or TeacherHonor like ?...)
            Disjunction dis = Restrictions.disjunction();//多个or可以拼接

            String[] thisKey = key.split(",");
            if ("0".equals(thisKey[0])) {
                if (thisKey.length == 2) {
                    dis.add(Restrictions.like("honorName", thisKey[1], MatchMode.ANYWHERE));
                    criteria.add(dis);
                }
            } else if ("1".equals(thisKey[0])) {
                if (thisKey.length == 2) {
                    criteria.add(Restrictions.like("honorTime", thisKey[1], MatchMode.ANYWHERE));
                }
            } else if ("2".equals(thisKey[0])) {
                criteria.createAlias("itemPerson", "itemPerson").
                        add(Restrictions.like("itemPerson.unitIds", thisKey[1], MatchMode.ANYWHERE));
            } else if ("3".equals(thisKey[0])) {
                try {
                    Integer newKey = Integer.valueOf(thisKey[1]);
                    criteria.add(Restrictions.eq("prizeGrade.id", newKey));
                } catch (Exception e) {
                    System.out.println("2，key:"+key+",不能转换为数字！");
                }
            } else if ("4".equals(thisKey[0])) {
                try {
                    Integer newKey = Integer.valueOf(thisKey[1]);
                    criteria.add(Restrictions.eq("prizeLevel.id", newKey));
                } catch (Exception e) {
                    System.out.println("3，key:"+key+",不能转换为数字！");
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

    @Override
    public List<TeacherHonorEntity> findByExport(Long deptId, String[] Ids, Integer teacherId) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(TeacherHonorEntity.class);

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
            criteria.add(Restrictions.in("honorId", ids));
        }

        //是否添加教师外键约束
        if (teacherId != null) {
            //TODO 外键查询
            criteria.add(Restrictions.eq("itemPerson.teacherId", teacherId));
        }

        return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public void deleteBatch(String[] Ids) {

        List<TeacherHonorEntity> list = new ArrayList<>();

        for (String id : Ids) {
            TeacherHonorEntity TeacherHonorEntity = new TeacherHonorEntity();
            TeacherHonorEntity.setHonorId(Long.valueOf(id));
            list.add(TeacherHonorEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
