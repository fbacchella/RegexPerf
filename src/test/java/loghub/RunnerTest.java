package loghub;

import org.junit.Assert;
import org.junit.Test;
import org.openjdk.jmh.infra.Blackhole;

import loghub.states.State_com_basistech_tclre;
import loghub.states.State_com_google_code_regexp;
import loghub.states.State_com_google_re2j;
import loghub.states.State_com_ibm_icu_text;
import loghub.states.State_com_karneim_util_collection_regex;
import loghub.states.State_com_stevesoft_pat;
import loghub.states.State_dk_brics_automaton;
import loghub.states.State_dregex;
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
import loghub.states.State_org_joni_utf16le;

public class RunnerTest {

    private static final int TEST_BASE = 2 << 0;
    private static final int TEST_BACKREFERENCE = 2 << 1;
    private static final int TEST_BIG = 2 << 2;
    private static final int TEST_CATASTROPH = 2 << 3;
    private static final int TEST_NAMEDPATTERN = 2 << 4;
    private static final int TEST_FIND = 2 << 5;
    private static final int TEST_ALL = (2 << 6) - 1;

    private void run(Runner<?> runner, int testMask) {
        Blackhole bh = new Blackhole("Today's password is swordfish. I understand instantiating Blackholes directly is dangerous.");
        runner.prepare();

        if ((testMask & TEST_BASE) > 0) {
            runner.run(bh);
            runner.runmatch(bh);
        }
        if ((testMask & TEST_BACKREFERENCE) > 0) {
            runner.runbackreference(bh);
        }
        if ((testMask & TEST_BIG) > 0) {
            runner.runbig(bh);
        }
        if ((testMask & TEST_CATASTROPH) > 0) {
           runner.runcatastroph(bh);
        }
        if ((testMask & TEST_NAMEDPATTERN) > 0) {
            runner.runlog(bh);
        }
        if ((testMask & TEST_FIND) > 0) {
            runner.runfind(bh);
        }
    }

    @Test
    public void com_basistech_tclre() {
        run(new State_com_basistech_tclre(), TEST_BASE | TEST_BACKREFERENCE | TEST_BIG | TEST_CATASTROPH);
    }

    @Test
    public void com_google_re2j() {
        run(new State_com_google_re2j(), TEST_BASE | TEST_CATASTROPH);
    }

    @Test
    public void com_ibm_icu_text() {
        run(new State_com_ibm_icu_text(), 0);
    }

    @Test
    public void com_karneim_util_collection_regex() {
        run(new State_com_karneim_util_collection_regex(), 0);
    }

    @Test
    public void com_stevesoft_pat() {
        run(new State_com_stevesoft_pat(), TEST_ALL - TEST_NAMEDPATTERN - TEST_FIND);
    }

    @Test
    public void dk_brics_automaton() {
        run(new State_dk_brics_automaton(), TEST_BASE | TEST_BACKREFERENCE | TEST_CATASTROPH);
    }

    @Test
    public void gnu_regexp() {
        run(new State_gnu_regexp(), TEST_BASE | TEST_BACKREFERENCE | TEST_CATASTROPH);
    }

    @Test
    public void gnu_rex() {
        run(new State_gnu_rex(), 0);
    }

    @Test
    public void io_thekraken_grok_api() {
        run(new State_io_thekraken_grok_api(), TEST_ALL - TEST_FIND);
    }

    @Test
    public void java_util_regex() {
        run(new State_java_util_regex(), TEST_ALL);
    }

    @Test
    public void java_util_regex_reuse() {
        run(new State_java_util_regex_reuse(), TEST_ALL);
    }

    @Test
    public void jregex() {
        run(new State_jregex(), TEST_BASE | TEST_BACKREFERENCE | TEST_CATASTROPH | TEST_NAMEDPATTERN | TEST_FIND);
    }

    @Test
    public void kmy_regex_util() {
        run(new State_kmy_regex_util(), 0);
    }

    @Test
    public void monq_jfa() {
        run(new State_monq_jfa(), 0);
    }

    @Test
    public void org_apache_oro_text_regex() {
        run(new State_org_apache_oro_text_regex(), TEST_BASE | TEST_BACKREFERENCE | TEST_CATASTROPH);
    }

    @Test
    public void org_apache_regexp() {
        run(new State_org_apache_regexp(), TEST_BASE | TEST_BACKREFERENCE | TEST_CATASTROPH);
    }

    @Test
    public void org_apache_xerces_impl_xpath_regex() {
        run(new State_org_apache_xerces_impl_xpath_regex(), TEST_BASE | TEST_BACKREFERENCE | TEST_CATASTROPH);
    }

    @Test
    public void org_joni_ascii_reuse() {
        run(new State_org_joni_ascii_reuse(), TEST_ALL);
    }

    @Test
    public void org_joni_ascii() {
        run(new State_org_joni_ascii(), TEST_ALL);
    }

    @Test
    public void org_joni() {
        run(new State_org_joni(), TEST_ALL);
    }

    @Test
    public void org_joni_utf16le() {
        run(new State_org_joni_utf16le(), TEST_ALL);
    }

    @Test
    public void com_google_code_regexp() {
        run(new State_com_google_code_regexp(), TEST_ALL);
    }

    @Test
    public void dregex() {
        run(new State_dregex(), TEST_BASE);
    }

}
