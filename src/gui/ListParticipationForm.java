/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.io.Storage;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entite.Participation;
import static gui.BaseForm.myForm;
import gui.classementPilotes.ShowAll;
import java.util.ArrayList;
import services.ServiceParticipation;

/**
 *
 * @author ahmed
 */
public class ListParticipationForm extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
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

    public ListParticipationForm(Resources res) {
        
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
           new SignIn(theme).show();
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
        

        System.out.println("part 1");
        System.out.println("part 2");
        current.setTitle("Participations");
        Button ajout = new Button("Ajouter Participation");
        current.add(ajout);
        ajout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new AjouterParticipationForm(res);
            }
        });

        ArrayList<Participation> list = ServiceParticipation.getInstance().affichage();
        for (Participation p : list) {

            Container hi = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            System.out.println("abc abc");

            Label grid = new Label("Grid: " + p.getGrid());
            Label position = new Label("Position: " + p.getPosition());
            Label points = new Label("Points: " + p.getPoints());
            Button btnS = new Button("Supprimer");
            Button btnMail = new Button("Envoyer mail");

            btnS.addPointerPressedListener(l -> {
                Dialog dig = new Dialog("Suppression");
                if (dig.show("Suppression", "Vous voulez supprimer ce participation ?", "Annuler", "Oui")) {
                    dig.dispose();
                } else {
                    dig.dispose();
                }
                //n3ayto l suuprimer men service Reclamation
                if (ServiceParticipation.getInstance().deleteParticipation(p)) {
                    
                    new ListParticipationForm(res).show();
                    refreshTheme();
                }

            });

            btnMail.addPointerPressedListener(l -> {
                Dialog dig = new Dialog("Envoyer mail");
                if (dig.show("Mail", "Envoyer mail l'equipe ?", "Annuler", "Oui")) {
                    dig.dispose();
                } else {
                    dig.dispose();
                }
                //n3ayto l suuprimer men service Reclamation
                if (ServiceParticipation.getInstance().sendEmail(p)) {
                    new ListParticipationForm(res);
                }

            });

            Button btnM = new Button("Modifier");

            hi.add(grid);
            hi.add(position);
            hi.add(points);
            hi.add(btnS);
            hi.add(btnM);
            hi.add(btnMail);
            current.add(hi);
        }
    }
    public void show() {
        current.show();
    }
}
