package ru.ggproject.happyweather.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.textview.MaterialTextView
import ru.ggproject.happyweather.R
import ru.ggproject.happyweather.business.model.HourlyWeatherModel
import ru.ggproject.happyweather.view.DAY_WEEK_NAME_LONG
import ru.ggproject.happyweather.view.provideIcon
import ru.ggproject.happyweather.view.toDataFormatOf
import ru.ggproject.happyweather.view.toDegree
import java.lang.StringBuilder

class MainHourlyListAdapter : BaseAdapter<HourlyWeatherModel>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_main_hourly, parent, false)
        return HourlyViewHolder(view)
    }

    @SuppressLint("NonConstantResourceId")
    inner class HourlyViewHolder(view: View) : BaseViewHolder(view) {

        @BindView(R.id.item_hourly_time_tv)
        lateinit var time : MaterialTextView              //время
        @BindView(R.id.item_hourly_temp_tv)
        lateinit var temperature : MaterialTextView       //температура
        @BindView(R.id.item_hourly_pop_tv)
        lateinit var popRate : MaterialTextView           //вероятность осадков
        @BindView(R.id.item_hourly_weather_condition_icon)
        lateinit var icon : ImageView

        init {
            ButterKnife.bind(this, itemView)
        }

        override fun bindView(position: Int) {

            mData[position].apply {
                time.text = dt.toDataFormatOf(DAY_WEEK_NAME_LONG)
                temperature.text = StringBuilder().append(temp.toDegree()).append(" °").toString()
                popRate.text = pop.toString()
                icon.setImageResource(weather[0].icon.provideIcon())
            }

        }

    }

}