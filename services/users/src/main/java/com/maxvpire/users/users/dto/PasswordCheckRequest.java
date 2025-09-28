package com.maxvpire.users.users.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordCheckRequest {
    @NotNull
    @Min(6)
    private String currentPassword;

    @NotNull
    @Min(6)
    private String newPassword;
}
