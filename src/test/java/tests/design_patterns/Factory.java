package tests.design_patterns;

import org.testng.annotations.Test;

/**
 * Інтрфейс загальний для фігури
 **/
interface Figure {
    void draw();

    enum FigureType {
        SQUARE, TRIANGLE, ROUND;
    }
}

/**
 * Класи, які імплементять
 **/
class Square implements Figure {
    private int oneSide = 0;
    private int secondSide = 0;

    Square(int x, int y) {
        this.oneSide = x;
        this.secondSide = y;
    }

    @Override
    public void draw() {
        System.out.println("I am square with sides " + oneSide + " & " + secondSide + " accordingly");
    }
}

class Triangle implements Figure {
    @Override
    public void draw() {
        System.out.println("I am triangle");
    }
}

class Round implements Figure {
    @Override
    public void draw() {
        System.out.println("I am round");
    }
}

/**
 * Клас, який має фабричний метод, який повертає інстанс того типу що треба
 **/
class FigureFactory {
    public Figure createFigure(Figure.FigureType type) {
        if (type.equals(Figure.FigureType.ROUND)) {
            return new Round();
        } else if (type.equals(Figure.FigureType.SQUARE)) {
            return new Square(3, 5);
        } else if (type.equals(Figure.FigureType.TRIANGLE)) {
            return new Triangle();
        } else return null;
    }
}

/**
 * Хочем квадрат, хочем трикутник - нема проблем
 **/
public class Factory {
    @Test
    public void main() {
        FigureFactory creation = new FigureFactory();
        Figure round = creation.createFigure(Figure.FigureType.ROUND);
        Figure square = creation.createFigure(Figure.FigureType.SQUARE);
        round.draw();
        square.draw();
    }

}
