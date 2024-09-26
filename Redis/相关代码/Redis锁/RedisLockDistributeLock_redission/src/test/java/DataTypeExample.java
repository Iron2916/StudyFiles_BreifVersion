import java.util.Arrays;

public class DataTypeExample {

    public static void main(String[] args) {
        // Primitive types
        byte aByte = 127;
        short aShort = 32767;
        int anInt = 2147483647;
        long aLong = 9223372036854775807L;

        float aFloat = 3.14f;
        double aDouble = 3.141592653589793;

        char aChar = 'A';
        boolean aBoolean = true;

        // Reference types
        String aString = "Hello, World!";
        int[] anArray = {1, 2, 3, 4, 5};
        MyClass myObject = new MyClass();
        MyInterface myInterface = new MyClass();
        Day today = Day.MONDAY;

        // Printing values
        System.out.println("byte: " + aByte);
        System.out.println("short: " + aShort);
        System.out.println("int: " + anInt);
        System.out.println("long: " + aLong);
        System.out.println("float: " + aFloat);
        System.out.println("double: " + aDouble);
        System.out.println("char: " + aChar);
        System.out.println("boolean: " + aBoolean);
        System.out.println("String: " + aString);
        System.out.println("Array: " + Arrays.toString(anArray));
        System.out.println("Object: " + myObject);
        System.out.println("Interface: " + myInterface);
        System.out.println("Enum: " + today);
    }
}

class MyClass implements MyInterface {
    // Class implementation
}

interface MyInterface {
    // Interface methods
}

enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}