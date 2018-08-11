/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.posjava.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import static br.com.caelum.vraptor.view.Results.json;
import br.edu.utfpr.pb.posjava.model.Serie;
import br.edu.utfpr.pb.posjava.repository.Repository;
import br.edu.utfpr.pb.posjava.repository.SerieRepository;
import javax.inject.Inject;
@Controller
@Path(value = "/serie")
public class SerieController extends AbstractController<Serie, Long>{
        
    @Inject
    private SerieRepository serieRepository;

    @Override
    protected Repository getRepository() {
       return serieRepository;
    }
      
    @Override
    @Get
    @Path(value = {"","/"})
    public void findAll(){
        result.use(json())
                .withoutRoot()
                .from(getRepository().findAll())
                .include("genero")
                .include("produtora")
                .serialize();
    }

    @Override
    @Get
    @Path(value = "/page/")
    public void findAllPaged(int page, int size) {
    result.use(json())
                .withoutRoot()
                .from(getRepository().findAll(page, size))
                .include("genero")
                .include("produtora")
                .serialize();
        
    }
    
      
    
}


/**
 *
 * @author denis

@Controller
@Path(value = "/serie")
public class SerieController {
    
     private Result result;

    private SerieRepository serieRepository;

    @Deprecated
    public SerieController() {
    }

    @Inject
    public SerieController(Result result, SerieRepositoryImpl serieRepositoryImpl) {
        this.result = result;
        this.serieRepository = serieRepositoryImpl;
    }
    
    @Get
    @Path(value = {"", "/"})
    public void list() {

        result.use(json())
                .withoutRoot()
                .from(serieRepository.findAll())
                .include("genero")
                .include("produtora")
                .serialize();

    }
    
    @Post
    @Path(value = {"/",""})
    @Consumes(value = "application/json",options = WithoutRoot.class )
    public void salvar(Serie serie){
        serieRepository.save(serie);
        result.use(status()).created();
    }
        
    
}
*/