/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import services.UserServices;

/**
 *
 * @author hamou
 */
public class AjouterUser extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
    Form ajouter = new Form(BoxLayout.y());
    UserServices us = new UserServices();
    Container Container = new Container(new FlowLayout(Component.CENTER));
    Container Container1 = new Container(new FlowLayout(Component.CENTER));
    Container Container2 = new Container(new FlowLayout(Component.CENTER));
    Container Container3 = new Container(new FlowLayout(Component.CENTER));
    Container Container4 = new Container(new FlowLayout(Component.CENTER));
    TextField Name = new TextField();
    TextField Email = new TextField();
    TextField Password = new TextField();
    TextField ConfPassword = new TextField();
    TextField Tel = new TextField();
    Button btn = new Button("Ajouter");
    Label Namelabel = new Label("Name");
    Label Emaillabel = new Label("Email");
    Label Passwordlabel = new Label("Password");
    Label Tellabel = new Label("N° Telephone");

    public AjouterUser(Resources res) {
        theme = UIManager.initFirstTheme("/theme");
        ajouter.getToolbar().addCommandToLeftBar("Retour", theme.getImage("back-command.png"), e -> new HomeFormY(res).show());

        Container.add(Namelabel).add(Name);
        Container1.add(Emaillabel).add(Email);
        Container2.add(Passwordlabel).add(Password);
        Container4.add(Tellabel).add(Tel);
        btn.addActionListener(ev
                -> {
            us.addUser(Email, Password, Name, Tel, res);
            new HomeFormY(res).show();
            refreshTheme();
        });
        ajouter.add(Container).add(Container1).add(Container2).add(Container3).add(Container4).add(btn);
    }

    public void show() {
        ajouter.show();
    }
}
