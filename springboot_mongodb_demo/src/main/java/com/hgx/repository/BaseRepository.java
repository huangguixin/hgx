package com.hgx.repository;

import com.hgx.entity.BaseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author :huangguixin / 2677540604@qq.com
 * @version : 1.0
 */
@NoRepositoryBean
public interface BaseRepository<T> extends MongoRepository<T,Long> {
}
