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
import entite.Participation;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static utils.Statics.BASE_URL;

/**
 *
 * @author ahmed
 */
public class ServiceParticipation {

    public static ServiceParticipation instance = null;
    private ConnectionRequest req;
    public static boolean resultOk = true;

    public static ServiceParticipation getInstance() {
        if (instance == null) {
            instance = new ServiceParticipation();
        }
        return instance;
    }

    public ServiceParticipation() {
        req = new ConnectionRequest();
    }

    public void addParticipation(Participation participation) {
        String url = BASE_URL + "/mobile/addParticipation?pilote=" + participation.getIdPilote()
                + "&equipe=" + participation.getIdEquipe()
                + "&course=" + participation.getIdCourse()
                + "&position=" + participation.getPosition()
                + "&points=" + participation.getPoints()
                + "&grid=" + participation.getGrid()
                + "&qualifying=" + participation.getIdQualifying();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data,== " + str);
            System.out.println(url);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public ArrayList<Participation> affichage() {
        ArrayList<Participation> result = new ArrayList<>();
        String url = BASE_URL + "/mobile/participation";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapParticipation = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapParticipation.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Participation part = new Participation();
                        System.out.println(obj.toString());
                        float id = Float.parseFloat(obj.get("id").toString());

                        float grid = Float.parseFloat(obj.get("grid").toString());
                        float position = Float.parseFloat(obj.get("position").toString());
                        float points = Float.parseFloat(obj.get("points").toString());

                        part.setId((int) id);
                        part.setGrid((int) grid);
                        part.setPosition((int) position);
                        part.setPoints((int) points);

                        result.add(part);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

    
    
    public boolean deleteParticipation(Participation participation) {
        String url = "http://127.0.0.1:8000/mobile/deleteParticipation?id=" + participation.getId();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
    
    
    
    
    public boolean sendEmail(Participation participation) {
        String url = BASE_URL+"/mobile/sendmail?id=" + participation.getId();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

}
