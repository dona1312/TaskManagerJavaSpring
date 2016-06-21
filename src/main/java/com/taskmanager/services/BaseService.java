package com.taskmanager.services;

import com.taskmanager.entity.BaseEntity;
import com.taskmanager.entity.User;

import java.util.List;

/**
 * Created by dona on 13.06.16.
 */
public interface BaseService<T extends BaseEntity> {
    public void create(T item);
    public void update(T item);
    public void delete(T item);
    public void save(T item);
    public List<T> getAll();
    public T getByID(int id);
}
