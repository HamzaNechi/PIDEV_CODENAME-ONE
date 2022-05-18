/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entite.Equipe;
import services.ServiceEquipe;

/**
 *
 * @author nechi
 */
public class ModifierEquipeForm extends Form{
    Resources theme;
    
    public ModifierEquipeForm(Equipe e){
        theme = UIManager.initFirstTheme("/theme");
        this.getToolbar().addCommandToLeftBar("Retour",theme.getImage("back-command.png"),p->new ListEquipeForm(theme));
        
        TextField nom=new TextField(e.getNom());
        TextField email=new TextField(e.getEmail());
        TextField voiture=new TextField(e.getVoiture());
        TextField pays=new TextField(e.getPays_origine());
        TextField logo=new TextField(e.getLogo());
        Button submit=new Button("Modifier");
        
        submit.addActionListener(l->{
            try{
                if(nom.getText()=="" | email.getText()=="" | voiture.getText()=="" | pays.getText()=="" | logo.getText()==""){
                    Dialog.show("Veuiller remplir tous les champs","","Annuler", "OK");
                }else{
                    
                    Equipe e1=new Equipe(nom.getText(),email.getText(),logo.getText(),voiture.getText(),pays.getText());
                    e1.setId(e.getId());
                    ServiceEquipe.getInstance().modifierEquipe(e1);
                    refreshTheme();
                    new ListEquipeForm(theme).show();
                }
            }catch(Exception ex){
                
            }
        });
        this.setTitle("Modifier Equipe");
        this.setLayout(BoxLayout.y());
        this.add(nom);
        this.add(email);
        this.add(voiture);
        this.add(pays);
        this.add(logo);
        this.add(submit);
        this.show();
    }
    
}
