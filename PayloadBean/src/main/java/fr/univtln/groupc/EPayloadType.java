package fr.univtln.groupc;

/**
 * Created by marti on 26/05/2016.
 */

public enum EPayloadType{
    POSE_RESONATOR{
        public String toString() {
            return "pose_resonator";
        }
    },
    ATTACK_BUILDING {
        public String toString() {
            return "attack_building";
        }
    },
    CREATE_LINK {
        public String toString() {
            return "create_link";
        }
    },
    DESTROY_LINK {
        public String toString() {
            return "destroy_link";
        }
    },
    RESONATOR_POSED{
        public String toString(){
            return "resonator_posed";
        }
    },
    BUILDING_ATTACKED{
        public String toString(){
            return "building_attacked";
        }
    },
    LINK_CREATED{
        public String toString(){
            return "link_created";
        }
    },
    LINK_DESTROYED{
        public String toString(){
            return "link_destroyed";
        }
    },
    CONNECTION{
        public String toString(){
            return "connection";
        }
    },
    CONNECTED{
        public String toString(){
            return "connected";
        }
    },
    ATTACK_SUCCESFUL{
        public String toString(){
            return "succesful_attack";
        }
    },
    DESTROYED_BUILDING{
        public String toString(){
            return "destroyed_building";
        }
    },
    PORTAL_CHANGING_TEAM{
        public String toString(){
            return "portal_changing_team";
        }
    },
    FIELD_CREATED{
        public String toString(){
            return "field_created";
        }
    },
    FIELD_DESTROYED{
        public String toString(){
            return "field_destroyed";
        }
    },
    TEAM_PORTAL_CHANGED{
        public String toString(){
            return "team_portal_changed";
        }
    }
}
