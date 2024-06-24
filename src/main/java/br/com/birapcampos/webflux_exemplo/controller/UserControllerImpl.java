package br.com.birapcampos.webflux_exemplo.controller;

import br.com.birapcampos.webflux_exemplo.controller.UserController;
import br.com.birapcampos.webflux_exemplo.mapper.UserMapper;
import br.com.birapcampos.webflux_exemplo.model.request.UserRequest;
import br.com.birapcampos.webflux_exemplo.model.response.UserResponse;
import br.com.birapcampos.webflux_exemplo.service.UserService;
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
    private final UserMapper userMapper;

    public UserControllerImpl(UserService userService,UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public ResponseEntity<Mono<Void>> save(UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.save(request).then());
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> findById(String id) {
        return ResponseEntity.ok().body(
                userService.findById(id)
                        .map(userMapper::toResponse));
    }

    @Override
    public ResponseEntity<Flux<UserResponse>> findAll() {
        return ResponseEntity.ok().body(
                userService.findAll().map(obj -> userMapper.toResponse(obj)));
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> update(String id, UserRequest request) {
        return ResponseEntity.ok().body(
                userService.update(id,request)
                        .map(userMapper::toResponse));
    }

    @Override
    public ResponseEntity<Mono<Void>> delete(String id) {
        return ResponseEntity.ok().body(userService.delete(id).then());
    }

}
