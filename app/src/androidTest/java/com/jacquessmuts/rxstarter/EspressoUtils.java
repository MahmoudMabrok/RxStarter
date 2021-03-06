package com.jacquessmuts.rxstarter;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by jacquessmuts on 2018/12/08
 * TODO: Add a class header comment!
 */
public class EspressoUtils {

    public static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    public static TypeSafeMatcher<View> isTextLength(final int minLength, final int maxLength) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                int length = ((TextView) item).getText().length();
                return (length <= maxLength && length >= minLength);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("isTextLength");
            }
        };
    }

}
