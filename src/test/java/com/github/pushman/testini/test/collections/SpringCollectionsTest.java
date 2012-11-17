package com.github.pushman.testini.test.collections;

import com.github.pushman.testini.runner.TestiniSpringTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(TestiniSpringTestRunner.class)
public class SpringCollectionsTest {

    @Test
    public void shouldReturnCollectionSize(Collection collection, Integer expectedSize) {
        assertThat(collection.size()).isEqualTo(expectedSize);
    }

    @Test
    public <T> void shouldAddAllToCollection(Collection<T> firstCollection, Collection<T> secondCollection,
                                             Collection<T> expectedResult) {
        firstCollection.addAll(secondCollection);
        assertThat(firstCollection).isEqualTo(expectedResult);
    }
}