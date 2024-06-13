package com.aa.coolreads.User.repositories;

import com.aa.coolreads.User.models.Notification;
import com.aa.coolreads.User.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT n FROM Notification n WHERE n.customer.username = :username")
    Page<Notification> findByCustomerUsername(@Param("username") String username, PageRequest pageable);

}
