package com.company;

import com.company.LineDrawer;
import com.company.PixelDrawer;

import java.awt.*;

public class WuLineDrawer implements LineDrawer {
    private PixelDrawer pd;

    public WuLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {

    }

    public void drawLine(int x1, int y1, int x2, int y2, boolean change) {
        int step;
        int dx;
        int dy;
        if (x1 > x2) {
            int c = x1; x1 = x2; x2 = c;

            int cy = y1; y1 = y2; y2 = cy;
        }
        if (y2 < y1) {
            step = -1;
        } else step = 1;
        dx = x2 - x1;
        dy = step * (y2 - y1);


        int x = x1 + 1;
        int y = y1;
        int yy = y1;
        double coefficient = (double) dy / dx;
        double y_line;
        double grad;

        int d = 2 * dy - dx;
        int d1 = 2 * dy;
        int d2 = 2 * (dy - dx);
        while (x < x2) {
            y_line = coefficient * (x - x1) * step + y1;

            if (d < 0) {
                d = d + d1;
            } else if (d >= 0) {

                d = d + d2;
                y = y + step;
            }
            if (((step > 0) && (y_line > y)) || ((step < 0) && (y_line < y))) {

                yy = y + step;
            } else {
                yy = y - step;
            }
            if (yy == y) {
                grad = 1;
            } else {
                if (Math.abs(y_line - y) < 1) {
                    grad = Math.abs((y_line - y));
                } else grad = Math.abs(y_line - yy);
            }
            if (change) {
                pd.colorPixel(yy, x, colorOne(grad, 1));
                pd.colorPixel(y, x, colorOne(grad, 2));
            } else {
                pd.colorPixel(x, yy, colorTwo(grad, 1));
                pd.colorPixel(x, y, colorTwo(grad, 2));
            }
            x++;
        }
    }

    public Color colorOne(double grad, int var) {
        return var == 1 ? new Color(218, 53, 32, (int) (255 * grad)) : new Color(218, 53, 32, (int) (255 * (1 - grad)));
    }

    public Color colorTwo(double grad, int var) {
        return var == 1 ? new Color(0, 53, 32, (int) (255 * grad)) : new Color(0, 53, 32, (int) (255 * (1 - grad)));
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        int dy = y2 - y1;
        int dx = x2 - x1;
        pd.colorPixel(x1, y1, colorOne(0.5, 1));
        if (Math.abs(dy) < Math.abs(dx)) {
            drawLine(x1, y1, x2, y2, false);
        } else if (Math.abs(dy) >= Math.abs(dx)) {
            drawLine(y1, x1, y2, x2, true);
        }
    }

}
