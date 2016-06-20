package com.taskmanager.dao;

import com.taskmanager.entity.BaseEntity;
import com.taskmanager.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.MappedSuperclass;
import java.util.List;

/**
 * Created by dona on 15.06.16.
 */
@MappedSuperclass
public abstract class BaseDAO<T extends BaseEntity> {
    protected Class<T> tClass;

    @Autowired
    protected HibernateUtil hibernateUtil;

    public void create(T item){
      hibernateUtil.save(item);
    }
    public  void update(T item){
       hibernateUtil.save(item);
    }
    public  void delete(T item){ hibernateUtil.delete(item);}
    public List<T> getAll(){
      return hibernateUtil.getAll(tClass);
    }
    public T getByID(int id){
        return  hibernateUtil.getByID(tClass,id);
    }
}
