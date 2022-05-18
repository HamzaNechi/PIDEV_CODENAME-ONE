/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entite.Circuit;
import static gui.BaseForm.myForm;
import gui.classementPilotes.ShowAll;
import services.ServiceCircuit;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author manaa
 */
public class ListCircuitForm extends Form{
    ImageViewer imgv ;
    Image image;
    private EncodedImage enc;
    private String url="http://127.0.0.1:8000/images/circuits/";
    Resources theme = UIManager.initFirstTheme("/theme");
    Form current = new Form(BoxLayout.y());
    
    Form f0 = new Form(new FlowLayout(CENTER, CENTER));
    Form f1 = new Form(new FlowLayout(CENTER, CENTER));
    Form f2 = new Form(new FlowLayout(CENTER, CENTER));
    Form f3 = new Form(new FlowLayout(CENTER, CENTER));
    Form f4 = new Form(new FlowLayout(CENTER, CENTER));
    Form f5 = new Form(new FlowLayout(CENTER, CENTER));
    ListCircuitForm(Resources res) {
         f0.add(new Label("Home"));
        f1.add(new Label("Voir compte"));
        f2.add(new Label("Voir equipe"));
        f3.add(new Label("Voir Circuit"));
        f4.add(new Label("Voir participation"));
        f5.add(new Label("Voir classement"));
        
      // this.getToolbar().addCommandToLeftBar("Retour",theme.getImage("back-command.png"),e->new BaseForm(res).show());
          Toolbar tb = this.getToolbar();
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
   Button ajout = new Button("Ajouter un nouveau circuit");
        
       this.add(ajout);
        ajout.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                new AddCircuitForm(theme).show();
            }
        });
        
        ArrayList<Circuit> list=ServiceCircuit.getInstance().afficherCircuit();
        for(Circuit e:list){
            
            //image
            try {              
                EncodedImage enc = EncodedImage.create("/circuit.jpg");
                String path="http://127.0.0.1:8000/images/circuits/"+e.getImage();
                image=URLImage.createToStorage(enc,e.getImage(),path,URLImage.RESIZE_SCALE).scaled(700, 300);
                imgv=new ImageViewer(image);
            } catch (IOException ex) {
                
            }
            
            
            //endimage
            
          Label nom =new Label("Nom : "+ e.getNom());
          Label pays =new Label("pays : "+ e.getPays());
          Label longeur =new Label("longeur : "+ e.getlongeur());
          Label coursedistance =new Label("Course distance : "+ e.getCourse_distance());
          Label desc =new Label("Description : "+ e.getDescription());
          Label capacite =new Label("capacite : "+ e.getCapacite());
          Label img =new Label("Voiture : "+ e.getImage());
          
          
          Button btnS=new Button("Supprimer");
          Button btnM = new Button("Modifier");
          Button btnAddMap = new Button("Afficher mapp");
        
          
          
          
          
          
          
          btnS.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {
                  ServiceCircuit.getInstance().DeleteCircuit(e.getCircuit_id());
                  //refreshTheme();
                  new ListCircuitForm(res);
              }
          });
           btnAddMap.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {
                 
                 new MapForm();
              }
          });
          
          Container c1=new Container(BoxLayout.x());
          c1.add(btnS);
          c1.add(btnM);
          c1.add(btnAddMap);
            Container c=new Container(BoxLayout.y());
           c.add(imgv);
            c.add(nom);
            c.add(pays);
            c.add(longeur);
            
            c.add(coursedistance);
            c.add(desc);
            c.add(capacite);
            c.add(img);
            c.add(c1);
            this.add(c);
        }
        
        
        this.setTitle("Circuit");
        
                

        this.show();    }
    
    

    
}
