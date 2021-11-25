package apap.tugasakhir.sibusiness.controller;

import apap.tugasakhir.sibusiness.model.RoleModel;
import apap.tugasakhir.sibusiness.model.UserModel;
import apap.tugasakhir.sibusiness.service.RoleService;
import apap.tugasakhir.sibusiness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/add")
    private String addUserFormPage(Model model){
        UserModel user = new UserModel();
        List<RoleModel> listRole = roleService.getListRole();
        model.addAttribute("user", user);
        model.addAttribute("listRole", listRole);
        return "form-add-user" ;
    }

    @PostMapping(value = "/add")
    private String addUserSubmit(@ModelAttribute UserModel user, Model model) {
        userService.addUser(user);
        model.addAttribute("user", user);
        return "redirect:/";
    }

    @GetMapping("/view-all")
    private String getUsers(Model model) {
        List<UserModel> listUser = userService.getListUser();
        model.addAttribute("listUser", listUser);
        return "viewall-user";
    }

    @GetMapping("/update/{username}")
    private String updateUserFormPage(
            @PathVariable String username,
            Model model) {
        UserModel user = userService.getUserByUsername(username);
        List<RoleModel> listRole = roleService.getListRole();
        model.addAttribute("listRole", listRole);
        model.addAttribute("user", user);
        model.addAttribute("selectedRole", user.getRole().getId());
        return "form-update-user";
    }

    @PostMapping(value = "/update")
    private String updateUserSubmit(
            @ModelAttribute UserModel user,
            Model model
    ) {
        userService.updateUser(user);
        return "update-user";
    }

    @GetMapping("/updatePassword/{username}")
    public String updatePasswordForm(
            @PathVariable String username,
            Model model
    ) {
        UserModel user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        return "form-update-password";
    }

    @PostMapping(value = "/updatePassword")
    private String updatePasswordSubmit(
            @ModelAttribute UserModel user,
            @RequestParam(value = "oldPassword") String oldPassword,
            @RequestParam(value = "newPassword") String newPassword,
            @RequestParam(value = "confirmedNewPassword") String confirmedNewPassword,
            Model model) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(passwordEncoder.matches(oldPassword, user.getPassword())) {
            if (newPassword.equals(confirmedNewPassword)) {
                String pass = userService.encrypt(newPassword);
                user.setPassword(pass);
                userService.updateUser(user);
                return "update-user";
            } else {
                return "not-matches-kriteria";
            }
        } else {
            return "not-matches-kriteria";
        }
    }
}
