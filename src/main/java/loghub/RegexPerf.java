
package loghub;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;

import loghub.states.State_com_basistech_tclre;
import loghub.states.State_com_google_code_regexp;
import loghub.states.State_com_google_re2j;
import loghub.states.State_com_ibm_icu_text;
import loghub.states.State_com_karneim_util_collection_regex;
import loghub.states.State_com_stevesoft_pat;
import loghub.states.State_dk_brics_automaton;
import loghub.states.State_gnu_regexp;
import loghub.states.State_io_thekraken_grok_api;
import loghub.states.State_java_util_regex;
import loghub.states.State_java_util_regex_reuse;
import loghub.states.State_jregex;
import loghub.states.State_kmy_regex_util;
import loghub.states.State_org_apache_oro_text_regex;
import loghub.states.State_org_apache_regexp;
import loghub.states.State_org_apache_xerces_impl_xpath_regex;
import loghub.states.State_org_joni;
import loghub.states.State_org_joni_ascii;
import loghub.states.State_org_joni_ascii_reuse;
import loghub.states.State_org_joni_utf16le;

/**
 * See <a href="http://tusker.org/regex/regex_benchmark.html">...</a>
 * and <a href="http://www.javaadvent.com/2015/12/java-regular-expression-library-benchmarks-2015.html">...</a>
 * @author fa4
 *
 */
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class RegexPerf {

    /***********************
     * com_basistech_tclre *
     ***********************/

    @Benchmark
    public void base_com_basistech_tclre(State_com_basistech_tclre state, Blackhole blackHole) {
        state.run(blackHole);
    }

    @Benchmark
    public void br_com_basistech_tclre(State_com_basistech_tclre state, Blackhole blackHole) {
        state.runbackreference(blackHole);
    }

    public void log_com_basistech_tclre(State_com_basistech_tclre state, Blackhole blackHole) {
        state.runlog(blackHole);
    }

    @Benchmark
    public void big_com_basistech_tclre(State_com_basistech_tclre state, Blackhole blackHole) {
        state.runbig(blackHole);
    }
    @Benchmark
    public void catastrophic_com_basistech_tclre(State_com_basistech_tclre state, Blackhole blackHole) {
        state.runcatastroph(blackHole);
    }

    /*******************
     * com_google_re2j *
     *******************/

    @Benchmark
    public void base_com_google_re2j(State_com_google_re2j state, Blackhole blackHole) {
        state.run(blackHole);
    }

    public void log_com_google_re2j(State_com_google_re2j state, Blackhole blackHole) {
        state.runlog(blackHole);
    }

    @Benchmark
    public void catastrophic_com_google_re2j(State_com_google_re2j state, Blackhole blackHole) {
        state.runcatastroph(blackHole);
    }

    /********************
     * com_ibm_icu_text *
     ********************/

    public void base_com_ibm_icu_text(State_com_ibm_icu_text state, Blackhole blackHole) {
        state.run(blackHole);
    }

    public void log_com_ibm_icu_text(State_com_ibm_icu_text state, Blackhole blackHole) {
        state.runlog(blackHole);
    }

    /**************************************
     * jcom_karneim_util_collection_regex *
     **************************************/

    public void base_jcom_karneim_util_collection_regex(State_com_karneim_util_collection_regex state, Blackhole blackHole) {
        state.run(blackHole);
    }

    /**************************
     * com_stevesoft_patRegex *
     **************************/

    @Benchmark
    public void base_com_stevesoft_patRegex(State_com_stevesoft_pat state, Blackhole blackHole) {
        state.run(blackHole);
    }

    @Benchmark
    public void catastrophic_com_stevesoft_patRegex(State_com_stevesoft_pat state, Blackhole blackHole) {
        state.runcatastroph(blackHole);
    }

    @Benchmark
    public void big_com_stevesoft_patRegex(State_com_stevesoft_pat state, Blackhole blackHole) {
        state.runbig(blackHole);
    }

    @Benchmark
    public void br_com_stevesoft_patRegex(State_com_stevesoft_pat state, Blackhole blackHole) {
        state.runbackreference(blackHole);
    }

    /*****************************
     * dk_brics_automaton_RegExp *
     *****************************/

    @Benchmark
    public void base_dk_brics_automaton_RegExp(State_dk_brics_automaton state, Blackhole blackHole) {
        state.run(blackHole);
    }

    @Benchmark
    public void br_dk_brics_automaton_RegExp(State_dk_brics_automaton state, Blackhole blackHole) {
        state.runbackreference(blackHole);
    }

    @Benchmark
    public void catastrophic_dk_brics_automaton_RegExp(State_dk_brics_automaton state, Blackhole blackHole) {
        state.runcatastroph(blackHole);
    }

    /**************
     * gnu_regexp *
     **************/

    @Benchmark
    public void base_gnu_regexp(State_gnu_regexp state, Blackhole blackHole) {
        state.run(blackHole);
    }

    @Benchmark
    public void br_gnu_regexp(State_gnu_regexp state, Blackhole blackHole) {
        state.runbackreference(blackHole);
    }

    @Benchmark
    public void catastrophic_gnu_regexp(State_gnu_regexp state, Blackhole blackHole) {
        state.runcatastroph(blackHole);
    }

    /***********
     * gnu_rex *
     ***********/

    // Failing

    /*******************
     * java_util_regex *
     *******************/

    @Benchmark
    public void base_java_util_regex(State_java_util_regex state, Blackhole blackHole) {
        state.run(blackHole);
    }

    @Benchmark
    public void br_java_util_regex(State_java_util_regex state, Blackhole blackHole) {
        state.runbackreference(blackHole);
    }

    @Benchmark
    public void catastrophic_java_util_regex(State_java_util_regex state, Blackhole blackHole) {
        state.runcatastroph(blackHole);
    }

    @Benchmark
    public void big_java_util_regex(State_java_util_regex state, Blackhole blackHole) {
        state.runbig(blackHole);
    }

    @Benchmark
    public void log_java_util_regex(State_java_util_regex state, Blackhole blackHole) {
        state.runlog(blackHole);
    }

    @Benchmark
    public void find_java_util_regex(State_java_util_regex state, Blackhole blackHole) {
        state.runfind(blackHole);
    }

    @Benchmark
    public void match_java_util_regex(State_java_util_regex state, Blackhole blackHole) {
        state.runmatch(blackHole);
    }

    /*************************
     * java_util_regex_reuse *
     *************************/

    @Benchmark
    public void base_java_util_regex_reuse(State_java_util_regex_reuse state, Blackhole blackHole) {
        state.run(blackHole);
    }

    @Benchmark
    public void br_java_util_regex_reuse(State_java_util_regex_reuse state, Blackhole blackHole) {
        state.runbackreference(blackHole);
    }

    @Benchmark
    public void catastrophic_java_util_regex_reuse(State_java_util_regex_reuse state, Blackhole blackHole) {
        state.runcatastroph(blackHole);
    }

    @Benchmark
    public void big_java_util_regex_reuse(State_java_util_regex_reuse state, Blackhole blackHole) {
        state.runbig(blackHole);
    }

    @Benchmark
    public void log_java_util_regex_reuse(State_java_util_regex_reuse state, Blackhole blackHole) {
        state.runlog(blackHole);
    }

    /**********
     * jregex *
     **********/

    @Benchmark
    public void base_jregex(State_jregex state, Blackhole blackHole) {
        state.run(blackHole);
    }

    @Benchmark
    public void catastrophic_jregex(State_jregex state, Blackhole blackHole) {
        state.runcatastroph(blackHole);
    }

    @Benchmark
    public void br_jregex(State_jregex state, Blackhole blackHole) {
        state.runbackreference(blackHole);
    }

    /*************************
     * io_thekraken_grok_api *
     *************************/

    @Benchmark
    public void base_io_thekraken_grok_api(State_io_thekraken_grok_api state, Blackhole blackHole) {
        state.run(blackHole);
    }

    @Benchmark
    public void catastrophic_io_thekraken_grok_api(State_io_thekraken_grok_api state, Blackhole blackHole) {
        state.runcatastroph(blackHole);
    }

    @Benchmark
    public void br_io_thekraken_grok_api(State_io_thekraken_grok_api state, Blackhole blackHole) {
        state.runbackreference(blackHole);
    }

    @Benchmark
    public void big_io_thekraken_grok_api(State_io_thekraken_grok_api state, Blackhole blackHole) {
        state.runbig(blackHole);
    }

    @Benchmark
    public void log_io_thekraken_grok_api(State_io_thekraken_grok_api state, Blackhole blackHole) {
        state.runlog(blackHole);
    }

    /******************
     * kmy_regex_util *
     ******************/

    public void base_kmy_regex_util(State_kmy_regex_util state, Blackhole blackHole) {
        state.run(blackHole);
    }

    /************
     * monq_jfa *
     ************/

    // Not mavenized

    /*****************************
     * org_apache_oro_text_regex *
     *****************************/

    @Benchmark
    public void base_org_apache_oro_text_regex(State_org_apache_oro_text_regex state, Blackhole blackHole) {
        state.run(blackHole);
    }

    @Benchmark
    public void br_org_apache_oro_text_regex(State_org_apache_oro_text_regex state, Blackhole blackHole) {
        state.runbackreference(blackHole);
    }

    @Benchmark
    public void catastrophic_org_apache_oro_text_regex(State_org_apache_oro_text_regex state, Blackhole blackHole) {
        state.runcatastroph(blackHole);
    }

    /*********************
     * org_apache_regexp *
     *********************/

    @Benchmark
    public void base_org_apache_regexp(State_org_apache_regexp state, Blackhole blackHole) {
        state.run(blackHole);
    }

    @Benchmark
    public void br_org_apache_regexp(State_org_apache_regexp state, Blackhole blackHole) {
        state.runbackreference(blackHole);
    }

    @Benchmark
    public void catastrophic_org_apache_regexp(State_org_apache_regexp state, Blackhole blackHole) {
        state.runcatastroph(blackHole);
    }

    /********************************************************
     * org_apache_xerces_impl_xpath_regex_RegularExpression *
     ********************************************************/

    @Benchmark
    public void base_org_apache_xerces_impl_xpath_regex_RegularExpression(State_org_apache_xerces_impl_xpath_regex state, Blackhole blackHole) {
        state.run(blackHole);
    }

    @Benchmark
    public void br_org_apache_xerces_impl_xpath_regex_RegularExpression(State_org_apache_xerces_impl_xpath_regex state, Blackhole blackHole) {
        state.runbackreference(blackHole);
    }

    @Benchmark
    public void catastrophic_org_apache_xerces_impl_xpath_regex_RegularExpression(State_org_apache_xerces_impl_xpath_regex state, Blackhole blackHole) {
        state.runcatastroph(blackHole);
    }

    /************************
     * org_joni_ascii_reuse *
     ************************/

    @Benchmark
    public void base_org_joni_ascii_reuse(State_org_joni_ascii_reuse state, Blackhole blackHole) {
        state.run(blackHole);
    }

    @Benchmark
    public void br_org_joni_ascii_reuse(State_org_joni_ascii_reuse state, Blackhole blackHole) {
        state.runbackreference(blackHole);
    }

    @Benchmark
    public void log_org_joni_ascii_reuse(State_org_joni_ascii_reuse state, Blackhole blackHole) {
        state.runlog(blackHole);
    }

    @Benchmark
    public void big_org_joni_ascii_reuse(State_org_joni_ascii_reuse state, Blackhole blackHole) {
        state.runbig(blackHole);
    }

    @Benchmark
    public void catastrophic_org_joni_ascii_reuse(State_org_joni_ascii_reuse state, Blackhole blackHole) {
        state.runcatastroph(blackHole);
    }

    /*************************
     * org_joni_ascii *
     *************************/

    @Benchmark
    public void base_org_joni_ascii(State_org_joni_ascii state, Blackhole blackHole) {
        state.run(blackHole);
    }

    @Benchmark
    public void br_org_joni_ascii(State_org_joni_ascii state, Blackhole blackHole) {
        state.runbackreference(blackHole);
    }

    @Benchmark
    public void log_org_joni_ascii(State_org_joni_ascii state, Blackhole blackHole) {
        state.runlog(blackHole);
    }

    @Benchmark
    public void big_org_joni_ascii(State_org_joni_ascii state, Blackhole blackHole) {
        state.runbig(blackHole);
    }

    @Benchmark
    public void catastrophic_org_joni_ascii(State_org_joni_ascii state, Blackhole blackHole) {
        state.runcatastroph(blackHole);
    }

    /********************
     * org_joni_utf16le *
     ********************/

    @Benchmark
    public void base_org_joni_utf16le(State_org_joni_utf16le state, Blackhole blackHole) {
        state.run(blackHole);
    }

    @Benchmark
    public void br_org_joni_utf16le(State_org_joni_utf16le state, Blackhole blackHole) {
        state.runbackreference(blackHole);
    }

    @Benchmark
    public void log_org_joni_utf16le(State_org_joni_utf16le state, Blackhole blackHole) {
        state.runlog(blackHole);
    }

    @Benchmark
    public void big_org_joni_utf16le(State_org_joni_utf16le state, Blackhole blackHole) {
        state.runbig(blackHole);
    }

    @Benchmark
    public void catastrophic_org_joni_utf16le(State_org_joni_utf16le state, Blackhole blackHole) {
        state.runcatastroph(blackHole);
    }

    /************
     * org_joni *
     ************/

    @Benchmark
    public void base_org_joni(State_org_joni state, Blackhole blackHole) {
        state.run(blackHole);
    }

    @Benchmark
    public void br_org_joni(State_org_joni state, Blackhole blackHole) {
        state.runbackreference(blackHole);
    }

    @Benchmark
    public void catastrophic_org_joni(State_org_joni state, Blackhole blackHole) {
        state.runcatastroph(blackHole);
    }

    @Benchmark
    public void log_org_joni(State_org_joni state, Blackhole blackHole) {
        state.runlog(blackHole);
    }

    @Benchmark
    public void big_org_joni(State_org_joni state, Blackhole blackHole) {
        state.runbig(blackHole);
    }

    /**************************
     * com_google_code_regexp *
     **************************/

    @Benchmark
    public void base_com_google_code_regexp(State_com_google_code_regexp state, Blackhole blackHole) {
        state.run(blackHole);
    }

    @Benchmark
    public void big_com_google_code_regexp(State_com_google_code_regexp state, Blackhole blackHole) {
        state.runbig(blackHole);
    }

    @Benchmark
    public void catastrophic_com_google_code_regexp(State_com_google_code_regexp state, Blackhole blackHole) {
        state.runcatastroph(blackHole);
    }

    @Benchmark
    public void br_com_google_code_regexp(State_com_google_code_regexp state, Blackhole blackHole) {
        state.runbackreference(blackHole);
    }

    @Benchmark
    public void log_com_google_code_regexp(State_com_google_code_regexp state, Blackhole blackHole) {
        state.runlog(blackHole);
    }

}
