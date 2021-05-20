import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class FractalExplorer {
    private int displaySize;
    private JImageDisplay imageDisplay;
    private FractalGenerator fractalGenerator;
    private Rectangle2D.Double range;

    private FractalExplorer (int displaySize) {
        this.displaySize = displaySize;
        this.fractalGenerator = new Mandelbrot();
        this.range = new Rectangle2D.Double(0,0,0,0);
        fractalGenerator.getInitialRange(this.range);
    }

    public static void main(String[] args) {
        FractalExplorer fractalExplorer = new FractalExplorer(600);
        fractalExplorer.setGUI();
        fractalExplorer.drawFractal();
    }

    // задание интерфейса
    public void setGUI() {
        JFrame frame = new JFrame("Fractal Generator");
        //Создание кнопки RESET
        JButton button = new JButton("Reset");

        imageDisplay = new JImageDisplay(displaySize, displaySize);
        imageDisplay.addMouseListener(new MouseListener());

        //Привязываю кнопку к событию
        button.addActionListener(new ActionHandler());

        frame.setLayout(new java.awt.BorderLayout());
        //Позиция изображения
        frame.add(imageDisplay, BorderLayout.CENTER);
        //Позиция кнопки RESET
        frame.add(button, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Делаем всё это видимым
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    // отрисовка фрактала в JImageDisplay
    private void drawFractal() {
        // Проходимся по всем пикселям в строке
        for (int x = 0; x < displaySize; x++) {
            for (int y = 0; y < displaySize; y++) {
                // Вычисляем кол-во итераций для координат
                int counter = fractalGenerator.numIterations(FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, x),
                        //Определяем набор координат пикселей
                        fractalGenerator.getCoord(range.y, range.y + range.width, displaySize, y));
                if (counter == -1) {
                    imageDisplay.drawPixel(x, y, 0);
                }
                else {
                    // Выбираем значение цвета на основе числа итераций
                    float hue = 0.7f + (float) counter / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    // Обновляем цвет текущего пикселя
                    imageDisplay.drawPixel(x, y, rgbColor);
                }
            }
        }
        imageDisplay.repaint();
    }

    //Обработка событий с дисплея
    public class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            fractalGenerator.getInitialRange(range);
            drawFractal();
        }
    }

    //Событие с нажатие кнопки
    public class MouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            double x = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, e.getX());
            double y = FractalGenerator.getCoord(range.y, range.y + range.width, displaySize, e.getY());
            fractalGenerator.recenterAndZoomRange(range, x, y, 0.5);
            drawFractal();
        }
    }
}