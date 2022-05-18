/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Participation;
import services.ServiceParticipation;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author ahmed
 */
public class AjouterParticipationForm extends Form {

    Resources theme;

    public AjouterParticipationForm(Resources res) {
        theme = UIManager.initFirstTheme("/theme");
        this.getToolbar().addCommandToLeftBar("Retour",theme.getImage("back-command.png"),e->new gui.ListParticipationForm(theme));
        TextField pilote = new TextField("", "Pilote");
        TextField equipe = new TextField("", "Equipe");
        TextField course = new TextField("", "Course");
        TextField qual = new TextField("", "Qualifying");
        TextField grid = new TextField("", "Grid");
        TextField position = new TextField("", "Position");
        TextField points = new TextField("", "Points");
        Button submit = new Button("Ajouter");

        this.setTitle("Ajouter Participation");
        this.setLayout(BoxLayout.y());
        this.add(qual);
        this.add(pilote);
        this.add(equipe);
        this.add(course);
        this.add(grid);
        this.add(position);
        this.add(points);
        this.add(submit);

        submit.addActionListener(ev
                -> {
            Participation p = new Participation(Integer.parseInt(pilote.getText()), Integer.parseInt(equipe.getText()), Integer.parseInt(course.getText()), Integer.parseInt(grid.getText()), Integer.parseInt(position.getText()), Integer.parseInt(points.getText()),Integer.parseInt(qual.getText()));
            ServiceParticipation.getInstance().addParticipation(p);
            new ListParticipationForm(res).show();
            refreshTheme();
        }
        );

        this.show();
    }
}
