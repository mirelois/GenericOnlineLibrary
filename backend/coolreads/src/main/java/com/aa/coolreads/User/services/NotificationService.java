package com.aa.coolreads.User.services;

import com.aa.coolreads.User.dto.NotificationCreationDTO;
import com.aa.coolreads.User.dto.NotificationDTO;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import com.aa.coolreads.User.mappers.NotificationMapper;
import com.aa.coolreads.User.models.Customer;
import com.aa.coolreads.User.models.Notification;
import com.aa.coolreads.User.repositories.CustomerRepository;
import com.aa.coolreads.User.repositories.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final CustomerRepository customerRepository;

    private final NotificationMapper notificationMapper;

    public NotificationService(NotificationRepository notificationRepository, CustomerRepository customerRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.customerRepository = customerRepository;
        this.notificationMapper = notificationMapper;
    }

    @Transactional
    public void insertNotification(NotificationCreationDTO notificationCreationDTO, String userName) throws CustomerNotFoundException {

        Customer customer = this.customerRepository.findById(userName).orElseThrow(() -> new CustomerNotFoundException(userName));

        this.notificationRepository.save(this.notificationMapper.toNotification(notificationCreationDTO, customer));
    }

    @Transactional
    public Set<NotificationDTO> getNotificationsByUserName(String username, Integer pageNumber, Integer pageSize) {

        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        Page<Notification> notificationPage = this.notificationRepository.findByCustomerUsername(username, pageable);

        return notificationPage.get().map(this.notificationMapper::toNotificationDTO).collect(Collectors.toSet());

    }

    @Transactional
    public void deleteNotification(Long notificationId) {
        this.notificationRepository.deleteById(notificationId);
    }

}
