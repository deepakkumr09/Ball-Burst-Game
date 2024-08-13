package code;

import java.awt.*;

public class MapGenerator
{
    public int map[][];
    public int bubbleWidth;
    public int bubbleHeight;
    public MapGenerator (int row, int col)   //constructor
    {
        map = new int[row][col];
        for(int i = 0; i<row; i++)
        {
            for(int j =0; j<col; j++)
            {
                map[i][j] = 1;
            }
        }

        bubbleWidth = 540/col;
        bubbleHeight = 150/row;
    }


    public void draw(Graphics2D g)
    {
        for(int i = 0; i<map.length; i++)
        {
            for(int j =0; j<map[0].length; j++)
            {
                if(map[i][j] > 0)
                {
                    Color v =new Color(249,123,34);
                    g.setColor(v);
                    g.fillOval(j * bubbleWidth + 80, i * bubbleHeight + 50, bubbleWidth, bubbleHeight);

            }
            }
        }
    }

    public void setBubble(int value, int row, int col)
    {
        map[row][col] = value;
    }
}
