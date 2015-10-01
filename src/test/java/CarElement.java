/**
 * User: robshield
 * Date: 22/02/2012
 * Time: 20:33
 */
public interface CarElement {
    void accept(CarElementVisitor visitor); // CarElements have to provide accept().

    //visitor has two parts, something that accepts a visitor, and a visitor itself.
    //the visitor is the thing that does the work is it?
    //ideal for running the same thing on multiple instances.
    //you call visit passing in the different objects that go to make up the collection.
    //the thing that holds the collection has the accept method on it.   This method is an interface
    //implementation which accepts a visitor.
}
