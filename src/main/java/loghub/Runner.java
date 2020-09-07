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

import java.util.stream.IntStream;

import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.infra.Blackhole;

import com.google.common.base.Strings;

public abstract class Runner<P> {

    private static final String[] patterns = {
            "^(([^:]+)://)?([^:/]+)(:([0-9]+))?(/.*)", // URL match
            "(([^:]+)://)?([^:/]+)(:([0-9]+))?(/.*)", // URL match without starting ^
            "usd [+-]?[0-9]+.[0-9][0-9]", // Canonical US dollar amount
            "\\b(\\w+)(\\s+\\1)+\\b", // Duplicate words
            "(x+x+)+y", // Catastrophic Backtracking
            "\\{(\\d+):((.(?!-}))*).*", // Long matches
            "\\[(?<time>\\d{4}[\\/-]\\d{2}[\\/-]\\d{2}[\\-\\s]\\d{2}:\\d{2}:\\d{2}([\\.,]\\d+)?)\\]\\s+\\[\\s?(?<severity>(?i)TRACE|DEBUG|PERF|NOTE|INFO|WARN|ERROR|FATAL)\\s?\\]\\s+\\[(?<thread>([\\\"\\w\\d\\.\\,\\-_\\@\\s\\/\\:\\#\\\\\\=\\{\\}\\&\\+\\%\\)\\(]*)((\\.\\.\\.\\[).*(ing\\]))?)\\]\\s+\\[(?<logger>[\\w\\d\\.\\-_]*)\\]\\s(?<message>.*)"
    };

    private static final String[] strings = {
            "http://www.linux.com/",
            "http://www.thelinuxshow.com/main.php3",
            "usd 1234.00",
            "he said she said he said no",
            "same same same",
            "xxxxxxxxxxxxxxxxy", //Succeed backtracking
            "xxxxxxxxxxxxxxxxx", //Failing backtracking
            "{1:" + Strings.repeat("this is some more text - and some more and some more and even more ", 10) + "-}\n",
            "[2020-07-20 09:35:41,099] [INFO ] [1637163705@thread-name-3284] [loghub.dummy.class] A found String."
    };
    
    private final P[] compiledPatterns;

    private static boolean[][] expectedMatch = new boolean[7][9];

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
        expectedMatch[1][0] = true;
        expectedMatch[1][1] = true;
        expectedMatch[1][2] = false;
        expectedMatch[1][3] = false;
        expectedMatch[1][4] = false;
        expectedMatch[1][5] = false;
        expectedMatch[1][6] = false;
        expectedMatch[1][7] = false;
        expectedMatch[1][8] = false;
        expectedMatch[2][0] = false;
        expectedMatch[2][1] = false;
        expectedMatch[2][2] = true;
        expectedMatch[2][3] = false;
        expectedMatch[2][4] = false;
        expectedMatch[2][5] = false;
        expectedMatch[2][6] = false;
        expectedMatch[2][7] = false;
        expectedMatch[2][8] = false;
        expectedMatch[3][0] = false;
        expectedMatch[3][1] = false;
        expectedMatch[3][2] = false;
        expectedMatch[3][3] = false;
        expectedMatch[3][4] = true;
        expectedMatch[3][5] = false;
        expectedMatch[3][6] = false;
        expectedMatch[3][7] = false;
        expectedMatch[3][8] = false;
        expectedMatch[4][0] = false;
        expectedMatch[4][1] = false;
        expectedMatch[4][2] = false;
        expectedMatch[4][3] = false;
        expectedMatch[4][4] = false;
        expectedMatch[4][5] = true;
        expectedMatch[4][6] = false;
        expectedMatch[4][7] = false;
        expectedMatch[4][8] = false;
        expectedMatch[5][0] = false;
        expectedMatch[5][1] = false;
        expectedMatch[5][2] = false;
        expectedMatch[5][3] = false;
        expectedMatch[5][4] = false;
        expectedMatch[5][5] = false;
        expectedMatch[5][6] = false;
        expectedMatch[5][7] = true;
        expectedMatch[5][8] = false;
        expectedMatch[6][0] = false;
        expectedMatch[6][1] = false;
        expectedMatch[6][2] = false;
        expectedMatch[6][3] = false;
        expectedMatch[6][4] = false;
        expectedMatch[6][5] = false;
        expectedMatch[6][6] = false;
        expectedMatch[6][7] = false;
        expectedMatch[6][8] = true;
    }

    protected Runner() {
        compiledPatterns = getPatternStorage(7);
    }

    protected abstract P[] getPatternStorage(int size);
    protected abstract P generate(String i);
    protected abstract boolean match(P pattern, String searched);

    public void run(Blackhole blackHole) {
        for (int patnum = 0; patnum < patterns.length - 1; patnum++) {
            for (int strnum = 0; strnum < strings.length - 3; strnum++) {
                match(patnum, strnum, blackHole);
            }
        }
    }

    public void runbig(Blackhole blackHole) {
        for (int patnum = 0; patnum < patterns.length - 1; patnum++) {
            int strnum = strings.length - 2;
            match(patnum, strnum, blackHole);
        }
    }

    public void runcatastroph(Blackhole blackHole) {
        for (int patnum = 0; patnum < patterns.length - 1; patnum++) {
            int strnum = strings.length - 3;
            match(patnum, strnum, blackHole);
        }
    }

    public void runlog(Blackhole blackHole) {
        for (int patnum = 0; patnum < patterns.length; patnum++) {
            int strnum = strings.length - 1;
            match(patnum, strnum, blackHole);
        }
    }

    @Setup
    public void prepare() {
        IntStream.range(0, patterns.length).forEach(this::compileAndStore);
    }

    private void compileAndStore(int pattnum) {
        try {
            compiledPatterns[pattnum] = generate(patterns[pattnum]);
        } catch (Exception e) {
            // Invalid pattern, skip
        }
    }

    private void match(int patnum, int strnum, Blackhole blackHole) {
        P pattern = compiledPatterns[patnum];
        assert pattern != null : patterns[patnum] + " not found";
        boolean b = match(pattern, strings[strnum]);
        blackHole.consume(b);
        assert (b == expectedMatch[patnum][strnum]) : String.format("[%d][%d] %s %s: %s = %s ?", patnum, strnum, patterns[patnum], strings[strnum], b, expectedMatch[patnum][strnum]);
    }

}
