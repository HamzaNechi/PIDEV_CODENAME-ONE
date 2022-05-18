/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import entite.Equipe;
import static gui.BaseForm.myForm;
import gui.classementPilotes.ShowAll;
import java.io.IOException;
import java.util.ArrayList;
import services.ServiceEquipe;

/**
 *
 * @author nechi
 */
public class ListEquipeForm extends Form {

    private EncodedImage enc;
    Image image;
    ImageViewer imgv;
    private String url = "http://127.0.0.1:8000/images/equipe/";

    Form current = new Form(BoxLayout.y());
    Button compte = new Button("Voir compte");
    Button equipe = new Button("Voir equipe");
    Button course = new Button("Voir Circuit");
    Button participation = new Button("Voir participation");
    Button classement = new Button("Voir classement");
    Form f0 = new Form(new FlowLayout(CENTER, CENTER));
    Form f1 = new Form(new FlowLayout(CENTER, CENTER));
    Form f2 = new Form(new FlowLayout(CENTER, CENTER));
    Form f3 = new Form(new FlowLayout(CENTER, CENTER));
    Form f4 = new Form(new FlowLayout(CENTER, CENTER));
    Form f5 = new Form(new FlowLayout(CENTER, CENTER));

    public ListEquipeForm(Resources res) {

        f0.add(new Label("Home"));
        f1.add(new Label("Voir compte"));
        f2.add(new Label("Voir equipe"));
        f3.add(new Label("Voir Circuit"));
        f4.add(new Label("Voir participation"));
        f5.add(new Label("Voir classement"));
        Toolbar tb = current.getToolbar();
                tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_WEB, (ActionListener) (ActionEvent evt) -> {
            new BaseForm(res).show();
        });
        tb.addMaterialCommandToSideMenu("Voir compte", FontImage.MATERIAL_ACCOUNT_CIRCLE, (ActionListener) (ActionEvent evt) -> {
            new HomeFormY(res).show();
        });
        tb.addMaterialCommandToSideMenu("Voir equipe", FontImage.	MATERIAL_DIRECTIONS_CAR_FILLED, e -> {
            new ListEquipeForm(res).show();
        });
        tb.addMaterialCommandToSideMenu("Voir Circuit", FontImage.MATERIAL_MAP, e -> {
            new ListCircuitForm(res).show();
        });
        tb.addMaterialCommandToSideMenu("Voir participation", FontImage.MATERIAL_INFO, e -> {
            new ListParticipationForm(res).show();
        });
        tb.addMaterialCommandToSideMenu("Voir classement", FontImage.	MATERIAL_FORMAT_LIST_NUMBERED, e -> {
            new ShowAll(myForm).show();
        });
         tb.addMaterialCommandToSideMenu("Deconnexion", FontImage.	MATERIAL_LOGOUT, e -> {
           new SignIn(res).show();
                    SessionManager.pref.clearAll();
                    Storage.getInstance().clearStorage();
                    Storage.getInstance().clearCache();
                    System.out.println(SessionManager.getName());
        });
        f1.getToolbar().addCommandToLeftBar("Back", null, (ActionListener) (ActionEvent evt) -> {
            current.showBack();
        });
        f2.getToolbar().addCommandToRightBar("Back", null, (ActionListener) (ActionEvent evt) -> {
            current.showBack();
        });
        f3.getToolbar().addCommandToOverflowMenu("Back", null, (ActionListener) (ActionEvent evt) -> {
            current.showBack();
        });

        Storage.getInstance().clearCache();
        refreshTheme();

        Button ajout = new Button("Ajouter une nouvelle équipe");

        current.add(ajout);
        ajout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new AjoutEquipeForm();
            }
        });

        ArrayList<Equipe> list = ServiceEquipe.getInstance().afficheEquipe();
        for (Equipe e : list) {

            //image
            try {
                enc = EncodedImage.create("/hhh.png");
            } catch (IOException ex) {

            }
            image = URLImage.createToStorage(enc, e.getLogo(), url + e.getLogo(), URLImage.RESIZE_SCALE).scaled(700, 300);
            imgv = new ImageViewer(image);
            //endimage

            Label nom = new Label("Nom : " + e.getNom());
            Label email = new Label("Email : " + e.getEmail());
            Label voiture = new Label("Voiture : " + e.getVoiture());

            Button btnS = new Button("Supprimer");
            Button btnM = new Button("Modifier");
            Button pdf = new Button("Attestation");

            btnM.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new ModifierEquipeForm(e);
                }
            });

            pdf.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    //System.out.println(e.getEmail());
                    new AttestationForm(e.getId(), e.getNom(), e.getEmail());
                }
            });

            btnS.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ServiceEquipe.getInstance().DeleteEquipe(e);
                    
                    new ListEquipeForm(res).show();
                    refreshTheme();
                }
            });

            Container c1 = new Container(BoxLayout.x());
            c1.add(btnS);
            c1.add(btnM);
            c1.add(pdf);
            Container c = new Container(BoxLayout.y());
            c.add(imgv);
            c.add(nom);
            c.add(email);
            c.add(voiture);
            c.add(c1);

            current.add(c);
        }

        current.setTitle("équipes");

    }

    public void show() {
        current.show();
    }

}
