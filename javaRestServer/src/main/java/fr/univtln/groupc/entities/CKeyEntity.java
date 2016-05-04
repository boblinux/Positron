package fr.univtln.groupc.entities;

import javax.persistence.*;

/**
 * Created by marti on 02/05/2016.
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "t_key", schema = "positron")
@NamedQueries({@NamedQuery(name = CKeyEntity.GET_ALL, query = "select k from CKeyEntity k"),@NamedQuery(name = CKeyEntity.GET_KEY_BY_PORTAL, query = "select k from CKeyEntity k where k.mPortal=(select p from CPortalEntity p where p.mId=:id )")})
public class CKeyEntity extends AObjectEntity {
    @ManyToOne
    @JoinColumn(name = "portal_fk")
    private CPortalEntity mPortal;

    public final static String GET_ALL = "Key.getAll";
    public final static String GET_KEY_BY_PORTAL = "Key.getbyportal";

    public CKeyEntity(){}

    public CKeyEntity(CKeyBuilder pBuilder){
        super(pBuilder.mId, pBuilder.mName);
        mPortal = pBuilder.mPortal;
    }

    public CPortalEntity getPortal() {
        return mPortal;
    }

    public void setPortal(CPortalEntity pPortal) {
        mPortal = pPortal;
    }

    public static class CKeyBuilder{
        int mId;
        String mName;
        CPortalEntity mPortal;

        public CKeyBuilder(int pId){
            mId = pId;
        }

        public CKeyBuilder name(String pName){
            mName = pName;
            return this;
        }

        public CKeyBuilder portal(CPortalEntity pPortal){
            mPortal = pPortal;
            return this;
        }

        public CKeyEntity build(){
            return new CKeyEntity(this);
        }
    }

}