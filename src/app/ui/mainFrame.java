package app.ui;

import app.newDataTypes.SportIndustry;
import app.child.Match;
import app.child.Hall;
import app.parent.Scene;
import app.child.Stadium;
import app.child.Sportsman;
import app.newDataTypes.DateAndTime;
import app.child.Training;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.math.BigInteger;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class mainFrame {
    public JPanel mainPanel;
    private JButton sceneButton;
    private JButton matchButton;
    private JButton sportsmanButton;
    private JButton trainingButton;
    private JPanel scenePanel;
    private JPanel matchPanel;
    private JPanel sportsmanPanel;
    private JPanel trainingPanel;
    private JPanel parentPanel;
    private JTextField TFMatchName;
    private JTextField TFMatchDateAndTime;
    private JCheckBox CBAddMatchCancelled;
    private JButton BTNAddMatch;
    private JList LTMatchList;
    private JPanel listMatchPanel;
    private JPanel modifyMatchPanel;
    private JTextField TFModifyMatchName;
    private JTextField TFModifyMatchDateAndTime;
    private JCheckBox CBModifyMatchCancelled;
    private JButton BTNModifyMatch;
    private JPanel addMatchPanel;
    private JTextField TFAddSceneName;
    private JTextField TFAddScenePhone;
    private JTextField TFAddSceneNumberOfAddedHalls;
    private JComboBox CBAddSceneSportIndustry;
    private JButton BTNAddScene;
    private JList LTSceneList;
    private JList LTAddMatchSportsman;
    private JList LTModifyMatchSportsman;
    private JList LTAddSceneMatch;
    private JTextField TFModifySceneName;
    private JTextField TFModifyScenePhone;
    private JTextField TFModifySceneNumberOfAddedHalls;
    private JComboBox CBModifySceneSportIndustry;
    private JList LTModifyCBModifySceneMatch;
    private JButton BTNModifyCBModifyScene;
    private JButton BTNModifySceneList;
    private JButton BTNDeleteSceneList;
    private JButton BTNModifyMatchList;
    private JButton BTNDeleteMatchList;
    private JTextField TFAddTrainingName;
    private JTextField TFAddTrainingCoachName;
    private JCheckBox CBAddTrainingCancelled;
    private JButton BTNAddTraining;
    private JList LTTrainingList;
    private JButton BTNModifyTrainingList;
    private JButton BTNDeleteTrainingList;
    private JTextField TFModifyTrainingName;
    private JTextField TFModifyTrainingDateAndTime;
    private JTextField TFModifyTrainingCoachName;
    private JCheckBox CBModifyTrainingCancelled;
    private JButton BTNModifyTraining;
    private JTextField TFAddSportsmanName;
    private JTextField TFAddSportsmanLastName;
    private JTextField TFAddSportsmanNumber;
    private JComboBox CBAddSportsmanIndustry;
    private JButton BTNAddSportsman;
    private JTextField TFModifySportsmanName;
    private JTextField TFModifySportsmanLastName;
    private JTextField TFModifySportsmanNumber;
    private JTextField TFModifySportsmanDOB;
    private JComboBox CBModifySportsmanIndustry;
    private JButton BTNModifySportsman;
    private JButton BTNCheckSportsman;
    private JTextField TFCheckSportsmanOutput;
    private JList LTCheckMatch;
    private JButton BTNCheckMatch;
    public JTextField TFCheckMatchOutput;
    private JPanel navigationPanel;
    private JPanel addScenePanel;
    private JPanel listScenePanel;
    private JPanel modifyScenePanel;
    private JPanel addTrainingPanel;
    private JPanel listTrainingPanel;
    private JPanel modifyTrainingPanel;
    private JPanel addSportsmanPanel;
    private JPanel listSportsmanPanel;
    private JPanel modifySportsmanPanel;
    private JPanel checkSportsmanPanel;
    private JPanel checkMatchPanel;
    private JList LTCheckSportsman;
    private JList LTSportsmanList;
    private JButton BTNModifySportsmanList;
    private JButton BTNDeleteSportsmanList;
    private JComboBox CBAddSceneType;
    private JComboBox CBModifySceneType;
    private JComboBox CBSceneList;
    private JTextField TFAddSceneCapacity;
    private JFormattedTextField TFAddTrainingDateAndTime;
    private JFormattedTextField TFAddSportsmanDOB;
    private JTextField TFModifySceneCapacity;
    private JComboBox<String> CBAddScenePanel;
    private JPanel addCardLayoutPanel;
    private JPanel addHallPanel;
    private JPanel addStadiumPanel;

    private ArrayList<Hall> arrayHall = new ArrayList<>();

    private ArrayList<Stadium> arrayStadium = new ArrayList<>();

    private ArrayList<Training> arrayTraining = new ArrayList<>();

    private ArrayList<Sportsman> arraySportsman = new ArrayList<>();

    private ArrayList<Match> arrayMatch = new ArrayList<>();

    DefaultListModel sceneListModel = new DefaultListModel();

    DefaultListModel trainingListModel = new DefaultListModel();

    DefaultListModel sportsmanListModel = new DefaultListModel();

    DefaultListModel matchListModel = new DefaultListModel();

    DefaultListModel matchModifyListModel = new DefaultListModel();

    DefaultListModel sceneModifyListModel = new DefaultListModel();

    DefaultListModel checkSceneListModel = new DefaultListModel();

    private static final Locale SLOVENIAN_LOCALE = new Locale("sl", "SI");
    private static final Collator SLOVENIAN_COLLATOR = Collator.getInstance(SLOVENIAN_LOCALE);

    public static void sortComboBox(JComboBox<SportIndustry.Industry> comboBox) {
        SportIndustry.Industry[] items = new SportIndustry.Industry[comboBox.getItemCount()];
        for (int i = 0; i < items.length; i++) {
            items[i] = comboBox.getItemAt(i);
        }
        Arrays.sort(items, (a, b) -> SLOVENIAN_COLLATOR.compare(a.toString(), b.toString()));
        comboBox.removeAllItems();
        for (SportIndustry.Industry item : items) {
            comboBox.addItem(item);
        }
    }


    public void saveToFile() {
        try {
            FileOutputStream fos = new FileOutputStream("data.bin");
            GZIPOutputStream gos = new GZIPOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(gos);
            oos.writeObject(arrayHall);
            oos.writeObject(arrayStadium);
            oos.writeObject(arrayTraining);
            oos.writeObject(arraySportsman);
            oos.writeObject(arrayMatch);
            oos.close();
            gos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        try {
            FileInputStream fis = new FileInputStream("data.bin");
            GZIPInputStream gis = new GZIPInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(gis);
            arrayHall = (ArrayList<Hall>) ois.readObject();
            arrayStadium = (ArrayList<Stadium>) ois.readObject();
            arrayTraining = (ArrayList<Training>) ois.readObject();
            arraySportsman = (ArrayList<Sportsman>) ois.readObject();
            arrayMatch = (ArrayList<Match>) ois.readObject();
            ois.close();
            gis.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void log(Severity severity, String description) {
        try {
            FileWriter writer = new FileWriter("application.log", true);
            LocalDateTime now = LocalDateTime.now();
            writer.write(String.format("[%s] %s: %s\n", now.toString(), severity.name(), description));
            writer.close();
        } catch (IOException var4) {
            System.err.println("Error writing to log file: " + var4.getMessage());
        }

    }

    public void setTFCheckSportsmanOutput(String text) {
        TFCheckSportsmanOutput.setText(text);
    }

    public Sportsman getSportsmanForCheckSportsman() {

        return (Sportsman) LTSportsmanList.getSelectedValue();
    }

    public Scene getSceneForCheckSportsman() {

        return (Scene) LTSceneList.getSelectedValue();
    }

    public void updateListHall() {
        sceneListModel.removeAllElements();
        for (int i = 0; i < arrayHall.size(); i++) {
            sceneListModel.addElement(arrayHall.get(i));
        }
        LTSceneList.setModel(sceneListModel);
    }

    public void updateListStadium() {
        sceneListModel.removeAllElements();
        for (int i = 0; i < this.arrayStadium.size(); i++) {
            sceneListModel.addElement(this.arrayStadium.get(i));
        }
        LTSceneList.setModel(sceneListModel);
    }

    public void updateListTraining() {
        trainingListModel.removeAllElements();
        for (int i = 0; i < this.arrayTraining.size(); i++) {
            trainingListModel.addElement(this.arrayTraining.get(i));
        }
        LTTrainingList.setModel(trainingListModel);
    }

    public void updateListSportsman() {
        sportsmanListModel.removeAllElements();
        for (int i = 0; i < this.arraySportsman.size(); i++) {
            sportsmanListModel.addElement(this.arraySportsman.get(i));
        }
        LTSportsmanList.setModel(sportsmanListModel);
        LTAddMatchSportsman.setModel(sportsmanListModel);
    }

    public void updateListMatch() {
        matchListModel.removeAllElements();
        for (int i = 0; i < this.arrayMatch.size(); i++) {
            matchListModel.addElement(this.arrayMatch.get(i));
        }
        LTMatchList.setModel(matchListModel);
        LTAddSceneMatch.setModel(matchListModel);
    }

    public void updateListMatchModify(Sportsman[] selectedSportsman, int sportsmanOnMatch) {
        matchModifyListModel.removeAllElements();
        for (int i = 0; i < this.arraySportsman.size(); i++) {
            matchModifyListModel.addElement(this.arraySportsman.get(i));

        }

        int[] indices = new int[sportsmanOnMatch];
        int counter = 0;
        for (int i = 0; i < sportsmanOnMatch; i++) {
            int index = matchModifyListModel.indexOf(selectedSportsman[i]);
            if (index >= 0) {
                indices[counter++] = index;
            }
        }
        LTModifyMatchSportsman.setModel(matchModifyListModel);
        LTModifyMatchSportsman.setSelectedIndices(indices);
    }

    public void updateListSceneMatchModify(Match[] selectedMatch, int matchOnScene) {
        sceneModifyListModel.removeAllElements();
        for (int i = 0; i < this.arrayMatch.size(); i++) {
            sceneModifyListModel.addElement(this.arrayMatch.get(i));

        }

        int[] indices = new int[matchOnScene];

        System.out.println(Arrays.toString(indices));

        int counter = 0;
        for (int i = 0; i < matchOnScene; i++) {
            int index = sceneModifyListModel.indexOf(selectedMatch[i]);
            if (index >= 0) {
                indices[counter++] = index;
            }
        }
        LTModifyCBModifySceneMatch.setModel(sceneModifyListModel);
        LTModifyCBModifySceneMatch.setSelectedIndices(indices);
    }

    public void updateListCheck() {
        checkSceneListModel.removeAllElements();

        for (int i = 0; i < arrayHall.size(); i++) {
            checkSceneListModel.addElement(arrayHall.get(i));
        }

        for (int i = 0; i < arrayStadium.size(); i++) {
            checkSceneListModel.addElement(arrayStadium.get(i));
        }

        LTCheckMatch.setModel(checkSceneListModel);
        LTCheckSportsman.setModel(checkSceneListModel);
    }

    public mainFrame() {
        loadFromFile();
        sceneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(scenePanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });

        matchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(matchPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });

        sportsmanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(sportsmanPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });

        trainingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(trainingPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });

        CBAddSceneType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = CBAddSceneType.getSelectedIndex();
                if (index == 1) {
                    TFAddSceneNumberOfAddedHalls.setEnabled(false);
                    TFAddSceneNumberOfAddedHalls.setText("Not Available");
                } else {
                    TFAddSceneNumberOfAddedHalls.setEnabled(true);
                    TFAddSceneNumberOfAddedHalls.setText("");
                }
            }
        });
        CBModifySceneType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = CBModifySceneType.getSelectedIndex();
                if (index == 1) {
                    TFModifySceneNumberOfAddedHalls.setEnabled(false);
                    TFModifySceneNumberOfAddedHalls.setText("Not Available");
                } else {
                    TFModifySceneNumberOfAddedHalls.setEnabled(true);
                    TFModifySceneNumberOfAddedHalls.setText("");
                }
            }
        });
        BTNAddScene.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int[] selectedIndices = LTAddSceneMatch.getSelectedIndices();

                if (CBAddSceneType.getSelectedIndex() == 0) {
                    BigInteger reallyBig = new BigInteger(TFAddScenePhone.getText().trim());
                    Hall newHall = new Hall(TFAddSceneName.getText().trim(), reallyBig, Integer.parseInt(TFAddSceneCapacity.getText().trim()), Integer.parseInt(TFAddSceneNumberOfAddedHalls.getText().trim()));
                    newHall.setSi((SportIndustry.Industry) CBAddSceneSportIndustry.getSelectedItem());
                    arrayHall.add(newHall);
                    TFAddSceneNumberOfAddedHalls.setText("");

                    for (int i = 0; i < selectedIndices.length; i++) {
                        try {
                            Match newMatch = (Match) matchListModel.getElementAt(selectedIndices[i]);
                            newHall.addMatch(newMatch);
                        } catch (Scene.AddingMatchException | Scene.TypeOfIndustryException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } else {
                    BigInteger reallyBig = new BigInteger(TFAddScenePhone.getText().trim());
                    Stadium newStadium = new Stadium(TFAddSceneName.getText().trim(), reallyBig, Integer.parseInt(TFAddSceneCapacity.getText().trim()));
                    newStadium.setSi((SportIndustry.Industry) CBAddSceneSportIndustry.getSelectedItem());
                    arrayStadium.add(newStadium);
                    TFAddSceneNumberOfAddedHalls.setText("Not Available");

                    for (int i = 0; i < selectedIndices.length; i++) {
                        try {
                            Match newMatch = (Match) matchListModel.getElementAt(selectedIndices[i]);
                            newStadium.addMatch(newMatch);
                        } catch (Scene.AddingMatchException | Scene.TypeOfIndustryException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }

                updateListCheck();

                TFAddSceneName.setText("");
                TFAddScenePhone.setText("");
                TFAddSceneCapacity.setText("");
                CBAddSceneSportIndustry.setSelectedIndex(-1);
                LTAddSceneMatch.clearSelection();
                saveToFile();
            }
        });
        CBSceneList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CBSceneList.getSelectedIndex() == 0) {
                    updateListHall();
                } else {
                    updateListStadium();
                }
            }
        });
        BTNModifySceneList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = LTSceneList.getSelectedIndex();


                if (CBSceneList.getSelectedIndex() == 0) {
                    TFModifySceneName.setText(arrayHall.get(index).getName());
                    TFModifyScenePhone.setText(String.valueOf(arrayHall.get(index).getPhone()));
                    TFModifySceneNumberOfAddedHalls.setText(String.valueOf(arrayHall.get(index).getNumberOfAdditionalHalls()));
                    CBModifySceneSportIndustry.setSelectedItem(arrayHall.get(index).getSi());
                    TFModifySceneNumberOfAddedHalls.setEnabled(true);

                    Match[] selectedMatch = arrayHall.get(index).getListOfMatches();

                    updateListSceneMatchModify(selectedMatch, arrayHall.get(index).getCounterMatches());
                } else {
                    TFModifySceneName.setText(arrayStadium.get(index).getName());
                    TFModifyScenePhone.setText(String.valueOf(arrayStadium.get(index).getPhone()));
                    CBModifySceneSportIndustry.setSelectedItem(arrayStadium.get(index).getSi());
                    TFModifySceneNumberOfAddedHalls.setText("Not Available");
                    TFModifySceneNumberOfAddedHalls.setEnabled(false);

                    Match[] selectedMatch = arrayStadium.get(index).getListOfMatches();

                    updateListSceneMatchModify(selectedMatch, arrayStadium.get(index).getCounterMatches());
                }

                CBSceneList.setEnabled(false);
                LTSceneList.setEnabled(false);
                BTNModifySceneList.setEnabled(false);
                BTNDeleteSceneList.setEnabled(false);
            }
        });
        BTNDeleteSceneList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = LTSceneList.getSelectedIndex();
                if (CBSceneList.getSelectedIndex() == 0) {
                    arrayHall.remove(index);
                    updateListHall();
                } else {
                    arrayStadium.remove(index);
                    updateListStadium();
                }
                saveToFile();
            }
        });
        BTNModifyCBModifyScene.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int index = LTSceneList.getSelectedIndex();

                if (CBSceneList.getSelectedIndex() == 0) {
                    BigInteger reallyBig = new BigInteger(TFModifyScenePhone.getText().trim());

                    Match[] indices = arrayHall.get(index).getListOfMatches();

                    int counter = 0;
                    for (int i = 0; i <= arrayHall.get(index).getCounterMatches(); i++) {
                        arrayHall.get(index).removeMatches();
                    }

                    int[] selectedIndices = LTModifyCBModifySceneMatch.getSelectedIndices();
                    for (int i = 0; i < selectedIndices.length; i++) {
                        try {
                            Match newMatch = (Match) matchListModel.getElementAt(selectedIndices[i]);
                            arrayHall.get(index).addMatch(newMatch);
                        } catch (Scene.AddingMatchException | Scene.TypeOfIndustryException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                    arrayHall.get(index).setName(TFModifySceneName.getText());
                    arrayHall.get(index).setPhone(reallyBig);
                    arrayHall.get(index).setNumberOfAdditionalHalls(Integer.parseInt(TFModifySceneNumberOfAddedHalls.getText()));
                    arrayHall.get(index).setSi((SportIndustry.Industry) CBModifySceneSportIndustry.getSelectedItem());
                } else {
                    BigInteger reallyBig = new BigInteger(TFModifyScenePhone.getText().trim());

                    Match[] indices = arrayStadium.get(index).getListOfMatches();

                    int counter = 0;
                    for (int i = 0; i <= arrayStadium.get(index).getCounterMatches(); i++) {
                        arrayStadium.get(index).removeMatches();
                    }

                    int[] selectedIndices = LTModifyCBModifySceneMatch.getSelectedIndices();
                    for (int i = 0; i < selectedIndices.length; i++) {
                        try {
                            Match newMatch = (Match) matchListModel.getElementAt(selectedIndices[i]);
                            arrayStadium.get(index).addMatch(newMatch);
                        } catch (Scene.AddingMatchException | Scene.TypeOfIndustryException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                    arrayStadium.get(index).setName(TFModifySceneName.getText());
                    arrayStadium.get(index).setPhone(reallyBig);
                    arrayStadium.get(index).setSi((SportIndustry.Industry) CBModifySceneSportIndustry.getSelectedItem());
                }

                TFModifySceneName.setText("");
                TFModifyScenePhone.setText("");
                TFModifySceneNumberOfAddedHalls.setText("");
                CBModifySceneSportIndustry.setSelectedIndex(-1);
                sceneModifyListModel.removeAllElements();
                LTModifyCBModifySceneMatch.setModel(sceneModifyListModel);

                CBSceneList.setEnabled(true);
                LTSceneList.setEnabled(true);
                BTNModifySceneList.setEnabled(true);
                BTNDeleteSceneList.setEnabled(true);
                saveToFile();

            }
        });
        BTNAddTraining.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(TFAddTrainingDateAndTime.getText().trim(), formatter);
                DateAndTime dateAndTime = new DateAndTime(dateTime);
                Training newTraining = new Training(TFAddTrainingName.getText().trim(), dateAndTime, CBAddTrainingCancelled.isSelected());
                newTraining.setCoachName(TFAddTrainingCoachName.getText().trim());
                arrayTraining.add(newTraining);
                updateListTraining();

                TFAddTrainingDateAndTime.setText("");
                TFAddTrainingName.setText("");
                TFAddTrainingCoachName.setText("");
                CBAddTrainingCancelled.setSelected(false);
                saveToFile();
            }
        });
        BTNModifyTrainingList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = LTTrainingList.getSelectedIndex();

                TFModifyTrainingName.setText(arrayTraining.get(index).getName());
                TFModifyTrainingDateAndTime.setText(String.valueOf(arrayTraining.get(index).getDateAndTime()));
                TFModifyTrainingCoachName.setText(arrayTraining.get(index).getCoachName());
                CBModifyTrainingCancelled.setSelected(arrayTraining.get(index).getCancelled());

                LTTrainingList.setEnabled(false);
                BTNModifyTrainingList.setEnabled(false);
                BTNDeleteTrainingList.setEnabled(false);
            }
        });
        BTNDeleteTrainingList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = LTTrainingList.getSelectedIndex();
                arrayTraining.remove(index);
                updateListTraining();
                saveToFile();
            }
        });
        BTNModifyTraining.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = LTTrainingList.getSelectedIndex();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(TFModifyTrainingDateAndTime.getText().trim(), formatter);
                DateAndTime dateAndTime = new DateAndTime(dateTime);

                arrayTraining.get(index).setName(TFModifyTrainingName.getText().trim());
                arrayTraining.get(index).setDateAndTime(dateAndTime);
                arrayTraining.get(index).setCoachName(TFModifyTrainingCoachName.getText().trim());
                arrayTraining.get(index).setCancelled(CBModifyTrainingCancelled.isSelected());
                updateListTraining();

                TFModifyTrainingName.setText("");
                TFModifyTrainingCoachName.setText("");
                TFModifyTrainingDateAndTime.setText("");
                CBModifyTrainingCancelled.setSelected(false);

                LTTrainingList.setEnabled(true);
                BTNModifyTrainingList.setEnabled(true);
                BTNDeleteTrainingList.setEnabled(true);
                saveToFile();
            }
        });
        BTNAddSportsman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sportsman newSportsman = new Sportsman(TFAddSportsmanName.getText().trim(), TFAddSportsmanLastName.getText().trim(), Integer.parseInt(TFAddSportsmanNumber.getText().trim()), LocalDate.parse(TFAddSportsmanDOB.getText().trim()));
                newSportsman.setSi((SportIndustry.Industry) CBAddSportsmanIndustry.getSelectedItem());

                arraySportsman.add(newSportsman);
                updateListSportsman();

                TFAddSportsmanName.setText("");
                TFAddSportsmanLastName.setText("");
                TFAddSportsmanNumber.setText("");
                TFAddSportsmanDOB.setText("");
                CBAddSportsmanIndustry.setSelectedIndex(-1);
                saveToFile();
            }
        });
        BTNModifySportsmanList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = LTSportsmanList.getSelectedIndex();

                TFModifySportsmanName.setText(arraySportsman.get(index).getName());
                TFModifySportsmanLastName.setText(arraySportsman.get(index).getLastName());
                TFModifySportsmanNumber.setText(String.valueOf(arraySportsman.get(index).getSportsmanNumber()));
                TFModifySportsmanDOB.setText(String.valueOf(arraySportsman.get(index).getDOB()));
                CBModifySportsmanIndustry.setSelectedItem(arraySportsman.get(index).getSi());

                LTSportsmanList.setEnabled(false);
                BTNModifySportsmanList.setEnabled(false);
                BTNDeleteSportsmanList.setEnabled(false);
            }
        });
        BTNModifySportsman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = LTSportsmanList.getSelectedIndex();

                arraySportsman.get(index).setName(TFModifySportsmanName.getText().trim());
                arraySportsman.get(index).setLastName(TFModifySportsmanLastName.getText().trim());
                arraySportsman.get(index).setSportsmanNumber(Integer.parseInt(TFModifySportsmanNumber.getText().trim()));
                arraySportsman.get(index).setDOB(LocalDate.parse(TFModifySportsmanDOB.getText().trim()));
                arraySportsman.get(index).setSi((SportIndustry.Industry) CBModifySportsmanIndustry.getSelectedItem());

                updateListSportsman();

                TFModifySportsmanName.setText("");
                TFModifySportsmanLastName.setText("");
                TFModifySportsmanNumber.setText("");
                TFModifySportsmanDOB.setText("");
                CBModifySportsmanIndustry.setSelectedIndex(-1);

                LTSportsmanList.setEnabled(true);
                BTNModifySportsmanList.setEnabled(true);
                BTNDeleteSportsmanList.setEnabled(true);
                saveToFile();
            }
        });
        BTNDeleteSportsmanList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = LTSportsmanList.getSelectedIndex();
                arraySportsman.remove(index);
                updateListSportsman();
                saveToFile();
            }
        });
        BTNAddMatch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(TFMatchDateAndTime.getText().trim(), formatter);
                DateAndTime dateAndTime = new DateAndTime(dateTime);
                Match newMatch = new Match(TFMatchName.getText().trim(), dateAndTime, CBAddMatchCancelled.isSelected());

                int[] selectedIndices = LTAddMatchSportsman.getSelectedIndices();

                for (int i = 0; i < selectedIndices.length; i++) {
                    try {
                        Sportsman newSportsman = (Sportsman) sportsmanListModel.getElementAt(selectedIndices[i]);
                        newMatch.addSportsmanToMatch(newSportsman);
                    } catch (Match.AddingSportsmanException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                arrayMatch.add(newMatch);
                updateListMatch();
                TFMatchName.setText("");
                TFMatchDateAndTime.setText("");
                CBAddMatchCancelled.setSelected(false);
                LTAddMatchSportsman.clearSelection();
                saveToFile();
            }
        });
        BTNModifyMatchList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = LTMatchList.getSelectedIndex();

                Sportsman[] selectedSportsman = arrayMatch.get(index).getListOfAthletes();

                updateListMatchModify(selectedSportsman, arrayMatch.get(index).returnNumberOfAthletesAtMatch());

                TFModifyMatchName.setText(arrayMatch.get(index).getName());
                TFModifyMatchDateAndTime.setText(String.valueOf(arrayMatch.get(index).getDateAndTime()));
                CBModifyMatchCancelled.setSelected(arrayMatch.get(index).getCancelled());

                LTMatchList.setEnabled(false);
                BTNModifyMatchList.setEnabled(false);
                BTNDeleteMatchList.setEnabled(false);
            }
        });
        BTNDeleteMatchList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = LTMatchList.getSelectedIndex();
                arrayMatch.remove(index);
                updateListMatch();
                saveToFile();
            }
        });
        BTNModifyMatch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = LTMatchList.getSelectedIndex();

                Sportsman[] indices = arrayMatch.get(index).getListOfAthletes();

                int counter = 0;
                for (int i = 0; i <= arrayMatch.get(index).returnNumberOfAthletesAtMatch(); i++) {
                    arrayMatch.get(index).removeSportsmanFromMatch(indices[i]);
                }

                int[] selectedIndices = LTModifyMatchSportsman.getSelectedIndices();
                for (int i = 0; i < selectedIndices.length; i++) {
                    try {
                        Sportsman newSportsman = (Sportsman) sportsmanListModel.getElementAt(selectedIndices[i]);
                        arrayMatch.get(index).addSportsmanToMatch(newSportsman);
                    } catch (Match.AddingSportsmanException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(TFModifyMatchDateAndTime.getText().trim(), formatter);
                DateAndTime dateAndTime = new DateAndTime(dateTime);
                arrayMatch.get(index).setName(TFModifyMatchName.getText().trim());
                arrayMatch.get(index).setDateAndTime(dateAndTime);
                arrayMatch.get(index).setCancelled(CBModifyMatchCancelled.isSelected());

                updateListMatch();

                TFModifyMatchName.setText("");
                TFModifyMatchDateAndTime.setText("");
                CBModifyMatchCancelled.setSelected(false);
                matchModifyListModel.removeAllElements();
                LTModifyMatchSportsman.setModel(matchModifyListModel);

                LTMatchList.setEnabled(true);
                BTNModifyMatchList.setEnabled(true);
                BTNDeleteMatchList.setEnabled(true);

                saveToFile();
            }
        });
    }

    private static class MultilineListCellRenderer extends JLabel implements ListCellRenderer<Object> {
        public MultilineListCellRenderer() {
            setOpaque(true);
            setVerticalAlignment(CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            setText(value.toString());
            setPreferredSize(new Dimension(list.getWidth(), 20)); // adjust height as needed
            setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
            setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
            return this;
        }
    }

    private void createUIComponents() {
        CBAddSceneType = new JComboBox();
        CBAddSceneType.addItem("Hall");
        CBAddSceneType.addItem("Stadium");
        CBAddSceneType.setSelectedIndex(0);

        CBModifySceneType = new JComboBox();
        CBModifySceneType.addItem("Hall");
        CBModifySceneType.addItem("Stadium");
        CBModifySceneType.setSelectedIndex(-1);

        CBSceneList = new JComboBox();
        CBSceneList.addItem("Hall");
        CBSceneList.addItem("Stadium");
        CBSceneList.setSelectedIndex(-1);


        CBAddSportsmanIndustry = new JComboBox(SportIndustry.Industry.values());
        CBModifySportsmanIndustry = new JComboBox(SportIndustry.Industry.values());
        CBAddSceneSportIndustry = new JComboBox(SportIndustry.Industry.values());
        CBModifySceneSportIndustry = new JComboBox(SportIndustry.Industry.values());

        sortComboBox(CBAddSportsmanIndustry);
        sortComboBox(CBModifySportsmanIndustry);
        sortComboBox(CBAddSceneSportIndustry);
        sortComboBox(CBModifySceneSportIndustry);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TFAddTrainingDateAndTime = new JFormattedTextField(formatter);
        TFMatchDateAndTime = new JFormattedTextField(formatter);

        SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
        TFAddSportsmanDOB = new JFormattedTextField(formatterDate);

        TFModifySportsmanDOB = new JFormattedTextField(formatterDate);

        LTTrainingList = new JList();
        LTTrainingList.setCellRenderer(new MultilineListCellRenderer());

        LTSportsmanList = new JList();
        LTSportsmanList.setCellRenderer(new MultilineListCellRenderer());

        LTMatchList = new JList();
        LTMatchList.setCellRenderer(new MultilineListCellRenderer());

        LTAddMatchSportsman = new JList();
        LTAddMatchSportsman.setCellRenderer(new MultilineListCellRenderer());

        LTModifyMatchSportsman = new JList();
        LTModifyMatchSportsman.setCellRenderer(new MultilineListCellRenderer());

        LTAddSceneMatch = new JList();
        LTAddSceneMatch.setCellRenderer(new MultilineListCellRenderer());

        LTModifyCBModifySceneMatch = new JList();
        LTModifyCBModifySceneMatch.setCellRenderer(new MultilineListCellRenderer());

        LTCheckMatch = new JList();
        LTCheckMatch.setCellRenderer(new MultilineListCellRenderer());

        LTCheckSportsman = new JList();
        LTCheckSportsman.setCellRenderer(new MultilineListCellRenderer());

        BTNCheckMatch = new JButton();

        class ButtonClick implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {

                Match match = (Match) LTMatchList.getSelectedValue();
                Scene scene = (Scene) LTCheckMatch.getSelectedValue();
                String text = match.checkValidity(scene);
                TFCheckMatchOutput.setText(text);
            }
        }

        BTNCheckMatch.addActionListener(new ButtonClick());

        TFCheckSportsmanOutput = new JTextField();

        BTNCheckSportsman = new JButton();

        ButtonClickListener listener = new ButtonClickListener(BTNCheckSportsman, LTSportsmanList, LTCheckSportsman, this);
        BTNCheckSportsman.addActionListener(listener);
    }

    public enum Severity {
        CRITICAL,
        ERROR,
        WARNING,
        BOOKMARK;

        private Severity() {
        }
    }
}
