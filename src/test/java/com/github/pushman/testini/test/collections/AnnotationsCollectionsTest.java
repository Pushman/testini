package com.github.pushman.testini.test.collections;

import com.github.pushman.testini.runner.TestiniTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
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

    @Test
    public <T> void shouldAddAllToCollection(Collection<T> firstCollection, Collection<T> secondCollection,
                                             Collection<T> expectedResult) {
        firstCollection.addAll(secondCollection);
        assertThat(firstCollection).isEqualTo(expectedResult);
    }

    public Object[] parametersForShouldAddAllToCollection() {
        List<String> collection = newArrayList("a", "b");
        return $(
                $(collection, newArrayList("c", "d"), newArrayList("a", "b", "c", "d")),
                $(collection, newHashSet("e"), newArrayList("a", "b", "e"))
        );
    }
}