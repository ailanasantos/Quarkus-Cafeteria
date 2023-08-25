package br.unitins.dto;

import jakarta.validation.constraints.NotBlank;

public record CafeDTO(

@NotBlank(message = "O campo nome deve ser informado.")
    String sabor,

    String quantidade,

    String temperatura

){ }
    

