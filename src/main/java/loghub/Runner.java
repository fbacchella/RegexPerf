/*
 * Copyright (c) 2005, Damien Mascord <tusker@tusker.org> All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following
 * conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided with the distribution. Neither the name of the <ORGANIZATION>
 * nor the names of its contributors may be used to endorse or promote products derived from this software without specific
 * prior written permission. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package loghub;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.infra.Blackhole;

import com.google.common.base.Strings;

public abstract class Runner<P> {

    public static final String LIBRARY_NOT_FOUND = "Library not found";
    public static final String NOT_SUPPORTED = "Not supported";
    public static final String NOT_MAVENIZED = "Not mavenized";

    private static final String[] patterns = {
            "^(([^:]+)://)?([^:/]+)(:([0-9]+))?(/.*)", // URL match
            "(([^:]+)://)?([^:/]+)(:([0-9]+))?(/.*)", // URL match without starting ^
            "usd [+-]?[0-9]+.[0-9][0-9]", // Canonical US dollar amount
            "\\b(\\w+)(\\s+\\1)+\\b", // backreference
            "(x+x+)+y", // Catastrophic Backtracking
            "\\{(\\d+):((.(?!-}))*).*", // Long matches
            "\\[(?<time>\\d{4}[\\/-]\\d{2}[\\/-]\\d{2}[\\-\\s]\\d{2}:\\d{2}:\\d{2}([\\.,]\\d+)?)\\]\\s+\\[\\s?(?<severity>(?i)TRACE|DEBUG|PERF|NOTE|INFO|WARN|ERROR|FATAL)\\s?\\]\\s+\\[(?<thread>([\\\"\\w\\d\\.\\,\\-_\\@\\s\\/\\:\\#\\\\\\=\\{\\}\\&\\+\\%\\)\\(]*)((\\.\\.\\.\\[).*(ing\\]))?)\\]\\s+\\[(?<logger>[\\w\\d\\.\\-_]*)\\]\\s(?<message>.*)",
            ".* SomeString .*"
    };

    private static final String[] strings = {
            "http://www.linux.com/",
            "http://www.thelinuxshow.com/main.php3",
            "usd 1234.00",
            "he said she said he said no",
            "same same same",
            "xxxxxxxxxxxxxxxxy", //Succeed backtracking
            "xxxxxxxxxxxxxxxxx", //Failing backtracking
            "{1:" + Strings.repeat("this is some more text - and some more and some more and even more ", 10) + "-}",
            "[2020-07-20 09:35:41,099] [INFO ] [1637163705@thread-name-3284] [loghub.dummy.class] A found String.",
            "prefix SomeString suffix",
            Strings.repeat("this is some more text - and some more and some more and even more ", 10) + " SomeString " + Strings.repeat("this is some more text - and some more and some more and even more ", 10),
    };

    private final P[] compiledPatterns;

    private static final boolean[][] expectedMatch = new boolean[8][11];

    static
    {
        expectedMatch[0][0] = true;
        expectedMatch[0][1] = true;
        expectedMatch[0][2] = false;
        expectedMatch[0][3] = false;
        expectedMatch[0][4] = false;
        expectedMatch[0][5] = false;
        expectedMatch[0][6] = false;
        expectedMatch[0][7] = false;
        expectedMatch[0][8] = false;
        expectedMatch[0][9] = false;
        expectedMatch[0][10] = false;
        expectedMatch[1][0] = true;
        expectedMatch[1][1] = true;
        expectedMatch[1][2] = false;
        expectedMatch[1][3] = false;
        expectedMatch[1][4] = false;
        expectedMatch[1][5] = false;
        expectedMatch[1][6] = false;
        expectedMatch[1][7] = false;
        expectedMatch[1][8] = false;
        expectedMatch[1][9] = false;
        expectedMatch[1][10] = false;
        expectedMatch[2][0] = false;
        expectedMatch[2][1] = false;
        expectedMatch[2][2] = true;
        expectedMatch[2][3] = false;
        expectedMatch[2][4] = false;
        expectedMatch[2][5] = false;
        expectedMatch[2][6] = false;
        expectedMatch[2][7] = false;
        expectedMatch[2][8] = false;
        expectedMatch[1][9] = false;
        expectedMatch[1][10] = false;
        expectedMatch[3][0] = false;
        expectedMatch[3][1] = false;
        expectedMatch[3][2] = false;
        expectedMatch[3][3] = false;
        expectedMatch[3][4] = true;
        expectedMatch[3][5] = false;
        expectedMatch[3][6] = false;
        expectedMatch[3][7] = false;
        expectedMatch[3][8] = false;
        expectedMatch[3][9] = false;
        expectedMatch[3][10] = false;
        expectedMatch[4][0] = false;
        expectedMatch[4][1] = false;
        expectedMatch[4][2] = false;
        expectedMatch[4][3] = false;
        expectedMatch[4][4] = false;
        expectedMatch[4][5] = true;
        expectedMatch[4][6] = false;
        expectedMatch[4][7] = false;
        expectedMatch[4][8] = false;
        expectedMatch[4][9] = false;
        expectedMatch[4][10] = false;
        expectedMatch[5][0] = false;
        expectedMatch[5][1] = false;
        expectedMatch[5][2] = false;
        expectedMatch[5][3] = false;
        expectedMatch[5][4] = false;
        expectedMatch[5][5] = false;
        expectedMatch[5][6] = false;
        expectedMatch[5][7] = true;
        expectedMatch[5][8] = false;
        expectedMatch[5][9] = false;
        expectedMatch[5][10] = false;
        expectedMatch[6][0] = false;
        expectedMatch[6][1] = false;
        expectedMatch[6][2] = false;
        expectedMatch[6][3] = false;
        expectedMatch[6][4] = false;
        expectedMatch[6][5] = false;
        expectedMatch[6][6] = false;
        expectedMatch[6][7] = false;
        expectedMatch[6][8] = true;
        expectedMatch[6][9] = false;
        expectedMatch[6][10] = false;
        expectedMatch[7][0] = false;
        expectedMatch[7][1] = false;
        expectedMatch[7][2] = false;
        expectedMatch[7][3] = false;
        expectedMatch[7][4] = false;
        expectedMatch[7][5] = false;
        expectedMatch[7][6] = false;
        expectedMatch[7][7] = false;
        expectedMatch[7][8] = false;
        expectedMatch[7][9] = true;
        expectedMatch[7][10] = true;
    }

    protected Runner() {
        compiledPatterns = getPatternStorage(8);
    }

    protected abstract P[] getPatternStorage(int size);
    protected abstract P generate(String i);

    protected boolean match(P pattern, String searched) {
        throw new AssertionError(NOT_SUPPORTED);
    }
    protected boolean find(P pattern, String searched) {
        throw new AssertionError(NOT_SUPPORTED);
    }

    protected String[] matchGroup(P pattern, String searched) {
        throw new AssertionError(NOT_SUPPORTED);
    }

    protected String[] findGroup(P pattern, String searched) {
        throw new AssertionError(NOT_SUPPORTED);
    }

    private interface PatternOperation {
        void action(int patnum, int strnum, Blackhole blackHole);
    }

    private void iterate(IntStream patterns, IntStream strings, Blackhole blackHole, PatternOperation op) {
        int[] p = patterns.toArray();
        int[] s = strings.toArray();
        for (int patnum: p) {
            for (int strnum: s) {
                op.action(patnum, strnum, blackHole);
            }
        }
    }

    public void run(Blackhole blackHole) {
        iterate(IntStream.of(0, 1, 2), IntStream.of(0, 1, 2, 3, 4, 9), blackHole, this::match);
    }

    public void runbackreference(Blackhole blackHole) {
        iterate(IntStream.of(3), IntStream.of(3), blackHole, this::match);
    }

    public void runcatastroph(Blackhole blackHole) {
        iterate(IntStream.of(4), IntStream.of(5, 6), blackHole, this::match);
    }

    public void runbig(Blackhole blackHole) {
        int patnum = 5;
        int strnum = 7;
        match(patnum, strnum, blackHole);
    }

    public void runlog(Blackhole blackHole) {
        int patnum = 6;
        int strnum = 8;
        matchGroup(patnum , strnum, blackHole);
    }

    public void runmatch(Blackhole blackHole) {
        iterate(IntStream.of(7), IntStream.of(9, 10), blackHole, this::match);
    }

    public void runfind(Blackhole blackHole) {
        iterate(IntStream.of(7), IntStream.of(9, 10), blackHole, this::find);
    }

    @Setup
    public void prepare() {
        IntStream.range(0, patterns.length).filter(this::filterPattern).forEach(this::compileAndStore);
    }

    protected boolean filterPattern(int patternNum) {
        return true;
    }

    private void compileAndStore(int pattnum) {
        try {
            compiledPatterns[pattnum] = generate(translate(pattnum));
        } catch (Exception e) {
            throw new IllegalStateException(String.format("pattern %d \"%s\" failed",  pattnum, patterns[pattnum]), e);
        }
    }

    private void match(int patnum, int strnum, Blackhole blackHole) {
        doSearch(patnum, strnum, blackHole, this::match);
    }

    private void find(int patnum, int strnum, Blackhole blackHole) {
        doSearch(patnum, strnum, blackHole, this::find);
    }

    private void matchGroup(int patnum, int strnum, Blackhole blackHole) {
        doSearchGroup(patnum, strnum, blackHole, this::matchGroup);
    }

    private void findGroup(int patnum, int strnum, Blackhole blackHole) {
        doSearchGroup(patnum, strnum, blackHole, this::findGroup);
    }

    private void doSearch(int patnum, int strnum, Blackhole blackHole, BiFunction<P, String, Boolean> search) {
        P pattern = compiledPatterns[patnum];
        assert pattern != null : patterns[patnum] + " not found";
        boolean b = search.apply(pattern, strings[strnum]);
        blackHole.consume(b);
        assert (b == expectedMatch[patnum][strnum]) : String.format("[%d][%d] /%s/ \"%s\": expected %s, was %s", patnum, strnum, patterns[patnum], strings[strnum], expectedMatch[patnum][strnum], b);
    }

    private void doSearchGroup(int patnum, int strnum, Blackhole blackHole, BiFunction<P, String, String[]> search) {
        P pattern = compiledPatterns[patnum];
        assert pattern != null : patterns[patnum] + " not found";
        String[] b = search.apply(pattern, strings[strnum]);
        blackHole.consume(b);
        assert expectedMatch[patnum][strnum] == false || b != null : String.format("[%d][%d] /%s/ \"%s\": %s = %s", patnum, strnum, patterns[patnum], strings[strnum], Arrays.toString(b), expectedMatch[patnum][strnum]);
    }

    protected String translate(int pattnum) {
        return patterns[pattnum];
    }

}
