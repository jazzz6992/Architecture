package com.vsevolodvisnevskij.domain.interactors;

import com.vsevolodvisnevskij.domain.entity.UserEntity;
import com.vsevolodvisnevskij.domain.executor.PostExecutionThread;
import com.vsevolodvisnevskij.domain.repositories.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by vsevolodvisnevskij on 21.03.2018.
 */

public class GetUsersUseCase extends BaseUseCase {
    private UserRepository userRepository;

    @Inject
    public GetUsersUseCase(PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }

    public Observable<List<UserEntity>> get(String offset) {
        return userRepository.getUsers(offset).subscribeOn(threadExecution).observeOn(postExecutionThread);
    }
}
