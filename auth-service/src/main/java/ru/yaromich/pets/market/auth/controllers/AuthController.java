package ru.yaromich.pets.market.auth.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.yaromich.pets.market.api.JwtRequest;
import ru.yaromich.pets.market.api.JwtResponse;
import ru.yaromich.pets.market.auth.exceptions.AppError;
import ru.yaromich.pets.market.auth.services.UserService;
import ru.yaromich.pets.market.auth.utils.JwtTokenUtil;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Аутентификация", description = "Методы работы с аутентификацией")
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Operation(
            summary = "Запрос на создание токена",
            responses = {
                    @ApiResponse(
                            description = "Токен успешно создан", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = JwtResponse.class))
                    ),
                    @ApiResponse(
                            description = "Некорректный логин или пароль", responseCode = "401",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError("CHECK_TOKEN_ERROR", "Некорректный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}