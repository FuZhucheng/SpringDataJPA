package com.ima.repository;

import com.ima.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 符柱成 on 17-3-11.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User getByAccount(String account);

    @Modifying
    @Transactional
    @Query("UPDATE User user SET user.name = ?1 WHERE user.id = ?2")
    void updateName(String name, Long id);

}
