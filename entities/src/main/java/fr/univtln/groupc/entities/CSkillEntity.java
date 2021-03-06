package fr.univtln.groupc.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by nmartinez016 on 25/04/16.
 */

@Entity
@Table(name = "t_skill", schema = "positron")
@NamedQueries({@NamedQuery(name = CSkillEntity.GET_ALL, query = "select p from CSkillEntity p"),
        @NamedQuery(name = CSkillEntity.GET_BY_LEVEL, query = "select s from CSkillEntity s where s.mLevel = :mLevel")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CSkillEntity.class)



//TODO: --Cost ++Branche/type

public class CSkillEntity implements Serializable {

    @Id
    @Column(name = "id")
    private int mId;
    @Column(name = "name")
    private String mName;
    @Column(name = "level")
    private int mLevel;
    @Column(name = "cost")
    private int mCost;
    @Column(name = "type")
    private String mType;



    public final static String GET_ALL = "Skill.getAll";
    public final static String GET_BY_LEVEL = "Skill.getByLevel";

    public CSkillEntity(){}

    public CSkillEntity(CSkillBuilder pBuilder){
        mId = pBuilder.mId;
        mName = pBuilder.mName;
        mLevel = pBuilder.mLevel;
        mCost = pBuilder.mCost;
        mType = pBuilder.mType;
    }

    public int getId() {
        return mId;
    }

    public void setId(int pId) {
        mId = pId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String pName) {
        mName = pName;
    }

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int pLevel) {
        mLevel = pLevel;
    }

    public int getCost() {
        return mCost;
    }

    public void setCost(int pCost) {
        mCost = pCost;
    }

    public String getType() {
        return mType;
    }

    public void setType(String pType) {
        mType = pType;
    }


    @Override
    public String toString() {
        return "CSkillEntity{" +
                "mId=" + mId +
                ", mName ='" + mName + '\'' +
                ", mLevel=" + mLevel +
                ", mCost=" + mCost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CSkillEntity that = (CSkillEntity) o;

        return mId == that.mId;
    }

    @Override
    public int hashCode() {
        return mId;
    }

    public static class CSkillBuilder{
        private final int mId;
        private String mName;
        private int mLevel;
        private int mCost;
        private String mType;

        public CSkillBuilder(int pId){
            mId = pId;
        }

        public CSkillBuilder name(String pName){
            mName = pName;
            return this;
        }

        public CSkillBuilder level(int pLevel){
            mLevel = pLevel;
            return this;
        }

        public CSkillBuilder cost(int pCost){
            mCost = pCost;
            return this;
        }

        public CSkillBuilder type(String pType){
            mType = pType;
            return this;
        }


        public CSkillEntity build(){
            return new CSkillEntity(this);
        }
    }



}