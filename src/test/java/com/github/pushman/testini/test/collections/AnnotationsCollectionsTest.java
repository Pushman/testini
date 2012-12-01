package com.github.pushman.testini.test.collections;

import com.github.pushman.testini.Parameterised;
import com.github.pushman.testini.runner.TestiniTestRunner;
import com.github.pushman.testini.testKits.TestKit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.github.pushman.testini.utils.shortcuts.TestCaseShortcuts.$;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static org.fest.assertions.Assertions.assertThat;

@RunWith(TestiniTestRunner.class)
public class AnnotationsCollectionsTest {

    @Test
    @Parameterised
    public void shouldReturnCollectionSize(Collection collection, Integer expectedSize) {
        assertThat(collection.size()).isEqualTo(expectedSize);
    }

    public TestKit[] parametersForShouldReturnCollectionSize() {
        return $(
                $(Collections.emptySet(), 0),
                $(newArrayList("a", "b"), 2)
        );
    }

    @Test
    @Parameterised
    public <T> void shouldAddAllToCollection(Collection<T> firstCollection, Collection<T> secondCollection,
                                             Collection<T> expectedResult) {
        firstCollection.addAll(secondCollection);
        assertThat(firstCollection).isEqualTo(expectedResult);
    }

    public TestKit[] parametersForShouldAddAllToCollection() {
        List<String> collection = newArrayList("a", "b");
        return $(
                $(collection, newArrayList("c", "d"), newArrayList("a", "b", "c", "d")),
                $(collection, newHashSet("e"), newArrayList("a", "b", "e"))
        );
    }
}