package com.github.pushman.testini.test.collections;

import com.github.pushman.testini.runner.TestiniTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.Collections;

import static com.google.common.collect.Lists.newArrayList;
import static junitparams.JUnitParamsRunner.$;
import static org.fest.assertions.Assertions.assertThat;

@RunWith(TestiniTestRunner.class)
public class AnnotationsCollectionsTest {

    @Test
    public void shouldReturnCollectionSize(Collection collection, Integer expectedSize) {
        assertThat(collection.size()).isEqualTo(expectedSize);
    }

    public Object[] parametersForShouldReturnCollectionSize() {
        return $(
                $(Collections.emptySet(), 0),
                $(newArrayList("Hop, hop"), 1)
        );
    }
}