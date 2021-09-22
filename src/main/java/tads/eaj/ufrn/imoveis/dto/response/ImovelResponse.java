package tads.eaj.ufrn.imoveis.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import tads.eaj.ufrn.imoveis.dominio.Imovel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data

public class ImovelResponse extends RepresentationModel<ImovelResponse> {

    private String endereco;
    private double valor;
    private String tipo;
    private String CEP;

    public ImovelResponse(Imovel i){
        setEndereco(i.getEndereco());
        setValor(i.getValor());
        setTipo(i.getTipo());
        setCEP(i.getCEP());
        this.add(linkTo(ImovelResponse.class).slash(i.getId()).withSelfRel());
        this.add(linkTo(ImovelResponse.class).withRel("listar imoveis"));
        this.add(linkTo(ImovelResponse.class).slash(i.getId()).withRel("editar imovel"));
        this.add(linkTo(ImovelResponse.class).slash(i.getId()).withRel("excluir imovel"));

    }
}
