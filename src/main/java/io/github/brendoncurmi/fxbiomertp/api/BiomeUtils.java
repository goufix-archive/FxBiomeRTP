/*
 * MIT License
 *
 * Copyright (c) 2020 Brendon Curmi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.github.brendoncurmi.fxbiomertp.api;

import org.spongepowered.api.world.biome.BiomeType;
import org.spongepowered.api.world.biome.BiomeTypes;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;

public class BiomeUtils implements Serializable {
    private static final long serialVersionUID = 1859671383177704519L;

    /**
     * Stores the biome names and biome field names.
     */
    private static HashMap<String, String> biomeNamesMap = new HashMap<>();

    public static String getBiomeName(BiomeType biomeType) {
        return biomeNamesMap.get(biomeType.getName());
    }

    /**
     * Initializes the biome map.
     *
     * @throws IllegalAccessException if this {@code Field} object
     *                                is enforcing Java language access control and the underlying
     *                                field is inaccessible.
     */
    public static void initBiomes() throws IllegalAccessException {
        Field[] fields = BiomeTypes.class.getFields();
        for (Field field : fields) {
            String name = field.getName();
            BiomeType type = (BiomeType) field.get(null);
            biomeNamesMap.put(type.getName(), name.toLowerCase());
        }
    }

    /**
     * Stores the biome type and their biome data.
     */
    private HashMap<String, BiomeData> scannedBiomes = new HashMap<>();

    public boolean empty() {
        return scannedBiomes.isEmpty();
    }

    public boolean hasBiome(String biome) {
        return scannedBiomes.containsKey(biome);
    }

    public BiomeData getBiomeData(String biome) {
        if (!hasBiome(biome)) scannedBiomes.put(biome, new BiomeData());
        return scannedBiomes.get(biome);
    }
}
