package br.com.birapcampos.webflux_exemplo.service;

import br.com.birapcampos.webflux_exemplo.entity.User;
import br.com.birapcampos.webflux_exemplo.mapper.UserMapper;
import br.com.birapcampos.webflux_exemplo.model.request.UserRequest;
import br.com.birapcampos.webflux_exemplo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

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

}
