package tads.eaj.ufrn.imoveis.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String rg;
    private String profissao;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Imovel> propriedades;

    private Date deleted;
}
