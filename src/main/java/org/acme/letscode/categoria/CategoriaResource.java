package org.acme.letscode.categoria;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaResource {

    @Inject
    CategoriaService categoriaService;


    @GET
    @Path("/all_categorias")
    @Tag(name = "Categoria")
    @Operation(summary = "Listar todas as categorias.")
    @APIResponse(
            responseCode = "200",
            description = "Categorias retornadas com sucesso.",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Categoria.class))
            }
    )
    public Response getAllCategories() {
        return Response
                .status(Response.Status.OK)
                .entity(Categoria.listAll())
                .build();
    }

    @GET
    @Path("/{id}")
    @Tag(name = "Categoria")
    @Operation(
            summary = "Buscar uma categoria.",
            description = "Informe um id -> Receba os dados de uma categoria."
    )
    @APIResponses({
            @APIResponse(
                    name = "OK",
                    responseCode = "200",
                    description = "Categoria foi encontrada.",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = Categoria.class))
                    }
            ),
            @APIResponse(
                    name = "Not Found",
                    responseCode = "404",
                    description = "Categoria não encontrada."
            )
    })
    public Response getOneCategory(@PathParam("id") Long id) {
        return Response
                .status(Response.Status.OK)
                .entity(categoriaService.getOneCategory(id))
                .build();
    }


    @POST
    @Tag(name = "Categoria")
    @Operation(summary = "Cadastrar uma nova categoria.")
    @APIResponses({
            @APIResponse(
                    responseCode = "201",
                    description = "Categoria cadastrada com sucesso.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Categoria.class)
                            )
                    }
            ),
            @APIResponse(
                    responseCode = "400",
                    description = "Dados inválidos ao inserir uma categoria."
            )
    })
    public Response addCategoria(final Categoria categoria) {
        return Response
                .status(Response.Status.CREATED)
                .entity(categoriaService.addCategoria(categoria))
                .build();
    }

    @PUT
    @Path("/{id}")
    @Tag(name = "Categoria")
    @Operation(summary = "Atualizar uma categoria")
    @APIResponses({
            @APIResponse(
                    name = "OK",
                    responseCode = "200",
                    description = "Categoria atualizada com sucesso.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = Categoria.class)
                    )
            ),
            @APIResponse(
                    name = "BAD_REQUEST",
                    responseCode = "400",
                    description = "Dado inválido ao atualizar uma categoria."
            ),
            @APIResponse(
                    name = "NOT_FOUND",
                    responseCode = "404",
                    description = "Não existe essa categoria. Não há como atualizar."
            )
    })
    public Response updateCategoria(final @PathParam("id") Long id, Categoria categoria) {
        return Response
                .status(Response.Status.OK)
                .entity(categoriaService.updateCategoria(id, categoria))
                .build();
    }

    @DELETE
    @Path("/{id}")
    @Tag(name = "Categoria")
    @Operation(
            summary = "Deletar uma categoria",
            description = "Informe um Id para deletar uma categoria."
    )
    @APIResponses({
            @APIResponse(
                    name = "OK",
                    responseCode = "200",
                    description = "Categoria deletada com sucesso."
            ),
            @APIResponse(
                    name = "NOT_FOUND",
                    responseCode = "404",
                    description = "Não existe essa categoria. Não tem como deletar."
            )
    })
    public Response deleteCategoria(@PathParam("id") final Long id) throws Exception {
        return Response
                .status(Response.Status.OK)
                .entity(categoriaService.deleteCategoria(id))
                .build();
    }

}