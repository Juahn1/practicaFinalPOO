package view;
import controlador.Controlador;
import modelo.Persona;
import view.views.EdificioView;
import view.views.PersonaView;
import view.views.UnidadView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VistaAlquiler extends JFrame{

    private JLabel lblEdificio, lblUnidad, lblPersona;

    private JComboBox<EdificioView> edificioCombo ;
    private JComboBox<PersonaView> personaCombo;
    private JComboBox<UnidadView> unidadCombo;

    private JButton btnAlquilar;


    public VistaAlquiler(){
        construirInterfaz();
        manejoEventos();
        this.setVisible(true);
        setSize(600,600);

        //primer paso hacer un constructor
    }


    private void construirInterfaz(){

        //segundo construir la interfaz
        Container c = this.getContentPane();
        c.setLayout(null);

        //instancio todos los items del container
        declararItems();
        //relleno los comboboxes
        fillComboBoxes();
        //agrego los items al layour
        addItemsToLayout(c);
        //agrego el comportamiento de los botones

    }

    private void setDefaultNull(){
        edificioCombo.setSelectedItem(null);
        unidadCombo.setSelectedItem(null);
        personaCombo.setSelectedItem(null);
    }

    private void declararItems(){

        //labels
        lblEdificio = new JLabel("Edificios");
        lblEdificio.setBounds(10,20,140,30);
        lblUnidad = new JLabel("Unidades");
        lblUnidad.setBounds(160,20,140,30);
        lblPersona = new JLabel("Personas");
        lblPersona.setBounds(310,20,140,30);

        //combo boxes

        edificioCombo = new JComboBox<EdificioView>();
        edificioCombo.setBounds(10,40,140,30);
        unidadCombo = new JComboBox<UnidadView>();
        unidadCombo.setBounds(160,40,140,30);
        personaCombo = new JComboBox<PersonaView>();
        personaCombo.setBounds(310,40,140,30);

        //botones

        btnAlquilar = new JButton("Alquilar");
        btnAlquilar.setBounds(350,500,140,30);


    }

    private void fillComboBoxes(){
        //remuevo los items apra que queden vacios
        edificioCombo.removeAllItems();
        unidadCombo.removeAllItems();
        personaCombo.removeAllItems();

        //relleno los combos
        List<EdificioView> edificiosList = Controlador.getInstance().getEdificios();
        for (EdificioView ev: edificiosList) {
            edificioCombo.addItem(ev);
        }
        List<UnidadView> unidadesList = Controlador.getInstance().getUnidades();
        for (UnidadView un: unidadesList) {
            unidadCombo.addItem(un);
        }
        List<PersonaView> personasList = Controlador.getInstance().getPersonas();
        for (PersonaView per: personasList) {
            personaCombo.addItem(per);
        }
        setDefaultNull();
    }

    private void addItemsToLayout(Container c){
        //labels
        c.add(lblEdificio);
        c.add(lblUnidad);
        c.add(lblPersona);

        //combos

        c.add(edificioCombo);
        c.add(unidadCombo);
        c.add(personaCombo);

        //btn

        c.add(btnAlquilar);



    }


    private void manejoEventos(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        btnAlquilar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EdificioView eSelected = (EdificioView) edificioCombo.getSelectedItem();
                UnidadView uSelected = (UnidadView) unidadCombo.getSelectedItem();
                PersonaView pSelected = (PersonaView) unidadCombo.getSelectedItem();

                Controlador.getInstance().alquilarUnidad(eSelected.getCodigo(),uSelected.getPiso(),uSelected.getNumero(),pSelected.getDocumento());


            }
        });
    }
}
