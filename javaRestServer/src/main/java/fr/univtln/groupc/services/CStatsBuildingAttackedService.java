package fr.univtln.groupc.services;

import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.stats.CStatsBuildingsAttacked;
import fr.univtln.groupc.stats.CStatsPlayer;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by arouani277 on 04/05/16.
 */
@Path("/statsBuildingAttacked")
public class CStatsBuildingAttackedService {
    private CCrudMethods mCrudMethods = new CCrudMethods();

    /**
     * @param pStatsBuildingsAttacked
     */
    @POST
    @Consumes("application/json")
    @Path("create")
    public void createField(CStatsBuildingsAttacked pStatsBuildingsAttacked){
        mCrudMethods.create(pStatsBuildingsAttacked);
    }

    /**
     * @param pId
     * @return CStatsBuildingsAttacked
     */
    @GET
    @Path("/{id}")
    public CStatsBuildingsAttacked read(@PathParam("id") int pId){
        return mCrudMethods.find(CStatsBuildingsAttacked.class, pId);
    }

    /**
     * @return List<CStatsPlayer>
     */
    @GET
    @Path("/all")
    public List<CStatsBuildingsAttacked> readAll(){
        return mCrudMethods.findWithNamedQuery(CStatsBuildingsAttacked.GET_ALL);
    }

    /**
     * @param pStatsBuildingsAttacked
     * @return CStatsPlayer
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/update")
    public CStatsBuildingsAttacked updateTeam(CStatsBuildingsAttacked pStatsBuildingsAttacked){
        return mCrudMethods.update(pStatsBuildingsAttacked);
    }
}
