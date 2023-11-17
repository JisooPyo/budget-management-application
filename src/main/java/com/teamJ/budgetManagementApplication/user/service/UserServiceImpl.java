package com.teamJ.budgetManagementApplication.user.service;

import com.teamJ.budgetManagementApplication.common.exception.CustomErrorCode;
import com.teamJ.budgetManagementApplication.common.exception.CustomException;
import com.teamJ.budgetManagementApplication.user.dto.SignupRequestDto;
import com.teamJ.budgetManagementApplication.user.entity.User;
import com.teamJ.budgetManagementApplication.user.reository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signup(SignupRequestDto requestDto) {
        String account = requestDto.getAccount();
        checkAlreadySignup(account);

        String password = passwordEncoder.encode(requestDto.getPassword());

        User user = User.builder().account(account).password(password).build();
        userRepository.save(user);
    }

    public void checkAlreadySignup(String account) {
        if (userRepository.findByAccount(account).isPresent()) {
            throw new CustomException(CustomErrorCode.USER_ALREADY_EXISTS);
        }
    }

    public User findUser(String account) {
        return userRepository.findByAccount(account).orElseThrow(
                () -> new CustomException(CustomErrorCode.USER_NOT_FOUND)
        );
    }
}
