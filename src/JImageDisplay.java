import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/** Класс для отображения фракталов. Производный от javax.swing.JComponent.*/
public class JImageDisplay extends JComponent {

    /** * Экземпляр Buffered Image.
     ** Управляет изображением, содержимое которого можно записать. */
    private BufferedImage image;

    // объявление изображения и его параметров
    public JImageDisplay(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        /**
         ** Вызов метода родительского класса setPreferredSize()
         ** с указанной шириной и высотой. */
        Dimension dimension = new Dimension(width, height);
        super.setPreferredSize(dimension);
    }

    // метод отрисовки изображения
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    // очистка изображения (устанавливает все пиксели
    // в черный цвет)
    public void clearImage() {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                drawPixel(i, j, 0);
            }
        }
    }

    /** Установка пикселя в определённый цвет. */
    public void drawPixel(int x, int y, int rgbColor) {
        image.setRGB(x, y, rgbColor);
    }

    public BufferedImage getImage() {
        return image;
    }
}