/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.util.Resources;
import entite.Membre;
import java.util.ArrayList;
import services.ServiceEquipe;
import utils.Statics;

/**
 *
 * @author nechi
 */
public class AttestationForm extends Form{
    TableModel model;
    Resources theme;
    
    public AttestationForm(int id,String nom,String email){
        theme = UIManager.initFirstTheme("/theme");
        this.getToolbar().addCommandToLeftBar("Retour",theme.getImage("back-command.png"),e->new ListEquipeForm(theme));
        this.setTitle("Attestation d'inscription");
        Button pdf=new Button("Télécharger pdf");
            
            pdf.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    String url=Statics.BASE_URL+"/generate_pdf_mobile/"+id;
                    FileSystemStorage fs = FileSystemStorage.getInstance();
                    String fileName = fs.getAppHomePath() + "attestation inscription_"+ id +".pdf";
                    System.out.println(fileName);
                    if(!fs.exists(fileName)) {
                        Util.downloadUrlToFile(url, fileName, true);
                    }
                    Display.getInstance().execute(fileName);
                }
            });

            Label sep=new Label("---------------------------------");
            Label sep1=new Label("---------------------------------");
            Label lab1 = new Label("Equipe : "+ nom);
            Label lab2= new Label("Email : " + email);
            Label lab3=new Label("Responsable : L'équipe "+nom +" a été inscrit dans la tournoi tunisienne de FormulaOne .");
            Label lab4=new Label("Les membres inscrit dans cette équipe :");
            Container c=new Container(BoxLayout.y());
            c.add(lab1);
            c.add(lab2);
            c.add(sep);
            c.add(lab3);
            c.add(lab4);
            c.add(sep1);
            this.add(c);
            
            
            //afficher membre
            ArrayList<Membre> list=ServiceEquipe.getInstance().Equipeattestation(id);
            
            for(Membre m:list){
                
                //tableau
                model = new DefaultTableModel(new String[] {"Nom", "Role", "Numero"}, new Object[][] {
                {m.getNom(), m.getRole(), m.getNum()},
                }) {
                    public boolean isCellEditable(int row, int col) {
                        return col != 0;
                    }
                };
            }
                Table table = new Table(model) {
                    @Override
                    protected Component createCell(Object value, int row, int column, boolean editable) { // (1)
                        Component cell;
                        if(row == 1 && column == 1) { // (2)
                            Picker p = new Picker();
                            p.setType(Display.PICKER_TYPE_STRINGS);
                            p.setStrings("Row B can now stretch", "This is a good value", "So Is This", "Better than text field");
                            p.setSelectedString((String)value); // (3)
                            p.setUIID("TableCell");
                            p.addActionListener((e) -> getModel().setValueAt(row, column, p.getSelectedString())); // (4)
                            cell = p;
                        } else {
                            cell = super.createCell(value, row, column, editable);
                        }
                        if(row > -1 && row % 2 == 0) { // (5)
                            // pinstripe effect 
                            cell.getAllStyles().setBgColor(0xeeeeee);
                            cell.getAllStyles().setBgTransparency(255);
                        }
                        return cell;
                    }

                    @Override
                    protected TableLayout.Constraint createCellConstraint(Object value, int row, int column) {
                        TableLayout.Constraint con =  super.createCellConstraint(value, row, column);
                        if(row == 1 && column == 1) {
                            con.setHorizontalSpan(2);
                        }
                        con.setWidthPercentage(33);
                        return con;
                    }
                };
                //endtableua
            
            //end
            
                
        this.add(table);
        this.add(pdf);
        this.show();
    }
}
