package org.acme.letscode.exception;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ReferencialIntegrity implements ExceptionMapper<JdbcSQLIntegrityConstraintViolationException> {

    @Override
    public Response toResponse(JdbcSQLIntegrityConstraintViolationException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .type(MediaType.TEXT_PLAIN)
                .entity("salve salve...")
                .build();
    }

}