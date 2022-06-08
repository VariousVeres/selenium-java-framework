package other.design_patterns;


import org.testng.annotations.Test;

interface Equipment {
    void sayHi();
}

class BasketballBalls implements Equipment {
    public void sayHi() {
        System.out.println("Basketball balls");
    }
}

class FootballBalls implements Equipment {
    public void sayHi() {
        System.out.println("Football balls");
    }
}

class BasketballShoes implements Equipment {
    public void sayHi() {
        System.out.println("Basketball shoes");
    }
}

class FootballShoes implements Equipment {
    public void sayHi() {
        System.out.println("Football shoes");
    }
}

interface FactoryAbstraction {
    Equipment createEquipment(String type);
}

class BasketballFactory implements FactoryAbstraction {
    public Equipment createEquipment(String type) {
        if (type.equals("balls")) {
            return new BasketballBalls();
        } else if (type.equals("shoes")) {
            return new BasketballShoes();
        } else return null;
    }
}

class FootballFactory implements FactoryAbstraction {
    public Equipment createEquipment(String type) {
        if (type.equals("balls")) {
            return new FootballBalls();
        } else if (type.equals("shoes")) {
            return new FootballShoes();
        } else return null;
    }
}

class FactoryProducer {
    FactoryAbstraction getFactory(String sport) {
        if (sport.equals("football")) {
            return new FootballFactory();
        } else return new BasketballFactory();
    }
}

public class AbstractFactory {
    @Test
    public void main() {
        FactoryProducer producer = new FactoryProducer();
        Equipment footballBalls = producer.getFactory("football").createEquipment("balls");
        Equipment basketballShoes = producer.getFactory("basketball").createEquipment("shoes");
        footballBalls.sayHi();
        basketballShoes.sayHi();
    }
}
