package com.example.projectwork.newwords

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.projectwork.R
import com.example.projectwork.databinding.NewWordsFragmentBinding
import com.example.projectwork.network.bindImage

import kotlinx.android.synthetic.main.new_words_fragment.*


class NewWordsFragment : Fragment() {

    companion object {
        fun newInstance() = NewWordsFragment()
    }

    private val viewModel: NewWordsViewModel by viewModels()

    private lateinit var binding : NewWordsFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewWordsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        binding.apply {

            newWordsViewModel = viewModel
            binding.lifecycleOwner = lifecycleOwner

            bindImage(wordImage, newWordsViewModel!!.intWord?.value?.imgSrcUrl)
//            newWordsViewModel.intWord?.let { bindImage(wordImage, it?.imgSrcUrl) }

            leftTool.setOnClickListener { view?.let { findNavController().navigate(R.id.action_newWordsFragment_to_menuFragment) } }
            rightTool.setOnClickListener {
                viewModel.nextWord()
            }

            viewModel.intWord.observe(viewLifecycleOwner) {
                newWordsViewModel = viewModel
//                binding.lifecycleOwner = lifecycleOwner

                bindImage(wordImage, newWordsViewModel!!.intWord?.value?.imgSrcUrl)
            }
        }

        viewModel.intWord.observe(viewLifecycleOwner){
            Log.d("NewWords", "intWord = $it")
        }
        viewModel.word.observe(viewLifecycleOwner){
            Log.d("NewWords", "Word = $it")
        }


        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.let { findNavController().navigate(R.id.action_newWordsFragment_to_menuFragment) }
        }
    }

}
