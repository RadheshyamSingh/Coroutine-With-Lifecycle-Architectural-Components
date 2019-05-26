package com.radheshyam.coroutinelivedatademo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.radheshyam.coroutinelivedatademo.model.Employee
import com.radheshyam.coroutinelivedatademo.repository.EmployeeRepository
import kotlinx.coroutines.*


/**
 * Class header need to define
 *
 * author Radheshyam
 * date 21/May/2019
 */
class MainActivityViewModel : BaseViewModel() {

    private var repository= EmployeeRepository()

    var employee: MutableLiveData<List<Employee>> = MutableLiveData()

    // from livedata-2.2.0-alpha01
    var employee2 = liveData(Dispatchers.IO) {
        val emps = repository.getEmployees()
        emit(emps)
    }

    // before livedata-2.2.0-alpha01 i.e. livedata-2.1.0-alpha01
    fun loadEmployee() {
        viewModelScope.launch {
            var emps: List<Employee>? = null
            withContext(Dispatchers.IO) {
                emps = repository.getEmployees()
            }

            employee.value = emps
        }
    }

}