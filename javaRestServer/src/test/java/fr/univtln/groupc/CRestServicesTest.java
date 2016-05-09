package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.server.CServer;
import junit.framework.TestCase;

/**
 * Created by marti on 06/05/2016.
 */
public class CRestServicesTest extends TestCase {

    Client c = Client.create();
    WebResource mWebResource = c.resource(CServer.BASE_URI);
    ObjectMapper mMapper = new ObjectMapper();

    public void testPostPlayerService() throws Exception {
        CPlayerEntity lPlayerToPost = new CPlayerEntity.CPlayerBuilder(1).email("email_de_test").energy(50).build();
        String lJsonPlayer = mMapper.writeValueAsString(lPlayerToPost);
        mWebResource.path("players/create").post(lPlayerToPost);
    }
/*
    public void testGetByIdPlayerService() throws Exception {
        CPlayerEntity lPlayerGotten = mMapper.readValue(mWebResource.path("players/1").get(String.class), CPlayerEntity.class);
        // need post method first
        assertEquals(lPlayerGotten.getId(), 1);
        assertEquals(lPlayerGotten.getEmail(), "email_de_test");
        assertEquals(lPlayerGotten.getEnergy(), 50);
        // etc

    }
*/
}