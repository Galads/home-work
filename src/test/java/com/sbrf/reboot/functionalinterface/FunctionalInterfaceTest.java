package com.sbrf.reboot.functionalinterface;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sbrf.reboot.utils.JSONUtils;
import lombok.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;

public class FunctionalInterfaceTest {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class SomeObject {
        private String objectName;
    }

    @FunctionalInterface
    interface ObjectToJsonFunction<T> {
        String applyAsJson(T t) throws JsonProcessingException;
    }

    @FunctionalInterface
    interface ParseReferencesParameters<T> {
        LinkedList<String> parseAsString(T t);
    }

    static class ListConverter<T> {

        public List<String> toJsonsList(
                @NonNull List<T> someObjects,
                ObjectToJsonFunction<T> objectToJsonFunction) throws JsonProcessingException {

            List<String> result = new ArrayList<>();
            if (someObjects.isEmpty())
                throw new IllegalArgumentException("The list is empty");
            //add code here...
            for (T someObject : someObjects) {
                result.add(objectToJsonFunction.applyAsJson(someObject));
            }
            return result;
        }
    }

    @Test
    public void successCallFunctionalInterface() throws JsonProcessingException {
        ListConverter<SomeObject> ListConverter = new ListConverter<>();

        //add code here...
        ObjectToJsonFunction<SomeObject> objectToJsonFunction = JSONUtils::toJSON;

        List<String> strings = ListConverter.toJsonsList(
                asList(
                        new SomeObject("Object-1"),
                        new SomeObject("Object-2")
                ),
                objectToJsonFunction
        );

        Assertions.assertTrue(strings.contains("{\"objectName\":\"Object-1\"}"));
        Assertions.assertTrue(strings.contains("{\"objectName\":\"Object-2\"}"));
    }

    static class ParseParameters<T> {
        public List<String> toListParam(
                @NonNull List<T> objects,
                ParseReferencesParameters<T> parseReferencesParameters) {
            List<String> res = new ArrayList<>();

            if (objects.isEmpty())
                throw new IllegalArgumentException("The list is empty");
            objects.forEach(e -> res.addAll(parseReferencesParameters.parseAsString(e)));
            return res;
        }
    }

    @Test
    public void successCallFunctionalInterfaceSec() {
        ParseReferencesParameters<SomeObject> parseReferencesParameters = e -> {
            val splits = e.objectName.split("[?]")[1];
            val arr = splits.split("[&,]");

            return new LinkedList<>(Arrays.asList(arr));
        };

        ParseParameters<SomeObject> parseParameters = new ParseParameters<>();

        List<String> strings = parseParameters.toListParam(
                asList(
                        new SomeObject("http://localhost:443?values=firstValue,secondValue,thirdValue"),
                        new SomeObject("http://localhost:8080?values=firstValue&values=secondValue&values=thirdValue")
                ),
                parseReferencesParameters
        );

        Assertions.assertEquals(6, strings.size());
        Assertions.assertEquals("values=firstValue", strings.get(0));
        Assertions.assertEquals("thirdValue", strings.get(2));
        Assertions.assertEquals("values=thirdValue", strings.get(5));
    }
}