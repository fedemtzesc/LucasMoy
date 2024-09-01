package com.fdxsoft.lucas.repositories;

import com.fdxsoft.lucas.models.UserModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IUserRepository extends CrudRepository<UserModel, Long> {
    public abstract UserModel findByEmail(String email);
}
