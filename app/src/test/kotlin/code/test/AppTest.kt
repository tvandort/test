package code.test

import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class AppTest {
    @Test fun testAppHasAGreeting() {
        val classUnderTest = App()
        assertNotNull(classUnderTest.greeting, "app should have a greeting")
    }

    @Test fun foo() {
        assertTrue(true)
    }
}
