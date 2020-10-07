package ui;

import players.Bot;
import players.Gamer;
import players.Player;
import players.Value;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import static players.Value.*;

public class Panel extends JPanel {

    static final  int SIZE_X = 8;
    static final int SIZE_Y = 8;
    private static final int SIZE_EDGE = 75;

    private static int movies = SIZE_X * SIZE_Y;

    private static Gamer currentGamer;

    private static Bot bot;
    private static Player player;

    public Panel() {

        Value[] field = new Value[SIZE_X * SIZE_Y];

        Arrays.fill(field, NULL);

        bot = new Bot(field, BLACK);
        player = new Player(field, WHITE);

        currentGamer = player;

        addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {

                int x = e.getX();
                int y = e.getY();

                int m = movies;

                currentGamer.move(x, y);
                repaint();
                currentGamer.move(x, y);
                repaint();


                if(movies == 0) JOptionPane.showMessageDialog(null, "Bot: " + bot.getCount() + ", player: " + player.getCount());
            }
        });
    }

    public static int getSizeEdge() {

        return SIZE_EDGE;
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D gr = (Graphics2D)g;

        gr.setColor(Color.GREEN);
        gr.fillRect(0, 0, 800, 900);

        gr.setColor(Color.BLACK);

        for(int i = 0; i <= SIZE_X; i++)
            gr.drawLine((i * SIZE_EDGE), 0, (i * SIZE_EDGE), (SIZE_EDGE * SIZE_Y));

        for(int i = 0; i <= SIZE_Y; i++)
            gr.drawLine(0, (i * SIZE_EDGE), (SIZE_EDGE * SIZE_X), (i * SIZE_EDGE));

        if(currentGamer.getValue().equals(BLACK)) gr.setColor(Color.BLACK);
        else if(currentGamer.getValue().equals(WHITE)) gr.setColor(Color.WHITE);

        gr.fillOval(10, (SIZE_EDGE * (SIZE_Y + 1)), SIZE_EDGE, SIZE_EDGE);

        for(int i = 0; i < currentGamer.getField().length; i++) {

            if(currentGamer.getField()[i].equals(NULL)) continue;

            Value value = currentGamer.getField()[i];

            if(value.equals(BLACK)) gr.setColor(Color.BLACK);
            else gr.setColor(Color.WHITE);

            gr.fillOval(((i % SIZE_X) * SIZE_EDGE), ((i / SIZE_Y) * SIZE_EDGE), SIZE_EDGE, SIZE_EDGE);
        }

        gr.setColor(Color.GRAY);

        for(int i: currentGamer.getPossibleMovies())
            gr.fillOval(((i % SIZE_X) * SIZE_EDGE), ((i / SIZE_Y) * SIZE_EDGE), SIZE_EDGE, SIZE_EDGE);

        gr.setColor(Color.BLACK);
        gr.drawString(String.valueOf(bot.getCount()), 200, (SIZE_EDGE * (SIZE_Y + 1)));
        gr.setColor(Color.WHITE);
        gr.drawString(String.valueOf(player.getCount()), 250, (SIZE_EDGE * (SIZE_Y + 1)));
    }

    public static void changeGamer() {

        if(currentGamer instanceof Bot) currentGamer = player;
        else currentGamer = bot;
    }

    public static void decrementMovies() {

        movies--;
    }


    public static int getMovies() {

        return movies;
    }

    public static int getSizeX() {

        return SIZE_X;
    }

    public static int getSizeY() {

        return SIZE_Y;
    }
}

