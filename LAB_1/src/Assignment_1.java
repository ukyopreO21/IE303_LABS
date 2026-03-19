import java.util.Scanner;

public class Assignment_1 {
    public static double calcArea(double r) {
        final int n = 1000000;
        double area = 0;
        double dx = r / n;
        for (int i = 0; i < n; i++) {
            double x = i * dx;
            double y = Math.sqrt(r * r - x * x);
            area += y * dx;
        }

        return area * 4;
    }

    public static void main(String[] args) {
        System.out.print("Nhap ban kinh r: ");
        Scanner sc = new Scanner(System.in);
        double r = sc.nextDouble();
        sc.close();

        System.out.println("Dien tich hinh tron la: " + calcArea(r));
    }
}