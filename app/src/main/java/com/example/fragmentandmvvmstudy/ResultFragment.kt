package com.example.fragmentandmvvmstudy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels

class ResultFragment : Fragment() {

    private lateinit var tempTextView: TextView
    private lateinit var descriptionTextView: TextView

    var temperature = 0.0
    var description = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return  inflater.inflate(R.layout.fragment_result, container, false)
    }

    // onCreateView가 되고 나서 나타아햐 하므로 onViewCreated 사용하`
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 초기화 필수!!!
        tempTextView = view.findViewById(R.id.tempTextView)
        descriptionTextView = view.findViewById(R.id.descriptionTextView)

        val viewModel: MainViewModel by activityViewModels()

        viewModel.weatherData.observe(viewLifecycleOwner) { weatherModel ->
//            val temperature = weatherModel?.main?.temp ?: 0.0
//            val description = weatherModel?.weather?.get(0)?.description ?: ""

            // 텍스트를 보여주는 것이므로 뷰!!
            tempTextView.text = "온도: " + weatherModel?.main?.temp
            descriptionTextView.text = "날씨: " + weatherModel?.weather?.get(0)?.description
        }
    }
}