package br.com.fiap.roardemo.service;

import br.com.fiap.roardemo.model.User;
import br.com.fiap.roardemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String id = oAuth2User.getAttribute("id").toString();
        String name = oAuth2User.getAttribute("name");
        String login = oAuth2User.getAttribute("login");
        String email = oAuth2User.getAttribute("email");
        String avatarUrl = oAuth2User.getAttribute("avatar_url");

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setName(name);
            existingUser.setLogin(login);
            existingUser.setEmail(email);
            existingUser.setAvatarUrl(avatarUrl);
            existingUser.setLastLogin(LocalDateTime.now());
            userRepository.save(existingUser);
        } else {
            User newUser = new User();
            newUser.setId(id);
            newUser.setName(name);
            newUser.setLogin(login);
            newUser.setEmail(email);
            newUser.setAvatarUrl(avatarUrl);
            userRepository.save(newUser);
        }

        return oAuth2User;
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public Map<String, Object> extractUserInfo(OAuth2User oAuth2User) {
        return Map.of(
                "id", oAuth2User.getAttribute("id").toString(),
                "name", oAuth2User.getAttribute("name"),
                "login", oAuth2User.getAttribute("login"),
                "email", oAuth2User.getAttribute("email"),
                "avatarUrl", oAuth2User.getAttribute("avatar_url")
        );
    }
}