package com.umbrella.projectumbrella.dto;

import lombok.Builder;

@Builder
public record ChangePassword(String password, String confirmPassword) {

}
