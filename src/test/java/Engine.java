/**
 * User: robshield
 * Date: 22/02/2012
 * Time: 20:35
 */
public class Engine implements CarElement{

    public void accept(CarElementVisitor visitor) {
        visitor.visit(this);
    }
}
