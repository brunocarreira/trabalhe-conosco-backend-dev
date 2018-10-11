package com.picpay.repositories;

import com.picpay.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Bruno Carreira
 */

@Transactional
public interface UserRepository extends PagingAndSortingRepository<User, String> {
    Page<User> findAll(Specification<User> spec, Pageable pageable);

    Page<User> findByNameContainingOrUsernameContaining(@Param("name") String nameText, @Param("username") String usernameText, Pageable pageable);

    @Modifying
    @Query(value = "update User u set u.priority=:priority where u.id in (:ids)")
    int updatePriorityByIds(@Param("ids") List<String> id, @Param("priority") int priority);
}