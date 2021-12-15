package com.met.tech.injection.component

import com.met.tech.injection.scope.ActivityScope
import com.met.tech.ui.splash.SplashActivity1
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface SplashComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SplashComponent
    }

    fun inject(activity: SplashActivity1)


}