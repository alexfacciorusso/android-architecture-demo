import android.view.View
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import kotlin.math.exp

object TextInputLayoutMatchers {
    fun hasTextInputLayoutErrorText(expectedErrorText: String?): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun matchesSafely(view: View): Boolean =
                (view as? TextInputLayout)?.error?.toString() == expectedErrorText

            override fun describeTo(description: Description?) {}
        }
    }
}