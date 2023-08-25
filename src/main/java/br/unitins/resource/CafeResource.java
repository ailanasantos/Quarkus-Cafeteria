package br.unitins.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.application.Result;
import br.unitins.dto.CafeDTO;
import br.unitins.dto.CafeResponseDTO;
import br.unitins.service.CafeService;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/cafes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CafeResource {

    @Inject
    CafeService cafeService;
    
private static final Logger LOG = Logger.getLogger(CafeResource.class);

    @GET
    public List<CafeResponseDTO> getAll() {
        LOG.info("Buscando todos os cafes.");
        LOG.debug("ERRO DE DEBUG.");
        return cafeService.getAll();
    }

    @GET
    @Path("/{id}")
    public CafeResponseDTO findById(@PathParam("id") Long id) {
        return cafeService.findById(id);
    }

    @POST
    public Response insert(CafeDTO dto) {
        LOG.infof("Inserindo um cafe: %s", dto.sabor());
        Result result = null;
        try {
            CafeResponseDTO cafe = cafeService.create(dto);
            LOG.infof("Cafe (%d) criado com sucesso.", cafe.id());
            return Response.status(Status.CREATED).entity(cafe).build();
        } catch(ConstraintViolationException e) {
            LOG.error("Erro ao incluir um cafe.");
            LOG.debug(e.getMessage());
            result = new Result(e.getConstraintViolations());
        } catch (Exception e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());
            result = new Result(e.getMessage(), false);
        }
        return Response.status(Status.NOT_FOUND).entity(result).build();

    }    

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, CafeDTO dto) {
        try {
            CafeResponseDTO cafe = cafeService.update(id, dto);
            return Response.ok(cafe).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }      
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        cafeService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public long count(){
        return cafeService.count();
    }

    @GET
    @Path("/search/{nome}")
    public List<CafeResponseDTO> search(@PathParam("nome") String nome){
        return cafeService.findByNome(nome);
        
    }
}

