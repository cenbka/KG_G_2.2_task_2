package com.company;

import com.company.DrawPanel;
import com.company.LineDrawer;
import com.company.PixelDrawer;

import java.awt.*;

public class BresenhamLineDrawer implements LineDrawer {
    private PixelDrawer pd;

    public BresenhamLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {

        int x, y, d;
        int dx = (x2 - x1 >= 0 ? 1 : -1);
        int dy = (y2 - y1 >= 0 ? 1 : -1);
        int lengthX = Math.abs(x2 - x1);
        int lengthY = Math.abs(y2 - y1);
        int length = Math.max(lengthX, lengthY);
        if (length == 0) {
            pd.colorPixel(x1, y1, Color.black);
        }
        if (lengthY <= lengthX) {

            x = x1;
            y = y1;
            d = -lengthX;

            length++;
            while (length != 0) {
                length--;
                pd.colorPixel(x, y, Color.orange);
                x += dx;
                d += 2 * lengthY;
                if (d > 0) {
                    d -= 2 * lengthX;
                    y += dy;
                }
            }
        } else {

            x = x1;
            y = y1;
            d = -lengthY;

            length++;
            while (length != 0) {
                length--;
                pd.colorPixel(x, y, Color.red);
                y += dy;
                d += 2 * lengthX;
                if (d > 0) {
                    d -= 2 * lengthY;
                    x += dx;
                }
            }
        }
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {

    }
}
