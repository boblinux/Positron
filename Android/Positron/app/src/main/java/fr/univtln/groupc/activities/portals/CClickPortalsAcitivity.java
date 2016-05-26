package fr.univtln.groupc.activities.portals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import fr.univtln.groupc.actions.CActions;
import fr.univtln.groupc.entities.ABuildingEntity;
import fr.univtln.groupc.entities.CConsumableEntity;

import fr.univtln.groupc.entities.CLinkEntity;

import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.entities.CResonatorEntity;
import fr.univtln.groupc.rest.CRestDelete;
import fr.univtln.groupc.rest.CRestUpdate;
import fr.univtln.m1dapm.groupec.tperron710.positron.R;

public class CClickPortalsAcitivity extends AppCompatActivity {

    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cclick_portals_acitivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cclick_portals_acitivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void attackingBuilding(ABuildingEntity pTarget, CPlayerEntity pAttacker, CConsumableEntity pAmmunition , CPortalEntity pPortal){

        CActions lActions = new CActions();
        lActions.attackBuilding(pAmmunition,pTarget,pAttacker);

        if(pTarget.getEnergy() <= 0){
            // TODO Delete Building
            //new CRestDelete();
            updatePortalTeam(pPortal);
            //TODO addXP
        }
        else{
            //TODO Updtate Building
            //new CRestUpdate().;

            //TODO add XP
        }

    }




    public void buildingResonator(CPortalEntity pPortal,CResonatorEntity pResonator){
        CActions lActions = new CActions();

        pPortal = lActions.buildResonator(pPortal, pResonator);
        if(updatePortalTeam(pPortal) == 0){
            new CRestUpdate().updatePortalRest(pPortal);
        }

    }

    public int updatePortalTeam(CPortalEntity pPortal){
        List<CLinkEntity> lLinkList = new ArrayList<>();
        if(pPortal.getTeam() != null){
            int lTeam = pPortal.getTeam().getId();
            pPortal.attributeTeam();
            if (lTeam != pPortal.getTeam().getId()){
                new CRestUpdate().updatePortalRest(pPortal);
                for(CLinkEntity lLink : lLinkList) {
                    //DELETE lLink
                    new CRestDelete().deleteLinkRest(lLink.getId());
                }
                return 1;
            }
        }
        else{
            pPortal.attributeTeam();
            if (pPortal!=null){
              //  new CRestUpdate().updatePortalRest(pPortal);
                for(CLinkEntity lLink : lLinkList) {
                    // DELETE lLink
                    new CRestDelete().deleteLinkRest(lLink.getId());
                }
                return 1;
            }
        }
        return 0;
    }

    public void maintenir(View view){
        mIntent = new Intent(this, CPutPortalsView.class);
        startActivity(mIntent);
    }

    public void hacking(CPlayerEntity pPlayer, CPlayerEntity pPortal){
        int li =0;
        CActions lAction = new CActions();

        // TODO XP ++
        if(pPortal.getTeam() == null){
            for(li=0;li<5;li++){
                pPlayer.addObjects(lAction.createObject(lAction.calculTypeObject(), lAction.calculLevel(pPortal.getLevel(), pPlayer.getLevel()), lAction.calculRarety(pPortal.getLevel())));
            }
        }
        else{
            if(pPortal.getTeam() == pPlayer.getTeam()){
                for(li=0;li<10;li++){
                    pPlayer.addObjects(lAction.createObject(lAction.calculTypeObject(), lAction.calculLevel(pPortal.getLevel(), pPlayer.getLevel()), lAction.calculRarety(pPortal.getLevel())));

                }
            }
            else {
                //TODO XP ++
                for(li=0;li<5;li++){
                    pPlayer.addObjects(lAction.createObject(lAction.calculTypeObject(), lAction.calculLevel(pPortal.getLevel(), pPlayer.getLevel()), lAction.calculRarety(pPortal.getLevel())));

                }
            }
        }

    }

    public void useBombe(CConsumableEntity pAmmunition,CPortalEntity pPortal, CPlayerEntity pPlayer){
        CActions lAction = new CActions();

        if(pAmmunition.getName() == "Bombe"){
           // TODO rajouter vérification du skill
            if(true){
                lAction.bombeExplosion(pPortal,(pAmmunition.getRarity()*10)+20);
            }
            else{
                Log.d("UseBombe","Not able to use this weapon");
            }
        }
        else{
            Log.d("useBombe","Not good consumable");
        }
    }
}
