package com.example.learningapp.infrastructure.persistence.repository;

import com.example.learningapp.domain.model.user.Email;
import com.example.learningapp.domain.model.user.User;
import com.example.learningapp.domain.model.user.UserId;
import com.example.learningapp.domain.model.user.Username;
import com.example.learningapp.domain.repository.UserRepository;
import com.example.learningapp.infrastructure.persistence.entity.UserJpaEntity;
import com.example.learningapp.infrastructure.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepositoryインターフェースのJPA実装
 *
 */
@Repository
@RequiredArgsConstructor
public class JpaUserRepositoryImpl implements UserRepository {

    // Spring Data JPAの自動実装インターフェース
    private final JpaUserRepository jpaUserRepository;

    // Domain Entity ⇔ JPA Entity の変換を担当
    private final UserMapper userMapper;

    /**
     * ユーザーを保存する
     *
     */
    @Override
    public User save(User user) {
        UserJpaEntity jpaEntity = userMapper.toJpaEntity(user);
        UserJpaEntity saved = jpaUserRepository.save(jpaEntity);
        return userMapper.toDomainEntity(saved);
    }

    /**
     * IDでユーザーを検索する
     *
     */
    @Override
    public Optional<User> findById(UserId id) {
        return jpaUserRepository.findById(id.getValue()).map(userMapper::toDomainEntity);
    }

    @Override
    public Optional<User> findByUsername(Username username) {
        return jpaUserRepository.findByUsername(username).map(userMapper::toDomainEntity);
    }

    @Override
    public Optional<Email> findByEmail(Email email) {
        return jpaUserRepository.findByEmail(email).map(userMapper::toDomainEntity);
    }

    @Override
    public boolean existsByUsername(Username username) {
        return jpaUserRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(Email email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public void delete(UserId id) {
        jpaUserRepository.deleteById(id.getValue());
    }
}
