package stqa.sandbox;

public class Results {
    public static void main(String[] array){
        Point p1 = new Point(1,2);
        Point p2 = new Point(4,2);

        System.out.println("Расстояние между p1 и p2 = " + p1.distance(p2));
    }
}