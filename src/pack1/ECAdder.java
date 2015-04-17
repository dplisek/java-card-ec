/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack1;

/**
 *
 * @author Dominik
 */
public class ECAdder {
    
    public static ProjectivePoint add(ProjectivePoint p0, ProjectivePoint p1) {
        return ECDifferentAdder.addDifferent(p0, p1);
    }

}
