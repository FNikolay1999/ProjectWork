package com.example.projectwork.oldwordrepeat

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController

import com.example.projectwork.R

class OldWordRepeatFragment : Fragment() {

    companion object {
        fun newInstance() = OldWordRepeatFragment()
    }

    private lateinit var viewModel: OldWordRepeatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.old_word_repeat_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(OldWordRepeatViewModel::class.java)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.let { findNavController().navigate(R.id.action_oldWordRepeatFragment_to_oldWordsMenuFragment) }
        }
    }

}
