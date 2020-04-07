package com.example.projectwork.exitscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.projectwork.R
import kotlinx.android.synthetic.main.new_words_fragment.*


class ExitScreenFragment : Fragment() {

    companion object {
        fun newInstance() =
            ExitScreenFragment()
    }

    private val viewModel: ExitScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.exit_screen_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rightTool.setOnClickListener { System.exit((-1)) }
        leftTool.setOnClickListener { view?.let { findNavController().navigate(R.id.action_exitScreenFragment_to_menuFragment) } }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.let { findNavController().navigate(R.id.action_exitScreenFragment_to_menuFragment) }
        }
    }

}
