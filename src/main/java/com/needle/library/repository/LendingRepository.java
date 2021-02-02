package com.needle.library.repository;

import com.needle.library.models.Lending;
import com.needle.library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LendingRepository extends JpaRepository<Lending, Long> {
    List<Lending> findAllByUser(User user);
}
