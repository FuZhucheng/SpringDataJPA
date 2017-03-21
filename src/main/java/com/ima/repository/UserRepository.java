package com.ima.repository;

import com.ima.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 符柱成 on 17-3-11.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User getByAccount(String account);

}
