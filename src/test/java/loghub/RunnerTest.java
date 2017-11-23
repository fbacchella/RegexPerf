package loghub;

import org.apache.xerces.impl.xpath.regex.ParseException;
import org.junit.Test;

import loghub.states.*;

public class RunnerTest {
    
    private void run(Runner<?> runner) {
        runner.prepare();
        runner.run();
        runner.runbig();
    }

    @Test(expected = AssertionError.class)
    public void com_basistech_tclre() {
        run(new State_com_basistech_tclre());
    }

    @Test(expected = com.google.re2j.PatternSyntaxException.class)
    public void com_google_re2j() {
        run(new State_com_google_re2j());
    }

    @Test(expected = IllegalArgumentException.class)
    public void com_ibm_icu_text() {
        run(new State_com_ibm_icu_text());
    }

    @Test(expected = com.karneim.util.collection.regex.ParseException.class)
    public void com_karneim_util_collection_regex() {
        run(new State_com_karneim_util_collection_regex());
    }

    @Test(expected = AssertionError.class)
    public void com_stevesoft_pat() {
        run(new State_com_stevesoft_pat());
    }

    @Test(expected = AssertionError.class)
    public void dk_brics_automaton() {
        run(new State_dk_brics_automaton());
    }

    @Test
    public void gnu_regexp() {
        run(new State_gnu_regexp());
    }

    @Test
    public void gnu_rex() {
        //run(new State_gnu_rex());
    }

    @Test(expected = AssertionError.class)
    public void java_util_regex() {
        run(new State_java_util_regex());
    }

    @Test
    public void jregex() {
        run(new State_jregex());
    }

    @Test
    public void kmy_regex_util() {
        //run(new State_kmy_regex_util());
    }

    @Test
    public void monq_jfa() {
        //run(new State_monq_jfa());
    }

    @Test
    public void org_apache_oro_text_regex() {
        run(new State_org_apache_oro_text_regex());
    }

    @Test
    public void org_apache_regexp() {
        //run(new State_org_apache_regexp);
    }

    @Test(expected = ParseException.class)
    public void org_apache_xerces_impl_xpath_regex() {
        run(new State_org_apache_xerces_impl_xpath_regex());
    }

    @Test(expected = AssertionError.class)
    public void org_joni() {
        run(new State_org_joni());
    }

    @Test(expected = AssertionError.class)
    public void org_joni_utf16le() {
        run(new State_org_joni_utf16le());
    }

    @Test(expected = AssertionError.class)
    public void org_joni_ascii() {
        run(new State_org_joni_ascii());
    }

}
