package br.unitins.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import br.unitins.dto.CafeDTO;
import br.unitins.dto.CafeResponseDTO;
import br.unitins.model.Cafe;
import br.unitins.repository.CafeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CafeServicelmpl implements CafeService {
    
    @Inject
    CafeRepository cafeRepository;

    @Inject 
    Validator validator;

    @Override
    public List<CafeResponseDTO> getAll(){
        List<Cafe> list = cafeRepository.listAll();
        return list.stream().map(e -> CafeResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public CafeResponseDTO findById(Long id) {
        Cafe cafe = cafeRepository.findById(id);
        if (cafe == null)
            throw new NotFoundException("Estado não encontrado.");
        return CafeResponseDTO.valueOf(cafe);
    }

    @Override
    @Transactional
    public CafeResponseDTO create(CafeDTO cafeDTO) throws ConstraintViolationException {
       validar(cafeDTO);

       Cafe entity = new Cafe();
       entity.setSabor(cafeDTO.sabor());
       entity.setQuantidade(cafeDTO.quantidade());
       entity.setTemperatura(cafeDTO.temperatura());

       cafeRepository.persist(entity);

       return CafeResponseDTO.valueOf(entity);
    }

    @Override
    public CafeResponseDTO update(Long id, CafeDTO cafeDTO) {
       
       validar(cafeDTO);

       Cafe entity = new Cafe();
       entity.setSabor(cafeDTO.sabor());
       entity.setQuantidade(cafeDTO.quantidade());
       entity.setTemperatura(cafeDTO.temperatura());

       cafeRepository.persist(entity);

       return CafeResponseDTO.valueOf(entity);
    }


    private void validar(CafeDTO cafeDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<CafeDTO>> violations = validator.validate(cafeDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }
    @Override
    public void delete(Long id) {
        cafeRepository.deleteById(id);
    }

    @Override
    public List<CafeResponseDTO> findByNome(String nome) {
        List<Cafe> list = cafeRepository.findByNome(nome);
        return list.stream().map(e -> CafeResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return cafeRepository.count();
    }

}
