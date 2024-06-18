package com.aa.coolreads.User.repositories;

import com.aa.coolreads.User.models.Customer;
import com.aa.coolreads.User.models.ExclusivityClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface ExclusivityClassRepository extends JpaRepository<ExclusivityClass, Long> {

    @Query("SELECT ec FROM ExclusivityClass ec WHERE ec.customer = :customer")
    Set<ExclusivityClass> getExclusivityClassesByCustomer(Customer customer);

    @Query("SELECT ec FROM ExclusivityClass ec WHERE ec.customer = :customer AND ec.name = :name")
    Optional<ExclusivityClass> getExclusivityClassByCustomerAndName(Customer customer, String name);

    @Modifying
    @Query("DELETE FROM ExclusivityClass ec WHERE ec.customer = :customer AND ec.name = :name")
    void deleteExclusivityClassByCustomerAndName(Customer customer, String name);
}
