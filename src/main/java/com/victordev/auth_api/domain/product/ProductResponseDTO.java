package com.victordev.auth_api.domain.product;

import java.util.UUID;

public record ProductResponseDTO(UUID id, String name, Double price) {
}