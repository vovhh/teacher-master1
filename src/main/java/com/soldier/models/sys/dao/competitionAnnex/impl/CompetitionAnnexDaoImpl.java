package com.soldier.models.sys.dao.competitionAnnex.impl;

import com.soldier.common.vo.PageBean;
import com.soldier.models.sys.dao.competitionAnnex.CompetitionAnnexDao;
import com.soldier.models.sys.model.CompetitionAnnexEntity;
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
 * @title: CompetitionAnnexDaoImpl
 * @projectName teacher_files
 * @date 19-7-5上午11:29
 */
@Component
public class CompetitionAnnexDaoImpl implements CompetitionAnnexDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(CompetitionAnnexEntity competitionAnnexEntity) {
        hibernateTemplate.save(competitionAnnexEntity);
    }

    @Override
    public void delete(CompetitionAnnexEntity competitionAnnexEntity) {
        sessionFactory.getCurrentSession().delete(competitionAnnexEntity);
    }

    @Override
    public void update(CompetitionAnnexEntity competitionAnnexEntity) {
        sessionFactory.getCurrentSession().update(competitionAnnexEntity);
    }

    @Override
    public List<CompetitionAnnexEntity> findByCompetitionId(Integer competitionId) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(CompetitionAnnexEntity.class);

        List list = criteria.add(Restrictions.eq("competitionId", competitionId)).list();

        session.close();

        return list;
    }

    @Override
    public CompetitionAnnexEntity findById(CompetitionAnnexEntity competitionAnnexEntity) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(CompetitionAnnexEntity.class);

        List list = criteria.add(Restrictions.eq("competitionAnnexId", competitionAnnexEntity.getCompetitionAnnexId())).list();

        session.close();

        return list!=null&&list.size()>0? (CompetitionAnnexEntity) list.get(0) :null;
    }

    @Override
    public PageBean findByPage(String key, PageBean<CompetitionAnnexEntity> pageBean) {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(CompetitionAnnexEntity.class);

        if (key != null && !key.equals("")) {

            criteria.add(Restrictions.or(
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
    public void deleteBatch(String[] Ids) {

        List<CompetitionAnnexEntity> list = new ArrayList<>();

        for (String id : Ids) {
            CompetitionAnnexEntity competitionAnnexEntity = new CompetitionAnnexEntity();
            competitionAnnexEntity.setCompetitionAnnexId(Integer.parseInt(id));
            list.add(competitionAnnexEntity);
        }

        hibernateTemplate.deleteAll(list);
    }
}
