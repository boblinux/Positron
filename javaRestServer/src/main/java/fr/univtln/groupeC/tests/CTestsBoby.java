package fr.univtln.groupeC.tests;

import fr.univtln.groupeC.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by arouani277 on 02/05/16.
 */
public class CTestsBoby {
    public static void main(String[] args) {

        CTurretEntity c1 = new CTurretEntity
                .CTurretBuilder(78678687).level(10).damage(10).lifeTime(1111)
                .energy(150).energyMax(200).latitude(10.5)
                .longitude(11.2).name("c1").radius(100).build();

        CResonatorEntity cr = new CResonatorEntity.CResonatorBuilder(78687678).energy(100)
                .latitude(10.5).energyMax(200)
                .level(9).longitude(5.2).name("cr")
                .radius(54).build();

        List<AObjectEntity> objects = new ArrayList();
        objects.add(c1);
        objects.add(cr);

        List<CResonatorEntity> resonators = new ArrayList<CResonatorEntity>();
        resonators.add(cr);

        CPortalEntity cpr = new CPortalEntity.CPortalBuilder(2).latitude(10).longitude(5.2).objects(objects).resonators(resonators).build();
        System.out.println(cpr.toString());

        List<CPortalEntity> portals = new ArrayList<>();
        System.out.println(portals.add(cpr));

        CTeamEntity ctm = new CTeamEntity.CTeamBuilder(78678).color("red").portals(portals).build();
        System.out.println(ctm.toString());

<<<<<<< HEAD
        CTeamEntity ctm = new CTeamEntity.CTeamBuilder(0).color("red").build();
        CPlayerEntity cp = new CPlayerEntity.CPlayerBuilder(0).email("bob@z.fr").nickname("mahmoud").build();
=======
        CPlayerEntity cp = new CPlayerEntity.CPlayerBuilder(5858).email("bob@z.fr").nickname("mahmoud").team(ctm).build();
        System.out.println(cp.toString());
>>>>>>> 4019515a0c71ef75179e7529e4b07b102e4b910d

        CSkillEntity csk = new CSkillEntity.CSkillBuilder(5).cost(140).level(10).name("ntm").build();
        System.out.println(csk.toString());

    }
}