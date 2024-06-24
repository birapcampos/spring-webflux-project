package br.com.birapcampos.webflux_exemplo.service;

import br.com.birapcampos.webflux_exemplo.controller.exception.ObjectNotFoundException;
import br.com.birapcampos.webflux_exemplo.entity.User;
import br.com.birapcampos.webflux_exemplo.mapper.UserMapper;
import br.com.birapcampos.webflux_exemplo.model.request.UserRequest;
import br.com.birapcampos.webflux_exemplo.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.lang.String.format;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Mono<User> save(final UserRequest request){
        return userRepository.save(userMapper.toEntity(request));
    }

    public Mono<User> findById(final String id){
        return handleNotFound(userRepository.findById(id),id);    }

    public Flux<User> findAll(){
        return userRepository.findAll();
    }

    public Mono<User> update(final String id,UserRequest request){
        return findById(id)
                .map(entity -> userMapper.toEntity(request,entity))
                .flatMap(userRepository::save);
    }

    public Mono<User> delete(final String id){
        return handleNotFound(userRepository.findAndRemove(id),id);
    }

    private <T> Mono<T> handleNotFound(Mono<T> mono,String id){
        return mono.switchIfEmpty(
                Mono.error(new ObjectNotFoundException(
                        format("Object not found. id: %s, Type: %s",id,User.class.getSimpleName())
                )
        ));
    }

}
