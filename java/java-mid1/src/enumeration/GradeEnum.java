package enumeration;

public enum GradeEnum {
    GOLD(15), BASIC(5);

    private final int discountPercentage;

    GradeEnum(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public double discount(int price) {
        return price - (double)price * discountPercentage / 100;
    }
}
