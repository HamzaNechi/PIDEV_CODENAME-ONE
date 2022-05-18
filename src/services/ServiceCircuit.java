/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entite.Circuit;
import utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author manaa
 */
public class ServiceCircuit {
    public ArrayList<Circuit> circuits;
    
    public static ServiceCircuit instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public static boolean resultU = true;

     public static ServiceCircuit getInstance() {
        if (instance == null) {
            instance = new ServiceCircuit();
        }
        return instance;
    }
       public ServiceCircuit() {
         req = new ConnectionRequest();
    }

    public void addCircit(Circuit t) {
        System.out.println(t);
        System.out.println("********");
       //String url = Statics.BASE_URL + "/mobilecreate?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL+"/addcircuitM?nom="+t.getNom()+"&pays="+t.getPays()+"&longeur="+t.getlongeur()+"&course_distance="+ t.getCourse_distance()+"&description="+t.getDescription()+"&capacite="+t.getCapacite()+"&image="+t.getImage();
             System.out.println(Statics.BASE_URL+"/addcircuitM?nom="+t.getNom()+"&pays="+t.getPays()+"&longeur="+t.getlongeur()+"&course_distance="+ t.getCourse_distance()+"&description="+t.getDescription()+"&capacite="+t.getCapacite()+"&image="+t.getImage());
             req.setUrl(url);
       req.addResponseListener((e)->{
       String str = new String(req.getResponseData());
           System.out.println("data =="+str);
       });
         NetworkManager.getInstance().addToQueueAndWait(req);

        
        
    }
    public ArrayList<Circuit>afficherCircuit(){
        ArrayList<Circuit> result=new ArrayList<>();
        String url=Statics.BASE_URL+"/displayCircuitM";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp= new JSONParser();
                try{
                    Map<String,Object>mapCircuit;
                    mapCircuit = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> listOfMaps= (List<Map<String,Object>>)mapCircuit.get("root");
                    for(Map<String,Object> obj : listOfMaps){
                        Circuit cs = new Circuit();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String nom=obj.get("nom").toString();
                        String pays=obj.get("pays").toString();
                        float longeur=Float.parseFloat(obj.get("longeur").toString());
                        //float course_distance=Float.parseFloat(obj.get("course_distance").toString());
                        String description=obj.get("description").toString();
                        float capacite=Float.parseFloat(obj.get("capacite").toString());
                        String image=obj.get("image").toString();
                        cs.setCircuit_id((int)id);
                        cs.setNom(nom);
                        cs.setPays(pays);
                        cs.setlongeur((int)longeur);
                        //cs.setCourse_distance((int)course_distance);
                        cs.setDescription(description);
                        cs.setCapacite((int)capacite);
                        cs.setImage(image);
                        result.add(cs);
                    }
                }catch(Exception ex){ex.printStackTrace();}
                }
        });
        
         NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
        
    }

    public void DeleteCircuit(int id) {
            String url = Statics.BASE_URL +"/deleteCircuitMobile/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
    public boolean UpdateCircuit( Circuit t) {
        
        String url = Statics.BASE_URL+"/addcircuitM?nom="+t.getNom()+"&pays="+t.getPays()+"&longeur="+t.getlongeur()+"&course_distance="+ t.getCourse_distance()+"&description="+t.getDescription()+"&capacite="+t.getCapacite()+"&image="+t.getImage();
        System.out.println(url);
        req.setUrl(url);
                      
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultU = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
                 
              //  JsontooString();
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultU;
        
    }
    }
