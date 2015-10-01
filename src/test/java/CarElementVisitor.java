/**
 * User: robshield
 * Date: 22/02/2012
 * Time: 20:33
 */
public interface CarElementVisitor {

        void visit(Wheel wheel);
        void visit(Engine engine);
        void visit(Body body);
        void visit(Car car);
}
