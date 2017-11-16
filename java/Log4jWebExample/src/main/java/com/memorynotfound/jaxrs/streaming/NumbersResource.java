package com.memorynotfound.jaxrs.streaming;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.*;

@Path("/numbers")
public class NumbersResource {

    @GET
    public Response streamExample(){
        StreamingOutput stream = new StreamingOutput() {
            @Override
            public void write(OutputStream out) throws IOException, WebApplicationException {
                Writer writer = new BufferedWriter(new OutputStreamWriter(out));
                for (int i = 0; i < 100 ; i++){
                    writer.write(i + " ");
                }
                writer.flush();
            }
        };
        return Response.ok(stream).build();
    }

}
