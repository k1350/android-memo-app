package a84b9cb.info.guchiru

import android.support.test.espresso.Espresso.onData
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.anything
import android.support.test.espresso.assertion.ViewAssertions.matches
import org.hamcrest.Matchers.not

@RunWith(AndroidJUnit4::class)
class MainListActivityTest {
    @JvmField
    @Rule
    val activityTestRule = ActivityTestRule(MainListActivity::class.java)

    /**
     * 各ビューが表示されている
     */
    @Test
    fun AllDisplayed() {
        onView(withId(R.id.lvCommentView)).check(matches(isDisplayed()))
        onView(withId(R.id.footer)).check(matches(isDisplayed()))
        onView(withId(R.id.et_guchi)).check(matches(isDisplayed()))
        onView(withId(R.id.bt_submit)).check(matches(isDisplayed()))
    }

    fun TextEdited() {
        onView(withId(R.id.et_guchi))
                .perform(typeText("テスト文字列だよー"))
        onView(withId(R.id.bt_submit)).perform(click())
        onData(anything())
                .inAdapterView(withId(R.id.lvCommentView))
                .atPosition(0)
                .onChildView(withId(R.id.tv_comment))
                .check(matches(withText("aaa")))
    }

}
