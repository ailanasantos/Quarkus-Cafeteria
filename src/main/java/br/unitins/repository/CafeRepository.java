package br.unitins.repository;

import java.util.List;

import br.unitins.model.Cafe;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CafeRepository implements PanacheRepository<Cafe> {
    
    public List<Cafe> findByNome(String nome){
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%"+nome.toUpperCase()+"%").list();
    }

}
