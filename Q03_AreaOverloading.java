class Shape {
    double a, b, c;
    String type;

    // Constructor overloading - different constructors for different shapes
    Shape(double radius) {                       // Circle
        a = radius;
        type = "Circle";
    }

    Shape(double length, double breadth) {       // Rectangle
        a = length;
        b = breadth;
        type = "Rectangle";
    }

    Shape(double s1, double s2, double s3) {     // Triangle (three sides)
        a = s1;
        b = s2;
        c = s3;
        type = "Triangle";
    }
}

class AreaCalculator {
    // Method overloading - same name, different parameter lists
    double area(double radius) {
        return Math.PI * radius * radius;
    }

    double area(double length, double breadth) {
        return length * breadth;
    }

    double area(double s1, double s2, double s3) {   // Heron's formula
        double s = (s1 + s2 + s3) / 2;
        return Math.sqrt(s * (s - s1) * (s - s2) * (s - s3));
    }
}

public class Q03_AreaOverloading {
    public static void main(String[] args) {
        Shape circle = new Shape(7);
        Shape rectangle = new Shape(10, 5);
        Shape triangle = new Shape(3, 4, 5);

        AreaCalculator calc = new AreaCalculator();

        System.out.printf("%s (radius = %.1f)            : Area = %.2f%n", circle.type, circle.a, calc.area(circle.a));
        System.out.printf("%s (length = %.1f, breadth = %.1f) : Area = %.2f%n", rectangle.type, rectangle.a, rectangle.b, calc.area(rectangle.a, rectangle.b));
        System.out.printf("%s (sides = %.1f, %.1f, %.1f)   : Area = %.2f%n", triangle.type, triangle.a, triangle.b, triangle.c, calc.area(triangle.a, triangle.b, triangle.c));
    }
}

/*
==================== SAMPLE OUTPUT ====================
Circle (radius = 7.0)            : Area = 153.94
Rectangle (length = 10.0, breadth = 5.0) : Area = 50.00
Triangle (sides = 3.0, 4.0, 5.0)   : Area = 6.00
=======================================================
*/
