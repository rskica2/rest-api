/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.jaxrs;

import javax.json.JsonException;
import javax.json.JsonObject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.*;
import javax.sql.*;
import java.io.*;
import javax.naming.*;

/**
 * REST Web Service
 *
 * @author rober
 */
@Path("/messages")
public class ApiResource {

    @Context
    private UriInfo context;
    private String name = "";
    private String status ="";
    private String created_at = "";
    private String documentation = "";
    

    /**
     * Creates a new instance of ApiResource
     */
    public ApiResource() {
    }

    /**
     * Retrieves representation of an instance of pl.java.jaxrs.ApiResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJSON() {
        String responseContent = "{\"status\": \"success\"}";
        System.out.println("getJSON call");
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        return Response.ok().entity(responseContent).build();
    }
    /**
     * PUT method for updating or creating an instance of ApiResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putJSON(String content) {
        System.out.println("putJSON call");
        if (content == null) {
            return Response.status(400).build();
        }
        //System.out.println("JSON: "+ content);
        // return Response.status(Status.NOT_FOUND).build();
        return Response.ok().entity(content).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJSON(JsonObject inputJsonObj) throws JsonException{
        System.out.println("postJSON call");
        
        String fname = inputJsonObj.toString();
        System.out.println("fname: " + fname);
        try {
            JsonObject _meta = inputJsonObj.getJsonObject("meta");
            JsonObject _incident = inputJsonObj.getJsonObject("incident");
            
            documentation = _meta.getString("documentation");
 
            name = _incident.getString("name");
            status = _incident.getString("status");
            created_at = _incident.getString("created_at");
                
        
        System.out.println("1.meta_documentation = " + documentation);
        System.out.println("2.incident_name = " + name);
        System.out.println("3.incident_status = " + status);
        System.out.println("4.incident_created_at = " + created_at);
                
        // store data in database 
        
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/DefaultDataSource");
        //ds = (DataSource) ctx.lookup("jdbc/MySQLDataSource");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        String cmdSql = "delete from incidents";
        stmt.executeUpdate(cmdSql);
        cmdSql = "insert into incidents (name, status, created_at) values ('"+name+"', '"+status+"', '"+created_at+"')";
        stmt.executeUpdate(cmdSql);
        
        conn.commit();
        conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        //System.out.println("2.incident_status = " + status);
        //System.out.println("3.incident_created_at = " + created_at);
        
        /* if (content == null) {
            System.out.println("content null");
            return Response.status(400).build();
        } else {
        } 
        */
        //System.out.println("JSON: "+ content);
        // return Response.status(Status.NOT_FOUND).build();
          return Response.status(201).entity(fname).build();
//        return Response.ok().entity(content).build();
    }}
}

