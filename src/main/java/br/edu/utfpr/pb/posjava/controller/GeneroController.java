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
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.serialization.gson.WithoutRoot;
import static br.com.caelum.vraptor.view.Results.json;
import static br.com.caelum.vraptor.view.Results.status;
import br.edu.utfpr.pb.posjava.model.Genero;
import br.edu.utfpr.pb.posjava.repository.GeneroRepository;
import br.edu.utfpr.pb.posjava.repository.impl.GeneroRepositoryImpl;
import javax.inject.Inject;

/**
 *
 * @author denis
 */
@Controller
@Path("/genero")
public class GeneroController {

    private Result result;

    private GeneroRepository generoRepository;

    /**
     * @deprecated
     */
    public GeneroController() {
    }

    @Inject
    public GeneroController(Result result, GeneroRepositoryImpl generoRepositoryImpl) {
        this.result = result;
        this.generoRepository = generoRepositoryImpl;

    }

    @Get
    @Path(value = {"", "/"})
    public void list() {

        result.use(json())
                .withoutRoot()
                .from(generoRepository.findAll())
                .serialize();

    }
    
    @Get
    @Path(value =  "/{id}")
    public void buscar(Long id) {

        result.use(json())
                .withoutRoot()
                .from(generoRepository.findOne(id))
                .serialize();

    }
    @Post
    @Path(value = {"/",""})
    @Consumes(value = "application/json",options = WithoutRoot.class )
    public void salvar(Genero genero){
        generoRepository.save(genero);
        result.use(status()).created();
    }
    
    @Put
    @Path(value = {"/",""})
    @Consumes(value = "application/json",options = WithoutRoot.class )
    public void atualizar(Genero genero){
        generoRepository.save(genero);
        result.use(status()).ok();
        
    }

}
