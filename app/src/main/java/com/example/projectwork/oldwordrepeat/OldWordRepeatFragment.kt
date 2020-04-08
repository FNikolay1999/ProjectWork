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

    private lateinit var binding : OldWordRepeatFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OldWordRepeatFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.apply {
            oldWordRepeatViewModel = viewModel
            //также передаем lifecycleOwner, потому что, чтобы у тебя данные сами обновлялись, а не один раз и все, то liveDat'е нужен lifecycleOwner
            //лучше не забывай его передавать, если у тебя во viewModel liveData, потому что этот баг искать долго
            binding.lifecycleOwner = viewLifecycleOwner
        }

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
