package tads.eaj.ufrn.imoveis.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tads.eaj.ufrn.imoveis.dominio.Imovel;
import tads.eaj.ufrn.imoveis.dto.request.ImovelRequest;
import tads.eaj.ufrn.imoveis.dto.response.ImovelResponse;
import tads.eaj.ufrn.imoveis.serviço.ImovelService;

import java.net.URI;
import java.util.Date;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/imovel")
public class ImovelController {

    private ImovelService imovelService;

    @Autowired
    public void  setImovelService(ImovelService imovelService) {
        this.imovelService = imovelService;
    }

    @PostMapping
    public ResponseEntity inserir(@RequestBody ImovelRequest imovelRequest){
        imovelService.inserir(imovelRequest);
        Imovel imovel = imovelRequest.build();
        return ResponseEntity.created(URI.create("/imovel/"+imovel.getId())).body(new ImovelResponse(imovel));
    }

    @GetMapping
    public ResponseEntity listar(){
        return ResponseEntity.ok().body(imovelService.listar());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity buscar(@PathVariable Long id) {
        Optional<Imovel> imovel = imovelService.buscar(id);

        if(imovel.isPresent()) {
            return  ResponseEntity.ok().body(new ImovelResponse(imovel.get()));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity editar(@RequestBody ImovelRequest imovelRequest, @PathVariable Long id){
        Optional<Imovel> imovel = imovelService.buscar(id);
        if(imovel.isPresent()){
            return ResponseEntity.ok().body(new ImovelResponse(imovelService.editar(imovelRequest.build().setId(id))));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity excluir(@PathVariable Long id){
        Optional<Imovel> imovel = imovelService.buscar(id);
        if(imovel.isPresent()){
            imovelService.excluir(imovel.get());
            return ResponseEntity.ok(linkTo(ImovelController.class).withRel("listar imoveis"));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
