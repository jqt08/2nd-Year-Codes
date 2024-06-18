public class UnboxingExample {
    public static void main(String[] args) {
        Integer wrappedInt = 99; // Integer object
        int primitiveInt = wrappedInt; // Unboxing
        System.out.println("Wrapped Integer: " + wrappedInt);
        System.out.println("Primitive int: " + primitiveInt);
    }
}
