package tests.design_patterns;

import org.testng.annotations.Test;

/**
 * Клас дірки в якій є метод, який визначає чи влізе фігура всередині по порівнянні радіусів
 **/

/**Він працюватиме тільки з класами типу CirclePeg**/
class Hole {
    private final int radius;
    Hole(int d) {
        this.radius = d;
    }

    public boolean isFitOrNot(CirclePeg peg) {
        if (this.radius >= peg.getRadius()) {
            System.out.println("Peg with diameter " + peg.getRadius() + " fits to hole with " + this.radius + " diameter");
            return true;
        } else
            System.out.println("Peg with diameter " + peg.getRadius() + " doesn't fit to hole with " + this.radius + " diameter");
        return false;
    }
}

/**Клас круга, який підійде для такого визначення**/
class CirclePeg {
    private int radius;
    CirclePeg(){}

    CirclePeg(int d) {
        this.radius = d;
    }

    int getRadius() {
        return this.radius;
    }
}

/**Клас квадрату, який не підійде по типу, і методу про радіус він немає**/
class SquarePeg {
    private int width;

    SquarePeg(int w) {
        this.width = w;
    }

    int getWidth() {
        return this.width;
    }
}

/**Адаптер, в якому ми загортаєм квадрат в круг (CirclePeg) і робим можливим використання квадртаної фігури як круглої (getRadius())*/
class SquarePegAdapterForCircleHole extends CirclePeg {
    private SquarePeg peg;

    public SquarePegAdapterForCircleHole(SquarePeg p) {
        this.peg = p;
    }

    int getRadius() {
        return (int) (peg.getWidth() / Math.sqrt(2) / 2);
    }
}

public class Adapter {
    @Test
    public void run() {
        Hole h1 = new Hole(5);
        CirclePeg cP1 = new CirclePeg(5);
        h1.isFitOrNot(cP1);
        SquarePeg p1 = new SquarePeg(7);
        /**Так не піде, бо типу не пілходить**/
//        h1.isFitOrNot(p1);
        SquarePegAdapterForCircleHole p2 = new SquarePegAdapterForCircleHole(p1);
        /**А так піде, бо ми загорнули в пыдходящий тип**/
        h1.isFitOrNot(p2);
    }
}

