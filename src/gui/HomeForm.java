/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import static gui.BaseForm.myForm;
import gui.classementPilotes.ShowAll;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    
    Resources theme = UIManager.initFirstTheme("/theme");
    Form current = new Form(BoxLayout.y());

    Form f0 = new Form(new FlowLayout(CENTER, CENTER));
    Form f1 = new Form(new FlowLayout(CENTER, CENTER));
    Form f2 = new Form(new FlowLayout(CENTER, CENTER));
    Form f3 = new Form(new FlowLayout(CENTER, CENTER));
    Form f4 = new Form(new FlowLayout(CENTER, CENTER));
    Form f5 = new Form(new FlowLayout(CENTER, CENTER));

    public HomeForm(Resources res) {
        f0.add(new Label("Home"));
        f1.add(new Label("Voir compte"));
        f2.add(new Label("Voir equipe"));
        f3.add(new Label("Voir Circuit"));
        f4.add(new Label("Voir participation"));
        f5.add(new Label("Voir classement"));

        Toolbar tb = current.getToolbar();
        current.setTitle("Circuits");
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_WEB, (ActionListener) (ActionEvent evt) -> {
            new BaseForm(res).show();
        });

        tb.addMaterialCommandToSideMenu("Voir compte", FontImage.MATERIAL_WEB, (ActionListener) (ActionEvent evt) -> {
            new HomeFormY(res).show();
        });

        tb.addMaterialCommandToSideMenu("Voir equipe", FontImage.MATERIAL_SETTINGS, e -> {
            new ListEquipeForm(res).show();
        });

        tb.addMaterialCommandToSideMenu("Voir Circuit", FontImage.MATERIAL_INFO, e -> {
            new ListCircuitForm(res).show();
        });

        tb.addMaterialCommandToSideMenu("Voir participation", FontImage.MATERIAL_INFO, e -> {

            new ListParticipationForm(res).show();

        });

        tb.addMaterialCommandToSideMenu("Voir classement", FontImage.MATERIAL_INFO, e -> {
            new ShowAll(myForm).show();
        });

        
        Button btnAddTask = new Button("Add Circuit");
        Button btnListTasks = new Button("List Circuit");
        Button btnAddMap = new Button("Afficher mapp");
        
        btnAddMap.addActionListener(e -> new MapForm());
        btnAddTask.addActionListener(e -> new AddCircuitForm(res).show());
        btnListTasks.addActionListener(e -> new ListCircuitForm(res).show());
        
        
        
        current.add(btnAddTask);
        current.add(btnListTasks);
        current.add(btnAddMap);

        // this.getToolbar().addCommandToLeftBar("Retour",theme.getImage("back-command.png"),e->new BaseForm(res).show());
    }

    public void show() {
        current.show();
    }
}
