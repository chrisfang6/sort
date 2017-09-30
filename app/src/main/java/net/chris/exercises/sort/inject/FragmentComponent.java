package net.chris.exercises.sort.inject;

import net.chris.exercises.sort.kotlin.KTSortFragment;
import net.chris.exercises.sort.ui.SortFragment;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent(modules = FragmentModule.class)
public abstract class FragmentComponent {

    private static FragmentComponent component;

    public static FragmentComponent getFragmentComponent() {
        if (component == null) {
            component = MainComponent.getMainComponent().plus(new FragmentModule());
        }
        return component;
    }

    public abstract void inject(SortFragment fragment);

    public abstract void inject(KTSortFragment fragment);
}
