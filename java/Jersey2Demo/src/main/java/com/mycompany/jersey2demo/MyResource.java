package com.mycompany.jersey2demo;

import com.mycompany.jersey2demo.model.Person;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @GET
    @Path("/person")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPerson() {
        Person p = new Person();
        p.setFirstName("Paul");
        p.setLastName("Samsotha");
        return p;
    }

    @POST
    @Path("person")
    @Produces(MediaType.APPLICATION_JSON)
    public Person createPerson(Person person) {
        return person;
    }

}
