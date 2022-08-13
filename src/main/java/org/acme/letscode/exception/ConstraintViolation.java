package org.acme.letscode.exception;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstraintViolation implements ExceptionMapper<ConstraintViolationException> {

    /**
     * Pode ter 1 ou várias exceptions não importa.
     * A primeira que ocorrer já quero interromper a execução do programa.
     * Vai interromper até que não exista mais nenhuma exception.
     */
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .type(MediaType.TEXT_PLAIN)
                .entity(exception
                        .getConstraintViolations()
                        .stream()
                        .map(javax.validation.ConstraintViolation::getMessage)
                        .findFirst()
                        .get())
                .build();
    }

}
