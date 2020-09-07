package loghub;

import org.junit.Test;
import org.openjdk.jmh.infra.Blackhole;

import loghub.states.State_com_basistech_tclre;
import loghub.states.State_com_google_code_regexp;
import loghub.states.State_com_google_re2j;
import loghub.states.State_com_ibm_icu_text;
import loghub.states.State_com_karneim_util_collection_regex;
import loghub.states.State_com_stevesoft_pat;
import loghub.states.State_dk_brics_automaton;
import loghub.states.State_gnu_regexp;
import loghub.states.State_gnu_rex;
import loghub.states.State_io_thekraken_grok_api;
import loghub.states.State_java_util_regex;
import loghub.states.State_java_util_regex_reuse;
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

    private void run(Runner<?> runner, boolean withbig, boolean withlog) {
        Blackhole bh = new Blackhole("Today's password is swordfish. I understand instantiating Blackholes directly is dangerous.");
        runner.prepare();
        runner.run(bh);
        if (withbig) {
            runner.runbig(bh);
        }
        runner.runcatastroph(bh);
        if (withlog) {
            runner.runlog(bh);
        }
    }

    @Test(expected = AssertionError.class)
    public void com_basistech_tclre() {
        run(new State_com_basistech_tclre(), true, true);
    }

    @Test(expected = AssertionError.class)
    public void com_google_re2j() {
        run(new State_com_google_re2j(), false, true);
    }

    @Test(expected = AssertionError.class)
    public void com_ibm_icu_text() {
        run(new State_com_ibm_icu_text(), false, true);
    }

    @Test(expected = Error.class)
    public void com_karneim_util_collection_regex() {
        run(new State_com_karneim_util_collection_regex(), false, true);
    }

    @Test
    public void com_stevesoft_pat() {
        run(new State_com_stevesoft_pat(), true, false);
    }

    @Test(expected = AssertionError.class)
    public void dk_brics_automaton() {
        run(new State_dk_brics_automaton(), false, false);
    }

    @Test
    public void gnu_regexp() {
        run(new State_gnu_regexp(), false, false);
    }

    public void gnu_rex() {
        run(new State_gnu_rex(), true, true);
    }

    @Test
    public void io_thekraken_grok_api() {
        run(new State_io_thekraken_grok_api(), true, true);
    }

    @Test
    public void java_util_regex_reuse() {
        run(new State_java_util_regex_reuse(), true, true);
    }

    @Test
    public void java_util_regex() {
        run(new State_java_util_regex(), true, true);
    }

    @Test
    public void jregex() {
        run(new State_jregex(), false, false);
    }

    @Test(expected = AssertionError.class)
    public void kmy_regex_util() {
        run(new State_kmy_regex_util(), true, true);
    }

    @Test(expected = AssertionError.class)
    public void monq_jfa() {
        run(new State_monq_jfa(), true, true);
    }

    @Test
    public void org_apache_oro_text_regex() {
        run(new State_org_apache_oro_text_regex(), false, false);
    }

    @Test(expected = AssertionError.class)
    public void org_apache_regexp() {
        run(new State_org_apache_regexp(), false, true);
    }

    @Test(expected = AssertionError.class)
    public void org_apache_xerces_impl_xpath_regex() {
        run(new State_org_apache_xerces_impl_xpath_regex(), false, true);
    }

    @Test
    public void org_joni_ascii_reuse() {
        run(new State_org_joni_ascii_reuse(), true, true);
    }

    @Test
    public void org_joni_ascii_unsafe() {
        run(new State_org_joni_ascii_unsafe(), true, true);
    }

    @Test
    public void org_joni_ascii() {
        run(new State_org_joni_ascii(), true, true);
    }

    @Test
    public void org_joni() {
        run(new State_org_joni(), true, true);
    }

    @Test
    public void org_joni_utf16le() {
        run(new State_org_joni_utf16le(), true, true);
    }

    @Test
    public void com_google_code_regexp() {
        run(new State_com_google_code_regexp(), true, false);
    }

}
