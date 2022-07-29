/*
 * The MIT License
 *
 * Copyright 2022 Maurício Moraes Nantes.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package postordersearch;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class PostOrderSearchFather {

    private static int count;
    private static int verifiedFathers;

    public static int[] getFathers(int h, int[] q) {
        count = 1;
        verifiedFathers = 0;
        int treeSize = (int) Math.pow(2, h) - 1;
        int[] fathers = IntStream.generate(() -> -1).limit(q.length).toArray();
        Map<Integer, Integer> indices = new HashMap<>();
        IntStream.range(0, q.length).forEachOrdered(i -> indices.put(q[i], i));

        for(int item : q){
            if(item >= treeSize){
                verifiedFathers++;
            }
        }

        searchNodes(h, 0, indices, fathers);

        return fathers;
    }

    private static int searchNodes(int depth, int currentDepth, Map<Integer, Integer> indices, int[] fathers){

        if(currentDepth >= depth){
            return -1;
        }

        if(verifiedFathers >= fathers.length){
            return -1;
        }
    
        int left = searchNodes(depth, currentDepth + 1, indices, fathers);

        int right = searchNodes(depth, currentDepth + 1, indices, fathers);

        if(indices.containsKey(left)){
            int i = indices.get(left);
            fathers[i] = count;
            verifiedFathers ++;
        }
        if(indices.containsKey(right)){
            int i = indices.get(right);
            fathers[i] = count;
            verifiedFathers++;
        }

        count++;

        return count - 1;
 
    }

}