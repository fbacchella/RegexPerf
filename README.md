# Regex bench

This project uses [Code Tools: jmh](http://openjdk.java.net/projects/code-tools/jmh/) to bench many regex library for java, and try to defined the fastest way to run them.

It uses ideas and code from [Java Regular expression library benchmarks](http://tusker.org/regex/regex_benchmark.html) and [Java regular expression library benchmarks – 2015](https://www.javaadvent.com/2015/12/java-regular-expression-library-benchmarks-2015.html). It adds some new libraries.

The joni library works on byte[] instead of a String, so I also bench different way to extract that from a String, to get the fastest. But it check the result of the current most complete and fastest library.
