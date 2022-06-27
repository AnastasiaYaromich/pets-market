package ru.yaromich.pets.market.auth.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yaromich.pets.market.api.*;
import ru.yaromich.pets.market.auth.services.UserService;

@RestController
@RequiredArgsConstructor
@Tag(name = "Регистрация", description = "Методы работы с регистрацией")
public class RegisterController {
    private final UserService userService;

    @Operation(
            summary = "Запрос на регистрацию нового пользователя",
            responses = {
                    @ApiResponse(
                            description = "Пользователь успешно зарегистрирован", responseCode = "201"
                    )
            }
    )
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerNewUser(@RequestBody RegisterUserDto registerUserDto) {
       userService.registerNewUser(registerUserDto);
    }
}
