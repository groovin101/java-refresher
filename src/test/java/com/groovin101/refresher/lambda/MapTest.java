package com.groovin101.refresher.lambda;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import static org.junit.Assert.*;

public class MapTest {

    private Map<Integer, String> names = new HashMap<>();

    @Before
    public void init() {
        names.put(1, "Bart");
        names.put(2, "Jane");
        names.put(3, "Mary");
    }

    //default V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
    @Test
    public void compute_remapAKeyToANewValue() {

        String result = names.compute(2, (k, v) -> new StringBuilder(v).reverse().toString());

        assertEquals("enaJ", result);
        assertEquals("should have remapped our value in the map itself", result, names.get(2));
    }

    //default V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
    @Test
    public void compute_remapANonExistentKeyToANewValue() {

        String result = names.compute(99,
                (keyPassedToComputeMethod, valLookedUpFromMapInComputeMethod) -> {
                    if (valLookedUpFromMapInComputeMethod == null) {
                        return String.format("key [%d] not found so we inserted this val for it", keyPassedToComputeMethod);
                    }
                    return new StringBuilder(valLookedUpFromMapInComputeMethod).reverse().toString();
                });

        assertEquals("key [99] not found so we inserted this val for it", result);
        assertEquals("should have remapped our value in the map itself", result, names.get(99));
        System.out.println(names.get(99));
    }

    //default V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
    @Test
    public void computeIfAbsent_keyIsAbsent() {
        assertFalse(names.containsKey(88));
        assertEquals("key-wasnt-found-so-we-added-this-to-map",
                names.computeIfAbsent(88, k -> "key-wasnt-found-so-we-added-this-to-map"));
        assertEquals("key-wasnt-found-so-we-added-this-to-map", names.get(88));
    }

    //default V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
    @Test
    public void computeIfAbsent_keyIsPresent() {
        assertEquals("Jane",
                names.computeIfAbsent(2, k -> "key-wasnt-found-so-we-added-this-to-map"));
        assertEquals("Jane", names.get(2));
    }

    //default V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
    @Test
    public void computeIfPresent_keyIsAbsent() {
        assertFalse(names.containsKey(88));
        assertNull(names.computeIfPresent(88, (k, v) -> "key-was-not-present-so-this-wont-be-returned"));
        assertFalse(names.containsKey(88));
    }

    //default V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
    @Test
    public void computeIfPresent_keyIsPresent() {
        assertEquals("BartBartBart",
                names.computeIfPresent(1, (k, v) -> v + v + v));
        assertEquals("BartBartBart", names.get(1));
    }

    @Test
    public void merge_keyIsAbsent_newValInserted() {
        names.merge(44, "newValueHere", (oldVal, newVal) -> oldVal + newVal);
        assertEquals("newValueHere", names.get(44));
    }

    @Test
    public void merge_keyIsPresent_newValIsMergedWithOldValue() {
        names.merge(3, "newValueHere", (oldVal, newVal) -> oldVal + newVal);
        assertEquals("MarynewValueHere", names.get(3));
    }

    @Test
    public void merge_mergeTwoMaps() {
        Map<Integer, String> otherMap = new HashMap<>();
        otherMap.put(3, "Marie");
        otherMap.put(33, "this-was-not-in-original-list");

        System.out.println("before " + names);

        BiFunction<String, String, String> doThisInMerge = (oldVal, existingVal) -> "afterMerge";
        BiConsumer<Integer, String> doThisInForEach = (k, v) -> names.merge(k, v, doThisInMerge);
        otherMap.forEach(doThisInForEach);

        System.out.println("after " + names);
    }

    //Does the exact same thing as merge_mergeTwoMaps(), but inline lambda definitions:
    @Test
    public void merge_mergeTwoMaps_syntacticSugar() {
        Map<Integer, String> otherMap = new HashMap<>();
        otherMap.put(3, "Marie");
        otherMap.put(33, "afterMerge - this-was-not-in-original-list");

        System.out.println("before " + names);

        otherMap.forEach(
                (k, v) -> names.merge(k, v,
                        (oldVal, existingVal) -> String.format("afterMerge - %s or %s", oldVal, existingVal))
        );

        System.out.println("after " + names);
    }
}