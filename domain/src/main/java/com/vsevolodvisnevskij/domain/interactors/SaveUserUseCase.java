package com.vsevolodvisnevskij.domain.interactors;

import com.vsevolodvisnevskij.domain.executor.PostExecutionThread;
import com.vsevolodvisnevskij.domain.repositories.UserRepository;

/**
 * Created by vsevolodvisnevskij on 22.03.2018.
 */

public class SaveUserUseCase extends BaseUseCase {
    private UserRepository userRepository;

    public SaveUserUseCase(PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }


}
