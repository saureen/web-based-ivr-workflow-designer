package com.ivr.dao;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class AbstractSpringDao extends HibernateDaoSupport{

    public AbstractSpringDao() {
    }

    protected void saveOrUpdate(Object obj) {
        getHibernateTemplate().saveOrUpdate(obj);
    }

    protected void delete(Object obj) {
        getHibernateTemplate().delete(obj);
    }

    protected Object find(Class clazz, int id) {
        return getHibernateTemplate().load(clazz, id);
    }

    protected List findByCriteria(DetachedCriteria criteria) {
        return getHibernateTemplate().findByCriteria(criteria);
    }

    protected List findByQuery(String query, Object[] values){
        return getHibernateTemplate().find(query, values);
    }

    protected List findAll(Class clazz) {
        return getHibernateTemplate().find("from " + clazz.getName());
    }
}
