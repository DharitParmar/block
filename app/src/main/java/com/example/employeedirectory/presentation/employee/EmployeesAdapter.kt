package com.example.employeedirectory.presentation.employee

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.employeedirectory.common.extensions.loadUrl
import com.example.employeedirectory.core.domain.employee.Employee
import com.example.employeedirectory.databinding.EmployeeListItemBinding

class EmployeesAdapter(
    private val viewModel: EmployeeViewModel
): ListAdapter<Employee, EmployeesAdapter.EmployeeItemViewHolder>(EmployeeDiffCallBack()) {

    internal lateinit var employeesList: List<Employee>

    internal var clickListener: (Employee) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeItemViewHolder {
        return EmployeeItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: EmployeeItemViewHolder, position: Int) {
        holder.bind(getItem(position), viewModel, clickListener)
    }

    class EmployeeItemViewHolder(
        val binding: EmployeeListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(parent: ViewGroup): EmployeeItemViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = EmployeeListItemBinding.inflate(inflater, parent, false)
                return EmployeeItemViewHolder(binding)
            }
        }

        fun bind(employee: Employee, viewModel: EmployeeViewModel, clickListener: (Employee) -> Unit) {
            binding.viewmodel = viewModel
            binding.item = employee
            binding.employeeImage.loadUrl(employee.photoUrlSmall)
            binding.root.setOnClickListener {
                clickListener(employee)
            }
            binding.executePendingBindings()
        }

    }
}

class EmployeeDiffCallBack: DiffUtil.ItemCallback<Employee>() {
    override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem == newItem
    }
}