package ru.yaromich.pets.market.gateway;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {
    public static final List<String> openEndPoints = List.of(
            "/auth",
            "/register",
            "/api/v1/products"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openEndPoints
            .stream()
            .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
