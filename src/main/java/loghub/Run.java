package loghub;

import java.util.List;

import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.ChainedOptionsBuilder;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class Run {

    public static void main(String[] args) throws RunnerException {

        OptionParser parser = new OptionParser( "fF:o:l:" );
        OptionSet options = parser.parse(args);
        options.nonOptionArguments();
        long warmupTime;
        int warmupIterations;
        long measurementTime;
        int measurementIterations;
        if(options.has("f")) {
            warmupTime = 1;
            warmupIterations = 1;
            measurementTime = 1;
            measurementIterations = 1;
        } else {
            warmupTime = 1;
            warmupIterations = 5;
            measurementTime = 30;
            measurementIterations = 10;
        }
        ResultFormatType format;
        String resultFile = null;
        if (options.hasArgument("F")) {
            String formatName = (String) options.valueOf("F");
            format = ResultFormatType.valueOf(formatName.toUpperCase());
            if (options.hasArgument("o")) {
                resultFile = (String) options.valueOf("o");
            }
        } else {
            format = null;
        }
        String logFile = null;
        if (options.hasArgument("l")) {
            logFile = (String) options.valueOf("l");
        }


        ChainedOptionsBuilder builder = new OptionsBuilder()
                        // Set the following options as needed
                        .mode (Mode.AverageTime)
                        //.timeUnit(TimeUnit.NANOSECONDS)
                        .warmupTime(TimeValue.seconds(warmupTime))
                        .warmupIterations(warmupIterations)
                        .measurementTime(TimeValue.seconds(measurementTime))
                        .measurementIterations(measurementIterations)
                        .threads(3)
                        .forks(2)
                        .shouldFailOnError(true)
                        .shouldDoGC(false)
                        .timeout(TimeValue.seconds(11 * 6 + 2 * 11))
                        .exclude("com_basistech_tclre")
                        .exclude("com_google_re2j")
                        .exclude("com_stevesoft_patRegex")
                        .exclude("jcom_karneim_util_collection_regex")
                        .exclude("kmy_regex_util")
                        .exclude("monq_jfa")
                        .exclude("org_apache_oro_text_regex")
                        .exclude("org_apache_regexp")
                        .exclude("org_apache_xerces_impl_xpath_regex_RegularExpression")
                        .exclude("org_apache_xerces_impl_xpath_regex_RegularExpression")
                        ;

        if (format != null) {
            builder.resultFormat(format);
            if (resultFile != null) {
                builder.result(resultFile);
            }
        }
        if (logFile != null) {
            builder.output(logFile);
        }

        if (getVersion() >= 9 ) {
            builder.exclude("org_joni_ascii_unsafe")
            .exclude("org_joni_utf16le")
            .exclude("getBytesAsciiUnsafe");
        }

        // Check for gnu_rex availability
        try {
            Run.class.getClassLoader().loadClass("gnu.rex.Rex");
        } catch (ClassNotFoundException e) {
            builder.exclude("gnu_rex");
        }


        List<?> otheroptions = options.nonOptionArguments();
        if (otheroptions.size() != 0) {
            otheroptions.forEach(i -> builder.include("loghub." + i.toString()));
        } else {
            builder
            // Specify which benchmarks to run. 
            // You can be more specific if you'd like to run only one benchmark per test.
            .include(RegexPerf.class.getName() + ".*")
            .include(StringToBytes.class.getName() + ".*")
            ;
        }

        Options opt = builder.build();
        new org.openjdk.jmh.runner.Runner(opt).run();
    }

    private static int getVersion() {
        String version = System.getProperty("java.version");
        if(version.startsWith("1.")) {
            version = version.substring(2, 3);
        } else {
            int dot = version.indexOf(".");
            if(dot != -1) { version = version.substring(0, dot); }
        }
        return Integer.parseInt(version);
    }

}
