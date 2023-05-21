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
        int fork;
        int threads;
        boolean shouldFailOnError;
        if(options.has("f")) {
            warmupTime = 1;
            warmupIterations = 1;
            measurementTime = 1;
            measurementIterations = 1;
            fork = 1;
            threads = 2;
            shouldFailOnError = false;
        } else {
            warmupTime = 1;
            warmupIterations = 5;
            measurementTime = 30;
            measurementIterations = 10;
            fork = 2;
            threads = 4;
            shouldFailOnError = true;
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
                        .mode(Mode.AverageTime)
                        //.timeUnit(TimeUnit.NANOSECONDS)
                        .warmupTime(TimeValue.seconds(warmupTime))
                        .warmupIterations(warmupIterations)
                        .measurementTime(TimeValue.seconds(measurementTime))
                        .measurementIterations(measurementIterations)
                        .threads(threads)
                        .forks(fork)
                        .shouldFailOnError(shouldFailOnError)
                        .shouldDoGC(false)
                        .timeout(TimeValue.seconds(11L * 6 + 2 * 11))
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
        if (!otheroptions.isEmpty()) {
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
