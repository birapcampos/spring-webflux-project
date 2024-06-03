package br.com.birapcampos.webflux_exemplo.repository;

import br.com.birapcampos.webflux_exemplo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class UserRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    @Autowired
    public UserRepository(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Mono<User> save(final User user){
        return mongoTemplate.save(user);

    }
}
