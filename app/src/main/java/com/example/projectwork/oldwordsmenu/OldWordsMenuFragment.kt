package com.example.projectwork.oldwordsmenu

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import com.example.projectwork.R
import kotlinx.android.synthetic.main.old_words_menu_fragment.*

class OldWordsMenuFragment : Fragment() {

    companion object {
        fun newInstance() = OldWordsMenuFragment()
    }

    private val viewModel: OldWordsMenuViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.old_words_menu_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dictionaryButton.setOnClickListener { view?.let { findNavController().navigate(R.id.action_oldWordsMenuFragment_to_oldWordRepeatFragment) } }
        oldWordsButton.setOnClickListener { view?.let { findNavController().navigate(R.id.action_oldWordsMenuFragment_to_oldWordsFragment) } }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.let { findNavController().navigate(R.id.action_oldWordsMenuFragment_to_menuFragment) }
        }
    }


}
