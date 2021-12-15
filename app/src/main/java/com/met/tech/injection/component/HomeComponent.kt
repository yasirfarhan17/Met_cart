package com.met.tech.injection.component

import com.met.tech.injection.scope.ActivityScope
import com.met.tech.ui.splash.fragments.CategoryFragment
import com.met.tech.ui.splash.HomeActitvity
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface HomeComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(activity: HomeActitvity)

    fun inject(fragment: CategoryFragment)


}