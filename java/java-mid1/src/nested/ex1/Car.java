package nested.ex1;

public class Car {
    private String model;
    private int chargeLevel;
    private Engine engine;

    public Car(String model, int chargeLevel) {
        this.model = model;
        this.chargeLevel = chargeLevel;
        this.engine = new Engine();
    }

    public void start() {
        engine.start();
        System.out.println("Start " + model);
    }

    private class Engine {
        public void start() {
            System.out.println("Charge level: " + chargeLevel);
            System.out.println("Start engine of " + model);
        }
    }
}
