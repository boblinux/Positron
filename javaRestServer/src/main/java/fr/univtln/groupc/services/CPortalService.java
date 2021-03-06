package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.dao.CQueryParameter;
import fr.univtln.groupc.entities.CPortalEntity;
import org.codehaus.jackson.map.DeserializationConfig;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ContextResolver;
import java.io.IOException;
import java.util.List;

/**
 * Created by marti on 03/05/2016.
 */

@Path("/portals")
public class CPortalService implements ContextResolver<ObjectMapper>{

    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();

    @POST
    @Consumes("application/json")
    public Response createPortal(String pPortalJson){
        CPortalEntity lPortal = null;
        try {
            lPortal = mMapper.readValue(pPortalJson, CPortalEntity.class);
            System.out.println("portal -> " + lPortal);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.create(lPortal);
        return Response.status(201).entity(pPortalJson).build();

    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String readPortal(@PathParam("id") int pId){
        mCrudMethods.clearCache();
        String lJsonValue = null;
        CPortalEntity lPortal = (CPortalEntity)mCrudMethods.find(CPortalEntity.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lPortal);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }

    /*
     * Liste tous les portails appartenant a une team
     */

    @GET
    @Produces("application/json")
    @Path("/teams/{id}")
    public String readPortalsByTeam(@PathParam("id") int pId){
        String lJsonValue = null;
        List<CPortalEntity> lPortals = (List<CPortalEntity>)mCrudMethods.findWithNamedQuery(CPortalEntity.GET_BY_TEAM, CQueryParameter.with("mId", pId).parameters());
        try {
            lJsonValue = mMapper.writeValueAsString(lPortals);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }


    @GET
    @Produces("application/json")
    public String readAll(){
        mMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String lJsonValue = null;
        List<CPortalEntity> lPortals = (List<CPortalEntity>)mCrudMethods.findWithNamedQuery(CPortalEntity.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lPortals);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("json ->\n" + lJsonValue);
        return lJsonValue;
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response updatePortal(String pPortalJson){
        mMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        CPortalEntity lPortal = null;
        System.out.println("-------->"+pPortalJson);
        try {
            lPortal = mMapper.readValue(pPortalJson, CPortalEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mCrudMethods.openTransaction()){
            mCrudMethods.update(lPortal);
            mCrudMethods.commitTransaction();
        }
        return Response.status(200).build();
    }

    @DELETE
    @Consumes("application/json")
    @Path("/{id}")
    public Response deletePortal(@PathParam("id") int pId){
        mCrudMethods.delete(CPortalEntity.class, pId);
        return Response.status(200).build();
    }


    @Override
    public ObjectMapper getContext(Class<?> aClass) {
        return mMapper;
    }
}
