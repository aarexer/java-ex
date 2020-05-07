package aarexer.service;

import aarexer.domain.Role;
import aarexer.domain.User;
import aarexer.repository.UserRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Value("${server.url}")
    private String serverUrl;

    private final UserRepository repository;
    private final EmailSenderService emailSenderService;

    public UserService(UserRepository repository, EmailSenderService emailSenderService) {
        this.repository = repository;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = repository.findByUsername(username);

        if (Objects.isNull(byUsername)) {
            throw new UsernameNotFoundException("User not found by username: " + username);
        }

        return byUsername;
    }

    public boolean register(User user) {
        User userDb = loadUserByUsername(user.getUsername());

        if (userDb != null) {
            return false;
        }

        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        repository.save(user);

        if (!user.getEmail().isEmpty()) {
            String text = "Activation url:" + serverUrl + "/activation/" + user.getActivationCode();
            emailSenderService.send(user.getEmail(), "Regisration in JJournal", text);
        }

        return true;
    }

    public List<User> allUsers() {
        return Lists.newArrayList(repository.findAll());
    }

    public boolean activateUser(String code) {
        Optional<User> userByCode = repository.findByActivationCode(code);
        userByCode.ifPresent(u -> {
            u.setActivationCode(null);
            u.setActive(true);

            repository.save(u);
        });

        return userByCode.isPresent();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        repository.save(user);
    }

    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));

        if (isEmailChanged) {
            user.setEmail(email);

            if (!email.isEmpty()) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (!password.isEmpty()) {
            user.setPassword(password);
        }

        repository.save(user);

        if (isEmailChanged) {
            emailSenderService.send(user.getEmail(), "Changing profile", "We change your profile.");
        }
    }
}
