/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Command;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import services.ServiceCircuit;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entite.Circuit;

/**
 *
 * @author manaa
 */
public class AddCircuitForm  extends Form{
    Resources rs;
     Resources theme;
    AddCircuitForm(Resources res) {
         theme = UIManager.initFirstTheme("/theme");
        this.getToolbar().addCommandToLeftBar("Retour",theme.getImage("back-command.png"),e->new HomeForm(theme).show());
//         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
//    
//        Toolbar tb = new Toolbar(true);
//        AddCircuitForm current = this;
//        setToolbar(tb);
//        getTitleArea().setUIID("Container");
//        setTitle("Ajout Reclamation");
//        getContentPane().setScrollVisible(false);
//        
//        
//        tb.addSearchCommand(e ->  {
//            
//        });
//        
//        Tabs swipe = new Tabs();
//        
//        Label s1 = new Label();
//        Label s2 = new Label();
//        
//        //addTab(swipe,s1, previous.getImage("back-logo.jpeg"),"","",res);
//        
//        //
//        
//         swipe.setUIID("Container");
//        swipe.getContentPane().setUIID("Container");
//        swipe.hideTabs();
//
//        ButtonGroup bg = new ButtonGroup();
//        int size = Display.getInstance().convertToPixels(1);
//        Image unselectedWalkthru = Image.createImage(size, size, 0);
//        Graphics g = unselectedWalkthru.getGraphics();
//        g.setColor(0xffffff);
//        g.setAlpha(100);
//        g.setAntiAliased(true);
//        g.fillArc(0, 0, size, size, 0, 360);
//        Image selectedWalkthru = Image.createImage(size, size, 0);
//        g = selectedWalkthru.getGraphics();
//        g.setColor(0xffffff);
//        g.setAntiAliased(true);
//        g.fillArc(0, 0, size, size, 0, 360);
//        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
//        FlowLayout flow = new FlowLayout(CENTER);
//        flow.setValign(BOTTOM);
//        Container radioContainer = new Container(flow);
//        for (int iter = 0; iter < rbs.length; iter++) {
//            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
//            rbs[iter].setPressedIcon(selectedWalkthru);
//            rbs[iter].setUIID("Label");
//            radioContainer.add(rbs[iter]);
//        }
//
//        rbs[0].setSelected(true);
//        swipe.addSelectionListener((i, ii) -> {
//            if (!rbs[ii].isSelected()) {
//                rbs[ii].setSelected(true);
//            }
//        });
//        
        
        ServiceCircuit sc = new ServiceCircuit();
         setTitle("Add a new Circuit");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("","TaskName");
        TextField tfPays= new TextField("", "Pays");
        TextField tfLongeur= new TextField("", "Longeur");
        TextField tfCrsD= new TextField("", "Course Distance");
        TextField tfCapacite= new TextField("", "Capacite");
        TextField tfDescription= new TextField("", "Description");
        TextField tfImg= new TextField("", "Image");
        
        Button btnValider = new Button("Add Circuit");
        try{
         btnValider.addActionListener(new ActionListener() {
             
            
             @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0)||(tfCapacite.getText().length()==0)||(tfCrsD.getText().length()==0)||(tfDescription.getText().length()==0)||(tfImg.getText().length()==0)||(tfLongeur.getText().length()==0)||(tfPays.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();
                     Circuit
        c1 = new Circuit(String.valueOf(tfName.getText()) ,tfPays.getText(),Integer.parseInt(tfLongeur.getText()),Integer.parseInt(tfCrsD.getText()),tfDescription.getText(),Integer.parseInt(tfCapacite.getText()),tfImg.getText());
               ServiceCircuit.getInstance().addCircit(c1);
               iDialog.dispose();
               refreshTheme();
               new ListCircuitForm(rs).show();
               
                } }
                    
                
                
                
            });
         }catch(Exception ex){ex.printStackTrace();}
        
        
        addAll(tfName,tfPays,tfCapacite,tfCrsD,tfDescription,tfImg,tfLongeur,btnValider);
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
