package gui.classementPilotes;

import com.codename1.components.InteractionDialog;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import entite.ClassementPilotes;

import java.util.ArrayList;
import java.util.Collections;
import services.ClassementPilotesService;
import utils.Statics;

public class ShowAll extends Form {

    public static ClassementPilotes currentClassementPilotes = null;
    Form previous;
    Button addBtn;


    PickerComponent sortPicker;
    ArrayList<Component> componentModels;
    Label saisonsLabel, pilotesLabel, pointsTotalLabel, positionLabel;
    Button editBtn, deleteBtn;
    Container btnsContainer;

    public ShowAll(Form previous) {
        super("ClassementPilotess", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        addGUIs();
        addActions();

        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        addActions();
        this.refreshTheme();
    }

    private void addGUIs() {
        addBtn = new Button("Ajouter");
        addBtn.setUIID("buttonWhiteCenter");
        this.add(addBtn);


        ArrayList<ClassementPilotes> listClassementPilotess = ClassementPilotesService.getInstance().getAll();

        componentModels = new ArrayList<>();

        sortPicker = PickerComponent.createStrings("Saisons", "Pilotes", "PointsTotal", "Position").label("Trier par");
        sortPicker.getPicker().setSelectedString("");
        sortPicker.getPicker().addActionListener((l) -> {
            if (componentModels.size() > 0) {
                for (Component componentModel : componentModels) {
                    this.removeComponent(componentModel);
                }
            }
            componentModels = new ArrayList<>();
            Statics.compareVar = sortPicker.getPicker().getSelectedString();
            Collections.sort(listClassementPilotess);
            for (ClassementPilotes classementPilotes : listClassementPilotess) {
                Component model = makeClassementPilotesModel(classementPilotes);
                this.add(model);
                componentModels.add(model);
            }
            this.revalidate();
        });
        this.add(sortPicker);

        if (listClassementPilotess.size() > 0) {
            for (ClassementPilotes classementPilotes : listClassementPilotess) {
                Component model = makeClassementPilotesModel(classementPilotes);
                this.add(model);
                componentModels.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private void addActions() {
        addBtn.addActionListener(action -> {
            currentClassementPilotes = null;
            new Manage(this).show();
        });

    }

    private Container makeModelWithoutButtons(ClassementPilotes classementPilotes) {
        Container classementPilotesModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        classementPilotesModel.setUIID("containerRounded");


        saisonsLabel = new Label("Saisons : " + classementPilotes.getSaisons());
        saisonsLabel.setUIID("labelDefault");

        pilotesLabel = new Label("Pilotes : " + classementPilotes.getPilotes());
        pilotesLabel.setUIID("labelDefault");

        pointsTotalLabel = new Label("PointsTotal : " + classementPilotes.getPointsTotal());
        pointsTotalLabel.setUIID("labelDefault");

        positionLabel = new Label("Position : " + classementPilotes.getPosition());
        positionLabel.setUIID("labelDefault");

        saisonsLabel = new Label("Saisons : " + classementPilotes.getSaisons().getYear());
        saisonsLabel.setUIID("labelDefault");

        pilotesLabel = new Label("Pilotes : " + classementPilotes.getPilotes().getNumero());
        pilotesLabel.setUIID("labelDefault");


        classementPilotesModel.addAll(

                saisonsLabel, pilotesLabel, pointsTotalLabel, positionLabel
        );

        return classementPilotesModel;
    }

    private Component makeClassementPilotesModel(ClassementPilotes classementPilotes) {

        Container classementPilotesModel = makeModelWithoutButtons(classementPilotes);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonWhiteCenter");
        editBtn.addActionListener(action -> {
            currentClassementPilotes = classementPilotes;
            new Manage(this).show();
        });

        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonWhiteCenter");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce classementPilotes ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = ClassementPilotesService.getInstance().delete(classementPilotes.getId());

                if (responseCode == 200) {
                    currentClassementPilotes = null;
                    dlg.dispose();
                    classementPilotesModel.remove();
                    this.refreshTheme();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du classementPilotes. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);


        classementPilotesModel.add(btnsContainer);

        return classementPilotesModel;
    }

}