package fr.univtln.groupc.entities;

/**
 * Created by arouani277 on 02/05/16.
 */
public interface ITarget {
    public void takeDamage(IFighter pFighter,int pDamage);
    public CTeamEntity getTargetTeam();

}
