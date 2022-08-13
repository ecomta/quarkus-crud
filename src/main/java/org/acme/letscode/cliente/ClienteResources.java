package org.acme.letscode.cliente;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SessionScoped
public class ClienteResources {

    @Inject
    ClienteService clienteService;

    @GET
    @Path("{id}")
    @Tag(name = "Cliente")
    public Response getCliente(@PathParam("id") final UUID id) {
        return Response
                .ok(clienteService.getCliente(id))
                .build();
    }

    @GET
    @Path("/all")
    @Tag(name = "Cliente")
    public Response getAllClientes() {
        return Response
                .status(Response.Status.OK)
                .entity(new ClienteRepository().getAllClientes())
                .build();
    }

    @POST
    @Tag(name = "Cliente")
    @Operation(
            summary = "Cadastrar um novo cliente."
    )
    @APIResponse(
            name = "Cadastro realizado com sucesso",
            description = "Cadastro de um novo cliente realizado com sucesso.",
            responseCode = "201",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ClienteDTO.class)
                    )
            }
    )
    public Response addCliente(ClienteDTO clienteDTO) {
        return Response
                .status(Response.Status.OK)
                .entity(clienteService.validateClienteBeforeInsertIt(clienteDTO))
                .build();
    }

}
