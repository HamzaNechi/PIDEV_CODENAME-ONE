/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entite.User;
import services.UserServices;

/**
 *
 * @author hamou
 */
public class SignIn {
  UserServices us = new UserServices();
     Resources theme = UIManager.initFirstTheme("/theme");
       Form signin = new Form(BoxLayout.y());
      User u = new User();
         
         
         Container Container = new Container(new FlowLayout(Component.CENTER));
        Container Container1 = new Container(new FlowLayout(Component.CENTER));
        Container Container2 = new Container(new FlowLayout(Component.CENTER));
        
        TextField Email = new TextField();
        TextField Password = new TextField("","PASSWORD",20,TextField.PASSWORD);
        
        Button signinbtn = new Button("Se connecter");
        
         Label Emaillabel = new Label("Email");

     Label Passwordlabel = new Label("Password");
     Button Signup = new Button("Vous n'avez pas un compte?");
     
     
     public SignIn(Resources res) {
           this.us = new UserServices();
     Signup.getAllStyles().setBorder(Border.createEmpty());
     Signup.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        
          Container.add(Emaillabel).add(Email);
          Container1.add(Passwordlabel).add(Password);
          Container2.add(Signup);
          
          signinbtn.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {
                  UserServices.getInstance().signin(Email, Password, theme);
                  //refreshTheme();
                  if("ROLE_ADMIN" == u.getRoles()){
                      
                      new BaseForm(theme).show();
                  }
                  else if ("ROLE_USER" == u.getRoles()){
                        new ModifierProfil(theme, u).show();
                  }
                  
              }
          });
//               
//           signinbtn.addActionListener(ev ->
//                   
//          {
//              us.signin(Email, Password, res);
//               if("ROLE_ADMIN".equals(u.getRoles())){
//                  new HomeForm().show();
//               }     
//         
//           else{        
//                   new ModifierProfil(theme, u).show();
//           }
//                });
          signin.add(Container).add(Container1).add(signinbtn).add(Container2);
     }
     public void show() {
        signin.show();
    }
}
