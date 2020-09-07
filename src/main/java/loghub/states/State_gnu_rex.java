package loghub.states;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import gnu.rex.RegExprSyntaxException;
import gnu.rex.Rex;
import loghub.Runner;

@State(Scope.Benchmark)
public class State_gnu_rex extends Runner<Rex> {

    @Override
    protected Rex[] getPatternStorage(int size) {
        return new Rex[size];
    }

    @Override
    protected Rex generate(String i) {
        try {
            return Rex.build(i);
        } catch (RegExprSyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected boolean match(Rex pattern, String searched) {
        return pattern.match(searched.toCharArray(), 0, searched.length()) != null;
    }

    @Override
    protected String[] find(Rex pattern, String searched) {
        // TODO Auto-generated method stub
        return null;
    }

}
