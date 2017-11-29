# Regex bench

This project uses [Code Tools: jmh](http://openjdk.java.net/projects/code-tools/jmh/) to bench many regex library for java, and try to defined the fastest way to run them.

It uses ideas and code from [Java Regular expression library benchmarks](http://tusker.org/regex/regex_benchmark.html) and [Java regular expression library benchmarks – 2015](https://www.javaadvent.com/2015/12/java-regular-expression-library-benchmarks-2015.html). It adds some new libraries.

The [joni](https://github.com/jruby/jon)i library works on byte[] instead of a String, so I also bench different way to extract that from a String, to get the fastest. But each variation is test with joni to ensure that's it's really usefull.

[Grok](RegexPerf.org_joni_utf16le) is a thick wrapper around java's regex, inspired by logstash

First a test with the latest Java 8 (1.8.0_152)

```
$ lscpu 
CPU(s):                8
Model name:            Intel Xeon E312xx (Sandy Bridge)
CPU MHz:               2593.748

# JMH version: 1.19
# VM version: JDK 1.8.0_152, VM 25.152-b16
# VM invoker: /usr/java/jdk1.8.0_152/jre/bin/java
# VM options: <none>
# Warmup: 5 iterations, 1 s each
# Measurement: 10 iterations, 30 s each
# Timeout: 88 s per iteration
# Threads: 3 threads, will synchronize iterations

Benchmark                                                       Mode  Cnt      Score    Error  Units
RegexPerf.big_io_thekraken_grok_api                             avgt   10  14566.463 ± 79.139  us/op
RegexPerf.big_java_util_regex                                   avgt   10  14222.734 ± 49.692  us/op
RegexPerf.big_org_joni_ascii_reuse                              avgt   10    132.353 ±  0.903  us/op
RegexPerf.gnu_regexp                                            avgt   10    220.395 ±  2.042  us/op
RegexPerf.io_thekraken_grok_api                                 avgt   10     64.270 ±  0.634  us/op
RegexPerf.java_util_regex                                       avgt   10     58.757 ±  0.391  us/op
RegexPerf.jregex                                                avgt   10     14.245 ±  0.068  us/op
RegexPerf.org_apache_xerces_impl_xpath_regex_RegularExpression  avgt   10    150.605 ±  1.434  us/op
RegexPerf.org_joni                                              avgt   10     22.776 ±  4.679  us/op
RegexPerf.org_joni_ascii                                        avgt   10     19.061 ±  2.007  us/op
RegexPerf.org_joni_ascii_reuse                                  avgt   10     18.694 ±  1.692  us/op
RegexPerf.org_joni_ascii_unsafe                                 avgt   10     18.225 ±  1.214  us/op
StringToBytes.byCharsetEncoder_US_ASCII                         avgt   10    266.363 ± 16.720  ns/op
StringToBytes.byCharset_US_ASCII                                avgt   10     39.574 ±  0.181  ns/op
StringToBytes.byCharset_UTF_16                                  avgt   10    328.958 ±  5.447  ns/op
StringToBytes.byCharset_UTF_8                                   avgt   10     78.423 ±  0.396  ns/op
StringToBytes.byName_US_ASCII                                   avgt   10     61.946 ±  0.555  ns/op
StringToBytes.byName_UTF_16                                     avgt   10    131.655 ±  1.696  ns/op
StringToBytes.byName_UTF_8                                      avgt   10     69.874 ±  0.636  ns/op
StringToBytes.getBytesAscii                                     avgt   10     43.377 ±  0.213  ns/op
StringToBytes.getBytesAsciiReuse                                avgt   10     33.220 ±  0.229  ns/op
StringToBytes.getBytesAsciiUnsafe                               avgt   10     22.972 ±  0.225  ns/op
StringToBytes.getBytesUTF16LE                                   avgt   10     60.789 ±  0.584  ns/op
```

And one with 9 (9.0.1)

```
# JMH version: 1.19
# VM version: JDK 9.0.1, VM 9.0.1+11
# VM invoker: /usr/java/jdk-9.0.1/bin/java
# VM options: <none>
# Warmup: 5 iterations, 1 s each
# Measurement: 10 iterations, 30 s each
# Timeout: 88 s per iteration
# Threads: 3 threads, will synchronize iterations

Benchmark                                                       Mode  Cnt     Score     Error  Units
RegexPerf.big_io_thekraken_grok_api                             avgt   10  9247.838 ± 172.971  us/op
RegexPerf.big_java_util_regex                                   avgt   10  9255.848 ± 249.751  us/op
RegexPerf.big_org_joni_ascii_reuse                              avgt   10   128.855 ±   1.509  us/op
RegexPerf.gnu_regexp                                            avgt   10   171.659 ±   2.666  us/op
RegexPerf.io_thekraken_grok_api                                 avgt   10    44.095 ±   0.957  us/op
RegexPerf.java_util_regex                                       avgt   10    37.995 ±   0.756  us/op
RegexPerf.jregex                                                avgt   10    13.634 ±   0.187  us/op
RegexPerf.org_apache_xerces_impl_xpath_regex_RegularExpression  avgt   10   121.135 ±   1.173  us/op
RegexPerf.org_joni                                              avgt   10    23.943 ±   0.524  us/op
RegexPerf.org_joni_ascii                                        avgt   10    20.653 ±   1.148  us/op
RegexPerf.org_joni_ascii_reuse                                  avgt   10    21.571 ±   0.850  us/op
StringToBytes.byCharsetEncoder_US_ASCII                         avgt   10    90.954 ±   1.782  ns/op
StringToBytes.byCharset_US_ASCII                                avgt   10    29.405 ±   0.280  ns/op
StringToBytes.byCharset_UTF_16                                  avgt   10   379.761 ±   4.262  ns/op
StringToBytes.byCharset_UTF_8                                   avgt   10    50.512 ±   0.591  ns/op
StringToBytes.byName_US_ASCII                                   avgt   10    36.610 ±   0.487  ns/op
StringToBytes.byName_UTF_16                                     avgt   10   120.022 ±   1.346  ns/op
StringToBytes.byName_UTF_8                                      avgt   10    58.353 ±   0.909  ns/op
StringToBytes.getBytesAscii                                     avgt   10    40.438 ±   0.412  ns/op
StringToBytes.getBytesAsciiReuse                                avgt   10    32.157 ±   0.302  ns/op
StringToBytes.getBytesUTF16LE                                   avgt   10    59.463 ±   0.724  ns/op
```

The unsafe benchmarks are gone, as the internal structure of String changed in java 9.
