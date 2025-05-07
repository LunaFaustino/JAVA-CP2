package br.com.fiap.roardemo.service;

import br.com.fiap.roardemo.model.User;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;
import java.util.Optional;

public interface UserService {

    OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException;

    Optional<User> getUserById(String id);

    Optional<User> getUserByLogin(String login);

    Map<String, Object> extractUserInfo(OAuth2User oAuth2User);
}