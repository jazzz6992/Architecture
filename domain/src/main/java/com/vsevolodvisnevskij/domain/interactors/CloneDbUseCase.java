package com.vsevolodvisnevskij.domain.interactors;

import com.vsevolodvisnevskij.domain.executor.PostExecutionThread;
import com.vsevolodvisnevskij.domain.repositories.UserRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

public class CloneDbUseCase extends BaseUseCase {
    private UserRepository repository;

    @Inject
    public CloneDbUseCase(PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(postExecutionThread);
        repository = userRepository;
    }

//    public Completable cloneDb() {
//        return repository.cloneDb().subscribeOn(threadExecution);
//    }
}
