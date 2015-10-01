/**
 * User: robshield
 * Date: 22/02/2012
 * Time: 20:34
 */
public class Wheel implements CarElement{

    private String name;

    public Wheel(String name) {
        this.name = name;
    }

    public String getName() {

        return this.name;
    }

    public void accept(CarElementVisitor visitor) {
        visitor.visit(this);
    }
}
