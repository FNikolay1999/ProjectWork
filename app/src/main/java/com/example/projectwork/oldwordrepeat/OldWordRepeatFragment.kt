package com.example.projectwork.oldwordrepeat

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController

import com.example.projectwork.R
import com.example.projectwork.databinding.OldWordRepeatFragmentBinding

class OldWordRepeatFragment : Fragment() {

    companion object {
        fun newInstance() = OldWordRepeatFragment()
        const val WORD_ID = "word_id"
    }

    private val viewModel: OldWordRepeatViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.old_word_repeat_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.let { findNavController().navigate(R.id.action_oldWordRepeatFragment_to_oldWordsMenuFragment) }
        }
        lifecycleScope.launchWhenStarted {
            val wordId = arguments?.getLong(WORD_ID)
            wordId?.let {
                viewModel.getWord(it)
            }
        }
    }

}
