package br.com.birapcampos.webflux_exemplo.service;

import br.com.birapcampos.webflux_exemplo.controller.exception.ObjectNotFoundException;
import br.com.birapcampos.webflux_exemplo.entity.User;
import br.com.birapcampos.webflux_exemplo.mapper.UserMapper;
import br.com.birapcampos.webflux_exemplo.model.request.UserRequest;
import br.com.birapcampos.webflux_exemplo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.lang.String.format;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Mono<User> save(final UserRequest request){

        return userRepository.save(userMapper.toEntity(request));
    }

    public Mono<User> findById(final String id){

        return userRepository.findById(id).switchIfEmpty(Mono.error(
                new ObjectNotFoundException(
                        format("Object not found. id: %s, Type: %s",id,User.class.getSimpleName())
                )
        ));
    }

    public Flux<User> findAll(){
        return userRepository.findAll();
    }

    public Mono<User> update(final String id,UserRequest request){
        return findById(id)
                .map(entity -> userMapper.toEntity(request,entity))
                .flatMap(userRepository::save);
    }
}
