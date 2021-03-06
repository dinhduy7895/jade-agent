package agentsystem.server.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.io.File;
import agentsystem.server.Constants;
import java.awt.Image;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class ConfigureServerFrame extends JFrame {
    
    String choosertitle;
    ConfigureServerFrame self;
    private OnConfigurationChangedListener onConfigurationChangedListener;

    public void setOnConfigurationChangedListener(OnConfigurationChangedListener listener) {
        onConfigurationChangedListener = listener;
    }
    
    private static ImageIcon getIcon(String name) {
		java.net.URL imgURL = ConfigureServerFrame.class.getResource("/image/" + name);
		if (imgURL != null) {
			ImageIcon water = new ImageIcon(imgURL);
			Image img = water.getImage();
			Image newimg = img.getScaledInstance(30, 20, java.awt.Image.SCALE_SMOOTH);
			water = new ImageIcon(newimg);
			return water;
		} else {
			System.err.println("Couldn't find file: " + name);
			return null;
		}
	}

    
    public ConfigureServerFrame() {
        self = this;
        setTitle("Configure Server");
        setSize(800, 238);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(39, 155, 700, 45);
        getContentPane().add(panel);
        panel.setLayout(null);

        
        JButton btnOk = new JButton("Connect",getIcon("ok.png"));
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                acceptConfigure();
            }
        });
        btnOk.setBounds(10, 11, 180, 40);
        panel.add(btnOk);

        JButton btnDefault = new JButton("Default",getIcon("default.png"));
        btnDefault.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                useDefaultConfigure();
            }
        });
        btnDefault.setBounds(210, 11, 180, 40);
        panel.add(btnDefault);

        JButton btnCancel = new JButton("Cancel",getIcon("cancel.png"));
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeFrame();
            }
        });
        btnCancel.setBounds(410, 11, 180, 40);
        panel.add(btnCancel);

        JLabel lblSaveImagesDirectory = new JLabel("Save Images Directory:");
        lblSaveImagesDirectory.setBounds(10, 11, 200, 14);
        getContentPane().add(lblSaveImagesDirectory);

        JLabel lblNewLabel = new JLabel("Port:");
        lblNewLabel.setBounds(10, 36, 46, 14);
        getContentPane().add(lblNewLabel);

        txtImagesDirectory = new JTextField();
        txtImagesDirectory.setBounds(244, 8, 250, 20);
        getContentPane().add(txtImagesDirectory);
        txtImagesDirectory.setColumns(10);

        txtPort = new JTextField();
        txtPort.setBounds(244, 33,  100, 23);
        getContentPane().add(txtPort);
        txtPort.setColumns(40);
        
        JButton btnchoose = new JButton("Browse",getIcon("browse.png"));
        btnchoose.setBounds(530,8, 180, 23);
        btnchoose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new java.io.File("."));
                fileChooser.setDialogTitle(choosertitle);
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setAcceptAllFileFilterUsed(false);
                int returnVal = fileChooser.showOpenDialog(self);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    txtImagesDirectory.setText(file.getAbsolutePath());
                }

            }
        });
        getContentPane().add(btnchoose);
        
        
    }

    protected void closeFrame() {
        dispose();
    }

    private void updateServerConfigure(int port, String imagesDirectory) {
        if (onConfigurationChangedListener != null) {
            onConfigurationChangedListener.onConfigurationChanged(imagesDirectory, port);
        }
        System.setProperty("port", port + "");
        System.setProperty("imgdir", imagesDirectory);
        File dir = new File(imagesDirectory);
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }
        dispose();
    }

    protected void useDefaultConfigure() {
        String imagesDirectory = Constants.CONFIG_DEFAULT_SAVING_IMAGES_DIR;
        int port = Constants.CONFIG_DEFAULT_SERVER_PORT;
        updateServerConfigure(port, imagesDirectory);
    }

    protected void acceptConfigure() {
        String imagesDirectory = txtImagesDirectory.getText();
        String sPort = txtPort.getText();
        int port = Integer.valueOf(sPort);
        updateServerConfigure(port, imagesDirectory);
    }

    private static final long serialVersionUID = 1L;
    private JTextField txtImagesDirectory;
    private JTextField txtPort;

    public interface OnConfigurationChangedListener {
        void onConfigurationChanged(String savingImagesDirectory, int port);
    }

}
