/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entite.User;
import services.UserServices;

/**
 *
 * @author hamou
 */
public class ModifierProfil extends Form{
       Resources theme = UIManager.initFirstTheme("/theme");
       Form modifierC = new Form(BoxLayout.y());
        UserServices us = new UserServices();
   
        
         User u = new User();
        ImageViewer img = new ImageViewer(theme.getImage("f1tn.png"));
        Container Container = new Container(new FlowLayout(Component.CENTER));
        Container Container1 = new Container(new FlowLayout(Component.CENTER));
        Container Container2 = new Container(new FlowLayout(Component.CENTER));
        Container Container3 = new Container(new FlowLayout(Component.CENTER));
        Container Container4 = new Container(new FlowLayout(Component.CENTER));
        Container Container5 = new Container(new FlowLayout(Component.CENTER));
        Container Container6 = new Container(new FlowLayout(Component.CENTER));
        Container Container7 = new Container(new FlowLayout(Component.CENTER));
        
      //  Container Container3 = new Container(new FlowLayout(Component.CENTER));
       // Container Container4 = new Container(new FlowLayout(Component.CENTER));
        TextField Name = new TextField();
        TextField Tel = new TextField();
        
         Label Namelabel = new Label("Name");

     Label Emaillabel = new Label("Email");
       Label inputEmail = new Label (u.getEmail());


      Label Tellabel = new Label("N° Telephone");

        Button btn = new Button("Modifier"); 
        
         Button Deconnexion = new Button("Deconnexion"); 
        public ModifierProfil(Resources theme , User u) {
       
        
       modifierC.addComponent(img);
          Container.add(Namelabel).add(Name);
          Container1.add(Tellabel).add(Tel);
        //  Container2.add(Namelabel).add(inputEmail);
         
          btn.addActionListener(ev ->
          {   
              us.modifierCompte();
          });
              Deconnexion.addActionListener(ev ->
          {
            new SignIn(theme).show();
            SessionManager.pref.clearAll();
            Storage.getInstance().clearStorage();
            Storage.getInstance().clearCache();
            System.out.println(SessionManager.getName());
          });
          modifierC.add(Container).add(Container1).add(Container2).add(Container3)
                  .add(Container4).add(Container5).add(Container6).add(Container7).add(btn).add(Deconnexion);
       
    }

    public void show() {
         modifierC.show();
    }
    }

