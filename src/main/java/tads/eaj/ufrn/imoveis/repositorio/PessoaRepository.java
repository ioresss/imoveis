package tads.eaj.ufrn.imoveis.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import tads.eaj.ufrn.imoveis.dominio.Imovel;
import tads.eaj.ufrn.imoveis.dominio.Pessoa;

import java.util.Collection;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Collection<Pessoa> findAllByDeletedIsNull();
}
