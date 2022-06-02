package ru.ggproject.happyweather.business.repo

import io.reactivex.rxjava3.subjects.BehaviorSubject
import ru.ggproject.happyweather.business.ApiProvider

abstract class BaseRepository<T>(val api : ApiProvider) {

    val dataEnitter : BehaviorSubject<T> = BehaviorSubject.create()

}