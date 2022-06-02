package tests.design_patterns;

import org.testng.annotations.Test;

interface Shape {
    void draw();

    int getRadius();
}

//Вміє малювати круг
class Circle implements Shape {
    int radius;

    Circle(int r) {
        radius = r;
    }
    @Override
    public void draw() {
        System.out.println("Draw circle with " + radius + " radius");
    }

    @Override
    public int getRadius() {
        return radius;
    }
}

//Загортаю його в проксі, який додасть ще якусь логіку
class ProxyTry implements Shape {
    Shape shape;

    ProxyTry(Shape s) {
        this.shape = s;
    }

    @Override
    public void draw() {
        if (shape.getRadius() > 10) {
            shape.draw();
        } else System.out.println("I refuse to draw this");
    }

    @Override
    public int getRadius() {
        return shape.getRadius();
    }

}


interface ShapeDecorator extends Shape {
    void addMethod();
}

class RedShapeDecorator implements ShapeDecorator {
    Shape shape;

    RedShapeDecorator(Shape s)  {
        shape =s;
    }


    @Override
    public void draw() {
        shape.draw();
    }

    @Override
    public int getRadius() {
        return shape.getRadius();
    }

    public void addMethod() {
        System.out.println("DO DO");
    }
}


public class DecoratorAndProxy {
    @Test
    public void main() {
        Shape c1 = new Circle(2);
        c1.draw();
        Shape shape = new ProxyTry(c1);
        shape.draw();

        RedShapeDecorator rS = new RedShapeDecorator(c1);
        rS.draw();
        rS.addMethod();
    }

}


