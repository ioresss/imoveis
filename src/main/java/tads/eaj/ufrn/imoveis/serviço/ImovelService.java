package tads.eaj.ufrn.imoveis.serviço;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tads.eaj.ufrn.imoveis.dominio.Imovel;
import tads.eaj.ufrn.imoveis.dto.request.ImovelRequest;
import tads.eaj.ufrn.imoveis.dto.response.ImovelResponse;
import tads.eaj.ufrn.imoveis.repositorio.ImovelRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class ImovelService {

    ImovelRepository imovelRepository;

    @Autowired
    public void setImovelRepository(ImovelRepository imovelRepository){
        this.imovelRepository = imovelRepository;
    }

    public Collection<ImovelResponse> listar(){
        Collection<ImovelResponse> lista = new ArrayList<ImovelResponse>();
        for (Imovel imovel : imovelRepository.findAll()){
            if(imovel.getDeleted()!=null){
                lista.add(new ImovelResponse(imovel));
            }
        }

        return lista;
    }

    public void inserir(ImovelRequest imovelRequest){
        imovelRepository.save(imovelRequest.build());
    }

    public Imovel editar(Imovel imovel){
        imovelRepository.saveAndFlush(imovel);
        return imovel;
    }

    public void excluir(Imovel imovel){
        imovel.setDeleted(new Date());
        imovelRepository.saveAndFlush(imovel);
    }
    public Optional<Imovel> buscar(Long id){
        return imovelRepository.findById(id);
    }
}
