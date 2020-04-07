package com.example.projectwork.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.projectwork.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.menu_fragment.*


class MenuFragment : Fragment() {

    companion object {
        fun newInstance() = MenuFragment()
    }

    private val viewModel: MenuViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        newWords.setOnClickListener { view?.let { findNavController().navigate(R.id.action_menuFragment_to_newWordsFragment) } }
        oldWords.setOnClickListener { view?.let { findNavController().navigate(R.id.action_menuFragment_to_oldWordsFragment) } }
        stats.setOnClickListener { view?.let { findNavController().navigate(R.id.action_menuFragment_to_statsFragment) } }
        settings.setOnClickListener { view?.let { findNavController().navigate(R.id.action_menuFragment_to_settingsFragment) } }
        /*   Photography
        viewModel.userPhoto.observe(this){
            url -> Picasso.get().load(url).into(userPhoto)
        }
        */
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            view?.let { //findNavController().navigate(R.id.action_menuFragment_to_exitScreenFragment)
                System.exit((-1))
            }
        }
    }
}
