package et3_projet.Classes;
import javax.swing.*;
import java.awt.*;

public class TextInvisible extends JTextField {
    private String texte;

    public TextInvisible(String texte) {
        super();
        this.texte = texte;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!isFocusOwner()) {
            g.setColor(Color.GRAY);
            int x = getInsets().left;
            int y = g.getFontMetrics().getMaxAscent() + getInsets().left;
            g.drawString(texte, x, y);
        }
    }
}