package com.umbrella.projectumbrella.dto;

import lombok.Builder;

@Builder
public record MailBody(String to, String from, String text, String subject) {
}
