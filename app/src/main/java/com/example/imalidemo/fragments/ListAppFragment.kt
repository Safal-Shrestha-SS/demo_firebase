package com.example.imalidemo.fragments

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.imalidemo.R
import com.example.imalidemo.adapter.ListAppAdapter
import com.example.imalidemo.databinding.FragmentListAppBinding
import com.example.imalidemo.viewmodel.ListAppFragmentViewModel
import com.example.imalidemo.viewmodel.ListAppFragmentViewModelFactory


class ListAppFragment : Fragment() {
    private lateinit var binding: FragmentListAppBinding
    private  lateinit var viewModel: ListAppFragmentViewModel
    private  lateinit var viewModelFactory: ListAppFragmentViewModelFactory
    private lateinit var  adapter: ListAppAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelFactory= ListAppFragmentViewModelFactory(activity?.application!!)
        viewModel = ViewModelProvider(this,viewModelFactory)[ListAppFragmentViewModel::class.java]

//        button= view?.findViewById(R.id.button)!!
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_list_app, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm= viewModel
        viewModel.appList.observe(viewLifecycleOwner){
            adapter = ListAppAdapter(it!!)
            binding.listAppRV.adapter =adapter
        }
    }
    companion object {
        fun newInstance() = ListAppFragment()
    }
}