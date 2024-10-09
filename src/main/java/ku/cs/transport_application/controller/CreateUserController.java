package ku.cs.transport_application.controller;

import jakarta.validation.Valid;
import ku.cs.transport_application.request.CreateUserRequest;
import ku.cs.transport_application.service.CreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CreateUserController {

    @Autowired
    private CreateUserService createUserService;

    @PostMapping("/create-user")
    public String CreateUser(@Valid CreateUserRequest user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create-user";
        }

        if(createUserService.isUsernameAvailable(user.getUsername())) {
            createUserService.createUser(user);
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", "Username not available");
        }
        model.addAttribute("signupRequest", new CreateUserRequest());
        return "create-user";
    }

}
