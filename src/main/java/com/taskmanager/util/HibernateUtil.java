package com.taskmanager.util;

import com.taskmanager.entity.BaseEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dona on 13.06.16.
 */
@Component
@Transactional
public class HibernateUtil {

    @Autowired
    private SessionFactory factory;

    public <T> List<T> getAll(Class<T> entityClass) {
        try {
            List<T> items=new ArrayList<T>();
            items= factory.getCurrentSession().createQuery(" FROM "+entityClass.getName()).list();
            return  items;
        }catch (Exception ex){

        }
        return  null;
    }

    public <T extends BaseEntity> void save(T item) {
        if (item.getId() != 0) {
            update(item);
        } else {
            add(item);
        }
    }

    protected <T extends BaseEntity> void add(T item) {
        factory.getCurrentSession().save(item);
    }

    protected <T extends BaseEntity> void update(T item) {
        factory.getCurrentSession().update(item);
    }

    public <T extends BaseEntity> void delete(T item) {
        factory.getCurrentSession().delete(item);
    }

    public <T extends BaseEntity> T getByID(Class<T> tClass, int id) {

        String sql = "FROM " + tClass.getName() + " E WHERE E.id = " + id;

        return (T)factory.getCurrentSession().createQuery(sql).uniqueResult();
    }
}
