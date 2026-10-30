
package com.example.iscip.basic.mvvm;

import static org.koin.androidx.scope.ComponentActivityExtKt.createActivityScope;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.koin.android.scope.AndroidScopeComponent;
import org.koin.core.scope.Scope;
import org.koin.java.KoinJavaComponent;

public abstract class BaseActivity extends AppCompatActivity
        implements AndroidScopeComponent {

    private final String TAG = "BaseActivity";

    private Scope scope;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void createScope() {
        if (scope == null) {
            scope = createActivityScope(this);
        }
    }

    protected void disposeScope() {
        if (scope != null) {
            scope.close();
            scope = null;
        }
    }

    protected <T> T instanceOf(Class<T> componentClass) {
        T result;

        if (scope != null) {
            result = scope.get(
                    kotlin.jvm.JvmClassMappingKt.getKotlinClass(componentClass),
                    null,
                    null
            );
        } else {
            result = KoinJavaComponent.get(componentClass);
        }

        return result;
    }

    @NonNull
    @Override
    public Scope getScope() {
        return scope;
    }

    public abstract void onCloseScope();
}
