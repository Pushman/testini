package com.github.pushman.testini.runner;

import com.github.pushman.testini.xml.XMLTestSuite;
import com.github.pushman.testini.xml.data.TestCaseData;
import com.github.pushman.testini.xml.data.TestKitData;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static junitparams.JUnitParamsRunner.$;
import static org.fest.assertions.Assertions.assertThat;


@RunWith(JUnitParamsRunner.class)
public class RegexToolsTest {

    private static final String ASCII_QUERY = "abcd 123 ,/.";
    private static final String QUOTED_ASCII_QUERY = "\\Qabcd 123 ,/.\\E";
    private static final String UNICODE_QUERY = "Łódź abc 123 ,/.";
    private static final String QUOTED_UNICODE_QUERY = "[łŁ][óÓ]\\Qd\\E[źŹ]\\Q abc 123 ,/.\\E";

    @Test
    @Parameters
    public void shouldQuoteQuery(String given, String expected) {
        // when
        String result = RegexTools.unicodeCaseInsensitiveQuoted(given);

        // then
        assertThat(result).isEqualTo(expected);
    }

    public Object[] parametersForShouldQuoteQuery() {
        String configLocation = "classpath:/com/github/pushman/testini/runner/RegexToolsTest.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
        XMLTestSuite suite = context.getBean(XMLTestSuite.class);
        TestCaseData fixture = newArrayList(suite.getCases()).get(0);
        List<TestKitData> kits = newArrayList(fixture.getTestKits());
        return $(
                kits.get(0).getKitData().toArray(new Object[2])
                , kits.get(1).getKitData().toArray(new Object[2])
        );
    }

    public Object[] oldParametersForShouldQuoteQuery() {
        return $(
                $(ASCII_QUERY, QUOTED_ASCII_QUERY)
                , $(UNICODE_QUERY, QUOTED_UNICODE_QUERY)
        );
    }
}