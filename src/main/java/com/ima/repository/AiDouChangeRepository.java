package com.ima.repository;

import com.ima.model.IDouChange;
import com.ima.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ${符柱成} on 2017/3/14.
 */
@Repository
public interface AiDouChangeRepository extends JpaRepository<IDouChange, Long> {


    //降序查记录列表
    List<IDouChange> findFirst10ByUser(User user, Sort sort);

    List<IDouChange> findById(Long id);


}
