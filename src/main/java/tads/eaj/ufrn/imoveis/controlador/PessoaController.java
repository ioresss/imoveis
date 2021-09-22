package tads.eaj.ufrn.imoveis.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tads.eaj.ufrn.imoveis.dominio.Pessoa;
import tads.eaj.ufrn.imoveis.dto.request.PessoaRequest;
import tads.eaj.ufrn.imoveis.dto.response.PessoaResponse;
import tads.eaj.ufrn.imoveis.serviço.PessoaService;

import java.net.URI;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {
    private PessoaService pessoaService;

    @Autowired
    public void  setPessoaService(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity inserir(@RequestBody PessoaRequest pessoaRequest){
        pessoaService.inserir(pessoaRequest);
        Pessoa pessoa = pessoaRequest.build();
        return ResponseEntity.created(URI.create("/pessoa/"+pessoa.getId())).body(new PessoaResponse(pessoa));
    }

    @GetMapping
    public ResponseEntity listar(){
        return ResponseEntity.ok().body(pessoaService.listar());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity buscar(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaService.buscar(id);

        if(pessoa.isPresent()) {
            return  ResponseEntity.ok().body(new PessoaResponse(pessoa.get()));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity editar(@RequestBody PessoaRequest pessoaRequest, @PathVariable Long id){
        Optional<Pessoa> pessoa = pessoaService.buscar(id);
        if(pessoa.isPresent()){
            return ResponseEntity.ok().body(new PessoaResponse(pessoaService.editar(pessoaRequest.build().setId(id))));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity excluir(@PathVariable Long id){
        Optional<Pessoa> pessoa = pessoaService.buscar(id);
        if(pessoa.isPresent()){
            pessoaService.excluir(pessoa.get());
            return ResponseEntity.ok(linkTo(PessoaController.class).withRel("listar imoveis"));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
