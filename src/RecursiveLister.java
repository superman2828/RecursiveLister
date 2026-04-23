import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RecursiveLister extends JFrame implements ActionListener {

    JButton startBtn, quitBtn;
    JTextArea textArea;
    JScrollPane scrollPane;
    JLabel titleLbl = new JLabel("Recursive Lister");
    JPanel titlePnl, displayPnl, buttonPnl;
    JFileChooser fileChooser = new JFileChooser();

    public RecursiveLister() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 402);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        titlePnl = new JPanel();
        titleLbl.setFont(new Font("Arial", Font.BOLD, 20));
        titlePnl.add(titleLbl);

        startBtn = new JButton("Start");
        startBtn.addActionListener(this);
        quitBtn = new JButton("Quit");
        quitBtn.addActionListener(this);
        buttonPnl = new JPanel();
        buttonPnl.add(startBtn);
        buttonPnl.add(quitBtn);

        textArea = new JTextArea(20,50);
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
        displayPnl = new JPanel();
        displayPnl.add(scrollPane);

        add(titlePnl, BorderLayout.NORTH);
        add(displayPnl, BorderLayout.CENTER);
        add(buttonPnl, BorderLayout.SOUTH);

        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startBtn) {
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                textArea.setText("");
                listFiles(selectedFile, 0);
            }
        }
        if (e.getSource() == quitBtn) {
            System.exit(0);
        }
    }

    private void listFiles(File selectedFile, int level) {
        String indent = "  ".repeat(level);
        textArea.append(indent + selectedFile.getName() + "\n");

        File[] files = selectedFile.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listFiles(file, level + 1);
                } else {
                    textArea.append(indent + "  " + file.getName() + "\n");
                }
            }
        }
    }

    public static void main(String[] args) {
        RecursiveLister lister = new RecursiveLister();
    }
}
