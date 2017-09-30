package net.chris.exercises.sort.inject;

import net.chris.exercises.sort.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MainModule.class)
public abstract class MainComponent {

    public abstract void inject(MainActivity activity);

    public abstract SorterComponent plus(SorterModule module);

    public abstract FragmentComponent plus(FragmentModule module);

    private static MainComponent mainComponent;

    public static MainComponent getMainComponent() {
        if (mainComponent == null) {
            mainComponent = DaggerMainComponent.builder().build();
        }
        return mainComponent;
    }

}
