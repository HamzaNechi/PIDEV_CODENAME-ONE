/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import entite.Equipe;
import entite.Membre;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

/**
 *
 * @author nechi
 */
public class ServiceEquipe {
    public static ServiceEquipe instance=null;
    private ConnectionRequest req;
    public static boolean resultOk = true;
    int numero=0;
    
    public static ServiceEquipe getInstance(){
        if(instance == null)
            instance=new ServiceEquipe();
        return instance;
    }
    
    public ServiceEquipe(){
        req=new ConnectionRequest();
    }
    
    
    //ajout
    public void addEquipe(Equipe e){
        String url=Statics.BASE_URL+"/addEquipeMobile?nom="+e.getNom()+"&email="+ e.getEmail() +"&logo="+e.getLogo()+"&voiture="+e.getVoiture()+"&pays="+e.getPays_origine()+"&ex"+e.getExtention();
        req.setUrl(url);
        System.out.println("url : "+url);
        req.addResponseListener((a)->{
            String str=new String(req.getResponseData());
            System.out.println("data = "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
    //affichage
    public ArrayList<Equipe> afficheEquipe(){
	ArrayList<Equipe> result = new ArrayList<>();
        String url=Statics.BASE_URL+"/displayEquipeMobile";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try{
                    Map<String,Object> mapEquipe = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> listOfMaps=(List<Map<String,Object>>) mapEquipe.get("root");
                    System.out.println(listOfMaps);
                    for(Map<String,Object> obj : listOfMaps){
                        Equipe e=new Equipe();
                        
                        float id =Float.parseFloat(obj.get("id").toString());
                        String nom=obj.get("nom").toString();
                        String email=obj.get("email").toString();
                        String logo = obj.get("logo").toString();
                        String voiture=obj.get("voiture").toString();
                        String pays=obj.get("pays_origine").toString();
                        e.setId((int)id);
                        e.setNom(nom);
                        e.setEmail(email);
                        e.setLogo(logo);
                        e.setVoiture(voiture);
                        e.setPays_origine(pays);
                        result.add(e);
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    
    
    //suppression
    public boolean DeleteEquipe(Equipe e){
        String url=Statics.BASE_URL+"/deleteEquipeMobile/"+e.getId();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
    //attestation
    public ArrayList<Membre> Equipeattestation(int id){
	ArrayList<Membre> result = new ArrayList<>();
        String url=Statics.BASE_URL+"/getMembreDequipeMobile/"+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try{
                    Map<String,Object> mapMembre = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> listOfMaps=(List<Map<String,Object>>) mapMembre.get("root");
                    
                    for(Map<String,Object> obj : listOfMaps){
                        Membre m=new Membre();
                        
                        float id =Float.parseFloat(obj.get("id").toString());
                        String nom=obj.get("nom").toString();
                        String role=obj.get("role").toString();
                        if(obj.get("numero") != null){
                           String num = obj.get("numero").toString(); 
                            numero=Integer.parseInt(num);
                            
                        }else{
                            numero=0;
                        }
                        

                        
                        m.setId((int)id);
                        m.setNom(nom);
                        m.setRole(role);
                        m.setNum((int) numero);
                        result.add(m);
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    
    
    //attestation pdf
    public void generate_attestation_pdf(int id){
        String url=Statics.BASE_URL+"/generate_pdf_mobile/"+id;
        FileSystemStorage fs = FileSystemStorage.getInstance();
        String fileName = fs.getAppHomePath() + "pdf-sample.pdf";
        if(!fs.exists(fileName)) {
            Util.downloadUrlToFile(url, fileName, true);
        }
        Display.getInstance().execute(fileName);
        //return resultOk;
    }
    
    //modifier equipe
    public boolean modifierEquipe(Equipe e) {
        int id=(int) e.getId();
        String url=Statics.BASE_URL+"/updateEquipeMobile?id="+id+"&nom="+e.getNom()+"&email="+ e.getEmail() +"&logo="+e.getLogo()+"&voiture="+e.getVoiture()+"&pays="+e.getPays_origine();
        req.setUrl(url);
        System.out.println("url : "+ url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
}
