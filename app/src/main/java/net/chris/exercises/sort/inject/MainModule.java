package net.chris.exercises.sort.inject;

import net.chris.exercises.sort.Constants.Type;
import net.chris.exercises.sort.comm.RxSubject;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Singleton
    @Provides
    public RxSubject<Type> provideTypeSubject() {
        return new RxSubject<>();
    }

}
