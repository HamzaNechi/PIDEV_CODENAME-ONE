package services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import entite.Saisons;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Statics;

public class SaisonsService {

    public static SaisonsService instance = null;
    public int resultCode;
    private ConnectionRequest cr;
    private ArrayList<Saisons> listSaisonss;


    private SaisonsService() {
        cr = new ConnectionRequest();
    }

    public static SaisonsService getInstance() {
        if (instance == null) {
            instance = new SaisonsService();
        }
        return instance;
    }

    public ArrayList<Saisons> getAll() {
        listSaisonss = new ArrayList<>();

        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/mobile/saisons");
        cr.setHttpMethod("GET");

        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                if (cr.getResponseCode() == 200) {
                    listSaisonss = getList();
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

        return listSaisonss;
    }

    private ArrayList<Saisons> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Saisons saisons = new Saisons(
                        (int) Float.parseFloat(obj.get("id").toString()),

                        (int) Float.parseFloat(obj.get("year").toString())

                );

                listSaisonss.add(saisons);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listSaisonss;
    }

    public int add(Saisons saisons) {
        return manage(saisons, false);
    }

    public int edit(Saisons saisons) {
        return manage(saisons, true);
    }

    public int manage(Saisons saisons, boolean isEdit) {

        cr = new ConnectionRequest();


        cr.setHttpMethod("POST");
        if (isEdit) {
            cr.setUrl(Statics.BASE_URL + "/mobile/saisons/edit");
            cr.addArgument("id", String.valueOf(saisons.getId()));
        } else {
            cr.setUrl(Statics.BASE_URL + "/mobile/saisons/add");
        }

        cr.addArgument("year", String.valueOf(saisons.getYear()));


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

    public int delete(int saisonsId) {
        cr = new ConnectionRequest();
        cr.setUrl(Statics.BASE_URL + "/mobile/saisons/delete");
        cr.setHttpMethod("POST");
        cr.addArgument("id", String.valueOf(saisonsId));

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
