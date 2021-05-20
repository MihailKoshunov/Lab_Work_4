import java.awt.geom.Rectangle2D;

/** * Класс наследуется от FractalGenerator.
 * Используется для вычисления фрактала Мандельброта. */
public class Mandelbrot extends FractalGenerator {

    /**
     ** Константа с максимальным кол-вом итераций. */
    public static final int MAX_ITERATIONS = 2000;


    /** * Метод позволяет генератору фракталов определить наиболее «интересную»
     * область комплексной плоскости * для конкретного фрактала. */
    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -1.5;
        range.width = 3;
        range.height = 3;
    }

    /**
     ** Метод реализует итерационную функция Мандельброта */
    @Override
    public int numIterations(double x, double y) {

        int count = 0;
        double re = 0;
        double im = 0;

        while (re * re + im * im <= 4) {
            double nextRe = re * re - im * im + x;
            double nextIm = 2 * re * im + y;

            re = nextRe;
            im = nextIm;
            /** * Если кол-во итераций дошло до максимального, возвращаем -1,
             ** чтобы показать, что точка не выходит за границы. */
            if (count++ == Mandelbrot.MAX_ITERATIONS) return -1;
        }

        return count;
    }

}
