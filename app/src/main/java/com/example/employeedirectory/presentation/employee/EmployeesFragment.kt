package com.example.employeedirectory.presentation.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.employeedirectory.databinding.FragmentEmployeeBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class EmployeesFragment: Fragment() {

    private lateinit var employeesAdapter: EmployeesAdapter
    private lateinit var dataBinding: FragmentEmployeeBinding
    private val viewModel by viewModels<EmployeeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentEmployeeBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.lifecycleOwner = viewLifecycleOwner

        setupAdapter()
        observeEmployees()
        observeEmployeesFailure()
    }

    private fun observeEmployeesFailure() {
        viewModel.employees.observe(viewLifecycleOwner, Observer {
            //employeesAdapter.employeesList = it
        })
    }

    private fun observeEmployees() {
    }

    private fun setupAdapter() {
        dataBinding.viewmodel?.also {
            employeesAdapter = EmployeesAdapter(it)
            dataBinding.itemList.adapter = employeesAdapter
            employeesAdapter.clickListener = { employee ->
                // open details
            }
        } ?: Timber.d("view model null")
    }
}