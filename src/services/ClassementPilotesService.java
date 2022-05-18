package services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import entite.ClassementPilotes;
import entite.Pilotes;
import entite.Saisons;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

public class ClassementPilotesService {

    public static ClassementPilotesService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<ClassementPilotes> listClassementPilotess;


    private ClassementPilotesService() {
        cr = new ConnectionRequest();
    }

    public static ClassementPilotesService getInstance() {
        if (instance == null) {
            instance = new ClassementPilotesService();
        }
        return instance;
    }

    public ArrayList<ClassementPilotes> getAll() {
        listClassementPilotess = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/mobile/classementPilotes");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listClassementPilotess = getList();
                }

                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listClassementPilotess;
    }

    private ArrayList<ClassementPilotes> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                ClassementPilotes classementPilotes = new ClassementPilotes(
                        (int) Float.parseFloat(obj.get("id").toString()),

                        makeSaisons((Map<String, Object>) obj.get("saisonsYear")),
                        makePilotes((Map<String, Object>) obj.get("pilotes")),
                        (int) Float.parseFloat(obj.get("pointsTotal").toString()),
                        (int) Float.parseFloat(obj.get("position").toString())

                );

                listClassementPilotess.add(classementPilotes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listClassementPilotess;
    }

    public Saisons makeSaisons(Map<String, Object> obj) {
        if (obj == null) {
            return null;
        }
        Saisons saisons = new Saisons();
        saisons.setId((int) Float.parseFloat(obj.get("id").toString()));
        saisons.setYear((int) Float.parseFloat(obj.get("year").toString()));
        return saisons;
    }

    public Pilotes makePilotes(Map<String, Object> obj) {
        if (obj == null) {
            return null;
        }
        Pilotes pilotes = new Pilotes();
        pilotes.setId((int) Float.parseFloat(obj.get("id").toString()));
        pilotes.setNumero((int) Float.parseFloat(obj.get("numero").toString()));
        return pilotes;
    }

    public int add(ClassementPilotes classementPilotes) {
        return manage(classementPilotes, false);
    }

    public int edit(ClassementPilotes classementPilotes) {
        return manage(classementPilotes, true);
    }

    public int manage(ClassementPilotes classementPilotes, boolean isEdit) {

        cr = new ConnectionRequest();


        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "/mobile/classementPilotes/edit");
            cr.addArgument("id", String.valueOf(classementPilotes.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "/mobile/classementPilotes/add");
        }

        cr.addArgument("saisons", String.valueOf(classementPilotes.getSaisons().getId()));
        cr.addArgument("pilotes", String.valueOf(classementPilotes.getPilotes().getId()));
        cr.addArgument("pointsTotal", String.valueOf(classementPilotes.getPointsTotal()));
        cr.addArgument("position", String.valueOf(classementPilotes.getPosition()));


        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultCode = cr.getResponseCode();
                cr.removeResponseListener(this);
            }
        });
        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception ignored) {

        }
        return resultCode;
    }

    public int delete(int classementPilotesId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/mobile/classementPilotes/delete");
        cr.setHttpMethod("POST");
        cr.addArgument("id", String.valueOf(classementPilotesId));

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cr.removeResponseListener(this);
            }
        });

        try {
            cr.setDisposeOnCompletion(new InfiniteProgress().showInfiniteBlocking());
            NetworkManager.getInstance().addToQueueAndWait(cr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cr.getResponseCode();
    }
}
