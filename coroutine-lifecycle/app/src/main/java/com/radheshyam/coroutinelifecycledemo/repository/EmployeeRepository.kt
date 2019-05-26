package com.radheshyam.coroutinelifecycledemo.repository

import com.radheshyam.coroutinelifecycledemo.model.Employee
import kotlinx.coroutines.delay

/**
 * Class header need to define
 *
 * author Radheshyam
 * date 26/May/2019
 */
class EmployeeRepository {

    private var empCount = 0

    suspend fun getEmployees(): List<Employee> {
        // heavy work here
        delay(8000)

        val employees: List<Employee> = listOf(
            Employee(empCount++, "name$empCount", "company$empCount"),
            Employee(empCount++, "name$empCount", "company$empCount"),
            Employee(empCount++, "name$empCount", "company$empCount"),
            Employee(empCount++, "name$empCount", "company$empCount"),
            Employee(empCount++, "name$empCount", "company$empCount")
        )

        return employees
    }
}