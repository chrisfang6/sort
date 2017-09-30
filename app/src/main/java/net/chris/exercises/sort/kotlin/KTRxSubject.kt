package net.chris.exercises.sort.kotlin

import io.reactivex.Observable
import io.reactivex.subjects.Subject
import net.chris.exercises.sort.Constants.Type


class KTRxSubject(var subject: Subject<Type>) {

    fun listen(): Observable<Type> {
        return subject
    }

    fun post(data: Type) {
        if (subject.hasObservers()) {
            subject.onNext(data)
        }
    }
}
