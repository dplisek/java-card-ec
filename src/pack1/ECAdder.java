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
    
    private ECDifferentAdder differentAdder;
    
    public ECAdder(GFMember a) {
        differentAdder = new ECDifferentAdder(a);
    }
    
    public void add(ProjectivePoint p0, ProjectivePoint p1, ProjectivePoint p2) {
        differentAdder.addDifferent(p0, p1, p2);
    }

}
