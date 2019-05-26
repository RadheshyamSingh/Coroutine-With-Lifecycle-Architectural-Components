package com.radheshyam.coroutinelifecycledemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.radheshyam.coroutinelifecycledemo.model.Employee
import com.radheshyam.coroutinelifecycledemo.repository.EmployeeRepository
import com.radheshyam.coroutinelivedatasample.viewmodel.BaseViewModel
import kotlinx.coroutines.*

/**
 * Class header need to define
 *
 * author Radheshyam
 * date 21/May/2019
 */
class MainActivityViewModel : BaseViewModel() {

    private val repository: EmployeeRepository

    var employee: MutableLiveData<List<Employee>> = MutableLiveData()

    init {
        repository = EmployeeRepository()
    }

    fun loadEmployee() {
        viewModelScope.launch {
            var emps: List<Employee>? = null
            withContext(Dispatchers.IO) {
                emps = repository.getEmployees()
            }
            // here Some other operations like file read etc

            employee.postValue(emps)
            println("viewModelScope In main scope")
        }
    }
}