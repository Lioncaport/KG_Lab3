package workspace;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;

public class Main extends JDialog
{
    private JButton OKButton;
    private JPanel externalPanel;
    private JPanel window;
    private JTextField x1TextField;
    private JSlider hSlider;
    private JTextField hTextField;
    private JSlider vSlider;
    private JTextField vTextField;
    private JTextField y1TextField;
    private JTextField z1TextField;
    private JTextField x2TextField;
    private JTextField y2TextField;
    private JTextField z2TextField;
    private JTextField z4TextField;
    private JTextField y4TextField;
    private JTextField x4TextField;
    private JTextField z3TextField;
    private JTextField y3TextField;
    private JTextField x3TextField;

    public void count_of_horizont()
    {
        hTextField.setText(Integer.toString(hSlider.getValue()));
    }

    public void count_of_vertical()
    {
        vTextField.setText(Integer.toString(vSlider.getValue()));
    }

    public Main()
    {
        place myPlace = new place();
        window.add(myPlace);
        setContentPane(externalPanel);
        count_of_horizont();
        count_of_vertical();

        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                dispose();
            }
        });

        hSlider.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                count_of_horizont();
                myPlace.hslid = hSlider.getValue();
                myPlace.repaint();
            }
        });

        vSlider.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                count_of_vertical();
                myPlace.vslid = vSlider.getValue();
                myPlace.repaint();
            }
        });

        OKButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String sx1 = x1TextField.getText();
                String sy1 = y1TextField.getText();
                String sz1 = z1TextField.getText();
                String sx2 = x2TextField.getText();
                String sy2 = y2TextField.getText();
                String sz2 = z2TextField.getText();
                String sx3 = x3TextField.getText();
                String sy3 = y3TextField.getText();
                String sz3 = z3TextField.getText();
                String sx4 = x4TextField.getText();
                String sy4 = y4TextField.getText();
                String sz4 = z4TextField.getText();

                if (sx1.isEmpty() || sy1.isEmpty() || sz1.isEmpty() || sx2.isEmpty() || sy2.isEmpty() || sz2.isEmpty() || sx3.isEmpty() || sy3.isEmpty() || sz3.isEmpty() || sx4.isEmpty() || sy4.isEmpty() || sz4.isEmpty())
                {
                    JOptionPane.showMessageDialog(externalPanel, "Enter all field", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    myPlace.angle4[0][0] = Integer.parseInt(sx1);
                    myPlace.angle4[0][1] = Integer.parseInt(sy1);
                    myPlace.angle4[0][2] = Integer.parseInt(sz1);
                    myPlace.angle4[1][0] = Integer.parseInt(sx2);
                    myPlace.angle4[1][1] = Integer.parseInt(sy2);
                    myPlace.angle4[1][2] = Integer.parseInt(sz2);
                    myPlace.angle4[2][0] = Integer.parseInt(sx3);
                    myPlace.angle4[2][1] = Integer.parseInt(sy3);
                    myPlace.angle4[2][2] = Integer.parseInt(sz3);
                    myPlace.angle4[3][0] = Integer.parseInt(sx4);
                    myPlace.angle4[3][1] = Integer.parseInt(sy4);
                    myPlace.angle4[3][2] = Integer.parseInt(sz4);
                    myPlace.repaint();
                }
            }
        });
    }

    public static void main(String[] args)
    {
        Main dialog = new Main();
        dialog.setTitle("8307 -- Tkachev Igor, Guseinov Akshin -- Lab 3, Var 1");
        dialog.setSize(1300,700);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }
}
