package a84b9cb.info.guchiru

import a84b9cb.info.guchiru.model.Guchi
import android.support.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

class CustomViewAction {
    companion object {
        fun checkCommentText(commentText: String): Matcher<Any> {
            // Guchiがlistviw.getItemでかえってくるデータ
            return object : BoundedMatcher<Any, Guchi>(Guchi::class.java) {
                override fun matchesSafely(item: Guchi?): Boolean {
                    // 今回はテキストだけを比較、ここで色々Adapter内のデータとあっているか比較する
                    return item!!.equals(item.comment)
                }

                override fun describeTo(description: Description?) {
                    description?.appendText("with comment: " + commentText)
                }
            }
        }
    }
}
