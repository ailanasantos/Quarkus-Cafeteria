package br.unitins.dto;

import br.unitins.model.Cafe;

public record CafeResponseDTO (
    Long id,
    String sabor,
    String quantidade,
    String temperatura


){

    public static CafeResponseDTO valueOf(Cafe cafe) {
        return new CafeResponseDTO(
            cafe.getId(),
            cafe.getSabor(),
            cafe.getQuantidade(),
            cafe.getTemperatura()
         );
    }

}
    



