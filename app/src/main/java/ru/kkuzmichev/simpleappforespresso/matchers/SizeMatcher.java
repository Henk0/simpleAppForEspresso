package ru.kkuzmichev.simpleappforespresso.matchers;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Optional;

public class SizeMatcher extends TypeSafeMatcher<View> {
    private final int size;

    public SizeMatcher(int size) {
        this.size = size;
    }

    public static SizeMatcher of(int size) {
        return new SizeMatcher(size);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected boolean matchesSafely(View item) {
        return Optional.ofNullable((RecyclerView) item)
                .map(RecyclerView::getAdapter)
                .map(RecyclerView.Adapter::getItemCount)
                .map(count -> count == size)
                .orElse(false);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("RecyclerView should have " + size + " items");
    }
}