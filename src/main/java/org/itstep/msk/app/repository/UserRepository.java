package org.itstep.msk.app.repository;

import org.itstep.msk.app.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    Page<User> findAllByActiveIsTrue(Pageable pageable);

    Page<User> findAllByActiveIsFalse(Pageable pageable);
}
