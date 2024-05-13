package com.g1.curso.dtos;

import java.util.List;

public record UserDTO(String username, String avatar, String email, String password, List<String> notifys, List<String> posts) {

}
