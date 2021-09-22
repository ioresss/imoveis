package tads.eaj.ufrn.imoveis.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import tads.eaj.ufrn.imoveis.dominio.Imovel;

public interface ImovelRepository extends JpaRepository<Imovel, Long> {
}
