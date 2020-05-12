package org.itstep.msk.app.controller.admin;

import org.itstep.msk.app.entity.User;
import org.itstep.msk.app.enums.Role;
import org.itstep.msk.app.repository.UserRepository;
import org.itstep.msk.app.service.MyBCryptPasswordEncoder;
import org.itstep.msk.app.service.ValidationMessagesService;
import org.itstep.msk.app.service.impl.PaginationServiceImpl;
import org.itstep.msk.app.service.impl.ValidationMessagesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyBCryptPasswordEncoder myBCryptPasswordEncoder;

    @Autowired
    private PaginationServiceImpl paginationService;

    @Autowired
    private ValidationMessagesServiceImpl validationMessagesService;

    @GetMapping("/start")
    public String findAllActiveUsers(
            @PageableDefault(value = 4, sort = "username") Pageable pageable,
            Model model
    ) {
        Page<User> users = userRepository.findAllByActiveIsTrue(pageable);
        paginationService.addToModelWithPagination(model, users, pageable);

        return "admin/user/start";
    }

    @GetMapping("/archive")
    public String findAllArchiveUsers(
            @PageableDefault(value = 4, sort = "username") Pageable pageable,
            Model model
    ) {
        Page<User> users = userRepository.findAllByActiveIsFalse(pageable);
        paginationService.addToModelWithPagination(model, users, pageable);

        return "admin/user/archive";
    }

    @GetMapping("/add")
    public String adding(Model model){
        User newUser = new User();
        newUser.getRoles().add(Role.ROLE_WAITER);
        model.addAttribute("newUser", newUser);
        model.addAttribute("roles", Role.values());
        model.addAttribute("errors", new HashMap<>());

        return "admin/user/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute User newUser, BindingResult bindingResult, Model model) throws Exception {
        User sameName = userRepository.findByUsername(newUser.getUsername());
        if (sameName != null) {
            bindingResult.addError(
                    new FieldError("user", "username", "Пользователь с таким именем уже существует")
            );
        }

        User sameMail = userRepository.findByEmail(newUser.getEmail());
        if (sameMail != null) {
            bindingResult.addError(
                    new FieldError("user", "email", "Пользователь с таким адресом электронной почты уже существует")
            );
        }

        if (bindingResult.hasErrors()) {
            Map<String, List<String>> errors = new HashMap<>();
            validationMessagesService.createValidationMesages(bindingResult, errors);

            newUser.getRoles().add(Role.ROLE_WAITER);

            model.addAttribute("errors", errors);
            model.addAttribute("newUser", newUser);
            model.addAttribute("roles", Role.values());

            return "admin/user/add";
        }

        newUser.getRoles().add(Role.ROLE_WAITER);
        newUser.setPassword(
                myBCryptPasswordEncoder.encode(newUser.getPassword())
        );

        userRepository.save(newUser);
        userRepository.flush();

        return "redirect:/admin/user/start";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable(name = "id") User user,
            Model model
    ) {
        user.getRoles().add(Role.ROLE_WAITER);
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("errors", new HashMap<>());

        return "admin/user/edit";
    }

    @PostMapping("/edit/{id}")
    public String save(
            @PathVariable(name = "id") User user,
            @Valid @ModelAttribute User editedUser,
            BindingResult bindingResult,
            Model model
    ) {
        User sameMail = userRepository.findByEmail(editedUser.getEmail());
        if (sameMail != null && !user.getEmail().equals(editedUser.getEmail())) {
            System.out.println("First");
            bindingResult.addError(
                    new FieldError("editedUser", "email", "Пользователь с таким адресом электронной почты уже существует")
            );
        }

        if (bindingResult.hasErrors()) {
            System.out.println("Second");
            Map<String, List<String>> errors = new HashMap<>();
            validationMessagesService.createValidationMesages(bindingResult, errors);

            if (editedUser.getUsername().equals("admin")) {
                editedUser.getRoles().add(Role.ROLE_ADMIN);
            }

            user.setUsername(editedUser.getUsername());
            user.setPassword(editedUser.getPassword());
            user.setEmail(editedUser.getEmail());
            user.getRoles().clear();
            for (Role role : editedUser.getRoles()) {
                user.getRoles().add(role);
            }
            user.getRoles().add(Role.ROLE_WAITER);

            System.out.println("Third");
            model.addAttribute("errors", errors);
            model.addAttribute("user", user);
            model.addAttribute("roles", Role.values());

            return "admin/user/edit/" + user.getId();
        }



        if (editedUser.getUsername().equals("admin")) {
            editedUser.getRoles().add(Role.ROLE_ADMIN);
        }

        user.setUsername(editedUser.getUsername());
        user.setPassword(editedUser.getPassword());
        user.setEmail(editedUser.getEmail());
        user.getRoles().clear();
        for (Role role : editedUser.getRoles()) {
            user.getRoles().add(role);
        }
        user.getRoles().add(Role.ROLE_WAITER);

        userRepository.save(user);
        userRepository.flush();

        return "redirect:/admin/user/edit/" + user.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") User user
    ) {
        if (user == null) {
            throw new RuntimeException("Такого пользователя не найдено");
        }

        user.setActive(false);

        userRepository.save(user);
        userRepository.flush();

        return "redirect:/admin/user/start";
    }

    @GetMapping("/return/{id}")
    public String retur(@PathVariable(name = "id") User user
    ) {
        if (user == null) {
            throw new RuntimeException("Такого пользователя не найдено");
        }

        user.setActive(true);

        userRepository.save(user);
        userRepository.flush();

        return "redirect:/admin/user/archive";
    }

    @GetMapping("/finish-delete/{id}")
    public String finishDelete(@PathVariable(name = "id") User user
    ) {
        if (user == null) {
            throw new RuntimeException("Такого пользователя не найдено");
        }

        userRepository.delete(user);
        userRepository.flush();

        return "redirect:/admin/user/archive";
    }
}
