package com.bookstore.bookstore.service;

import com.bookstore.bookstore.entity.MyBookList;
import com.bookstore.bookstore.repository.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookListService {

    @Autowired
    private MyBookRepository myBookRepository;

    public void saveMyBook(MyBookList mybook){
        myBookRepository.save(mybook);
    }

    public List<MyBookList> getAllMyBooks(){
        return myBookRepository.findAll();
    }

    public  void deleteById(int id){
        myBookRepository.deleteById(id);
    }
}
