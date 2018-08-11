/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pb.posjava.controller;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.serialization.gson.WithoutRoot;
import static br.com.caelum.vraptor.view.Results.json;
import static br.com.caelum.vraptor.view.Results.status;
import br.edu.utfpr.pb.posjava.model.Serie;
import br.edu.utfpr.pb.posjava.repository.SerieRepository;
import br.edu.utfpr.pb.posjava.repository.impl.SerieRepositoryImpl;
import javax.inject.Inject;

/**
 *
 * @author denis
 */
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
