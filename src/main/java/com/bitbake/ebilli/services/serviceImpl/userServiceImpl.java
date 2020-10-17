package com.bitbake.ebilli.services.serviceImpl;

import com.bitbake.ebilli.entity.User;
import com.bitbake.ebilli.repository.UserRepository;
import com.bitbake.ebilli.services.UserService;
import com.bitbake.ebilli.types.UserType;
import com.bitbake.ebilli.util.EncryptDecryptAes;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class userServiceImpl implements UserService {

    private final UserRepository userRepository;

    public userServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        Optional<User> existingUser = userRepository.findByUserName(user.getUserName());
        if (existingUser.isPresent())
            return null;
        if (StringUtils.isNotBlank(user.getPassword()))
            user.setPassword(EncryptDecryptAes.encrypt(user.getPassword()));
        user.setUserType(UserType.USER);
        return userRepository.save(user);
    }
}
