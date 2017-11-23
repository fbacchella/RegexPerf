package loghub;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.ChainedOptionsBuilder;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class Run {

    public static void main(String[] args) throws RunnerException {

        OptionParser parser = new OptionParser( "f" );
        OptionSet options = parser.parse(args);
        options.nonOptionArguments();
        long warmupTime;
        int warmupIterations;
        long measurementTime;
        int measurementIterations;
        if(options.has("f")) {
            warmupTime = 1;
            warmupIterations = 2;
            measurementTime = 1;
            measurementIterations = 1;
        } else {
            warmupTime = 1;
            warmupIterations = 5;
            measurementTime = 30;
            measurementIterations = 10;
        }

        ChainedOptionsBuilder builder = new OptionsBuilder()
                // Set the following options as needed
                .mode (Mode.Throughput)
                .timeUnit(TimeUnit.SECONDS)
                .warmupTime(TimeValue.seconds(warmupTime))
                .warmupIterations(warmupIterations)
                .measurementTime(TimeValue.seconds(measurementTime))
                .measurementIterations(measurementIterations)
                .threads(3)
                .forks(1)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .timeout(TimeValue.seconds(11 * 6 + 2 * 11))
                //.jvmArgs("-XX:+UnlockDiagnosticVMOptions", "-XX:+PrintInlining")
                //.addProfiler(WinPerfAsmProfiler.class)
                ;

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

}
