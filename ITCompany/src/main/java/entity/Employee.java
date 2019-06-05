package entity;

import java.util.Objects;
import java.util.Optional;

public class Employee {
    private MainSkill mainSkill;
    private String name;
    private JobFunction jobFunction;
    private Job job;
    private int salary;

    Employee(){}

    public Employee(MainSkill mainSkill, String name, JobFunction jobFunction) {
        setMainSkill(mainSkill);
        setName(name);
        setJobFunction(jobFunction);
        //setJob(job);
        setSalary();
    }

    public int getSalary() {
        return salary;
    }

    private void setSalary(){
        this.salary = jobFunction.getSalary(jobFunction);
    }

    public MainSkill getMainSkill() {
        return mainSkill;
    }

    public void setMainSkill(MainSkill mainSkill) {
        this.mainSkill = mainSkill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JobFunction getJobFunction() {
        return jobFunction;
    }

    public void setJobFunction(JobFunction jobFunction) {
        this.jobFunction = jobFunction;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return salary == employee.salary &&
                mainSkill == employee.mainSkill &&
                Objects.equals(name, employee.name) &&
                jobFunction == employee.jobFunction &&
                job == employee.job;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainSkill, name, jobFunction, job, salary);
    }

    @Override
    public String toString() {
        return mainSkill +
                " " + name +
                " " + jobFunction +
                " " + job +
                " " + salary;
    }
}
