package org.rest;

import lombok.*;
import org.entities.Computer;
import org.persistence.ComputersDAO;
import org.persistence.ManufacturersDAO;
import org.rest.contracts.ComputerDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/computers")
public class ComputersController {

    @Inject
    @Setter @Getter
    private ComputersDAO computersDAO;

    @Inject
    @Setter @Getter
    private ManufacturersDAO manufacturerDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Computer computer = computersDAO.findOne(id);
        if (computer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ComputerDto computerDto = new ComputerDto();
        computerDto.setName(computer.getName());
        computerDto.setManufacturerName(computer.getManufacturer().getName());
        computerDto.setManufacturerId(computer.getManufacturer().getId());

        return Response.ok(computerDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer computerId,
            ComputerDto computerData) {
        try {
            Computer existingComputer = computersDAO.findOne(computerId);
            if (existingComputer == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingComputer.setName(computerData.getName());
            computersDAO.update(existingComputer);
            return Response.ok().build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(ComputerDto computerData) {
        try {
            Computer newComputer = new Computer();
            newComputer.setName(computerData.getName());
            newComputer.setManufacturer(manufacturerDAO.findOne(computerData.getManufacturerId()));
            computersDAO.persist(newComputer);
            return Response.ok().build();
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
