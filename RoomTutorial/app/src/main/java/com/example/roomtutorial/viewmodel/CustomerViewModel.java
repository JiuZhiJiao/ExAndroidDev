package com.example.roomtutorial.viewmodel;

import android.app.Application;
import android.widget.ListView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.roomtutorial.entity.Customer;
import com.example.roomtutorial.repository.CustomerRepository;

import java.util.List;

public class CustomerViewModel extends ViewModel {

    private CustomerRepository customerRepository;
    private MutableLiveData<List<Customer>> allCustomers;

    public CustomerViewModel() {
        allCustomers = new MutableLiveData<>();
    }

    public void setCustomers(List<Customer> customers) {
        allCustomers.setValue(customers);
    }

    public LiveData<List<Customer>> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    public void initializeVars(Application application) {
        customerRepository = new CustomerRepository(application);
    }

    public void insert(Customer customer) {
        customerRepository.insert(customer);
    }

    public void insertAll(Customer... customers) {
        customerRepository.insertAll(customers);
    }

    public void deleteAll() {
        customerRepository.deleteAll();
    }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    public void update(Customer... customers) {
        customerRepository.updateCustomers(customers);
    }

    public Customer findByID(int id) {
        return customerRepository.findByID(id);
    }

}
