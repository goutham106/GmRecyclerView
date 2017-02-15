/*
 * Copyright (c) 2017 Gowtham Parimalazhagan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gm.grecyclerview;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Name       : Gowtham
 * Created on : 15/2/17.
 * Email      : goutham.gm11@gmail.com
 * GitHub     : https://github.com/goutham106
 */
public final class Utils {

    private static int gcd(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }

    static int lcm(List<Integer> input) {
        int result = input.get(0);
        for (int i = 1; i < input.size(); i++) {
            result = lcm(result, input.get(i));
        }
        return result;
    }

    static List<Integer> toIntList(String sequence) {
        char[] chars = sequence.toCharArray();
        List<Integer> result = new ArrayList<>(chars.length);
        for (int i = 0; i < chars.length; i++) {
            result.add(Integer.parseInt(chars[i] + ""));
        }
        return result;
    }

    static List<Integer> toIntList(int first, int... rest) {
        List<Integer> result = new ArrayList<>();
        result.add(first);
        for (int i = 0; i < rest.length; i++) {
            result.add(rest[i]);
        }
        return result;
    }

    public static int dpToPx(Context context, int dp) {
        float density = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density);
    }

}

