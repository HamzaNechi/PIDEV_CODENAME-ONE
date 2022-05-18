/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
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
public class AjoutEquipeForm extends Form{
    String pathLogo;
    String exx;
    Resources theme = UIManager.initFirstTheme("/theme");
    public AjoutEquipeForm(){
        this.getToolbar().addCommandToLeftBar("Retour",theme.getImage("back-command.png"),e->new ListEquipeForm(theme).show());
        TextField nom=new TextField("","Nom");
        TextField email=new TextField("","Email");
        TextField voiture=new TextField("","Voiture");
        TextField pays=new TextField("","Pays");
        TextField logo=new TextField("","Logo");
        Button submit=new Button("Ajouter");
        
        
        
        
        
        
        this.setTitle("Ajouter Equipe");
        this.setLayout(BoxLayout.y());
        this.add(nom);
        this.add(email);
        this.add(voiture);
        this.add(pays);
        this.add(logo);
        
        
        //choose file
        CheckBox multiSelect = new CheckBox("Multi-select");
        Button testImage = new Button("Browse Images");
        testImage.addActionListener(e->{
            if (FileChooser.isAvailable()) {
                
                FileChooser.showOpenDialog(multiSelect.isSelected(), ".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg,.bmp", e2-> {
                    if(e2!=null && e2.getSource()!=null) {

                        String file = (String)e2.getSource();
                        pathLogo=file;
                        exx=file.substring(file.lastIndexOf("."));
                        
                        logo.setText(pathLogo);
                        System.out.println("path :"+pathLogo+ " extension :" +exx);
                        
                        try {
                            Image img = Image.createImage(file);
                            this.add(new Label(img));
                            this.revalidate();
                        } catch (Exception ex) {
                            Log.e(ex);
                        }


                    }
               });
            }
        });
        this.add(testImage);
        //endchoosefile
        
        
        
        //submit
        submit.addActionListener(l->{
            try{
                if(nom.getText()=="" | email.getText()=="" | voiture.getText()=="" | pays.getText()=="" | pathLogo ==""){
                    Dialog.show("Veuiller remplir tous les champs","","Annuler", "OK");
                }else{
                    Equipe e=new Equipe(nom.getText(),email.getText(),pathLogo,voiture.getText(),pays.getText(),exx);
                    
                    System.out.println(e.toString());
                    ServiceEquipe.getInstance().addEquipe(e);
                    refreshTheme();
                    new ListEquipeForm(theme).show();
                }
            }catch(Exception e){
                
            }
        });
        //end 
        
        this.add(submit);
        this.show();
    }
    
}
