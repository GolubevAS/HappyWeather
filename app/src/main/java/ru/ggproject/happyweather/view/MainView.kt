package ru.ggproject.happyweather.view


import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.ggproject.happyweather.business.model.DailyWeatherModel
import ru.ggproject.happyweather.business.model.HourlyWeatherModel
import ru.ggproject.happyweather.business.model.WeatherDataModel


interface MainView : MvpView {

    @AddToEndSingle
    fun displayLocation (data : String)
    @AddToEndSingle
    fun displayCurrentData (data : WeatherDataModel)
    @AddToEndSingle
    fun displayHourlyData (data : List<HourlyWeatherModel>)
    @AddToEndSingle
    fun displayDailyData (data : List<DailyWeatherModel>)
    @AddToEndSingle
    fun displayError (error : Throwable)
    @AddToEndSingle
    fun setLoading (flag : Boolean)


}