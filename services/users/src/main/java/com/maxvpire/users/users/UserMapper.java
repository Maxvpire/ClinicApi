package com.maxvpire.users.users;

import com.maxvpire.users.users.dto.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getMainId(),
                user.getRole()
        );
    }
}
