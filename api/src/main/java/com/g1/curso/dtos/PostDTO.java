package com.g1.curso.dtos;

import java.util.List;
import java.util.UUID;

public record PostDTO(int id,UUID uuid_user, String title, String description, List<String> imgs, List<String> videos, String commits ,String createdat) {
}
