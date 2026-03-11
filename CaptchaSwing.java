import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CaptchaSwing extends JFrame {

    private String captchaText;
    private JLabel captchaLabel;
    private JTextField inputField;

    public CaptchaSwing() {
        setTitle("CAPTCHA Generator");
        setSize(350,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        captchaLabel = new JLabel();
        generateCaptcha();

        inputField = new JTextField(10);

        JButton verifyBtn = new JButton("Verify");
        verifyBtn.addActionListener(e -> verifyCaptcha());

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> generateCaptcha());

        add(captchaLabel);
        add(new JLabel("Enter CAPTCHA:"));
        add(inputField);
        add(verifyBtn);
        add(refreshBtn);

        setVisible(true);
    }

    private void generateCaptcha() {
        captchaText = randomText(6);

        BufferedImage image = new BufferedImage(150, 50, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0,0,150,50);

        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.setColor(Color.BLACK);
        g.drawString(captchaText, 20, 35);

        // Noise lines
        Random rand = new Random();
        for(int i=0;i<10;i++){
            g.drawLine(rand.nextInt(150), rand.nextInt(50),
                       rand.nextInt(150), rand.nextInt(50));
        }

        captchaLabel.setIcon(new ImageIcon(image));
    }

    private String randomText(int length){
        String chars = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<length;i++){
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }

        return sb.toString();
    }

    private void verifyCaptcha(){
        if(inputField.getText().equalsIgnoreCase(captchaText)){
            JOptionPane.showMessageDialog(this,"CAPTCHA Verified!");
        } else {
            JOptionPane.showMessageDialog(this,"Incorrect CAPTCHA");
            generateCaptcha();
        }
    }

    public static void main(String[] args) {
        new CaptchaSwing();
    }
}