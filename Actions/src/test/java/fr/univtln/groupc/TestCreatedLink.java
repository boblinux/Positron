package fr.univtln.groupc;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.univtln.groupc.entities.CLinkEntity;
import junit.framework.TestCase;

/**
 * Created by marti on 01/06/2016.
 */
public class TestCreatedLink extends TestCase{
    private ObjectMapper mMapper = new ObjectMapper();


    public void testSerDeser() throws Exception {
        System.out.println(mMapper.writeValueAsString(new CLinkCreated.CLinkCreatedBuilder().build()));
    }

    public void testSerDeserCreateLink() throws Exception {
        System.out.println(mMapper.writeValueAsString(new CCreateLink.CCreateLinkBuilder().build()));
    }
}
