package com.example.projectwork.dictionary

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController

import com.example.projectwork.R
import kotlinx.coroutines.withContext

class DictionaryFragment : Fragment() {

    companion object {
        fun newInstance() = DictionaryFragment()
    }

    private lateinit var viewModel: DictionaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dictionary_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DictionaryViewModel::class.java)

        //val linDict = LinearLayout(this!)


        requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.let { findNavController().navigate(R.id.action_dictionaryFragment_to_oldWordsMenuFragment) }
        }
    }

}
