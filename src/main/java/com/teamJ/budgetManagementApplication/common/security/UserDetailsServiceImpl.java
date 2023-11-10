package com.teamJ.budgetManagementApplication.common.security;

import com.teamJ.budgetManagementApplication.user.entity.User;
import com.teamJ.budgetManagementApplication.user.reository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        User user = userRepository.findByAccount(account)
                .orElseThrow(() -> new UsernameNotFoundException("Not found " + account));

        return new UserDetailsImpl(user);
    }
}
