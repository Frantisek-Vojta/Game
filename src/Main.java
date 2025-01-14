import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JFrame frame;
    private JTextArea texts;
    private JButton option1Button;
    private JButton option2Button;
    private JLabel stats;
    private int dny;
    private int zlataky;
    private String jmeno;

    public void start() {
        frame = new JFrame("Proklata Relikvie");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 400);
        frame.setLayout(new BorderLayout());

        texts = new JTextArea();
        texts.setEditable(false);
        texts.setLineWrap(true);
        texts.setWrapStyleWord(true);
        texts.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(texts);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        option1Button = new JButton("Začít hru");
        option2Button = new JButton("Návod");

        buttonPanel.add(option1Button);
        buttonPanel.add(option2Button);

        stats = new JLabel("Den: 0 | Zlaťáky: 0 | Cíl: 100 zlaťáků");
        stats.setHorizontalAlignment(SwingConstants.CENTER);
        stats.setFont(new Font("Arial", Font.BOLD, 14));

        frame.add(stats, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        askForHeroName();
    }

    private void askForHeroName() {
        jmeno = JOptionPane.showInputDialog(frame, "Zadejte jméno svého hrdiny:", "Nastavení hrdiny", JOptionPane.PLAIN_MESSAGE);
        if (jmeno == null || jmeno.trim().isEmpty()) {
            jmeno = "Neznámý hrdina";
        }
        dny = 0;
        zlataky = 0;
        updateStatus();
        showIntro();
    }

    private void updateStatus() {
        stats.setText("Den: " + dny + " | Zlaťáky: " + zlataky + " | Cíl: 100 zlaťáků");
    }

    private void checkGameState() {
        if (zlataky >= 100) {
            texts.setText("Gratulujeme, " + jmeno + ", dosáhl jsi cíle a vyhrál hru!");
            option1Button.setEnabled(false);
            option2Button.setEnabled(false);
        } else if (dny >= 10) {
            texts.setText("Bohužel, " + jmeno + ", nepodařilo se ti získat dostatek zlaťáků během 10 dní. Hra končí.");
            option1Button.setEnabled(false);
            option2Button.setEnabled(false);
        }
    }

    private void showIntro() {
        texts.setText("Vítej ve hře Prokleta relikvie, " + jmeno + "!\n\nVyberte jednu z možností z tlačítek níže:");
        option1Button.setText("Začít hru");
        option2Button.setText("Návod");

        clearActionListeners();

        option1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dny++;
                updateStatus();
                startStory();
                checkGameState();
            }
        });

        option2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInfo();
            }
        });
    }

    private void showInfo() {
        texts.setText("Jak hrát: Vyberte z možností níže a dohrajte tuhle hru.!\n\n" +
                "Informace o hře: Získávejte zlaťáky. Musíte získat 100 zlaťáků během 10 dní. Pro uspesne dhrani hry");
        option1Button.setText("Zpět");
        option2Button.setVisible(false);

        clearActionListeners();

        option1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                option2Button.setVisible(true);
                showIntro();
            }
        });
    }

    private void startStory() {
        texts.setText("Den " + dny + ": Probudil ses v husté džungli.\n\n" +
                "1. Prozkoumat okolí\n" +
                "2. Odpočinout si");
        option1Button.setText("Prozkoumat okolí");
        option2Button.setText("Odpočinout si");

        clearActionListeners();

        option1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dny++;
                zlataky += 5; // Snížení zisku zlaťáků
                updateStatus();
                explore();
                checkGameState();
            }
        });

        option2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dny++;
                updateStatus();
                rest();
                checkGameState();
            }
        });
    }

    private void explore() {
        texts.setText("Den " + dny + ": Narazil jsi na opuštěný chrám.\n\n" +
                "1. Vejít dovnitř\n" +
                "2. Vrátit se zpět");
        option1Button.setText("Vejít dovnitř");
        option2Button.setText("Vrátit se zpět");

        clearActionListeners();

        option1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dny++;
                zlataky += 10; // Snížení zisku zlaťáků
                updateStatus();
                enterTemple();
                checkGameState();
            }
        });

        option2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dny++;
                updateStatus();
                startStory();
                checkGameState();
            }
        });
    }

    private void enterTemple() {
        texts.setText("Den " + dny + ": Chrám je temný a plný pastí.\n\n" +
                "1. Pokračovat do hlubin chrámu\n" +
                "2. Vrátit se na povrch");
        option1Button.setText("Pokračovat");
        option2Button.setText("Vrátit se");

        clearActionListeners();

        option1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dny++;
                zlataky += 15; // Snížení zisku zlaťáků
                updateStatus();
                findTreasure();
                checkGameState();
            }
        });

        option2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dny++;
                updateStatus();
                startStory();
                checkGameState();
            }
        });
    }

    private void findTreasure() {
        texts.setText("Den " + dny + ": Našel jsi poklad plný zlaťáků, ale také jsi aktivoval past!\n\n" +
                "1. Pokusit se utéct\n" +
                "2. Hledat jinou cestu ven");
        option1Button.setText("Utéct");
        option2Button.setText("Hledat cestu");

        clearActionListeners();

        option1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dny++;
                zlataky += 20; // Snížení zisku zlaťáků
                updateStatus();
                escapeTrap();
                checkGameState();
            }
        });

        option2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dny++;
                zlataky += 20; // Snížení zisku zlaťáků
                updateStatus();
                findAlternatePath();
                checkGameState();
            }
        });
    }

    private void escapeTrap() {
        texts.setText("Den " + dny + ": Úspěšně jsi utekl z chrámu s pokladem!\n\n" +
                "1. Pokračovat v dobrodružství\n" +
                "2. Vrátit se domů");
        option1Button.setText("Dobrodružství");
        option2Button.setText("Domů");

        clearActionListeners();

        option1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dny++;
                updateStatus();
                startStory();
                checkGameState();
            }
        });

        option2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endGame();
            }
        });
    }

    private void findAlternatePath() {
        texts.setText("Den " + dny + ": Našel jsi tajný východ z chrámu a jsi na svobodě!\n\n" +
                "1. Pokračovat v dobrodružství\n" +
                "2. Vrátit se domů");
        option1Button.setText("Dobrodružství");
        option2Button.setText("Domů");

        clearActionListeners();

        option1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dny++;
                updateStatus();
                startStory();
                checkGameState();
            }
        });

        option2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endGame();
            }
        });
    }

    private void rest() {
        texts.setText("Den " + dny + ": Odpočinul jsi si a načerpal energii na další dobrodružství.\n\n" +
                "1. Pokračovat v dobrodružství\n" +
                "2. Vrátit se domů");
        option1Button.setText("Dobrodružství");
        option2Button.setText("Domů");

        clearActionListeners();

        option1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dny++;
                updateStatus();
                startStory();
                checkGameState();
            }
        });

        option2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endGame();
            }
        });
    }

    private void endGame() {
        if (zlataky >= 100) {
            texts.setText("Gratulujeme, " + jmeno + "! Úspěšně jsi dokončil dobrodružství a získal poklad.");
        } else {
            texts.setText("Rozhodl jsi se vrátit domů, " + jmeno + ". Možná se dobrodružství příště podaří lépe.");
        }
        option1Button.setEnabled(false);
        option2Button.setEnabled(false);
    }

    private void clearActionListeners() {
        for (ActionListener al : option1Button.getActionListeners()) {
            option1Button.removeActionListener(al);
        }
        for (ActionListener al : option2Button.getActionListeners()) {
            option2Button.removeActionListener(al);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main game = new Main();
            game.start();
        });
    }
}





// THIS IS THE END OF THE CODE
// REALLY THE END