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
import java.util.HashMap;
import java.util.Map;

import org.openjdk.jmh.annotations.Setup;

import com.google.common.base.Strings;

public abstract class Runner<P> {

    private static final String[] patterns = {"^(([^:]+)://)?([^:/]+)(:([0-9]+))?(/.*)", // URL match
            "(([^:]+)://)?([^:/]+)(:([0-9]+))?(/.*)", // URL match without starting ^
            "usd [+-]?[0-9]+.[0-9][0-9]", // Canonical US dollar amount
            "\\b(\\w+)(\\s+\\1)+\\b", // Duplicate words
            "\\{(\\d+):(([^}](?!-} ))*)" // Long matches
    };

    private static final String[] strings = {
            "http://www.linux.com/",
            "http://www.thelinuxshow.com/main.php3",
            "usd 1234.00",
            "he said she said he said no",
            "same same same",
            "{1:\n" + Strings.repeat("this is some more text - and some more and some more and even more\n", 10) + "-}\n"
    };

    private static boolean[][] expectedMatch = new boolean[5][6];

    static
    {
        expectedMatch[0][0] = true;
        expectedMatch[0][1] = true;
        expectedMatch[0][2] = false;
        expectedMatch[0][3] = false;
        expectedMatch[0][4] = false;
        expectedMatch[0][5] = false;
        expectedMatch[1][0] = true;
        expectedMatch[1][1] = true;
        expectedMatch[1][2] = false;
        expectedMatch[1][3] = false;
        expectedMatch[1][4] = false;
        expectedMatch[1][5] = false;
        expectedMatch[2][0] = false;
        expectedMatch[2][1] = false;
        expectedMatch[2][2] = true;
        expectedMatch[2][3] = false;
        expectedMatch[2][4] = false;
        expectedMatch[2][5] = false;
        expectedMatch[3][0] = false;
        expectedMatch[3][1] = false;
        expectedMatch[3][2] = false;
        expectedMatch[3][3] = false;
        expectedMatch[3][4] = true;
        expectedMatch[3][5] = false;
        expectedMatch[4][0] = false;
        expectedMatch[4][1] = false;
        expectedMatch[4][2] = false;
        expectedMatch[4][3] = false;
        expectedMatch[4][4] = false;
        expectedMatch[4][5] = false;
    }

    private final Map<String, P> patternsMap = new HashMap<>(patterns.length);

    protected abstract P generate(String i);
    protected abstract boolean match(P pattern, String searched);

    public P get(String s) {
        //return patternsMap.computeIfAbsent(s, i -> generate(i));
        return patternsMap.get(s);
    }

    public void run() {
        for (int regnum = 0; regnum < patterns.length; regnum++) {
            //P pattern = generate(patterns[regnum])
            P pattern = get(patterns[regnum]);
            for (int strnum = 0; strnum < strings.length - 1; strnum++) {
                boolean b = match(pattern, strings[strnum]);
                assert (b == expectedMatch[regnum][strnum]) : String.format("%s %s: %s = %s ?", patterns[regnum], strings[strnum], b, expectedMatch[regnum][strnum]);
            }
        }
    }

    public void runbig() {
        for (int regnum = 0; regnum < patterns.length; regnum++) {
            //P pattern = generate(patterns[regnum])
            P pattern = get(patterns[regnum]);
            int strnum = strings.length - 1;
            boolean b = match(pattern, strings[strnum]);
            assert (b == expectedMatch[regnum][strnum]) : String.format("%s %s: %s = %s ?", patterns[regnum], strings[strnum], b, expectedMatch[regnum][strnum]);
        }
    }

    @Setup
    public void prepare() {
        Arrays.stream(patterns).forEach( i -> patternsMap.put(i, generate(i)));
    }

}
