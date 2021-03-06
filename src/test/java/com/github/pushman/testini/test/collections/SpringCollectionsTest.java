package com.github.pushman.testini.test.collections;

import com.github.pushman.testini.Parameterised;
import com.github.pushman.testini.XmlParametersConfiguration;
import com.github.pushman.testini.runner.TestiniRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(TestiniRunner.class)
@XmlParametersConfiguration
public class SpringCollectionsTest {

    @Test
    @Parameterised
    public void shouldReturnCollectionSize(Collection collection, Integer expectedSize) {
        assertThat(collection.size()).isEqualTo(expectedSize);
    }

    @Test
    @Parameterised
    public <T> void shouldAddAllToCollection(Collection<T> firstCollection, Collection<T> secondCollection,
                                             Collection<T> expectedResult) {
        firstCollection.addAll(secondCollection);
        assertThat(firstCollection).isEqualTo(expectedResult);
    }
}