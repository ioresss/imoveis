package tads.eaj.ufrn.imoveis.serviço;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tads.eaj.ufrn.imoveis.dominio.Imovel;
import tads.eaj.ufrn.imoveis.dominio.Pessoa;
import tads.eaj.ufrn.imoveis.dto.request.ImovelRequest;
import tads.eaj.ufrn.imoveis.dto.request.PessoaRequest;
import tads.eaj.ufrn.imoveis.dto.response.ImovelResponse;
import tads.eaj.ufrn.imoveis.dto.response.PessoaResponse;
import tads.eaj.ufrn.imoveis.repositorio.ImovelRepository;
import tads.eaj.ufrn.imoveis.repositorio.PessoaRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class PessoaService {
    PessoaRepository pessoaRepository;

    @Autowired
    public void setImovelRepository(PessoaRepository pessoaRepository){
        this.pessoaRepository = pessoaRepository;
    }

    public Collection<PessoaResponse> listar(){
        Collection<PessoaResponse> lista = new ArrayList<>();
        for (Pessoa pessoa : pessoaRepository.findAllByDeletedIsNull()){
            lista.add(new PessoaResponse(pessoa));
        }
        return lista;
    }

    public void inserir(PessoaRequest pessoaRequest){
        pessoaRepository.save(pessoaRequest.build());
    }

    public Pessoa editar(Pessoa pessoa){
        pessoaRepository.saveAndFlush(pessoa);
        return pessoa;
    }

    public void excluir(Pessoa pessoa){
        pessoa.setDeleted(new Date());
       pessoaRepository.saveAndFlush(pessoa);
    }
    public Optional<Pessoa> buscar(Long id){
        return pessoaRepository.findById(id);
    }
}
