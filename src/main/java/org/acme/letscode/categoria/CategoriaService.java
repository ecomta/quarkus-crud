package org.acme.letscode.categoria;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;
import java.util.Optional;

@RequestScoped
public class CategoriaService {

    @Transactional(rollbackOn = Exception.class)
    public Categoria addCategoria(@Valid final Categoria categoria) {
        String nome = categoria.getNome();

        if (Categoria.find("nome = '" + nome + "'").count() > 0) {
            throw new IllegalArgumentException("Essa categoria já existe");
        }

        Categoria newCategoria = new Categoria();
        newCategoria.setNome(nome);

        // Usando o método static dentro da classe Categoria.
        Categoria.persist(newCategoria);
        return newCategoria;
    }

    @Transactional(rollbackOn = Exception.class)
    public Response updateCategoria(final Long id, final @Valid Categoria categoria) {
        Objects.requireNonNull(id, "Id não pode ser nulo ao atualizar uma categoria");
        Objects.requireNonNull(categoria, "Categoria não pode ser nula ao atualizar uma categoria");

        Optional<Categoria> updateCategoria = Categoria.findByIdOptional(id);

        updateCategoria.ifPresentOrElse(
                (foundedCategoria) -> {
                    Categoria.persist(categoria);
                },
                () -> {
                    throw new WebApplicationException("Essa categoria nãp existe, não pode ser atualizada.", Response.Status.NOT_FOUND);
                }
        );

        return Response
                .status(Response.Status.OK)
                .type(MediaType.TEXT_PLAIN)
                .entity("Categoria atualizada com sucesso.")
                .build();
    }

    @Transactional(rollbackOn = Exception.class)
    public Response deleteCategoria(final Long id) throws Exception {
        Objects.requireNonNull(id, "Id não pode ser nulo ao deletar uma categoria");
        Categoria deleteCategoria = this.getOneCategory(id);

        Categoria.deleteById(deleteCategoria.getId());

        return Response
                .status(Response.Status.OK)
                .type(MediaType.TEXT_PLAIN)
                .entity("Categoria deletada com sucesso")
                .build();
    }

    /**
     * Utilizando Optional para buscar um objeto categoria.
     * Se existir: retorna o próprio objeto, senão existir retorna uma exceção.
     */
    public Categoria getOneCategory(final Long id) {
        Objects.requireNonNull(id, "Id não pode ser nulo ao buscar uma categoria, informe um id.");
        Optional<Categoria> categoria = Categoria.findByIdOptional(id);
        return categoria.orElseThrow(() -> new WebApplicationException("Categoria não existe.", Response.Status.NOT_FOUND));
    }


}