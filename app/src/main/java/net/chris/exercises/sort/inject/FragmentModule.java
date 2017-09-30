package net.chris.exercises.sort.inject;

import net.chris.exercises.sort.ui.model.SortViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    @Singleton
    @Provides
    public SortViewModel providesSortViewModel() {
        return new SortViewModel();
    }

}
