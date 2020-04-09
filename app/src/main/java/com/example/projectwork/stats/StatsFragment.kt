package com.example.projectwork.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.projectwork.R
import com.example.projectwork.databinding.StatsFragmentBinding


class StatsFragment : Fragment() {

    companion object {
        fun newInstance() = StatsFragment()
    }

    private val viewModel: StatsViewModel by viewModels()
    //Обьявления обьекта для binding
    private lateinit var binding : StatsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.stats_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.apply {
            statsViewModel = viewModel
            binding.lifecycleOwner = lifecycleOwner
        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.let { findNavController().navigate(R.id.action_statsFragment_to_menuFragment) }
        }
    }

}
