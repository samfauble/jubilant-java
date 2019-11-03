package filters;

import java.util.ArrayList;
import java.util.List;

public abstract class DoubleChildFilter {
    protected final Filter childOne;
    protected final Filter childTwo;

    public DoubleChildFilter(Filter childOne, Filter childTwo){
        this.childOne = childOne;
        this.childTwo = childTwo;
    }

    public List<String> terms() {
        List<String> list = new ArrayList<String>();
        list.addAll(childOneTerms());
        list.addAll(childTwoTerms());
        return list;
    }

    public List<String> childOneTerms() {
        return childOne.terms();
    }

    public List<String> childTwoTerms() {
        return childTwo.terms();
    }
}
