package br.unitins.service;

import java.util.List;

import br.unitins.dto.CafeDTO;
import br.unitins.dto.CafeResponseDTO;

public interface CafeService {

        // recursos basicos
        List<CafeResponseDTO> getAll();

        CafeResponseDTO findById(Long id);
    
        CafeResponseDTO create(CafeDTO dto);
    
        CafeResponseDTO update(Long id, CafeDTO dto);
    
        void delete(Long id);
    
        // recursos extras
    
        List<CafeResponseDTO> findByNome(String nome);
    
        long count();
    
}