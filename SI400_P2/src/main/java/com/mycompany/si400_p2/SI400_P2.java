/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.si400_p2;

import model.DAO;

/**
 *
 * @author Gritti
 */
public class SI400_P2 {

    public static void main(String[] args) {
        DAO dao = new DAO(); 
        System.out.println(dao.getConnection());
    }
}