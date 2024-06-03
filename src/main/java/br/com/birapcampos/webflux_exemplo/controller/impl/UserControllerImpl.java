package br.com.birapcampos.webflux_exemplo.controller.impl;

import br.com.birapcampos.webflux_exemplo.controller.UserController;
import br.com.birapcampos.webflux_exemplo.model.request.UserRequest;
import br.com.birapcampos.webflux_exemplo.model.response.UserResponse;
import br.com.birapcampos.webflux_exemplo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value ="/users")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Mono<Void>> save(UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.save(request).then());
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> find(String id) {

        return null;
    }

    @Override
    public ResponseEntity<Flux<UserResponse>> findAll() {
        return null;
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> update(String id, UserRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Mono<Void>> update(String id) {
        return null;
    }
}