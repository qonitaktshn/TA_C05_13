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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "form-add-user";
    }

    @PostMapping(value = "/add")
    private String addUserSubmit(
            @ModelAttribute UserModel user,
            RedirectAttributes redirectAttributes,
            Model model) {
        if (userService.getUserByUsername(user.getUsername()) == null) {
            userService.addUser(user);
            model.addAttribute("user", user);
            redirectAttributes.addFlashAttribute("message", "User berhasil ditambahkan.");
        } else {
            redirectAttributes.addFlashAttribute("message", "User gagal ditambahkan karena username telah dipakai user lain. Silakan coba lagi.");
        }
        return "redirect:/user/view-all";
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
            RedirectAttributes redirectAttributes
    ) {
        userService.updateUser(user);
        redirectAttributes.addFlashAttribute("message", "Username/role berhasil diubah.");
        return "redirect:/user/view-all";
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
            RedirectAttributes redirectAttributes) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            if (newPassword.equals(confirmedNewPassword)) {
                String pass = userService.encrypt(newPassword);
                user.setPassword(pass);
                userService.updateUser(user);
                redirectAttributes.addFlashAttribute("message", "Password user berhasil diubah.");
            } else {
                redirectAttributes.addFlashAttribute("message", "Password baru dan konfirmasinya belum sama. Silakan coba lagi.");
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Password lama salah. Silakan coba lagi.");
        }
        return "redirect:/user/view-all";
    }

    @GetMapping("/view-all")
    private String getUsers(Model model) {
        List<UserModel> listUser = userService.getListUser();
        model.addAttribute("listUser", listUser);
        return "viewall-user";
    }
    
}
