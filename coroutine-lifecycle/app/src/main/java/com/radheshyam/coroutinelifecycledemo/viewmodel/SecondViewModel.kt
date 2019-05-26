package com.radheshyam.coroutinelivedatasample.viewmodel

import androidx.lifecycle.MutableLiveData
import com.radheshyam.coroutinelifecycledemo.model.Employee
import com.radheshyam.coroutinelifecycledemo.repository.EmployeeRepository

class SecondViewModel : BaseViewModel() {
    private val repository: EmployeeRepository

    var employee: MutableLiveData<List<Employee>> = MutableLiveData()

    init {
        repository = EmployeeRepository()
    }

    suspend fun loadEmployee() = repository.getEmployees()
}
