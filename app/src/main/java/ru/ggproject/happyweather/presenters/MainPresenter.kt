package ru.ggproject.happyweather.presenters


import ru.ggproject.happyweather.business.ApiProvider
import ru.ggproject.happyweather.business.repo.MainRepository
import ru.ggproject.happyweather.view.MainView

class MainPresenter  : BasePresenter<MainView>() {

    private val repo = MainRepository(ApiProvider())

    override fun enable() {
        repo.dataEnitter.subscribe { response ->

            viewState.displayLocation(response.cityName)
            viewState.displayCurrentData(response.weatherData)
            viewState.displayDailyData(response.weatherData.daily)
            viewState.displayHourlyData(response.weatherData.hourly)
            response.error?.let{ viewState.displayError(response.error) }
        }
    }

    fun refresh (lat : String, lon : String) {
        viewState.setLoading(true)
        repo.reloadData(lat, lon)
    }

}