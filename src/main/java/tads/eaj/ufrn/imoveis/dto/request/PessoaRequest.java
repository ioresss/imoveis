package tads.eaj.ufrn.imoveis.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tads.eaj.ufrn.imoveis.dominio.Imovel;
import tads.eaj.ufrn.imoveis.dominio.Pessoa;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaRequest {
    private String nome;
    private String cpf;
    private String rg;
    private String profissao;
    private Collection<ImovelRequest> propriedades;

    public Pessoa build() {
        return new Pessoa()
                .setNome(this.nome)
                .setCpf(this.cpf)
                .setRg(this.rg)
                .setProfissao(this.profissao)
                .setPropriedades(this.build().getPropriedades());
    }
}
