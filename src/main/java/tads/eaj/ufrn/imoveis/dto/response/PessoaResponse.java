package tads.eaj.ufrn.imoveis.dto.response;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import tads.eaj.ufrn.imoveis.dominio.Imovel;
import tads.eaj.ufrn.imoveis.dominio.Pessoa;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
public class PessoaResponse  extends RepresentationModel<PessoaResponse> {
    private String nome;
    private String cpf;
    private String rg;
    private String profissao;
    private Collection<ImovelResponse> propriedades;

    public PessoaResponse(Pessoa pessoa){
        setNome(pessoa.getNome());
        setCpf(pessoa.getCpf());
        setRg(pessoa.getRg());
        setProfissao(pessoa.getProfissao());
        setPropriedades(new ArrayList<ImovelResponse>());
        for(Imovel imovel: pessoa.getPropriedades()){
            getPropriedades().add(new ImovelResponse(imovel));
        }
        this.add(linkTo(ImovelResponse.class).slash(pessoa.getId()).withSelfRel());
        this.add(linkTo(ImovelResponse.class).withRel("listar pessoas"));
        this.add(linkTo(ImovelResponse.class).slash(pessoa.getId()).withRel("editar pessoa"));
        this.add(linkTo(ImovelResponse.class).slash(pessoa.getId()).withRel("excluir pessoa"));

    }
}
