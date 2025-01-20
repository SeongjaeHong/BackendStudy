package equals;

import java.util.Objects;

public class Rectangle {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Rectangle rectangle)) return false;
        return width == rectangle.width && height == rectangle.height;

    }
}
