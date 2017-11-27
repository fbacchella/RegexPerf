
package loghub;

import org.openjdk.jmh.annotations.Benchmark;

import loghub.states.State_gnu_regexp;
import loghub.states.State_io_thekraken_grok_api;
import loghub.states.State_java_util_regex;
import loghub.states.State_jregex;
import loghub.states.State_org_apache_xerces_impl_xpath_regex;
import loghub.states.State_org_joni;
import loghub.states.State_org_joni_ascii;
import loghub.states.State_org_joni_ascii_reuse;
import loghub.states.State_org_joni_ascii_unsafe;
import loghub.states.State_org_joni_utf16le;

/**
 * See http://tusker.org/regex/regex_benchmark.html
 * and http://www.javaadvent.com/2015/12/java-regular-expression-library-benchmarks-2015.html
 * @author fa4
 *
 */
public class RegexPerf {

    //    @Benchmark
    //    public void com_basistech_tclre(State_com_basistech_tclre state) {
    //        state.run();
    //    }

    //    @Benchmark
    //    public void com_google_re2j(State_com_google_re2j state) {
    //        state.run();
    //    }

    //    @Benchmark
    //    public void jcom_karneim_util_collection_regex(State_com_karneim_util_collection_regex state) {
    //        state.run();
    //    }

    // Junit success, but bench fails
    //    @Benchmark
    //    public void com_stevesoft_patRegex(State_com_stevesoft_pat state) {
    //        state.run();
    //    }

    //    @Benchmark
    //    public void dk_brics_automaton_RegExp(State_dk_brics_automaton state) {
    //        state.run();
    //    }

    @Benchmark
    public void gnu_regexp(State_gnu_regexp state) {
        state.run();
    }

    //    @Benchmark
    //    public void gnu_rex(State_gnu_rex state) {
    //        state.run();
    //    }

    @Benchmark
    public void java_util_regex(State_java_util_regex state) {
        state.run();
    }

    @Benchmark
    public void big_java_util_regex(State_java_util_regex state) {
        state.runbig();
    }

   // @Benchmark
    public void jregex(State_jregex state) {
        state.run();
    }

    @Benchmark
    public void io_thekraken_grok_api(State_io_thekraken_grok_api state) {
        state.run();
    }

    @Benchmark
    public void big_io_thekraken_grok_api(State_io_thekraken_grok_api state) {
        state.runbig();
    }

    //    @Benchmark
    //    public void kmy_regex_util(State_kmy_regex_util state) {
    //        state.run();
    //    }

    //  @Benchmark
    //  public void monq_jfa(State_monq_jfa state) {
    //      state.run();
    //  }

    //    @Benchmark
    //    public void org_apache_oro_text_regex(State_org_apache_oro_text_regex state) {
    //        state.run();
    //    }

    //        @Benchmark
    //        public void org_apache_regexp(State_org_apache_regexp state) {
    //            state.run();
    //        }

    @Benchmark
    public void org_apache_xerces_impl_xpath_regex_RegularExpression(State_org_apache_xerces_impl_xpath_regex state) {
        state.run();
    }

    @Benchmark
    public void org_joni_ascii_reuse(State_org_joni_ascii_reuse state) {
        state.run();
    }

    @Benchmark
    public void org_joni_ascii_unsafe(State_org_joni_ascii_unsafe state) {
        state.run();
    }

    @Benchmark
    public void org_joni_ascii(State_org_joni_ascii state) {
        state.run();
    }

    @Benchmark
    public void org_joni_utf16le(State_org_joni_utf16le state) {
        state.run();
    }

    @Benchmark
    public void org_joni(State_org_joni state) {
        state.run();
    }

}
