package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.fasterxml.jackson.databind.ObjectMapper;

import types.GoData;
import types.PokemonBase;
import types.PokemonInstance;
import types.exceptions.LeagueEligibleException;
import types.inputs.BestRating;
import types.inputs.EncounterType;
import types.inputs.IVRating;
import types.inputs.PokemonIVInput;
import types.outputs.IVSpreads;
import utils.FileEncoder;
import utils.Utils;
import utils.calcs;

/**
 * Desktop Applet made to easily calculate the hidden IVs of a pokemon in the game Pokemon Go
 * 
 * This applet has some bugs with it, but it was essentially made to be a proof of concept application
 *      so that I may make one for the mobile android platform. As such this would not likely be getting
 *      updates in the future
 * 
 * @author Thomas Lee
 *
 */

public class IVCalculator extends JFrame{

    private Font readableFont;
    
    private JPanel pokemonPane;
    
    private GoData goData;
    private PokemonBase curPoke;
    
    private ObjectMapper mapper;
    
    private JPanel statPane;
    private JLabel nameLabel;
    private JLabel hpLabel;
    private JLabel attLabel;
    private JLabel defLabel;
    
    private JPanel appPane;

    private JCheckBox bestAttackButton;
    private JCheckBox bestDefenseButton;
    private JCheckBox bestHPButton;
    private ButtonGroup percentGroup;
    private ButtonGroup statAppGroup;
    
    private JPanel ivSpreadPane;
    private ArrayList<IVSpreads> ivSpreads;
    private JList<String> spreadList;
    private JTextArea statArea;
    
    private ArrayList<HashMap<PokemonInstance, Integer>> pokeRankMap;
    
    public static void main(String[] args) {
        new IVCalculator();
    }

    public IVCalculator() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(450, 100, 1050, 800);
        this.setTitle("Pokemon Go Data Updater");
        
        mapper = new ObjectMapper();
        goData = FileEncoder.decryptFile("PokeDataEncrypt", "######", mapper);
        
        readableFont = new Font(Font.DIALOG, Font.PLAIN, 20);

        pokemonPane = new JPanel(new BorderLayout());

        setupPokemonPane();
        this.add(pokemonPane);
        
        this.setVisible(true);
        this.setResizable(false);
    }
    
    public void setupPokemonPane() {

        JList<String> pokeList = new JList<String>();

        JPanel pokeScrollPane = new JPanel(new FlowLayout());
        pokeScrollPane.setPreferredSize(new Dimension(260, 800));
        
        pokeList.setModel(populatePokemonList());
        pokeList.setFont(readableFont);
        JScrollPane pokeListScroll = new JScrollPane(pokeList);
        pokeListScroll.setPreferredSize(new Dimension(260, 710));
        pokeList.setFixedCellHeight(40);
        
        JTextField pokeSearchField = new JTextField();
        pokeSearchField.setToolTipText("Search for Pokemon Here");
        pokeSearchField.setFont(readableFont);
        pokeSearchField.setPreferredSize(new Dimension(260, 40));
        
        pokeListScroll.setWheelScrollingEnabled(true);
        
        pokeSearchField.addKeyListener(new KeyAdapter() {
            
            public void keyTyped(KeyEvent e) {
                String search = pokeSearchField.getText();
                if (Character.isAlphabetic(e.getKeyChar())) {
                    search = search + e.getKeyChar();
                }
                pokeList.setModel(populateSearchPokemonList(search));
            }
        });
        
        pokeScrollPane.add(pokeSearchField);
        pokeScrollPane.add(pokeListScroll);

        JPanel pokeNameStatPane = new JPanel(new FlowLayout());
        
        setupStatPane();
        
        pokeNameStatPane.add(statPane);
        
        pokeList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!pokeList.isSelectionEmpty()) {
                    PokemonBase poke = goData.getPoke(pokeList.getSelectedValue());
                    nameLabel.setText(poke.getName());
                    hpLabel.setText("" + poke.getBaseHP());
                    attLabel.setText("" + poke.getBaseAtt());
                    defLabel.setText("" + poke.getBaseDef());
                    
                    populateRankMaps(poke);
                    
                    statPane.setVisible(true);
                }
            }
        });
        
        JPanel iVCalcPane = new JPanel(new GridLayout(3, 3));
        JLabel curCPLabel = new JLabel("Current CP");
        JLabel curHPLabel = new JLabel("Current HP");
        JLabel nextLevelLabel = new JLabel("Stardust for Level up");

        JTextField curCPField = new JTextField();
        JTextField curHPField = new JTextField();
        JTextField nextLevelField = new JTextField();
        
        JCheckBox vanillaBox = new JCheckBox("Have not Leveled up");
        JButton calcButton = new JButton("Calucate IVs");
        
        JCheckBox apprasial = new JCheckBox("Apprasail");

        setupApprasialPane();
        
        apprasial.setFont(readableFont);
        apprasial.setPreferredSize(new Dimension(750,20));
        appPane.setVisible(false);
        
        apprasial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (apprasial.isSelected()) {
                    appPane.setVisible(true);
                } else {
                    appPane.setVisible(false);
                }
            }
        });
        
        curCPLabel.setFont(readableFont);
        curHPLabel.setFont(readableFont);
        nextLevelLabel.setFont(readableFont);
        curCPField.setFont(readableFont);
        curHPField.setFont(readableFont);
        nextLevelField.setFont(readableFont);
        vanillaBox.setFont(readableFont);
        calcButton.setFont(readableFont);
        
        JComboBox<EncounterType> encounterDropDown = new JComboBox<EncounterType>(EncounterType.values());
        encounterDropDown.setFont(readableFont);

        iVCalcPane.add(curCPLabel);
        iVCalcPane.add(curHPLabel);
        iVCalcPane.add(nextLevelLabel);
        iVCalcPane.add(curCPField);
        iVCalcPane.add(curHPField);
        iVCalcPane.add(nextLevelField);
        iVCalcPane.add(apprasial);
        iVCalcPane.add(vanillaBox);
        iVCalcPane.add(encounterDropDown);
        
        iVCalcPane.setPreferredSize(new Dimension(750,100));
        
        curCPField.addFocusListener(new textSelector(curCPField));
        curHPField.addFocusListener(new textSelector(curHPField));
        nextLevelField.addFocusListener(new textSelector(nextLevelField));
        
        pokeNameStatPane.add(iVCalcPane);
        
        pokeNameStatPane.add(appPane);
        pokeNameStatPane.add(calcButton);
        
        setupIVSpreadPane();
        
        pokeNameStatPane.add(ivSpreadPane);
        
        nextLevelField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    calcButton.doClick();
                }
            }
        });
        
        calcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                curPoke = goData.getPoke(pokeList.getSelectedValue());
                int curCp = Integer.parseInt(curCPField.getText());
                int curHp = Integer.parseInt(curHPField.getText());
                int nextLevel = Integer.parseInt(nextLevelField.getText());
                EncounterType floor = (EncounterType) encounterDropDown.getSelectedItem();

                if (floor == EncounterType.LUCKY) {
                    nextLevel = 2 * nextLevel;
                }
                
                PokemonIVInput input = new PokemonIVInput(curPoke, curCp, curHp, nextLevel, vanillaBox.isSelected(), floor);
                
                if (apprasial.isSelected()) {
                    IVRating iv = IVRating.valueOf(percentGroup.getSelection().getActionCommand());
                    BestRating best = BestRating.valueOf(statAppGroup.getSelection().getActionCommand());
                    
                    input.setRating(iv);
                    input.setBest(best);

                    input.setAttB(bestAttackButton.isSelected());
                    input.setDefB(bestDefenseButton.isSelected());
                    input.setHpB(bestHPButton.isSelected());
                    
                }
                TreeSet<IVSpreads> ivs = calcs.calcIVs(input);
                ivSpreads = new ArrayList<IVSpreads>();
                DefaultListModel<String> ivList = new DefaultListModel<String>();
                
                for (IVSpreads iv: ivs) {
                    ivList.addElement("Level " + iv.getLevelString() + ": " + iv.getIVSpread() + " " + iv.getPercent() + "%");
                    ivSpreads.add(iv);
                }
                
                spreadList.setModel(ivList);
                
                ivSpreadPane.setVisible(true);
            }
        });
        
        pokemonPane.add(pokeScrollPane, BorderLayout.WEST);
        pokemonPane.add(pokeNameStatPane, BorderLayout.CENTER);
    }

    private void setupStatPane() {
        statPane = new JPanel(new GridLayout(3, 3));
        
        statPane.setPreferredSize(new Dimension(750,70));
        
        nameLabel = new JLabel("PlaceHolder");
        JLabel baseHpLabel = new JLabel("Base HP:");
        JLabel baseAttLabel = new JLabel("Base Attack:");
        JLabel baseDefLabel = new JLabel("Base Defense:");
        hpLabel = new JLabel("PlaceHolder");
        attLabel = new JLabel("PlaceHolder");
        defLabel = new JLabel("PlaceHolder");

        nameLabel.setFont(readableFont);
        baseHpLabel.setFont(readableFont);
        baseAttLabel.setFont(readableFont);
        baseDefLabel.setFont(readableFont);
        hpLabel.setFont(readableFont);
        attLabel.setFont(readableFont);
        defLabel.setFont(readableFont);
        
        statPane.add(new JLabel());
        statPane.add(nameLabel);
        statPane.add(new JLabel());
        statPane.add(baseHpLabel);
        statPane.add(baseAttLabel);
        statPane.add(baseDefLabel);
        statPane.add(hpLabel);
        statPane.add(attLabel);
        statPane.add(defLabel);
        
        statPane.setVisible(false);
    }

    private void setupApprasialPane() {
        appPane = new JPanel();
        appPane.setPreferredSize(new Dimension(700,120));
        
        JPanel percentPane = new JPanel(new GridLayout(1,4));
        percentGroup = new ButtonGroup();
        JRadioButton percentExcel = new JRadioButton("Excellent Rating");
        JRadioButton percentGreat = new JRadioButton("Great Rating");
        JRadioButton percentAverage = new JRadioButton("Average Rating");
        JRadioButton percentBad = new JRadioButton("Bad Rating");

        percentExcel.setFont(readableFont);
        percentExcel.setActionCommand("EXCELLENT");
        percentGreat.setFont(readableFont);
        percentGreat.setActionCommand("GREAT");
        percentAverage.setFont(readableFont);
        percentAverage.setActionCommand("GOOD");
        percentBad.setFont(readableFont);
        percentBad.setActionCommand("BAD");
        
        percentGroup.add(percentExcel);
        percentGroup.add(percentGreat);
        percentGroup.add(percentAverage);
        percentGroup.add(percentBad);
        percentPane.add(percentExcel);
        percentPane.add(percentGreat);
        percentPane.add(percentAverage);
        percentPane.add(percentBad);
        
        JPanel bestStatPane = new JPanel(new GridLayout(1, 3));
        bestAttackButton = new JCheckBox("Attack");
        bestDefenseButton = new JCheckBox("Defense");
        bestHPButton = new JCheckBox("Hp");

        bestAttackButton.setFont(readableFont);
        bestDefenseButton.setFont(readableFont);
        bestHPButton.setFont(readableFont);
        
        bestStatPane.add(bestAttackButton);
        bestStatPane.add(bestDefenseButton);
        bestStatPane.add(bestHPButton);
        
        JPanel statAppPane = new JPanel(new GridLayout(1,4));
        statAppGroup = new ButtonGroup();
        JRadioButton statExcel = new JRadioButton("Excellent Rating");
        JRadioButton statGreat = new JRadioButton("Great Rating");
        JRadioButton statAverage = new JRadioButton("Average Rating");
        JRadioButton statBad = new JRadioButton("Bad Rating");

        statExcel.setFont(readableFont);
        statExcel.setActionCommand("BEST");
        statGreat.setFont(readableFont);
        statGreat.setActionCommand("GREAT");
        statAverage.setFont(readableFont);
        statAverage.setActionCommand("GOOD");
        statBad.setFont(readableFont);
        statBad.setActionCommand("BAD");
        
        statAppGroup.add(statExcel);
        statAppGroup.add(statGreat);
        statAppGroup.add(statAverage);
        statAppGroup.add(statBad);
        statAppPane.add(statExcel);
        statAppPane.add(statGreat);
        statAppPane.add(statAverage);
        statAppPane.add(statBad);
        
        appPane.add(percentPane);
        appPane.add(bestStatPane);
        appPane.add(statAppPane);
        
    }
    
    private void setupIVSpreadPane() {
        ivSpreadPane = new JPanel(new FlowLayout());
        
        ivSpreadPane.setPreferredSize(new Dimension(750, 300));
        
        spreadList = new JList<String>();
        spreadList.setFont(readableFont);
        JScrollPane spredScroll = new JScrollPane(spreadList);
        spredScroll.setPreferredSize(new Dimension(230, 280));
        spreadList.setFixedCellHeight(40);
        
        spredScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        statArea = new JTextArea();
        
        statArea.setFont(readableFont);
        statArea.setPreferredSize(new Dimension(500, 300));
        statArea.setBackground(new Color(238, 238, 238));
        
        ivSpreadPane.add(spredScroll);
        ivSpreadPane.add(statArea);
        
        spreadList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!spreadList.isSelectionEmpty()) {
                    IVSpreads selected = ivSpreads.get(spreadList.getSelectedIndex());
                    
                    PokemonInstance poke = new PokemonInstance(curPoke, selected);
                    
                    String statText = getPokeCPStrings(poke, true, pokeRankMap.get(0));
                    
                    List<PokemonInstance> evolutions = goData.getEvolutions(curPoke, selected);
                    
                    if (evolutions != null) {
                        for (int i = 0; i < evolutions.size(); i++) {
                            PokemonInstance evo = evolutions.get(i);
                            statText += "\n\n" + getPokeCPStrings(evo, false, pokeRankMap.get(i+1));
                        }
                    }
                    
                    
                    statArea.setText(statText);
                }
            }
        });
        
        ivSpreadPane.setVisible(false);
    }
    
    private String getPokeCPStrings(PokemonInstance poke, boolean first, HashMap<PokemonInstance, Integer> rankMap) {
        String statText = poke.getNickname();
        
        if(statText.length() < 13) {
            statText += "\t";
        }
        
        if (first && poke.getLevel() != 40) {
            PokemonInstance next = new PokemonInstance(poke);
            
            next.levelUp();
            statText += "\tNext level up: " + calcs.calcCP(next) + " CP";
        } else {
            statText += "\tEvo CP: " + calcs.calcCP(poke) + " CP";
        }
        
        try {
            PokemonInstance glPoke = calcs.calcPvpLevel(poke, Utils.GREAT_LEAGUE);
            int glCP = calcs.calcCP(glPoke);
            
            statText = statText + "\nGL Eligible: Level " + glPoke.printLevel() + " @ " + glCP + " CP";
            
            statText = statText + "     Rank " + rankMap.get(poke);
        } catch (LeagueEligibleException e1) {
        }
        
        statText = statText + "\nMax CP at level 40: " + calcs.calcMaxCP(poke) + " CP";
        
        return statText;
    }
    
    private DefaultListModel<String> populatePokemonList() {
        DefaultListModel<String> list = new DefaultListModel<String>();
        
        for (PokemonBase poke: goData.getPokeSet()) {
            list.addElement(poke.getName());
        }
        
        return list;
    }

    private DefaultListModel<String> populateSearchPokemonList(String search){
        if (search == null || search.length() == 0) {
            return populatePokemonList();
        }
        
        DefaultListModel<String> list = new DefaultListModel<String>();
        search = search.toLowerCase();

        for (PokemonBase poke: goData.getPokeSet()) {
            if (poke.getName().toLowerCase().contains(search))
            list.addElement(poke.getName());
        }
        
        return list;
    }
    
    private void populateRankMaps(PokemonBase base) {
        pokeRankMap = new ArrayList<HashMap<PokemonInstance, Integer>>();
        
        pokeRankMap.add(calcs.calcRanks(base));
        
        List<PokemonBase> evos = goData.getEvolutions(base);
        
        for (PokemonBase p: evos) {
            pokeRankMap.add(calcs.calcRanks(p));
        }
    }
    
    private class textSelector extends FocusAdapter {
        private JTextField field;
        
        public textSelector(JTextField f) {
            field = f;
        }
        
        public void focusGained(FocusEvent e) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    field.selectAll();
                }
            });
        }
    }
    
}
