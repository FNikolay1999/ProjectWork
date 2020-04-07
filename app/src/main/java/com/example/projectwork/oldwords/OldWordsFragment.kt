package com.example.projectwork.oldwords

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.projectwork.R
import kotlinx.android.synthetic.main.old_words_fragment.*


class OldWordsFragment : Fragment() {

    companion object {
        fun newInstance() = OldWordsFragment()
    }

    private val viewModel: OldWordsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.old_words_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        checkButton.setOnClickListener { view?.let { findNavController().navigate(R.id.action_oldWordsFragment_to_menuFragment) } }

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.let { findNavController().navigate(R.id.action_oldWordsFragment_to_menuFragment) }
        }
    }

}
