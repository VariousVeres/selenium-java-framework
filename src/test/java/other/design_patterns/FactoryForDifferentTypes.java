package other.design_patterns;

import org.testng.annotations.Test;

public class FactoryForDifferentTypes {
    interface Button {
        void paint();
    }

    interface Checkbox {
        void paint();
    }

    class WindButton implements Button {
        @Override
        public void paint() {
            System.out.println("I am Window button");
        }
    }

    class MacosButton implements Button {
        @Override
        public void paint() {
            System.out.println("I am Macos button");
        }
    }

    class WindCheckbox implements Checkbox {
        @Override
        public void paint() {
            System.out.println("I am Window checkbox");
        }
    }

    class MacosCheckbox implements Checkbox {
        @Override
        public void paint() {
            System.out.println("I am Macos checkbox");
        }
    }


    public interface GUIFactory {
        Button createButton();

        Checkbox createCheckbox();
    }

    public class MacOSFactory implements GUIFactory {
        @Override
        public Button createButton() {
            return new MacosButton();
        }

        @Override
        public Checkbox createCheckbox() {
            return new MacosCheckbox();
        }
    }

    public class WindFactory implements GUIFactory {

        @Override
        public Button createButton() {
            return new WindButton();
        }

        @Override
        public Checkbox createCheckbox() {
            return new WindCheckbox();
        }
    }

    public class FactoryProducer {
        GUIFactory returnFactory() {
            if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                return new MacOSFactory();
            } else return new WindFactory();
        }
    }

    @Test
    public void test() {
        FactoryProducer prod = new FactoryProducer();
        prod.returnFactory().createButton().paint();
        prod.returnFactory().createCheckbox().paint();

    }


}
