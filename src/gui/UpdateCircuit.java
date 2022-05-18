package gui;
import com.codename1.components.FloatingHint;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entite.Circuit;
import services.ServiceCircuit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author manaa
 */
public class UpdateCircuit extends Form {
        Form current;
    public UpdateCircuit(Resources res , Circuit cs) {
        
        
   ServiceCircuit sc = new ServiceCircuit();
         setTitle("Add a new Circuit");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField(cs.getNom() ,"TaskName");
        TextField tfPays= new TextField(cs.getPays(), "Pays");
        
        TextField tfLongeur= new TextField("", "Longeur");
        TextField tfCrsD= new TextField( ""   , "Course Distance");
        TextField tfCapacite= new TextField("","Capacite");
        TextField tfDescription= new TextField(cs.getDescription(), "Description");
        TextField tfImg= new TextField(cs.getImage(), "Image");
        
        
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
          
                        cs.setNom(tfName.getText());
                        cs.setPays(tfPays.getText());
                        cs.setlongeur(Integer.parseInt(tfLongeur.getText()));
                        cs.setCourse_distance(Integer.parseInt(tfCrsD.getText()));
                        cs.setDescription(tfDescription.getText());
                        cs.setCapacite(Integer.parseInt(tfCapacite.getText()));
                        cs.setImage(tfImg.getText());
           
           
      
       
       //appel fonction modfier reclamation men service
       
       if(ServiceCircuit.getInstance().UpdateCircuit(cs)) { // if true
           new ListCircuitForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListCircuitForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
              
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
    
}
