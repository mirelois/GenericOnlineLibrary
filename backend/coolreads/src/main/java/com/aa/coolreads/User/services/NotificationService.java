package com.aa.coolreads.User.services;

import com.aa.coolreads.Book.exception.BookNotFoundException;
import com.aa.coolreads.Book.models.Book;
import com.aa.coolreads.Book.repositories.BookRepository;
import com.aa.coolreads.User.dto.NotificationCreationDTO;
import com.aa.coolreads.User.dto.NotificationDTO;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import com.aa.coolreads.User.exception.NoFriendRequestFromRequestedCustomerException;
import com.aa.coolreads.User.mappers.NotificationMapper;
import com.aa.coolreads.User.models.Customer;
import com.aa.coolreads.User.models.Notification;
import com.aa.coolreads.User.models.NotificationType;
import com.aa.coolreads.User.repositories.CustomerRepository;
import com.aa.coolreads.User.repositories.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final CustomerRepository customerRepository;

    private final NotificationMapper notificationMapper;

    private final BookRepository bookRepository;

    private final MailService mailService;

    public NotificationService(NotificationRepository notificationRepository, CustomerRepository customerRepository, NotificationMapper notificationMapper, BookRepository bookRepository, MailService mailService) {
        this.notificationRepository = notificationRepository;
        this.customerRepository = customerRepository;
        this.notificationMapper = notificationMapper;
        this.bookRepository = bookRepository;
        this.mailService = mailService;
    }

    @Transactional
    public void sendFriendRequestNotification(String my_username, String friend_username) throws CustomerNotFoundException {

        Customer myCustomer = this.customerRepository.findById(my_username).orElseThrow(() -> new CustomerNotFoundException(my_username));
        Customer customer = this.customerRepository.findById(friend_username).orElseThrow(() -> new CustomerNotFoundException(friend_username));

        this.notificationRepository.save(new Notification(customer, myCustomer, NotificationType.FRIEND_REQUEST_NOTIFICATION));

        this.customerRepository.save(customer);
    }

    @Transactional
    public void addFriend(String my_username, String friend_username) throws CustomerNotFoundException, NoFriendRequestFromRequestedCustomerException {

        Customer myCustomer = this.customerRepository.findById(my_username).orElseThrow(() -> new CustomerNotFoundException(my_username));
        Customer customer = this.customerRepository.findById(friend_username).orElseThrow(() -> new CustomerNotFoundException(friend_username));

        if(this.notificationRepository.hasNotificationFromOtherCustomer(myCustomer, customer, NotificationType.FRIEND_REQUEST_NOTIFICATION.name()))
            customer.addFriend(myCustomer);
        else
            throw new NoFriendRequestFromRequestedCustomerException(friend_username);

        this.notificationRepository.save(new Notification(customer, myCustomer, NotificationType.FRIEND_REQUEST_ACCEPTED_NOTIFICATION));

        this.customerRepository.save(customer);
    }

    @Transactional
    public void insertNotification(NotificationCreationDTO notificationCreationDTO, String userName) throws CustomerNotFoundException {

        Customer customer = this.customerRepository.findById(userName).orElseThrow(() -> new CustomerNotFoundException(userName));

        for(Customer friend: customer.getFriends()){
            this.notificationRepository.save(this.notificationMapper.toNotification(notificationCreationDTO, customer, friend));
            this.mailService.sendNotificationMail(friend.getEmail(), notificationCreationDTO.getNotificationType().name());
        }
    }

    @Transactional
    public void insertBookRelatedNotification(NotificationType notificationType, String isbn, String userName) throws CustomerNotFoundException, BookNotFoundException {

        Customer customer = this.customerRepository.findById(userName).orElseThrow(() -> new CustomerNotFoundException(userName));

        Book book = this.bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException(isbn));

        for(Customer friend: customer.getFriends()){
            this.notificationRepository.save(this.notificationMapper.toNotification(notificationType, book, friend, customer));
            this.mailService.sendNotificationMail(friend.getEmail(), notificationType.name());
        }
    }

    @Transactional
    public Set<NotificationDTO> getNotificationsByUserName(String username, Integer pageNumber, Integer pageSize) throws CustomerNotFoundException {

        this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        Page<Notification> notificationPage = this.notificationRepository.findByCustomerUsername(username, pageable);

        return notificationPage.get().map(this.notificationMapper::toNotificationDTO).collect(Collectors.toSet());

    }

    @Transactional
    public void deleteNotification(String username, Long notificationId) throws CustomerNotFoundException {

        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

        this.notificationRepository.deleteNotificationByIdAndCustomer(customer, notificationId);
    }

}
