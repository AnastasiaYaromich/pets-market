package ru.yaromich.pets.market.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yaromich.pets.market.api.*;
import ru.yaromich.pets.market.auth.exceptions.AppError;
import ru.yaromich.pets.market.auth.services.UserService;
import ru.yaromich.pets.market.auth.utils.JwtTokenUtil;

@RestController
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    // Нужно будет проверить что юзера не существует и перед сохранением пользователя в базу
    // пароль преобразовать к бкрипту т.е. получить хэш.
    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestBody RegisterUserDto registerUserDto) {
        if(!userService.findByemail(registerUserDto.getEmail()).isPresent()) {
            String bcryptCachedPassword = passwordEncoder.encode(registerUserDto.getPassword());
            registerUserDto.setConfirmPassword(bcryptCachedPassword);
            userService.registerNewUser(registerUserDto.getUsername(), registerUserDto.getConfirmPassword(), registerUserDto.getEmail());
            String userEmail = registerUserDto.getEmail();
            String userName = registerUserDto.getUsername();
            return ResponseEntity.ok(new RegisterUserResponce(userName, userEmail));
        }
        return new ResponseEntity<>(new AppError("USER_IS_EXIST", "Данный пользователь уже зарегистрирован"), HttpStatus.BAD_REQUEST);
    }
}
