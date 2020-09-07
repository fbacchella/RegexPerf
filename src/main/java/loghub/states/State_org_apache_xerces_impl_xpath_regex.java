package loghub.states;

import org.apache.xerces.impl.xpath.regex.RegularExpression;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import loghub.Runner;

@State(Scope.Benchmark)
public class State_org_apache_xerces_impl_xpath_regex extends Runner<RegularExpression> {

    @Override
    protected RegularExpression[] getPatternStorage(int size) {
        return new RegularExpression[size];
    }

    @Override
    protected RegularExpression generate(String i) {
        return new RegularExpression(i);
    }

    @Override
    protected boolean match(RegularExpression pattern, String searched) {
        return pattern.matches(searched);
    }

}
