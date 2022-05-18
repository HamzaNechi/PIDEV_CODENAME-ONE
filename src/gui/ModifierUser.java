/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.ComboBox;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entite.User;
import services.UserServices;
import com.codename1.ui.Toolbar;

/**
 *
 * @author hamou
 */
public class ModifierUser extends Form{
    Form previous;
    Resources theme = UIManager.initFirstTheme("/theme");
       Form modifU = new Form(BoxLayout.y());
        UserServices us = new UserServices(); 

    public ModifierUser(Resources res, User u) {
           ComboBox rolescombo = new ComboBox();
       
        rolescombo.addItem("ROLE_USER");
        
        rolescombo.addItem("ROLE_ADMIN");
        
        rolescombo.addItem("ROLE_ORGANISATEUR");
      
        rolescombo.addActionListener(e -> {
           
            new HomeFormY(res).show();
            u.setRoles((String) rolescombo.getSelectedItem());
            us.modifierAdmin(u);
           
        });
        modifU.add(rolescombo);
//getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> res.showBack());
    }

    public void show() {
       modifU.show();
    } 
}
