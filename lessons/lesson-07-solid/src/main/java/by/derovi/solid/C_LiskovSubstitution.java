package by.derovi.solid;

import lombok.*;

/**
 * Принцип в формулировке Роберта Мартина декларирует, что функции, которые используют базовый тип,
 * должны иметь возможность использовать подтипы базового типа не зная об этом.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
class Rectangle {
    public int getArea() {
        return height * width;
    }

    private int height;
    private int width;
}

@NoArgsConstructor
class Square extends Rectangle {
    Square(int size) {
        super(size, size);
    }

    public void setSize(int size) {
        super.setHeight(size);
        super.setWidth(size);
    }

    @Override
    public void setHeight(int size) {
        setSize(size);
    }

    @Override
    public void setWidth(int size) {
        setSize(size);
    }
}

public class C_LiskovSubstitution {
    static void testRectangle(Rectangle rectangle) {
        rectangle.setHeight(10);
        rectangle.setWidth(20);
        System.out.println(rectangle.getArea() == 200);
    }

    public static void main(String[] args) {
        testRectangle(new Rectangle());
        testRectangle(new Square());
    }
}
