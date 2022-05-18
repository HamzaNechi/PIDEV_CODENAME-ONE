package gui.classementPilotes;


import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.utils.AlertUtils;
import entite.ClassementPilotes;
import entite.Pilotes;
import entite.Saisons;

import java.util.ArrayList;
import services.ClassementPilotesService;
import services.PilotesService;
import services.SaisonsService;

public class Manage extends Form {


    ClassementPilotes currentClassementPilotes;

    TextField pointsTotalTF;
    TextField positionTF;
    Label pointsTotalLabel;
    Label positionLabel;


    ArrayList<Saisons> listSaisonss;
    PickerComponent saisonsPC;
    Saisons selectedSaisons = null;
    ArrayList<Pilotes> listPilotess;
    PickerComponent pilotesPC;
    Pilotes selectedPilotes = null;


    Button manageButton;

    Form previous;

    public Manage(Form previous) {
        super(ShowAll.currentClassementPilotes == null ? "Ajouter" : "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentClassementPilotes = ShowAll.currentClassementPilotes;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {

        String[] saisonsStrings;
        int saisonsIndex;
        saisonsPC = PickerComponent.createStrings("").label("Saisons");
        listSaisonss = SaisonsService.getInstance().getAll();
        saisonsStrings = new String[listSaisonss.size()];
        saisonsIndex = 0;
        for (Saisons saisons : listSaisonss) {
            saisonsStrings[saisonsIndex] = String.valueOf(saisons.getYear());
            saisonsIndex++;
        }
        if (listSaisonss.size() > 0) {
            saisonsPC.getPicker().setStrings(saisonsStrings);
            saisonsPC.getPicker().addActionListener(l -> selectedSaisons = listSaisonss.get(saisonsPC.getPicker().getSelectedStringIndex()));
        } else {
            saisonsPC.getPicker().setStrings("");
        }

        String[] pilotesStrings;
        int pilotesIndex;
        pilotesPC = PickerComponent.createStrings("").label("Pilotes");
        listPilotess = PilotesService.getInstance().getAll();
        pilotesStrings = new String[listPilotess.size()];
        pilotesIndex = 0;
        for (Pilotes pilotes : listPilotess) {
            pilotesStrings[pilotesIndex] = String.valueOf(pilotes.getNumero());
            pilotesIndex++;
        }
        if (listPilotess.size() > 0) {
            pilotesPC.getPicker().setStrings(pilotesStrings);
            pilotesPC.getPicker().addActionListener(l -> selectedPilotes = listPilotess.get(pilotesPC.getPicker().getSelectedStringIndex()));
        } else {
            pilotesPC.getPicker().setStrings("");
        }


        pointsTotalLabel = new Label("PointsTotal : ");
        pointsTotalLabel.setUIID("labelDefault");
        pointsTotalTF = new TextField();
        pointsTotalTF.setHint("Tapez le pointsTotal");


        positionLabel = new Label("Position : ");
        positionLabel.setUIID("labelDefault");
        positionTF = new TextField();
        positionTF.setHint("Tapez le position");


        if (currentClassementPilotes == null) {


            manageButton = new Button("Ajouter");
        } else {


            pointsTotalTF.setText(String.valueOf(currentClassementPilotes.getPointsTotal()));
            positionTF.setText(String.valueOf(currentClassementPilotes.getPosition()));

            saisonsPC.getPicker().setSelectedString(String.valueOf(currentClassementPilotes.getSaisons().getYear()));
            selectedSaisons = currentClassementPilotes.getSaisons();
            pilotesPC.getPicker().setSelectedString(String.valueOf(currentClassementPilotes.getPilotes().getNumero()));
            selectedPilotes = currentClassementPilotes.getPilotes();


            manageButton = new Button("Modifier");
        }
        manageButton.setUIID("buttonWhiteCenter");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(


                pointsTotalLabel, pointsTotalTF,
                positionLabel, positionTF,
                saisonsPC, pilotesPC,
                manageButton
        );

        this.addAll(container);
    }

    private void addActions() {

        if (currentClassementPilotes == null) {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = ClassementPilotesService.getInstance().add(
                            new ClassementPilotes(


                                    selectedSaisons,
                                    selectedPilotes,
                                    (int) Float.parseFloat(pointsTotalTF.getText()),
                                    (int) Float.parseFloat(positionTF.getText())
                            )
                    );
                    if (responseCode == 200) {
                        AlertUtils.makeNotification("ClassementPilotes ajouté avec succes");
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur d'ajout de classementPilotes. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        } else {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = ClassementPilotesService.getInstance().edit(
                            new ClassementPilotes(
                                    currentClassementPilotes.getId(),


                                    selectedSaisons,
                                    selectedPilotes,
                                    (int) Float.parseFloat(pointsTotalTF.getText()),
                                    (int) Float.parseFloat(positionTF.getText())

                            )
                    );
                    if (responseCode == 200) {
                        AlertUtils.makeNotification("ClassementPilotes modifié avec succes");
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur de modification de classementPilotes. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        }
    }

    private void showBackAndRefresh() {
        ((ShowAll) previous).refresh();
        previous.showBack();
    }

    private boolean controleDeSaisie() {


        if (pointsTotalTF.getText().equals("")) {
            Dialog.show("Avertissement", "PointsTotal vide", new Command("Ok"));
            return false;
        }
        try {
            Float.parseFloat(pointsTotalTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", pointsTotalTF.getText() + " n'est pas un nombre valide (pointsTotal)", new Command("Ok"));
            return false;
        }


        if (positionTF.getText().equals("")) {
            Dialog.show("Avertissement", "Position vide", new Command("Ok"));
            return false;
        }
        try {
            Float.parseFloat(positionTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", positionTF.getText() + " n'est pas un nombre valide (position)", new Command("Ok"));
            return false;
        }


        if (selectedSaisons == null) {
            Dialog.show("Avertissement", "Veuillez choisir un saisons", new Command("Ok"));
            return false;
        }

        if (selectedPilotes == null) {
            Dialog.show("Avertissement", "Veuillez choisir un pilotes", new Command("Ok"));
            return false;
        }


        return true;
    }
}