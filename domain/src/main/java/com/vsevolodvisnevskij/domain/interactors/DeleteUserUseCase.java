package com.vsevolodvisnevskij.domain.interactors;

import com.vsevolodvisnevskij.domain.entity.UserEntity;
import com.vsevolodvisnevskij.domain.executor.PostExecutionThread;
import com.vsevolodvisnevskij.domain.repositories.UserRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by vsevolodvisnevskij on 23.03.2018.
 */

public class DeleteUserUseCase extends BaseUseCase {
   private UserRepository userRepository;

    @Inject
    public DeleteUserUseCase(PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }

    public Completable remove(String id) {
       return userRepository.remove(id).subscribeOn(threadExecution).observeOn(postExecutionThread);
    }
}
