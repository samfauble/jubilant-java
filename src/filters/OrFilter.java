package filters;

import twitter4j.Status;


public class OrFilter extends DoubleChildFilter implements Filter {

    public OrFilter(Filter childOne, Filter childTwo){
        super(childOne, childTwo);
    }

    @Override
    public boolean matches(Status s) {
        return childOne.matches(s) || childTwo.matches(s);
    }

    public String toString() {
        return "(" + childOne.toString() + " or "  + childTwo.toString() + ")";
    }


}
