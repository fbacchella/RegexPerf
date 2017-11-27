# Regex bench

This project uses [Code Tools: jmh](http://openjdk.java.net/projects/code-tools/jmh/) to bench many regex library for java, and try to defined the fastest way to run them.

It uses ideas and code from [Java Regular expression library benchmarks](http://tusker.org/regex/regex_benchmark.html) and [Java regular expression library benchmarks – 2015](https://www.javaadvent.com/2015/12/java-regular-expression-library-benchmarks-2015.html). It adds some new libraries.

The joni library works on byte[] instead of a String, so I also bench different way to extract that from a String, to get the fastest. But each variation is test with joni to ensure that's it's really usefull.

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
# Benchmark mode: Throughput, ops/time

Benchmark                                                        Mode  Cnt          Score         Error  Units
RegexPerf.big_io_thekraken_grok_api                             thrpt   10        201.506 ±       2.345  ops/s
RegexPerf.big_java_util_regex                                   thrpt   10        207.655 ±       4.442  ops/s
RegexPerf.big_org_joni_ascii_reuse                              thrpt   10      21110.557 ±     176.040  ops/s
RegexPerf.gnu_regexp                                            thrpt   10      13129.258 ±     130.504  ops/s
RegexPerf.io_thekraken_grok_api                                 thrpt   10      45491.230 ±     935.587  ops/s
RegexPerf.java_util_regex                                       thrpt   10      51120.312 ±     958.495  ops/s
RegexPerf.jregex                                                thrpt   10     227759.280 ±    2263.130  ops/s
RegexPerf.org_apache_xerces_impl_xpath_regex_RegularExpression  thrpt   10      19649.718 ±     105.918  ops/s
RegexPerf.org_joni                                              thrpt   10     147986.472 ±    3078.977  ops/s
RegexPerf.org_joni_ascii                                        thrpt   10     157050.311 ±   15775.004  ops/s
RegexPerf.org_joni_ascii_reuse                                  thrpt   10     158587.976 ±    8951.768  ops/s
RegexPerf.org_joni_ascii_unsafe                                 thrpt   10     154033.393 ±   20010.222  ops/s
RegexPerf.org_joni_utf16le                                      thrpt   10     130347.658 ±   13275.599  ops/s
StringToBytes.byCharset_ISO_8859_1                              thrpt   10  111770633.248 ± 1339379.280  ops/s
StringToBytes.byCharset_UTF_16                                  thrpt   10   14152678.130 ±  288494.561  ops/s
StringToBytes.byCharset_UTF_8                                   thrpt   10   38172801.641 ±  318586.966  ops/s
StringToBytes.byName_ISO_8859_1                                 thrpt   10   72082976.396 ± 1040823.519  ops/s
StringToBytes.byName_UTF_16                                     thrpt   10    4956898.867 ±  180506.018  ops/s
StringToBytes.byName_UTF_8                                      thrpt   10   42687957.015 ± 1056687.425  ops/s
StringToBytes.getBytesAscii                                     thrpt   10   68885513.169 ±  420090.968  ops/s
StringToBytes.getBytesAsciiReuse                                thrpt   10   90351291.515 ±  365798.170  ops/s
StringToBytes.getBytesAsciiUnsafe                               thrpt   10  130430867.984 ± 1423620.406  ops/s
StringToBytes.getBytesUTF16LE                                   thrpt   10   49372595.953 ±  217561.354  ops/s
```
