package net.chris.exercises.sort.inject;

import net.chris.exercises.sort.ui.model.SortViewModel;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent(modules = SorterModule.class)
public abstract class SorterComponent {

    private static SorterComponent component;

    public static SorterComponent getSorterComponent() {
        if (component == null) {
            component = MainComponent.getMainComponent().plus(new SorterModule());
        }
        return component;
    }

    public abstract void inject(SortViewModel viewModel);

}
