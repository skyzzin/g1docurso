package com.g1.curso.dtos;

import java.util.UUID;

public record CommitDTO(UUID uuid_post,String sender,String recived,String content,String createdat) {

}
