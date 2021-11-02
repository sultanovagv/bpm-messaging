package messaging.app.controller;

import messaging.app.model.UserRequest;
import messaging.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/login")
    public String login(@Valid @RequestBody UserRequest userRequest) {
        return userService.login(userRequest);
    }

    @PostMapping(value = "/register")
    public void register(@Valid @RequestBody UserRequest userRequest) {
        userService.register(userRequest);
    }

}
