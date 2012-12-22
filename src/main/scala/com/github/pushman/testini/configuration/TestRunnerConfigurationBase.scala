package com.github.pushman.testini.configuration

import com.github.pushman.testini.runner.TestRunner

abstract class TestRunnerConfigurationBase extends
TestRunner with
TestClassProvider with
TestCasesProvider with
TestRunnerTestMethodProvider with
TestRunnerTestDescriptionProvider with
TestRunnerValidator with
TestRunnerMethodInvokerProvider with
RunningTestContextProvider
