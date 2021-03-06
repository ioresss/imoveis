package tads.eaj.ufrn.imoveis.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import tads.eaj.ufrn.imoveis.dominio.Imovel;

import java.util.Collection;
import java.util.List;

public interface ImovelRepository extends JpaRepository<Imovel, Long> {
    Collection<Imovel> findAllByDeletedIsNull();
}
