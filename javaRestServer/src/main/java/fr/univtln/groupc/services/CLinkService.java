package fr.univtln.groupc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.algorithm.CAlgorithm;
import fr.univtln.groupc.dao.CCrudMethods;
import fr.univtln.groupc.entities.CFieldEntity;
import fr.univtln.groupc.entities.CLinkEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by mpesnel786 on 03/05/16.
 */

@Path("/links")
public class CLinkService {
    private CCrudMethods mCrudMethods = new CCrudMethods();
    private ObjectMapper mMapper = new ObjectMapper();


    @POST
    @Consumes("application/json")
    public Response createLink(String pLinkJson){
        /*if (pLink.algoCreateLink(pLink.getPortals().get(1),pLink.getPortals().get(2))==true) {
            mCrudMethods.create(pLink);
        }*/
        CLinkEntity lLink = null;
        List<CLinkEntity> lLinkStorageField = new ArrayList<>();
        List<CLinkEntity> lLinkListField =new ArrayList<>();
        List<CFieldEntity> lListFieldToCreate = new ArrayList<>();
        List<CLinkEntity> lLinks = mCrudMethods.findWithNamedQuery(CLinkEntity.GET_ALL);
        List<CFieldEntity> lFields =mCrudMethods.findWithNamedQuery(CFieldEntity.GET_ALL);
        System.out.println("Lfields: "+lFields);
        //System.out.println("pLinkJson = = = " + pLinkJson);

        try {
            lLink = mMapper.readValue(pLinkJson, CLinkEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
       // System.out.println("llink ->->-> " + lLink);
        System.out.println("Pre-Detection CrossLink");

        if (CAlgorithm.detectColision(lLink, lLinks, lFields)){
            System.out.println("Detection succesfull \n!!!!!!!!!!!!!!!" + lLink+"!!!!!!!!!!!!!!");

            mCrudMethods.create(lLink);


            System.out.println("Pre-Detection Field");
            lLinkListField = CAlgorithm.detecteNewFields(lLink);
            //System.out.println("ListeLinkField = "+lLinkListField);

            for(int li=0;li<lLinkListField.size();li+=3){

                for(int lu=0;lu<3;lu++){
                    lLinkStorageField.add(lLinkListField.get(lu+li));
                }
               lListFieldToCreate.add( new CFieldEntity.CFieldBuilder(456).links(lLinkStorageField).build() );
            }

            System.out.println("Pre trie: "+lListFieldToCreate);
            Collections.sort(lListFieldToCreate);
            System.out.println("Aprés trie: " + lListFieldToCreate);
            System.out.println("Pre-CreationField");

            for(CFieldEntity lField : lListFieldToCreate){
                System.out.println("!!!!" + lField + "!!!!");
                mCrudMethods.create(lField);
                for (CLinkEntity lLinkInField : lField.getLinks()){
                    mCrudMethods.update(lLinkInField);
                }
            }

            return Response.status(201).entity(pLinkJson).build();
        }
        else{
            System.out.println("Detection Faild");
            return Response.status(500).build();
         // erreur a costumiser
        }


    }


    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String read(@PathParam("id") int pId){

        String lJsonValue = null;
        CLinkEntity lLink = (CLinkEntity)mCrudMethods.find(CLinkEntity.class, pId);
        try {
            lJsonValue = mMapper.writeValueAsString(lLink);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }


    @GET
    @Produces("application/json")
    public String readAll(){
        String lJsonValue = null;
        List<CLinkEntity> lLinks = (List<CLinkEntity>)mCrudMethods.findWithNamedQuery(CLinkEntity.GET_ALL);
        try {
            lJsonValue = mMapper.writeValueAsString(lLinks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lJsonValue;
    }

    @PUT
    @Consumes("application/json")
    public Response updateLink(String pLinkJson){
        CLinkEntity lLink = null;
        try {
            lLink = mMapper.readValue(pLinkJson, CLinkEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCrudMethods.update(lLink);
        return Response.status(200).build();
    }


    @DELETE
    @Consumes("application/json")
    @Path("/{id}")
    public Response deleteLink(@PathParam("id") int pId){
        mCrudMethods.delete(CLinkEntity.class, pId);
        return Response.status(200).build();
    }
}
