package other.design_patterns;

import org.testng.annotations.Test;

/**
 * Приклад шаблону проектування Builder
 **/


class Car {                   //Клас потрібного нам інстансу з закритими полями, конструктором і без сетерів
    private String name;
    private int speed;
    private Trasmission trasmission;
    private Modification modification;

    enum Trasmission {
        AUTO, MANUAL, COSMIC, DEFAULT
    }

    enum Modification {
        SEDAN, HATCHBACK, CABRIO;
    }

    private Car(CarBuilder builder) {   //Всім полям інстансу будуть присвоєні значення полів інстансу класу-білдера
        this.name = builder.name;
        this.speed = builder.speed;
        this.trasmission = builder.trasmission;
    }

    public String toString() {
        return "Car:" + "name='" + name + '\'' + ", speed=" + speed + " Transmission=" + "'" + trasmission + "'";
    }

    static class CarBuilder {                //Статичний клас-білдер, який має дефолтні значення полів
        private String name = "Default name";
        private int speed = 0;
        private Trasmission trasmission = Trasmission.DEFAULT;
        private Modification modification = Modification.SEDAN;

        CarBuilder setName(String n) {     // Методи,які сетять поля класу і повертають його інстанс (щоб можна було чейнити)
            this.name = n;
            return this;
        }

        CarBuilder setSpeed(int s) {
            this.speed = s;
            return this;
        }

        CarBuilder sertTransmission(Trasmission tr) {
            this.trasmission = tr;
            return this;
        }

        CarBuilder setModification(Modification m) {
            this.modification = m;
            return this;
        }

        Car build() {         //Метод, який білдить наш інстанс, через параметр конструктора якого передається інстанс класу-білдера з потрібними нам полями
            return new Car(this);
        }
    }
}

public class Builder {
    @Test
    public void test() {
        Car car1 = new Car.CarBuilder().setName("Alaba").setSpeed(234).sertTransmission(Car.Trasmission.COSMIC).build();
        System.out.println(car1);
        Car car2 = new Car.CarBuilder().setName("Chery").setSpeed(123).build();
        System.out.println(car2);
        Car car3 = new Car.CarBuilder().build();
        System.out.println(car3);
    }
}


