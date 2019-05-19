package com.hgx.service.impl;

import com.hgx.repository.BaseRepository;
import com.hgx.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @param <T> the type parameter
 * @author :huangguixin / 2677540604@qq.com
 * @version : 1.0
 */
@Transactional(rollbackFor = RuntimeException.class)
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    /**
     * Get dao base repository.
     *
     * @return the base repository
     * @author : huangguixin / 2019-03-12
     */
    protected abstract BaseRepository getDao();

    /**
     * Find by id t.
     *
     * @param id the id
     * @return the t
     * @author : huangguixin / 2019-03-12
     */
    @Override
    public T findById(Long id) {
        return (T) getDao().findById(id).get();
    }

    /**
     * Find all list.
     *
     * @return the list
     * @author : huangguixin / 2019-03-12
     */
    @Override
    public List<T> findAll() {
        return getDao().findAll();
    }

    /**
     * Save t.
     *
     * @param t the t
     * @return the t
     * @author : huangguixin / 2019-03-12
     */
    @Override
    public T save(T t) {
        return (T) getDao().save(t);
    }

    /**
     * Save all list.
     *
     * @param ts the ts
     * @return the list
     * @author : huangguixin / 2019-03-12
     */
    @Override
    public List<T> saveAll(List<T> ts) {
        return getDao().saveAll(ts);
    }

    /**
     * Delete by id.
     *
     * @param id the id
     * @author : huangguixin / 2019-03-12
     */
    @Override
    public void deleteById(Long id) {
        getDao().deleteById(id);
    }

    /**
     * Delete all.
     *
     * @param ids the ids
     * @author : huangguixin / 2019-03-12
     */
    @Override
    public void deleteAll(List<Long> ids) {
        getDao().deleteAll(getDao().findAllById(ids));
    }
}
