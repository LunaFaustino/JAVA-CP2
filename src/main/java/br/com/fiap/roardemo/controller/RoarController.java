package br.com.fiap.roardemo.controller;

import br.com.fiap.roardemo.model.Roar;
import br.com.fiap.roardemo.service.RoarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RoarController {

    private final RoarService roarService;

    @Autowired
    public RoarController(RoarService roarService) {
        this.roarService = roarService;
    }

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal OAuth2User principal) {
        List<Roar> roars = roarService.getAllRoars();
        model.addAttribute("roars", roars);

        if (principal != null) {
            model.addAttribute("username", principal.getAttribute("name"));
            model.addAttribute("userLogin", principal.getAttribute("login"));
            model.addAttribute("userId", principal.getAttribute("id").toString());
            model.addAttribute("avatarUrl", principal.getAttribute("avatar_url"));
        }

        return "index";
    }

    @PostMapping("/roar")
    public String roar(@RequestParam("content") String content,
                       @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            String username = principal.getAttribute("name");
            String userId = principal.getAttribute("id").toString();
            roarService.addRoar(content, username, userId);
        }
        return "redirect:/";
    }

    @PostMapping("/like")
    public String like(@RequestParam("roarId") Long roarId) {
        roarService.likeRoar(roarId);
        return "redirect:/";
    }
}