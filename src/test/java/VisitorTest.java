/**
 * User: robshield
 * Date: 22/02/2012
 * Time: 20:32
 */
public class VisitorTest {

    static public void main(String[] args) {
        Car car = new Car();
        car.accept(new CarElementPrintVisitor());
        car.accept(new CarElementDoVisitor());
    }
}
