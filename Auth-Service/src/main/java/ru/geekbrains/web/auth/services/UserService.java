package ru.geekbrains.web.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.geekbrains.web.api.dtos.UserDTO;
//import ru.geekbrains.web.core.exceptions.UserAlreadyExistException;
import ru.geekbrains.web.auth.model.Role;
import ru.geekbrains.web.auth.model.User;
import ru.geekbrains.web.auth.repositories.RoleRepository;
import ru.geekbrains.web.auth.repositories.UserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

//    public User registerNewUserAccount(UserDTO userDTO) {
//        if (userExists(userDTO.getUsername())) {
//            throw new UserAlreadyExistException("Пользователь с таким именем уже существует: " + userDTO.getUsername());
//        }
//        User user = new User();
//        user.setUsername(userDTO.getUsername());
//        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//        user.setEmail(userDTO.getEmail());
//        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
//
//        return userRepository.save(user);
//    }

    private boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}