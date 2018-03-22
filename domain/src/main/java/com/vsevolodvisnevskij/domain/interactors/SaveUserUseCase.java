package com.vsevolodvisnevskij.domain.interactors;

import com.vsevolodvisnevskij.domain.entity.UserEntity;
import com.vsevolodvisnevskij.domain.executor.PostExecutionThread;
import com.vsevolodvisnevskij.domain.repositories.UserRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by vsevolodvisnevskij on 22.03.2018.
 */

public class SaveUserUseCase extends BaseUseCase {
    private UserRepository userRepository;

    @Inject
    public SaveUserUseCase(PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }

    public Completable save(UserEntity userEntity) {
        return userRepository.save(userEntity).subscribeOn(threadExecution).observeOn(postExecutionThread);
    }


}
