package workspace;

import javax.swing.*;
import java.awt.*;

public class place extends JPanel
{
    public double[][] angle4;
    public int[][] kor;
    public int hslid;
    public int vslid;

    public place()
    {
        angle4 = new double[][]{
                {0,0,0},{0,10,0},{10,0,0},{10,10,0}};
        kor = new int[][]{
                {100,0,0},{0,100,0},{0,0,100}};
        hslid = 0;
        vslid = 0;

        setPreferredSize(new Dimension(1000,600));
    }

    // перемножение матриц
    public double[][] MultiMatrix(double[][] a, double[][] b)
    {
        double[][] result = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; i++)
        {
            for (int j = 0; j < b[0].length; j++)
            {
                for (int k = 0; k < b[0].length; k++)
                {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    @Override
    public void paintComponent(Graphics g) {
        int width = 1000;
        int hight = 600;
        super.paintComponent(g);

        // матрицы поворота изображения
        double[][] hMatrix = new double[][]{
                {Math.cos(-Math.toRadians(hslid)), 0, -Math.sin(-Math.toRadians(hslid))},
                {0, 1, 0},
                {Math.sin(-Math.toRadians(hslid)), 0, Math.cos(-Math.toRadians(hslid))}};
        double[][] vMatrix = new double[][]{
                {1, 0, 0}, {0, Math.cos(Math.toRadians(vslid)), Math.sin(Math.toRadians(vslid))},
                {0, -Math.sin(Math.toRadians(vslid)), Math.cos(Math.toRadians(vslid))}};

        // отрисовка координатных осей
        double[][] Rkor = new double[3][3];
        for (int i = 0; i < 3; i++) {
            Rkor[i][0] = kor[i][0];
            Rkor[i][1] = kor[i][1];
            Rkor[i][2] = kor[i][2];
        }
        Rkor = MultiMatrix(Rkor, hMatrix);
        Rkor = MultiMatrix(Rkor, vMatrix);

        for (int i = 0; i < 3; i++) {
            int sx = width / 2 + (int) Rkor[i][0] * 10;
            int sy = hight / 2 - (int) Rkor[i][1] * 10;
            g.setColor(Color.red);
            g.drawLine(width / 2, hight / 2, sx, sy);
        }

        // смена параметров для образования сетки
        for (double h = 0; h <= 1; h += 1)
        {
            // параметр задаёт шаг сетки
            for (double U = 0; U < 1.05; U += 0.05)
            {
                double[][] grid = new double[2][3];

                // параметр задаёт противоположные грани
                for (int W = 0; W <= 1; W += 1)
                {
                    double u = U;
                    double w = W;

                    // смена параметров
                    if (h == 1)
                    {
                        u = W;
                        w = U;
                    }

                    // параметрическое уравнение билинейной поверхности
                    double px = angle4[0][0]*(1-u)*(1-w) + angle4[1][0]*(1-u)*w + angle4[2][0]*u*(1-w) + angle4[3][0]*u*w;
                    double py = angle4[0][1]*(1-u)*(1-w) + angle4[1][1]*(1-u)*w + angle4[2][1]*u*(1-w) + angle4[3][1]*u*w;
                    double pz = angle4[0][2]*(1-u)*(1-w) + angle4[1][2]*(1-u)*w + angle4[2][2]*u*(1-w) + angle4[3][2]*u*w;
                    grid[W][0] = px;
                    grid[W][1] = py;
                    grid[W][2] = pz;
                }

                // поворот изображения по X и Y
                grid = MultiMatrix(grid, hMatrix);
                grid = MultiMatrix(grid, vMatrix);

                // проецирование на экран дисплея
                int[][] grid2d = new int[2][2];
                for (int i = 0; i < 2; i++)
                {
                    grid2d[i][0] = width / 2 + (int) (grid[i][0] * 10);
                    grid2d[i][1] = hight / 2 - (int) (grid[i][1] * 10);
                }

                // отрисовка элементов сетки
                g.setColor(Color.blue);
                g.drawLine(grid2d[0][0],grid2d[0][1],grid2d[1][0],grid2d[1][1]);
            }
        }
    }
}