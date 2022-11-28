package com.example.employeedirectory.presentation.employee

import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter("app:employees_items")
fun setListItems(view: RecyclerView, responseState: EmployeeViewState?) {
    responseState?.let {
        if (responseState is EmployeeViewState.EmployeeList) {
            responseState.list.also {
                (view.adapter as EmployeesAdapter).submitList(it)
            }
        }
    }

}

@BindingAdapter("app:hideOnLoading")
fun ViewGroup.hideOnLoading(responseState: EmployeeViewState?) {
    responseState?.let {
        visibility = if (responseState is EmployeeViewState.Loading)
            View.GONE
        else
            View.VISIBLE
    }
}

@BindingAdapter("app:showOnLoading")
fun ProgressBar.showOnLoading(responseState: EmployeeViewState?) {
    responseState?.let {
        visibility = if (responseState is EmployeeViewState.Loading)
            View.VISIBLE
        else
            View.GONE
    }
}

@BindingAdapter("app:showOnError")
fun TextView.showError(responseState: EmployeeViewState?) {
    responseState?.let {
        visibility = if (responseState is EmployeeViewState.EmployeeError) {
            text = this.context.getString(responseState.msgID)
            View.VISIBLE
        }
        else
            View.GONE

    }
}