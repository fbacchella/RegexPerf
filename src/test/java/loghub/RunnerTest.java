package loghub;

import org.junit.Test;

import loghub.states.State_com_basistech_tclre;
import loghub.states.State_com_google_re2j;
import loghub.states.State_com_ibm_icu_text;
import loghub.states.State_com_karneim_util_collection_regex;
import loghub.states.State_com_stevesoft_pat;
import loghub.states.State_dk_brics_automaton;
import loghub.states.State_gnu_regexp;
import loghub.states.State_gnu_rex;
import loghub.states.State_java_util_regex;
import loghub.states.State_jregex;
import loghub.states.State_kmy_regex_util;
import loghub.states.State_monq_jfa;
import loghub.states.State_org_apache_oro_text_regex;
import loghub.states.State_org_apache_regexp;
import loghub.states.State_org_apache_xerces_impl_xpath_regex;
import loghub.states.State_org_joni;
import loghub.states.State_org_joni_ascii;
import loghub.states.State_org_joni_ascii_reuse;
import loghub.states.State_org_joni_ascii_unsafe;
import loghub.states.State_org_joni_utf16le;

public class RunnerTest {

    private void run(Runner<?> runner, boolean withbig) {
        runner.prepare();
        runner.run();
        if (withbig) {
            runner.runbig();
        }
    }

    @Test(expected = AssertionError.class)
    public void com_basistech_tclre() {
        run(new State_com_basistech_tclre(), true);
    }

    @Test(expected = AssertionError.class)
    public void com_google_re2j() {
        run(new State_com_google_re2j(), false);
    }

    @Test(expected = AssertionError.class)
    public void com_ibm_icu_text() {
        run(new State_com_ibm_icu_text(), false);
    }

    @Test(expected = AssertionError.class)
    public void com_karneim_util_collection_regex() {
        run(new State_com_karneim_util_collection_regex(), false);
    }

    @Test
    public void com_stevesoft_pat() {
        run(new State_com_stevesoft_pat(), true);
    }

    @Test(expected = AssertionError.class)
    public void dk_brics_automaton() {
        run(new State_dk_brics_automaton(), true);
    }

    @Test
    public void gnu_regexp() {
        run(new State_gnu_regexp(), false);
    }

    @SuppressWarnings("rawtypes")
    @Test(expected = AssertionError.class)
    public void gnu_rex() {
        run(new State_gnu_rex(), true);
    }

    @Test
    public void java_util_regex() {
        run(new State_java_util_regex(), true);
    }

    @Test
    public void jregex() {
        run(new State_jregex(), false);
    }

    @SuppressWarnings("rawtypes")
    @Test(expected = AssertionError.class)
    public void kmy_regex_util() {
        run(new State_kmy_regex_util(), true);
    }

    @SuppressWarnings("rawtypes")
    @Test(expected = AssertionError.class)
    public void monq_jfa() {
        run(new State_monq_jfa(), true);
    }

    @Test
    public void org_apache_oro_text_regex() {
        run(new State_org_apache_oro_text_regex(), false);
    }

    @Test
    public void org_apache_regexp() {
        run(new State_org_apache_regexp(), false);
    }

    @Test()
    public void org_apache_xerces_impl_xpath_regex() {
        run(new State_org_apache_xerces_impl_xpath_regex(), false);
    }

    @Test
    public void org_joni_ascii_reuse() {
        run(new State_org_joni_ascii_reuse(), true);
    }

    @Test
    public void org_joni_ascii_unsafe() {
        run(new State_org_joni_ascii_unsafe(), true);
    }

    @Test
    public void org_joni_ascii() {
        run(new State_org_joni_ascii(), true);
    }

    @Test
    public void org_joni() {
        run(new State_org_joni(), true);
    }

    @Test
    public void org_joni_utf16le() {
        run(new State_org_joni_utf16le(), true);
    }

}
