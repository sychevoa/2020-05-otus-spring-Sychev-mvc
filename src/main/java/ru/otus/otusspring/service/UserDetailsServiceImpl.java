package ru.otus.otusspring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.otusspring.model.User;
import ru.otus.otusspring.model.UserDetailsImpl;
import ru.otus.otusspring.repositories.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(name);
        user.orElseThrow(() -> new UsernameNotFoundException(name + " not found"));
        return user.map(UserDetailsImpl::new).get();
    }
}