package tads.eaj.ufrn.imoveis.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tads.eaj.ufrn.imoveis.dominio.Imovel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImovelRequest {
    private String endereco;
    private double valor;
    private String tipo;
    private String CEP;

    public Imovel build() {
        return new Imovel()
                .setValor(this.valor)
                .setTipo(this.tipo)
                .setCEP(this.CEP)
                .setEndereco(this.endereco);
    }
}
