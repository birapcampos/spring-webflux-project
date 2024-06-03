package br.com.birapcampos.webflux_exemplo.mapper;

import br.com.birapcampos.webflux_exemplo.entity.User;
import br.com.birapcampos.webflux_exemplo.model.request.UserRequest;
import br.com.birapcampos.webflux_exemplo.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface UserMapper {

    @Mapping(target = "id",ignore = true)
    User toEntity(final UserRequest request);

    @Mapping(target = "id",ignore = true)
    User toEntity(final UserRequest request, @MappingTarget final User entity);

    UserResponse toResponse(final User entity);

}
