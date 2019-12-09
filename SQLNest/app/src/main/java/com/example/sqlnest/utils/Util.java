package com.example.sqlnest.utils;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.sqlnest.R;
import com.example.sqlnest.models.Option;
import com.example.sqlnest.models.Question;
import com.example.sqlnest.models.Test;

import java.util.ArrayList;

public class Util {

    private static ArrayList<Test> tests = new ArrayList<>();
    private static ArrayList<Question> dmlQuestions = new ArrayList<>();
    private static ArrayList<Question> ddlQuestions = new ArrayList<>();
    private static ArrayList<Question> afQuestions = new ArrayList<>();
    private static ArrayList<Question> acfQuestions = new ArrayList<>();

    public static void replaceFragment(Fragment fragment, String tag , Boolean addToStack , Boolean clearStack
            , FragmentActivity fa){
        if(clearStack)
            fa.getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction ft = fa.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container,fragment,tag);
        if(addToStack)
            ft.addToBackStack(tag);
        ft.commit();
    }

    public static void setDmlQuestions (){
        ArrayList<Option> questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "SELECT * FROM Table Emp" , false));
        questionOptions.add(new Option(1 , "SELECT * FROM Emp" , false));
        questionOptions.add(new Option(2 , "SELECT * FROM Emp Table" , false));
        questionOptions.add(new Option(3 , "All of above" , false));

        dmlQuestions.add(new Question(
                0 ,"What is the correct form of select all record from Emp table?" ,
                1 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "DROP" , false));
        questionOptions.add(new Option(1 , "LOCK TABLE" , false));
        questionOptions.add(new Option(2 , "MERGE" , false));
        questionOptions.add(new Option(3 , "UPDATE" , false));

        dmlQuestions.add(new Question(
                1 ,"Which of the following is not DML statement?" ,
                0 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "SELECT Rename Salary to Sal FROM emp" , false));
        questionOptions.add(new Option(1 , "SELECT Salary to Sal FROM emp" , false));
        questionOptions.add(new Option(2 , "SELECT Salary AS Sal FROM emp" , false));
        questionOptions.add(new Option(3 , "All the above" , false));

        dmlQuestions.add(new Question(
                2 ,"If we want to select ' Salary '  from emp table , which should be display as Sal, what is the correct statement ?" ,
                2 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "SELECT count(*) FROM emp" , false));
        questionOptions.add(new Option(1 , "SELECT * FROM emp" , false));
        questionOptions.add(new Option(2 , "SELECT count(All) FROM emp" , false));
        questionOptions.add(new Option(3 , "All the above" , false));

        dmlQuestions.add(new Question(
                3 ,"Which one is correct statement to get the number of record in emp?" ,
                0 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "INSERT INTO Emp (Ename,Eno,Age)VALUES('Alex',12,23)" , false));
        questionOptions.add(new Option(1 , "INSERT INTO Emp (Ename,Eno,Age)VALUES(\"Alex\",12,23)" , false));
        questionOptions.add(new Option(2 , "INSERT INTO Emp VALUES('Alex',12,23)" , false));
        questionOptions.add(new Option(3 , "INSERT INTO Emp VALUES(\"Alex\",12,23)" , false));

        dmlQuestions.add(new Question(
                4 ,"Emp table is having three column Ename,Eno and Age. Which are the correct insert statements to insert value?" ,
                0 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "Yes" , false));
        questionOptions.add(new Option(1 , "No" , false));

        dmlQuestions.add(new Question(
                5 ,"Inserted value of a table can be rollback?" ,
                0 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "Yes" , false));
        questionOptions.add(new Option(1 , "No" , false));

        dmlQuestions.add(new Question(
                6 ,"Can we insert multiple values to a table at a time?" ,
                0 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "UPDATE Customers SET CustmerName='Aled' Where empNo=10" , false));
        questionOptions.add(new Option(1 , "UPDATE table Customers SET CustmerName='Aled' Where empNo=10" , false));
        questionOptions.add(new Option(2 , "UPDATE Customers table SET CustmerName='Aled' Where empNo=10" , false));
        questionOptions.add(new Option(3 , "UPDATE Customers SET CustmerName='Aled' as empNo=10" , false));

        dmlQuestions.add(new Question(
                7 ,"Which of the following is correct  update statement?" ,
                0 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "EXPLAIN PLAN" , false));
        questionOptions.add(new Option(1 , "CALL" , false));
        questionOptions.add(new Option(2 , "LOCK TABLE" , false));
        questionOptions.add(new Option(3 , "None of above" , false));

        dmlQuestions.add(new Question(
                8 ,"Which of the following is not  a DML statement?" ,
                3 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "Yes" , false));
        questionOptions.add(new Option(1 , "No" , false));

        dmlQuestions.add(new Question(
                9 ,"Select   *  from Emp, Dept ; Is it possible to select records of both table like this?" ,
                1 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "Yes" , false));
        questionOptions.add(new Option(1 , "No" , false));

        dmlQuestions.add(new Question(
                10 ,"Can we update more than one table value at a single time ?" ,
                0 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "DELETE" , false));
        questionOptions.add(new Option(1 , "CALL" , false));
        questionOptions.add(new Option(2 , "EXPLAIN PLAN" , false));
        questionOptions.add(new Option(3 , "None of above" , false));

        dmlQuestions.add(new Question(
                11 ,"Which DML statements are supported in PL/SQL only when executed dynamically ?" ,
                3 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "DELETE" , false));
        questionOptions.add(new Option(1 , "CALL" , false));
        questionOptions.add(new Option(2 , "EXPLAIN PLAN" , false));
        questionOptions.add(new Option(3 , "None of above" , false));

        dmlQuestions.add(new Question(
                11 ,"Which DML statements are supported in PL/SQL only when executed dynamically ?" ,
                3 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "SELECT sal / 12 FROM Emp" , false));
        questionOptions.add(new Option(1 , "SELECT sal / 12 AS monthly_salary FROM Emp" , false));
        questionOptions.add(new Option(2 , "SELECT (sal / 12) AS monthly_salary FROM Emp" , false));
        questionOptions.add(new Option(3 , "All the Above" , false));

        dmlQuestions.add(new Question(
                12 ,"Which are the correct statement to get monthly salary of employees from Emp table ?" ,
                2 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "SELECT Emp_Name ,DeptNo FROM Emp, Dept" , false));
        questionOptions.add(new Option(1 , "select Empno ,Eeptno from Emp e, Dept d" , false));
        questionOptions.add(new Option(2 , "SELECT (sal / 12) AS monthly_salary FROM Emp" , false));
        questionOptions.add(new Option(3 , "All the Above" , false));

        dmlQuestions.add(new Question(
                13 ,"Which of the following are correct statements to select Emp_Name and DeptNo from Emp and Dept table ?" ,
                2 , 10 , 500 , 0 ,
                questionOptions));





    }

    public static void setDdlQuestions (){
        ArrayList<Option> questionOptions = new ArrayList<>();

        questionOptions.add(new Option(0 , "SELECT * FROM Table Emp" , false));
        questionOptions.add(new Option(1 , "SELECT * FROM Emp" , false));
        questionOptions.add(new Option(2 , "SELECT * FROM Emp Table" , false));
        questionOptions.add(new Option(3 , "All of above" , false));

        ddlQuestions.add(new Question(
                0 ,"What is the correct form of select all record from Emp table?" ,
                1 , 15 , 1000 , 0 ,
                questionOptions));

//        questionOptions = new ArrayList<>();
//        questionOptions.add(new Option(0 , "DROP" , false));
//        questionOptions.add(new Option(1 , "LOCK TABLE" , false));
//        questionOptions.add(new Option(2 , "MERGE" , false));
//        questionOptions.add(new Option(3 , "UPDATE" , false));
//
//        ddlQuestions.add(new Question(
//                1 ,"Which of the following is not DML statement?" ,
//                0 , 10 , 500 , 0 ,
//                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "SELECT Rename Salary to Sal FROM emp" , false));
        questionOptions.add(new Option(1 , "SELECT Salary to Sal FROM emp" , false));
        questionOptions.add(new Option(2 , "SELECT Salary AS Sal FROM emp" , false));
        questionOptions.add(new Option(3 , "All the above" , false));

        ddlQuestions.add(new Question(
                1 ,"If we want to select ' Salary '  from emp table , which should be display as Sal, what is the correct statement ?" ,
                2 , 15 , 1000 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "SELECT count(*) FROM emp" , false));
        questionOptions.add(new Option(1 , "SELECT * FROM emp" , false));
        questionOptions.add(new Option(2 , "SELECT count(All) FROM emp" , false));
        questionOptions.add(new Option(3 , "All the above" , false));

        ddlQuestions.add(new Question(
                2 ,"Which one is correct statement to get the number of record in emp?" ,
                0 , 15 , 1000 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "INSERT INTO Emp (Ename,Eno,Age)VALUES(\"Alex\",12,23)" , false));
        questionOptions.add(new Option(1 , "INSERT INTO Emp VALUES('Alex',12,23)" , false));
        questionOptions.add(new Option(2 , "INSERT INTO Emp (Ename,Eno,Age)VALUES('Alex',12,23)" , false));
        questionOptions.add(new Option(3 , "INSERT INTO Emp VALUES(\"Alex\",12,23)" , false));

        ddlQuestions.add(new Question(
                3 ,"Emp table is having three column Ename,Eno and Age. Which are the correct insert statements to insert value?" ,
                2 , 15 , 1000 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "Yes" , false));
        questionOptions.add(new Option(1 , "No" , false));

        ddlQuestions.add(new Question(
                4 ,"Inserted value of a table can be rollback?" ,
                0 , 5 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "Yes" , false));
        questionOptions.add(new Option(1 , "No" , false));

        ddlQuestions.add(new Question(
                5 ,"Can we insert multiple values to a table at a time?" ,
                0 , 5 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "UPDATE table Customers SET CustmerName='Aled' Where empNo=10" , false));
        questionOptions.add(new Option(1 , "UPDATE Customers table SET CustmerName='Aled' Where empNo=10" , false));
        questionOptions.add(new Option(2 , "UPDATE Customers SET CustmerName='Aled' as empNo=10" , false));
        questionOptions.add(new Option(3 , "UPDATE Customers SET CustmerName='Aled' Where empNo=10" , false));


        ddlQuestions.add(new Question(
                6 ,"Which of the following is correct  update statement?" ,
                3 , 15 , 1000 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "EXPLAIN PLAN" , false));
        questionOptions.add(new Option(1 , "CALL" , false));
        questionOptions.add(new Option(2 , "LOCK TABLE" , false));
        questionOptions.add(new Option(3 , "None of above" , false));

        ddlQuestions.add(new Question(
                7 ,"Which of the following is not  a DML statement?" ,
                3 , 10 , 800 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "Yes" , false));
        questionOptions.add(new Option(1 , "No" , false));

        ddlQuestions.add(new Question(
                8 ,"Select   *  from Emp, Dept ; Is it possible to select records of both table like this?" ,
                1 , 10 , 800 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "Yes" , false));
        questionOptions.add(new Option(1 , "No" , false));

        ddlQuestions.add(new Question(
                9 ,"Can we update more than one table value at a single time ?" ,
                0 , 5 , 500 , 0 ,
                questionOptions));

//        questionOptions = new ArrayList<>();
//        questionOptions.add(new Option(0 , "DELETE" , false));
//        questionOptions.add(new Option(1 , "CALL" , false));
//        questionOptions.add(new Option(2 , "EXPLAIN PLAN" , false));
//        questionOptions.add(new Option(3 , "None of above" , false));
//
//        ddlQuestions.add(new Question(
//                11 ,"Which DML statements are supported in PL/SQL only when executed dynamically ?" ,
//                3 , 10 , 500 , 0 ,
//                questionOptions));
//
//        questionOptions = new ArrayList<>();
//        questionOptions.add(new Option(0 , "DELETE" , false));
//        questionOptions.add(new Option(1 , "CALL" , false));
//        questionOptions.add(new Option(2 , "EXPLAIN PLAN" , false));
//        questionOptions.add(new Option(3 , "None of above" , false));
//
//        ddlQuestions.add(new Question(
//                11 ,"Which DML statements are supported in PL/SQL only when executed dynamically ?" ,
//                3 , 10 , 500 , 0 ,
//                questionOptions));
//
//        questionOptions = new ArrayList<>();
//        questionOptions.add(new Option(0 , "SELECT sal / 12 FROM Emp" , false));
//        questionOptions.add(new Option(1 , "SELECT sal / 12 AS monthly_salary FROM Emp" , false));
//        questionOptions.add(new Option(2 , "SELECT (sal / 12) AS monthly_salary FROM Emp" , false));
//        questionOptions.add(new Option(3 , "All the Above" , false));
//
//        ddlQuestions.add(new Question(
//                12 ,"Which are the correct statement to get monthly salary of employees from Emp table ?" ,
//                2 , 10 , 500 , 0 ,
//                questionOptions));
//
//        questionOptions = new ArrayList<>();
//        questionOptions.add(new Option(0 , "SELECT Emp_Name ,DeptNo FROM Emp, Dept" , false));
//        questionOptions.add(new Option(1 , "select Empno ,Eeptno from Emp e, Dept d" , false));
//        questionOptions.add(new Option(2 , "SELECT (sal / 12) AS monthly_salary FROM Emp" , false));
//        questionOptions.add(new Option(3 , "All the Above" , false));
//
//        ddlQuestions.add(new Question(
//                13 ,"Which of the following are correct statements to select Emp_Name and DeptNo from Emp and Dept table ?" ,
//                2 , 10 , 500 , 0 ,
//                questionOptions));
//
//
//


    }

    public static void setAfQuestions (){
        ArrayList<Option> questionOptions = new ArrayList<>();

        questionOptions.add(new Option(0 , "SELECT * FROM Table Emp" , false));
        questionOptions.add(new Option(1 , "SELECT * FROM Emp" , false));
        questionOptions.add(new Option(2 , "SELECT * FROM Emp Table" , false));
        questionOptions.add(new Option(3 , "All of above" , false));

        afQuestions.add(new Question(
                0 ,"What is the correct form of select all record from Emp table?" ,
                1 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "DROP" , false));
        questionOptions.add(new Option(1 , "LOCK TABLE" , false));
        questionOptions.add(new Option(2 , "MERGE" , false));
        questionOptions.add(new Option(3 , "UPDATE" , false));

        afQuestions.add(new Question(
                1 ,"Which of the following is not DML statement?" ,
                0 , 1000 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "SELECT Rename Salary to Sal FROM emp" , false));
        questionOptions.add(new Option(1 , "SELECT Salary to Sal FROM emp" , false));
        questionOptions.add(new Option(2 , "SELECT Salary AS Sal FROM emp" , false));
        questionOptions.add(new Option(3 , "All the above" , false));

        afQuestions.add(new Question(
                2 ,"If we want to select ' Salary '  from emp table , which should be display as Sal, what is the correct statement ?" ,
                2 , 1000 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "SELECT count(*) FROM emp" , false));
        questionOptions.add(new Option(1 , "SELECT * FROM emp" , false));
        questionOptions.add(new Option(2 , "SELECT count(All) FROM emp" , false));
        questionOptions.add(new Option(3 , "All the above" , false));

        afQuestions.add(new Question(
                3 ,"Which one is correct statement to get the number of record in emp?" ,
                0 , 1000 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "INSERT INTO Emp (Ename,Eno,Age)VALUES('Alex',12,23)" , false));
        questionOptions.add(new Option(1 , "INSERT INTO Emp (Ename,Eno,Age)VALUES(\"Alex\",12,23)" , false));
        questionOptions.add(new Option(2 , "INSERT INTO Emp VALUES('Alex',12,23)" , false));
        questionOptions.add(new Option(3 , "INSERT INTO Emp VALUES(\"Alex\",12,23)" , false));

        afQuestions.add(new Question(
                4 ,"Emp table is having three column Ename,Eno and Age. Which are the correct insert statements to insert value?" ,
                0 , 1000 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "Yes" , false));
        questionOptions.add(new Option(1 , "No" , false));

        afQuestions.add(new Question(
                5 ,"Inserted value of a table can be rollback?" ,
                0 , 1000 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "Yes" , false));
        questionOptions.add(new Option(1 , "No" , false));

        afQuestions.add(new Question(
                6 ,"Can we insert multiple values to a table at a time?" ,
                0 , 1000 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "UPDATE Customers SET CustmerName='Aled' Where empNo=10" , false));
        questionOptions.add(new Option(1 , "UPDATE table Customers SET CustmerName='Aled' Where empNo=10" , false));
        questionOptions.add(new Option(2 , "UPDATE Customers table SET CustmerName='Aled' Where empNo=10" , false));
        questionOptions.add(new Option(3 , "UPDATE Customers SET CustmerName='Aled' as empNo=10" , false));

        afQuestions.add(new Question(
                7 ,"Which of the following is correct  update statement?" ,
                0 , 1000 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "EXPLAIN PLAN" , false));
        questionOptions.add(new Option(1 , "CALL" , false));
        questionOptions.add(new Option(2 , "LOCK TABLE" , false));
        questionOptions.add(new Option(3 , "None of above" , false));

        afQuestions.add(new Question(
                8 ,"Which of the following is not  a DML statement?" ,
                3 , 1000 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "Yes" , false));
        questionOptions.add(new Option(1 , "No" , false));

        afQuestions.add(new Question(
                9 ,"Select   *  from Emp, Dept ; Is it possible to select records of both table like this?" ,
                1 , 1000 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "Yes" , false));
        questionOptions.add(new Option(1 , "No" , false));

        afQuestions.add(new Question(
                10 ,"Can we update more than one table value at a single time ?" ,
                0 , 1000 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "DELETE" , false));
        questionOptions.add(new Option(1 , "CALL" , false));
        questionOptions.add(new Option(2 , "EXPLAIN PLAN" , false));
        questionOptions.add(new Option(3 , "None of above" , false));

        afQuestions.add(new Question(
                11 ,"Which DML statements are supported in PL/SQL only when executed dynamically ?" ,
                3 , 1000 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "DELETE" , false));
        questionOptions.add(new Option(1 , "CALL" , false));
        questionOptions.add(new Option(2 , "EXPLAIN PLAN" , false));
        questionOptions.add(new Option(3 , "None of above" , false));

        afQuestions.add(new Question(
                11 ,"Which DML statements are supported in PL/SQL only when executed dynamically ?" ,
                3 , 1000 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "SELECT sal / 12 FROM Emp" , false));
        questionOptions.add(new Option(1 , "SELECT sal / 12 AS monthly_salary FROM Emp" , false));
        questionOptions.add(new Option(2 , "SELECT (sal / 12) AS monthly_salary FROM Emp" , false));
        questionOptions.add(new Option(3 , "All the Above" , false));

        afQuestions.add(new Question(
                12 ,"Which are the correct statement to get monthly salary of employees from Emp table ?" ,
                2 , 1000 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "SELECT Emp_Name ,DeptNo FROM Emp, Dept" , false));
        questionOptions.add(new Option(1 , "select Empno ,Eeptno from Emp e, Dept d" , false));
        questionOptions.add(new Option(2 , "SELECT (sal / 12) AS monthly_salary FROM Emp" , false));
        questionOptions.add(new Option(3 , "All the Above" , false));

        afQuestions.add(new Question(
                13 ,"Which of the following are correct statements to select Emp_Name and DeptNo from Emp and Dept table ?" ,
                2 , 1000 , 500 , 0 ,
                questionOptions));





    }

    public static void setAcfQuestions (){
        ArrayList<Option> questionOptions = new ArrayList<>();

        questionOptions.add(new Option(0 , "SELECT * FROM Table Emp" , false));
        questionOptions.add(new Option(1 , "SELECT * FROM Emp" , false));
        questionOptions.add(new Option(2 , "SELECT * FROM Emp Table" , false));
        questionOptions.add(new Option(3 , "All of above" , false));

        acfQuestions.add(new Question(
                0 ,"What is the correct form of select all record from Emp table?" ,
                1 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "DROP" , false));
        questionOptions.add(new Option(1 , "LOCK TABLE" , false));
        questionOptions.add(new Option(2 , "MERGE" , false));
        questionOptions.add(new Option(3 , "UPDATE" , false));

        acfQuestions.add(new Question(
                1 ,"Which of the following is not DML statement?" ,
                0 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "SELECT Rename Salary to Sal FROM emp" , false));
        questionOptions.add(new Option(1 , "SELECT Salary to Sal FROM emp" , false));
        questionOptions.add(new Option(2 , "SELECT Salary AS Sal FROM emp" , false));
        questionOptions.add(new Option(3 , "All the above" , false));

        acfQuestions.add(new Question(
                2 ,"If we want to select ' Salary '  from emp table , which should be display as Sal, what is the correct statement ?" ,
                2 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "SELECT count(*) FROM emp" , false));
        questionOptions.add(new Option(1 , "SELECT * FROM emp" , false));
        questionOptions.add(new Option(2 , "SELECT count(All) FROM emp" , false));
        questionOptions.add(new Option(3 , "All the above" , false));

        acfQuestions.add(new Question(
                3 ,"Which one is correct statement to get the number of record in emp?" ,
                0 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "INSERT INTO Emp (Ename,Eno,Age)VALUES('Alex',12,23)" , false));
        questionOptions.add(new Option(1 , "INSERT INTO Emp (Ename,Eno,Age)VALUES(\"Alex\",12,23)" , false));
        questionOptions.add(new Option(2 , "INSERT INTO Emp VALUES('Alex',12,23)" , false));
        questionOptions.add(new Option(3 , "INSERT INTO Emp VALUES(\"Alex\",12,23)" , false));

        acfQuestions.add(new Question(
                4 ,"Emp table is having three column Ename,Eno and Age. Which are the correct insert statements to insert value?" ,
                0 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "Yes" , false));
        questionOptions.add(new Option(1 , "No" , false));

        acfQuestions.add(new Question(
                5 ,"Inserted value of a table can be rollback?" ,
                0 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "Yes" , false));
        questionOptions.add(new Option(1 , "No" , false));

        acfQuestions.add(new Question(
                6 ,"Can we insert multiple values to a table at a time?" ,
                0 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "UPDATE Customers SET CustmerName='Aled' Where empNo=10" , false));
        questionOptions.add(new Option(1 , "UPDATE table Customers SET CustmerName='Aled' Where empNo=10" , false));
        questionOptions.add(new Option(2 , "UPDATE Customers table SET CustmerName='Aled' Where empNo=10" , false));
        questionOptions.add(new Option(3 , "UPDATE Customers SET CustmerName='Aled' as empNo=10" , false));

        acfQuestions.add(new Question(
                7 ,"Which of the following is correct  update statement?" ,
                0 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "EXPLAIN PLAN" , false));
        questionOptions.add(new Option(1 , "CALL" , false));
        questionOptions.add(new Option(2 , "LOCK TABLE" , false));
        questionOptions.add(new Option(3 , "None of above" , false));

        acfQuestions.add(new Question(
                8 ,"Which of the following is not  a DML statement?" ,
                3 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "Yes" , false));
        questionOptions.add(new Option(1 , "No" , false));

        acfQuestions.add(new Question(
                9 ,"Select   *  from Emp, Dept ; Is it possible to select records of both table like this?" ,
                1 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "Yes" , false));
        questionOptions.add(new Option(1 , "No" , false));

        acfQuestions.add(new Question(
                10 ,"Can we update more than one table value at a single time ?" ,
                0 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "DELETE" , false));
        questionOptions.add(new Option(1 , "CALL" , false));
        questionOptions.add(new Option(2 , "EXPLAIN PLAN" , false));
        questionOptions.add(new Option(3 , "None of above" , false));

        acfQuestions.add(new Question(
                11 ,"Which DML statements are supported in PL/SQL only when executed dynamically ?" ,
                3 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "DELETE" , false));
        questionOptions.add(new Option(1 , "CALL" , false));
        questionOptions.add(new Option(2 , "EXPLAIN PLAN" , false));
        questionOptions.add(new Option(3 , "None of above" , false));

        acfQuestions.add(new Question(
                11 ,"Which DML statements are supported in PL/SQL only when executed dynamically ?" ,
                3 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "SELECT sal / 12 FROM Emp" , false));
        questionOptions.add(new Option(1 , "SELECT sal / 12 AS monthly_salary FROM Emp" , false));
        questionOptions.add(new Option(2 , "SELECT (sal / 12) AS monthly_salary FROM Emp" , false));
        questionOptions.add(new Option(3 , "All the Above" , false));

        acfQuestions.add(new Question(
                12 ,"Which are the correct statement to get monthly salary of employees from Emp table ?" ,
                2 , 10 , 500 , 0 ,
                questionOptions));

        questionOptions = new ArrayList<>();
        questionOptions.add(new Option(0 , "SELECT Emp_Name ,DeptNo FROM Emp, Dept" , false));
        questionOptions.add(new Option(1 , "select Empno ,Eeptno from Emp e, Dept d" , false));
        questionOptions.add(new Option(2 , "SELECT (sal / 12) AS monthly_salary FROM Emp" , false));
        questionOptions.add(new Option(3 , "All the Above" , false));

        acfQuestions.add(new Question(
                13 ,"Which of the following are correct statements to select Emp_Name and DeptNo from Emp and Dept table ?" ,
                2 , 10 , 500 , 0 ,
                questionOptions));





    }

    public static void setTests(){
        if(tests.size() == 0){
            setDmlQuestions();
            setDdlQuestions();
            setAfQuestions();
            setAcfQuestions();

            tests.add(new Test(0 ,0 , 0 , "ddl" , 0 ,
                    ddlQuestions));
            tests.add(new Test(0 ,0 , 0 , "dml" , 0 ,
                    dmlQuestions));
            tests.add(new Test(0 ,0 , 0 ,  "af" , 0 ,
                    afQuestions));
            tests.add(new Test(0 ,0 , 0 , "acf" , 0 ,
                    acfQuestions));

        }
    }

    public static ArrayList<Test> getTests() {
        return tests;
    }
}
