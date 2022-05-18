package services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import entite.Pilotes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

public class PilotesService {

    public static PilotesService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Pilotes> listPilotess;


    private PilotesService() {
        cr = new ConnectionRequest();
    }

    public static PilotesService getInstance() {
        if (instance == null) {
            instance = new PilotesService();
        }
        return instance;
    }

    public ArrayList<Pilotes> getAll() {
        listPilotess = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/mobile/pilotes");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listPilotess = getList();
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

        return listPilotess;
    }

    private ArrayList<Pilotes> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Pilotes pilotes = new Pilotes(
                        (int) Float.parseFloat(obj.get("id").toString()),

                        (int) Float.parseFloat(obj.get("numero").toString())

                );

                listPilotess.add(pilotes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listPilotess;
    }

    public int add(Pilotes pilotes) {
        return manage(pilotes, false);
    }

    public int edit(Pilotes pilotes) {
        return manage(pilotes, true);
    }

    public int manage(Pilotes pilotes, boolean isEdit) {

        cr = new ConnectionRequest();


        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "/mobile/pilotes/edit");
            cr.addArgument("id", String.valueOf(pilotes.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "/mobile/pilotes/add");
        }

        cr.addArgument("numero", String.valueOf(pilotes.getNumero()));


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

    public int delete(int pilotesId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/mobile/pilotes/delete");
        cr.setHttpMethod("POST");
        cr.addArgument("id", String.valueOf(pilotesId));

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
