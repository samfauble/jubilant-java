package filters;

import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;

public class AndFilter extends DoubleChildFilter implements Filter {


    public AndFilter(Filter childOne, Filter childTwo){
        super(childOne, childTwo);
    }

    @Override
    public boolean matches(Status s) {
        return childOne.matches(s) && childTwo.matches(s);
    }

    public String toString() {
        return "(" + childOne.toString() + " and "  + childTwo.toString() + ")";
    }

}
