/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fmall.test;

import com.fmall.dao.FuserDao;
import com.fmall.dao.FuserDaoImpl;
import com.fmall.pojo.Fuser;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NIIT
 */
public class Test {
    public static void main(String []args){
        
        FuserDao dao = new FuserDaoImpl();
        List<Fuser> list = null;
        try {
            list = dao.findAll();
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        list.forEach(u->{
            System.out.println(u.getNickname());
        });
    }
}
