package com.dynamic.creator.app.acc.dao;

import com.dynamic.creator.app.acc.model.Acc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/***
 * @author wahab
 * @since v1.0
 */
@Repository
public interface AccRepo extends CrudRepository<Acc, String> {
}
