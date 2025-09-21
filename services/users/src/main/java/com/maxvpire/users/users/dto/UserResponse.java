package com.maxvpire.users.users.dto;

import com.maxvpire.users.users.Role;

public record UserResponse(
        String id,
        String username,
        String mainId,
        Role role
) {
}
