package service;

import entity.Employee;
import entity.Job;
import entity.JobFunction;
import entity.MainSkill;
import service.specification.Specification;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface Service<T> {
    void add(Job job, String name, JobFunction jobFunction, MainSkill mainSkill);
    void delete(Employee employee);
    List<T> find(Specification<T> specification);
    List<T> sort(Comparator comp);
    int getCostOfTeam();
    void setCostOfTeam(int days);
    int size();
    List<Employee> getEmployee();
    void addSome(List<Employee> list);
   // Employee getByIndex(int i);
    void deleteAll();
}
