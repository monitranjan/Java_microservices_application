package com.monit.accounts.repository;

import com.monit.accounts.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts,Long> {
    Optional<Accounts> findByCustomerId(long customerId);

    @Transactional
    @Modifying
    void deleteByCustomerId(long customerId);
}
