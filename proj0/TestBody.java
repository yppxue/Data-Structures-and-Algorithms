public class TestBody {
    public static void main(String[] args) {
        Body b1 = new Body(1.0, 0.0, -999, -999, 7e5, "samh.gif");
        Body b2 = new Body(3.0, 3.0, -999, -999, 8e5, "aegir.gif");
        System.out.println(b1.calcForceExertedBy(b2));
        System.out.println(b2.calcForceExertedBy(b1));
    }
}
