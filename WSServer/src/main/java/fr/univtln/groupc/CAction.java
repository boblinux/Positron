package fr.univtln.groupc;

import fr.univtln.groupc.entities.CResonatorEntity;

/**
 * Created by marti on 31/05/2016.
 */
public class CAction {

    /*
     * Ajoute le resonateur au portail
     * et augmente l'xp du joueur l'ayant posé.
     *
     * ---------------
     *
     * Add the resonator to the portal
     * and increases the player xp.
     */

    public static CPoseResonator attachResonatorToPortal(CPoseResonator pPoseResonator){
        System.out.println("salut attach 1");
        if (pPoseResonator.getPortal().getResonators().size() < 8){
            System.out.println("salut attach 2");
            // todo : demander a xavier d expliquer ce if
            if (pPoseResonator.getResonator().getOwner().getLevel() >= pPoseResonator.getResonator().getLevel()){
                System.out.println("salut attach 3");
                pPoseResonator.getPortal().addResonator(pPoseResonator.getResonator());
                System.out.println("nb reso au portail : " + pPoseResonator.getPortal().getResonators().size());
                System.out.println("getportal null ? " + pPoseResonator.getPortal() == null);
                pPoseResonator.getPortal().attributeTeam();
                // todo : add xp to player
            }
            else{
                System.out.println("Niveau pas assez eleve");
            }
        }
        else {
            System.out.println("Plus de place sur le portail");
        }
        System.out.println("team dans attach : " + pPoseResonator.getPortal().getTeam());
        return pPoseResonator;
    }

    /*
     * Appelle la methode qui attache un resonateur a un portail
     * Retourne un vrai si la team a change, false sinon
     *
     * -----------
     *
     * Calls the method that attaches a resonator
     * to the portal.
     * Returns true if the resonator's team changed,
     * false otherwise.
     */

    public static Boolean isTeamChangedAfterResonatorPoseOnPortal(CPoseResonator pPoseResonator){
        System.out.println("dans la methode");
        int lPreviousId = 0;
        if (pPoseResonator.getPortal().getTeam() != null){
            lPreviousId = pPoseResonator.getPortal().getTeam().getId();
        }
        pPoseResonator = attachResonatorToPortal(pPoseResonator);
        System.out.println("team apres methode attach : " + pPoseResonator.getPortal().getTeam());
        System.out.println("fin de la methode");
        return (lPreviousId != pPoseResonator.getPortal().getTeam().getId());
    }

    public static CAttackBuilding applyAttack(CAttackBuilding pAttackBuilding) {
        if (pAttackBuilding.getConsumable().getName().equals("Attack")) {
            pAttackBuilding.getPlayer().attack(pAttackBuilding.getBuilding(), pAttackBuilding.getConsumable());
            //TODO add XP degat * 10
            //pAttackBuilding.getPlayer().addXP();

        } else {
            System.out.println("Consommable non approrié");
        }
        return pAttackBuilding;
    }

    public static Boolean isDeadBuilding(CAttackBuilding pAttackBuilding){
        System.out.println("pv building avant atq : " + pAttackBuilding.getBuilding().getEnergy());
        //CAttackBuilding lAttackBuilding = applyAttack(pAttackBuilding);
        System.out.println("pv building apres atq : " + pAttackBuilding.getBuilding().getEnergy());
        if (pAttackBuilding.getBuilding().getEnergy() <= 0){
            if (pAttackBuilding.getBuilding() instanceof CResonatorEntity){
                pAttackBuilding.getBuilding().getPortal().removeResonator((CResonatorEntity)pAttackBuilding.getBuilding());
            }
            else{
                pAttackBuilding.getBuilding().getPortal().removeBuilding(pAttackBuilding.getBuilding());
            }
            return true;
        }
        else{
            return false;
        }
    }

    public static Boolean isPortalTeamOfBuildingChanged(CAttackBuilding pAttackBuilding){
        CAttackBuilding lAttackBuilding = applyAttack(pAttackBuilding);
        if (lAttackBuilding.getBuilding() instanceof CResonatorEntity){
            if (isDeadBuilding(lAttackBuilding)){
                int lTeamId = lAttackBuilding.getBuilding().getPortal().getTeam().getId();
                lAttackBuilding.getBuilding().getPortal().attributeTeam();
                int lTeamToCompare = lAttackBuilding.getBuilding().getPortal().getTeam().getId();
                return (lTeamId != lTeamToCompare);
                }
            else{
                return false;
            }
        }
        else{
            return false;
        }

    }

}