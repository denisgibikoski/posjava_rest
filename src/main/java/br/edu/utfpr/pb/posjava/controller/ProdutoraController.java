/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.posjava.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.edu.utfpr.pb.posjava.model.Produtora;
import br.edu.utfpr.pb.posjava.repository.ProdutoraRepository;
import br.edu.utfpr.pb.posjava.repository.Repository;
import javax.inject.Inject;

@Controller
@Path("/produtora")
public class ProdutoraController extends AbstractController<Produtora, Long>{
    @Inject
    private ProdutoraRepository produtoraRepository;

    @Override
    protected Repository getRepository() {
     return produtoraRepository;
    
    }
    
    
    
    
    
    
}

/**
 *
 * @author denis

@Controller
@Path("/produtora")
public class ProdutoraController {

    
    private Result result;

    private ProdutoraRepository produtoraRepository;

  
    public ProdutoraController() {
    }

    @Inject
    public ProdutoraController(Result result, ProdutoraRepositoryImpl produtoraRepositoryImpl) {
        this.result = result;
        this.produtoraRepository = produtoraRepositoryImpl;

    }

    @Get
    @Path(value = {"", "/"})
    public void list() {

        result.use(json())
                .withoutRoot()
                .from(produtoraRepository.findAll())
                .serialize();

    }
    
    @Get
    @Path(value =  "/{id}")
    public void buscar(Long id) {

        result.use(json())
                .withoutRoot()
                .from(produtoraRepository.findOne(id))
                .serialize();

    }
    @Post
    @Path(value = {"/",""})
    @Consumes(value = "application/json",options = WithoutRoot.class )
    public void salvar(Produtora produtora){
        produtoraRepository.save(produtora);
        result.use(status()).created();
    }
    
    @Put
    @Path(value = {"/",""})
    @Consumes(value = "application/json",options = WithoutRoot.class )
    public void atualizar(Produtora produtora){
        produtoraRepository.save(produtora);
        result.use(status()).ok();
        
    }
    
    @Delete
    @Path(value =  "/{id}")
    public void remove(Long id){
        produtoraRepository.remove(id);
        result.use(status()).ok();
        
    }
    
    @Delete
    @Path(value = {"/",""})
    @Consumes(value = "application/json",options = WithoutRoot.class )
    public void remove(Produtora produtora){
        produtoraRepository.remove(produtora.getId());
        result.use(status()).ok();
        
    }

}
*/