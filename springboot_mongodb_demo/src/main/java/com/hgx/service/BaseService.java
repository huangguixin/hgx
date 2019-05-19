package com.hgx.service;

import java.util.List;

/**
 * @param <T> the type parameter
 * @author : huangguixin / 2677540604@qq.com
 * @version : 1.0
 */
public interface BaseService <T>{

    /**
     * Find by id t.
     *
     * @param id the id
     * @return the t
     * @author : huangguixin / 2019-03-12
     */
    T findById(Long id);

    /**
     * Find all list.
     *
     * @return the list
     * @author : huangguixin / 2019-03-12
     */
    List<T> findAll();

    /**
     * Save t.
     *
     * @param t the t
     * @return the t
     * @author : huangguixin / 2019-03-12
     */
    T save(T t);

    /**
     * Save all list.
     *
     * @param ts the ts
     * @return the list
     * @author : huangguixin / 2019-03-12
     */
    List<T> saveAll(List<T> ts);

    /**
     * Delete by id.
     *
     * @param id the id
     * @author : huangguixin / 2019-03-12
     */
    void deleteById(Long id);

    /**
     * Delete all.
     *
     * @param ids the ids
     * @author : huangguixin / 2019-03-12
     */
    void deleteAll(List<Long> ids);
}
