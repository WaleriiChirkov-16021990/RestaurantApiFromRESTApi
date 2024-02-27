package com.chirkov.restApiRestaurantBussines.services;

import com.chirkov.restApiRestaurantBussines.models.Person;
import com.chirkov.restApiRestaurantBussines.repositories.PeopleRepository;
import com.chirkov.restApiRestaurantBussines.security.PersonDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PeopleRepository repository;

    /**
     * Сохранение пользователя
     *
     * @return сохраненный пользователь
     */
//    public PersonDetails save(PersonDetails user) {
//        return repository.save(user);
//    }


    /**
     * Создание пользователя
     *
     * @return созданный пользователь
     */
//    public User create(User user) {
//        if (repository.existsByUsername(user.getUsername())) {
//            // Заменить на свои исключения
//            throw new RuntimeException("Пользователь с таким именем уже существует");
//        }
//
//        if (repository.existsByEmail(user.getEmail())) {
//            throw new RuntimeException("Пользователь с таким email уже существует");
//        }
//        return save(user);
//    }

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    public Person getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService() {
//        return new PersonDetails(this::getByUsername);
//
        return username -> {
            return new PersonDetails(getByUsername(username));
        };
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public PersonDetails getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new PersonDetails(getByUsername(username)); // getByUsername(username);
    }

//
//    /**
//     * Выдача прав администратора текущему пользователю
//     * <p>
//     * Нужен для демонстрации
//     */
//    @Deprecated
//    public void getAdmin() {
//        var user = getCurrentUser();
//        user.setRole(Role.ROLE_ADMIN);
//        save(user);
//    }
}
