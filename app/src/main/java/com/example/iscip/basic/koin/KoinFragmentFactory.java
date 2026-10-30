
package com.example.iscip.basic.koin;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import com.example.iscip.basic.mvvm.BaseActivity;
import com.example.iscip.basic.mvvm.BaseFragment;

public class KoinFragmentFactory extends FragmentFactory {

    private final String TAG = "KoinFragmentFactory";

    private final Class<BaseFragment> baseFragmentClass = BaseFragment.class;
    private final BaseActivity activity;

    public KoinFragmentFactory(BaseActivity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public Fragment instantiate(
            @NonNull ClassLoader classLoader,
            @NonNull String className
    ) {
        Class<?> clazz = loadFragmentClass(classLoader, className);

        if (baseFragmentClass.isAssignableFrom(clazz)) {
            return activity.getScope().get(
                    kotlin.jvm.JvmClassMappingKt.getKotlinClass(clazz),
                    null,
                    null
            );
        }

        return super.instantiate(classLoader, className);
    }
}
