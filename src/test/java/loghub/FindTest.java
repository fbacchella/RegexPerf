package loghub;

import org.junit.Assert;
import org.junit.Test;

import loghub.states.State_com_basistech_tclre;
import loghub.states.State_com_google_code_regexp;
import loghub.states.State_com_google_re2j;
import loghub.states.State_com_ibm_icu_text;
import loghub.states.State_com_ibm_regex;
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
import loghub.states.State_org_joni_ascii_unsafe;
import loghub.states.State_org_joni_utf16le;

public class FindTest {

    private <P> void doFind(Runner<P> r) throws Exception {
        String pattern = "a";
        P compiled = r.generate(pattern);
        //Assert.assertFalse(r.match(compiled, " a "));
        Assert.assertTrue(r.find(compiled, " a "));
    }

    private <P> void failing(Runner<P> r, String message) {
        AssertionError ex = Assert.assertThrows(AssertionError.class, () -> doFind(r));
        Assert.assertEquals(message, ex.getMessage());
    }

    @Test
    public void findSemantic_com_basistech_tclre() throws Exception {
        doFind(new State_com_basistech_tclre());
    }

    @Test
    public void findSemantic_com_google_code_regexp() throws Exception {
        doFind(new State_com_google_code_regexp());
    }

    @Test
    public void findSemantic_com_google_re2j() {
        failing(new State_com_google_re2j(), Runner.NOT_SUPPORTED);
    }

    @Test
    public void findSemantic_com_ibm_icu_text() {
        failing(new State_com_ibm_icu_text(), Runner.NOT_SUPPORTED);
    }

    @Test
    public void findSemantic_com_ibm_regex() {
        failing(new State_com_ibm_regex(), Runner.LIBRARY_NOT_FOUND);
    }

    @Test
    public void findSemantic_com_karneim_util_collection_regex() {
        failing(new State_com_karneim_util_collection_regex(), Runner.NOT_SUPPORTED);
    }

    @Test
    public void findSemantic_com_stevesoft_pat() {
        failing(new State_com_stevesoft_pat(), Runner.NOT_SUPPORTED);
    }

    @Test
    public void findSemantic_dk_brics_automaton() {
        failing(new State_dk_brics_automaton(), Runner.NOT_SUPPORTED);
    }

    @Test
    public void findSemantic_dregex() {
        failing(new State_dregex(), Runner.NOT_SUPPORTED);
    }

    @Test
    public void findSemantic_gnu_regexp() {
        failing(new State_gnu_regexp(), Runner.NOT_SUPPORTED);
    }

    @Test
    public void findSemantic_gnu_rex() {
        failing(new State_gnu_rex(), Runner.NOT_SUPPORTED);
    }

    @Test
    public void findSemantic_io_thekraken_grok_api() {
        failing(new State_io_thekraken_grok_api(), Runner.NOT_SUPPORTED);
    }

    @Test
    public void findSemantic_java_util_regex() throws Exception {
        doFind(new State_java_util_regex());
    }

    @Test
    public void findSemantic_java_util_regex_reuse() throws Exception {
        doFind(new State_java_util_regex_reuse());
    }

    @Test
    public void findSemantic_jregex() throws Exception {
        doFind(new State_jregex());
    }

    @Test
    public void findSemantic_kmy_regex_util() {
        failing(new State_kmy_regex_util(), Runner.NOT_MAVENIZED);
    }

    @Test
    public void findSemantic_monq_jfa() {
        failing(new State_monq_jfa(), Runner.NOT_MAVENIZED);
    }

    @Test
    public void findSemantic_org_apache_oro_text_regex() {
        failing(new State_org_apache_oro_text_regex(), Runner.NOT_SUPPORTED);
    }

    @Test
    public void findSemantic_org_apache_regexp() {
        failing(new State_org_apache_regexp(), Runner.NOT_SUPPORTED);
    }

    @Test
    public void findSemantic_org_apache_xerces_impl_xpath_regex() {
        failing(new State_org_apache_xerces_impl_xpath_regex(), Runner.NOT_SUPPORTED);
    }

    @Test
    public void findSemantic_org_joni() throws Exception {
        doFind(new State_org_joni());
    }

    @Test
    public void findSemantic_org_joni_ascii() throws Exception {
        doFind(new State_org_joni_ascii());
    }

    @Test
    public void findSemantic_org_joni_ascii_reuse() throws Exception {
        doFind(new State_org_joni_ascii_reuse());
    }

    @Test
    public void findSemantic_org_joni_ascii_unsafe() throws Exception {
        doFind(new State_org_joni_ascii_unsafe());
    }

    @Test
    public void findSemantic_org_joni_utf16le() throws Exception {
        doFind(new State_org_joni_utf16le());
    }

}
