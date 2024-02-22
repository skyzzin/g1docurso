package com.g1.curso.dtos;

import java.util.UUID;

public record GlobalChatDTO(UUID user_post, String username, String content) {

}
