package aarexer.controller;

import aarexer.domain.User;
import aarexer.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String passwordConfirm,
                          @Valid User user,
                          BindingResult bindingResult,
                          Model model) {
        boolean isConfirmPasswordEmpty = passwordConfirm.isEmpty();
        if (isConfirmPasswordEmpty) {
            model.addAttribute("passwordConfirmError", "Password confirmation can't be empty!");
        }

        if (isConfirmPasswordEmpty || bindingResult.hasErrors()) {
            model.addAllAttributes(Utils.getErrors(bindingResult));

            return "registration";
        }

        if (user.getPassword() != null && user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Different passwords!");
            model.addAttribute("passwordConfirmError", "Different passwords!");

            return "registration";
        }

        if (!userService.register(user)) {
            model.addAttribute("usernameError", "User exists!");

            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code, Model model) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "User successfully activated");
            model.addAttribute("messageType", "success");
        } else {
            model.addAttribute("message", "Activation code is not found!");
            model.addAttribute("messageType", "danger");
        }

        return "login";
    }
}