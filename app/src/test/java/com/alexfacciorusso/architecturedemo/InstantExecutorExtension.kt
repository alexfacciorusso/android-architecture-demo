package com.alexfacciorusso.architecturedemo

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import io.kotest.core.listeners.TestListener
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@ExperimentalCoroutinesApi
class InstantExecutorListener : TestListener {
    @Suppress("MemberVisibilityCanBePrivate")
    val coroutineDispatcher = TestCoroutineDispatcher()

    override suspend fun beforeTest(testCase: TestCase) {
        super.beforeTest(testCase)

        ArchTaskExecutor.getInstance()
            .setDelegate(object : TaskExecutor() {
                override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

                override fun postToMainThread(runnable: Runnable) = runnable.run()

                override fun isMainThread(): Boolean = true
            })

        Dispatchers.setMain(coroutineDispatcher)
    }

    override suspend fun afterTest(testCase: TestCase, result: TestResult) {
        super.afterTest(testCase, result)
        ArchTaskExecutor.getInstance().setDelegate(null)
        Dispatchers.resetMain()
    }
}
