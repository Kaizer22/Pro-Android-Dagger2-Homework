package ru.otus.daggerhomework.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.otus.daggerhomework.R
import javax.inject.Inject

class ProducerFragment : Fragment() {

    @Inject
    lateinit var vmFactory: ProducerViewModel.Factory

    private val viewModel by viewModels<ProducerViewModel> { vmFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).mainActivityComponent
            .producerFragmentComponent()
            .create()
            .inject(this)
        return inflater.inflate(R.layout.fragment_producer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button).setOnClickListener {
            //отправить результат через flow в другой фрагмент
            viewModel.onGenerateColorClick()
        }
    }
}