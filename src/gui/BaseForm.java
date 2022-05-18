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
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import gui.classementPilotes.ShowAll;

/**
 *
 * @author nechi
 */
public class BaseForm extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");

    public static Form myForm;

    public BaseForm(Resources res) {
        myForm = this;
       
        Button compte = new Button("Voir compte");
        Button equipe = new Button("Voir equipe");
        Button course = new Button("Voir Circuit");
        Button participation = new Button("Voir participation");
        Button classement = new Button("Voir classement");

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_EXIT_TO_APP,
                e -> {
                    new SignIn(theme).show();
                    SessionManager.pref.clearAll();
                    Storage.getInstance().clearStorage();
                    Storage.getInstance().clearCache();
                    System.out.println(SessionManager.getName());
                });

        participation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListParticipationForm(theme).show();
            }
        });

        equipe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListEquipeForm(theme).show();
            }
        });

        compte.addActionListener(l -> {
            new HomeFormY(theme).show();
        });

        course.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListCircuitForm(theme).show();
            }
        });

        classement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ShowAll(myForm).show();
            }
        });

        Container c = new Container(BoxLayout.y());
        c.add(compte);
        c.add(equipe);
        c.add(course);
        c.add(participation);
        c.add(classement);

        this.add(c);
        this.setTitle("Espace Admin");
        this.show();
    }

}
