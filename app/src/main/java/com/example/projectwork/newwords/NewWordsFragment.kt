package com.example.projectwork.newwords

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


class NewWordsFragment : Fragment() {

    companion object {
        fun newInstance() = NewWordsFragment()
    }

    private val viewModel: NewWordsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_words_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        leftTool.setOnClickListener { view?.let { findNavController().navigate(R.id.action_newWordsFragment_to_menuFragment) } }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.let { findNavController().navigate(R.id.action_newWordsFragment_to_menuFragment) }
        }
    }

}
